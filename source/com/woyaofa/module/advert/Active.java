
package com.woyaofa.module.advert;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.woyaofa.GlobalConstants;
import com.woyaofa.data.advert.ActiveInfo;
import com.woyaofa.data.advert.ActiveReport;
import com.woyaofa.data.advert.TaskInfo;
import com.woyaofa.data.system.DeviceInfo;
import com.woyaofa.exchange.web.Result;

@IocBean
@At("/advert/active")
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
public class Active {

	@Inject
	Dao dao = null;

	@At("/report")
	public Result report (@Param("channelName") String channelName, @Param("activeTime") Date activeTime, @Param("activeEndTime") Date activeEndTime,
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
		int count = dao.count(ActiveReport.class, cnd);
		List<ActiveReport> items = dao.query(ActiveReport.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@At("/active_detail")
	public Result activeDetail (@Param("channelName") String channelName, @Param("activeTime") Date activeTime, @Param("activeEndTime") Date activeEndTime,
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

		cnd = activeTime == null ? cnd : cnd.and("activeTime", "=", activeTime);
		Pager pager = dao.createPager(pageIndex, GlobalConstants.DEFAULT_PAGE_SIZE);
		int count = dao.count(ActiveInfo.class, cnd);
		List<ActiveInfo> items = dao.query(ActiveInfo.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@At("/device-list")
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
		int count = dao.count(DeviceInfo.class, cnd);
		List<DeviceInfo> items = dao.query(DeviceInfo.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@At("/task-list")
	@Ok("json")
	@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
	public Result taskList (@Param("id") int id, @Param("pageindex") int pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		Cnd cnd = Cnd.where("deviceId", "=", id);
		Pager pager = dao.createPager(pageIndex, GlobalConstants.DEFAULT_PAGE_SIZE);
		int count = dao.count(TaskInfo.class, cnd);
		List<TaskInfo> items = dao.query(TaskInfo.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}
}
