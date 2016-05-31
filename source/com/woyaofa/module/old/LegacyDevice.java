
package com.woyaofa.module.old;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.woyaofa.GlobalConstants;
import com.woyaofa.data.advert.AdvertInfo;
import com.woyaofa.data.advert.PublishInfo;
import com.woyaofa.data.advert.RuleInfo;
import com.woyaofa.data.old.OldDeviceInfo;
import com.woyaofa.data.old.OldTaskInfo;
import com.woyaofa.data.system.ChannelInfo;
import com.woyaofa.exchange.web.Result;
import com.woyaofa.util.Area;
import com.woyaofa.util.XskData;

@IocBean
@Fail("http:500")
@Filters
public class LegacyDevice {

	@Inject
	Dao dao = null;

	@At("/youyiadv_test/platform/service/request-adv")
	@Ok("raw")
	public String refreshData (@Param("json") String s, HttpServletRequest request) {

		if (Strings.isBlank(s)) {
			return null;
		}

		s = XskData.decode(s);

		if (Strings.isBlank(s)) {
			return null;
		}

		RequestInfo o = Json.fromJson(RequestInfo.class, s);

		if (o == null || o.phone == null) {
			return null;
		}

		ClientInfo phone = o.phone;
		phone.phone_ip = request.getRemoteAddr();
		OldDeviceInfo device = dao.fetch(OldDeviceInfo.class, Cnd.where("imei", "=", phone.phone_imei));

		if (device == null) {
			device = this.registerNewDevice(phone);
		} else {
			this.updateActive(device, phone);
		}

		OldResult result = this.fetchOneAdvert(device);
		String rs = Json.toJson(result);
		rs = XskData.encode(rs);

		return rs;
	}

	@At("/youyiadv_test/platform/file/get-ip")
	@Ok("raw")
	public String getIp (HttpServletRequest request) {

		String ip = request.getRemoteAddr();
		return ip;
	}

	@At("/youyiadv_test/platform/service/list")
	@Ok("json")
	@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
	public Result list (@Param("channelName") String channelName, @Param("activeTime") Date activeTime, @Param("activeEndTime") Date activeEndTime,
			@Param("pageindex") int pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		Cnd cnd = Strings.isBlank(channelName) ? null : Cnd.where("channelName", "=", channelName);

		if (activeTime != null) {
			if (cnd == null) {
				cnd = Cnd.where("activeTime", ">=", activeTime);
			} else {
				cnd = cnd.and("activeTime", ">=", activeTime);
			}
		}

		if (activeEndTime != null) {
			if (cnd == null) {
				cnd = Cnd.where("activeTime", "<=", activeEndTime);
			} else {
				cnd = cnd.and("activeTime", "<=", activeEndTime);
			}
		}

		Pager pager = dao.createPager(pageIndex, GlobalConstants.DEFAULT_PAGE_SIZE);
		int count = dao.count(OldDeviceInfo.class, cnd);
		List<OldDeviceInfo> items = dao.query(OldDeviceInfo.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@At("/youyiadv_test/platform/service/task-list")
	@Ok("json")
	@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
	public Result taskList (@Param("id") int id, @Param("pageindex") int pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		Cnd cnd = Cnd.where("deviceId", "=", id);
		Pager pager = dao.createPager(pageIndex, GlobalConstants.DEFAULT_PAGE_SIZE);
		int count = dao.count(OldTaskInfo.class, cnd);
		List<OldTaskInfo> items = dao.query(OldTaskInfo.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	protected void updateActive (OldDeviceInfo device, ClientInfo phone) {

		device.setLastIP(phone.phone_ip);
		device.setLastActiveTime(new Date());

		if (device.getActiveTime() == null) {
			device.setActiveTime(device.getLastActiveTime());
		}

		dao.update(device);
	}

	protected OldDeviceInfo registerNewDevice (ClientInfo phone) {

		OldDeviceInfo device = new OldDeviceInfo();

		device.setBrand(phone.brand);
		device.setModel(phone.model);
		device.setOs(phone.phone_os);
		device.setVersion(phone.os_version);
		device.setMAC(phone.phone_mac);
		device.setReleaseKey(phone.release_key);
		device.setSecretKey(phone.secret_key);
		device.setHasSIM(phone.to_sim0 > 0);
		device.setImei(phone.phone_imei);
		device.setImsi(phone.phone_imsi);
		device.setISP(phone.sim_provider);
		device.setNetwork(phone.net_id);
		device.setLastIP(phone.phone_ip);
		device.setLastArea(Area.getAreaFromIP(phone.phone_ip));
		device.setLastActiveTime(new Date());
		device.setSystem(phone.is_system > 0);

		ChannelInfo channel = dao.fetch(ChannelInfo.class, Cnd.where("releaseKey", "=", phone.release_key));

		if (channel != null) {
			device.setChannelId(channel.getId());
		}

		device = dao.insert(device);
		return device;
	}

	protected OldResult fetchOneAdvert (OldDeviceInfo device) {

		Sql sql = Sqls.create("call proc_old_fetch_one_advert(" + device.getId() + ")");
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
		OldResult result = new OldResult();

		if (taskId > 0) {
			OldTaskInfo task = dao.fetch(OldTaskInfo.class, taskId);
			PublishInfo publish = dao.fetch(PublishInfo.class, task.getPublishId());
			AdvertInfo advert = dao.fetch(AdvertInfo.class, task.getAdvertId());
			RuleInfo rule = dao.fetch(RuleInfo.class, publish.getRuleId());

			result.json.adv_id = advert.getId();
			result.json.adv_key = String.valueOf(advert.getId());
			result.json.adv_start_date = rule.getStartDate().toString();
			result.json.adv_end_date = rule.getEndDate().toString();
		}

		return result;
	}
}
