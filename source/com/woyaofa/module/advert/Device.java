
package com.woyaofa.module.advert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.woyaofa.data.advert.ActiveInfo;
import com.woyaofa.data.advert.AdvertInfo;
import com.woyaofa.data.advert.PluginInfo;
import com.woyaofa.data.advert.PublishInfo;
import com.woyaofa.data.advert.RuleInfo;
import com.woyaofa.data.advert.TaskInfo;
import com.woyaofa.data.system.ChannelInfo;
import com.woyaofa.data.system.DeviceInfo;
import com.woyaofa.exchange.device.AdvertResult;
import com.woyaofa.exchange.device.AdvertUsage;
import com.woyaofa.exchange.device.DeviceData;
import com.woyaofa.util.Key;
import com.woyaofa.util.XskData;

@IocBean
@At("/advert/device")
@Ok("raw")
@Fail("http:500")
public class Device {

	@Inject
	Dao dao = null;

	@At("/active")
	public String active (@Param("json") String json) {

		json = XskData.decode(json);
		DeviceData dd = Json.fromJson(DeviceData.class, json);

		if (dd == null) {
			return null;
		}

		ChannelInfo channel = dao.fetch(ChannelInfo.class, Cnd.where("key", "=", dd.getChannelKey()));

		if (channel == null) {
			return null;
		}

		PluginInfo plugin = dao.fetch(PluginInfo.class, Cnd.where("key", "=", dd.getPluginKey()));

		if (plugin == null) {
			return null;
		}

		Date now = new Date();
		String key = Key.createKey(48);

		DeviceInfo device = new DeviceInfo();
		device.setKey(key);
		device.setBrand(dd.getBrand());
		device.setModel(dd.getModel());
		device.setIMEI(dd.getIMEI());
		device.setIMSI(dd.getIMSI());
		device.setOs(dd.getOs());
		device.setVersion(dd.getVersion());
		device.setISP(dd.getISP());
		device.setChannelId(channel.getId());
		device.setPluginId(plugin.getId());
		device.setActiveTime(now);

		device = dao.insert(device);

		ActiveReportManager.instance().updateRegister(dao, now, device, channel);

		if (device == null) {
			return null;
		} else {
			key = XskData.encode(key);
			return key;
		}
	}

	@At("/new-advert")
	public String newAdvert (String key, HttpServletRequest request) {

		DeviceInfo device = dao.fetch(DeviceInfo.class, Cnd.where("name", "=", key));

		if (device == null) {
			return null;
		}

		Date now = new Date();
		String ip = request.getRemoteAddr();
		this.recordActive(device, ip, now);

		AdvertResult result = this.publishAdvert(device);

		if (result == null) {
			return null;
		}

		String json = Json.toJson(result);
		json = XskData.encode(json);
		return json;
	}

	@At("/commit-usage")
	public String commitUsage (String json, HttpServletRequest request) {

		AdvertUsage usage = Json.fromJson(AdvertUsage.class, json);

		if (usage == null) {
			return null;
		}

		TaskInfo task = dao.fetch(TaskInfo.class, usage.getTaskId());

		if (task == null) {
			return null;
		}

		PublishInfo publish = dao.fetch(PublishInfo.class, usage.getPublishId());

		if (publish == null) {
			return null;
		}

		DeviceInfo device = dao.fetch(DeviceInfo.class, Cnd.where("key", "=", usage.getKey()));

		if (device == null) {
			return null;
		}

		if (Strings.equals(usage.getKey(), device.getKey()) == false) {
			return null;
		}

		Date now = new Date();
		String ip = request.getRemoteAddr();

		task.setReportTime(now);
		dao.update(task);

		this.recordActive(device, ip, now);
		ActiveReportManager.instance().updateUsage(dao, now, device, publish, usage);

		return "true";
	}

	protected void recordActive (DeviceInfo device, String ip, Date now) {
		
		if(device.isFlagActiveOnDay()){
			return;
		}

		ChannelInfo channel = dao.fetch(ChannelInfo.class, device.getChannelId());

		if (channel == null) {
			return;
		}

		long s = device.getActiveTime().getTime();
		long e = device.getLastActiveTime().getTime();
		long days = (e - s) / (24 * 60 * 60 * 1000);

		if (days >= 1) {
			device.setFlagActive2Day(true);

			if (days >= 6) {
				device.setFlagActive7Day(true);

				if (days >= 29) {
					device.setFlagActive30Day(true);
				}
			}

			dao.update(device);
		}

		ActiveInfo active = new ActiveInfo();

		active.setDeviceId(device.getId());
		active.setPluginId(device.getPluginId());
		active.setActiveTime(now);

		dao.insert(active);

		device.setLastActiveTime(now);
		device.setLastIP(ip);
		dao.update(device);

		ActiveReportManager.instance().updateActive(dao, now, device, channel);
	}

	protected AdvertResult publishAdvert (DeviceInfo device) {

		Sql sql = Sqls.create("call proc_fetch_one_advert(" + device.getId() + ")");
		sql.setCallback(new SqlCallback() {

			public Object invoke (Connection conn, ResultSet rs, Sql sql) throws SQLException {

				if (rs.next()) {
					return rs.getInt(1);
				} else {
					return 0;
				}
			}
		});

		dao.execute(sql);

		int taskId = sql.getInt();

		if (taskId > 0) {
			TaskInfo task = dao.fetch(TaskInfo.class, taskId);
			PublishInfo publish = dao.fetch(PublishInfo.class, task.getPublishId());
			AdvertInfo advert = dao.fetch(AdvertInfo.class, task.getAdvertId());
			RuleInfo rule = dao.fetch(RuleInfo.class, publish.getRuleId());

			AdvertResult result = new AdvertResult();

			result.setTaskId(task.getId());
			result.setPublishId(publish.getId());
			result.setType(advert.getType());
			result.setStartDate(rule.getStartDate());
			result.setEndDate(rule.getEndDate());
			result.setStartTime(rule.getStartTime());
			result.setEndTime(rule.getEndTime());
			result.setInterval(rule.getInterval());
			result.setPackageName(advert.getPackageName());
			result.setActiveScript(advert.getActiveScript());
			result.setReplace(advert.isReplace());
			result.setTargetURL(advert.getTargetURL());
			result.setShowCount(advert.getShowCount());
			result.setShowInterval(advert.getShowInterval());

			ActiveReportManager.instance().updateTask(dao, new Date(), device, task);

			return result;
		}

		return null;
	}
}
