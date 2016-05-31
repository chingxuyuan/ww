
package com.woyaofa.module.system;

import java.util.Date;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
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
import com.woyaofa.data.system.ChannelInfo;
import com.woyaofa.exchange.web.Result;
import com.woyaofa.util.Key;

@IocBean
@At("/system/channel")
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
public class Channel {

	@Inject
	Dao dao = null;

	@At("/list")
	public Result list (@Param("name") String name, @Param("pageindex") int pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		Cnd cnd = Strings.isBlank(name) ? null : Cnd.where("name", "=", name);
		QueryResult qr = new QueryResult();
		qr.setList(dao.query(ChannelInfo.class, cnd));
		int count = dao.count(ChannelInfo.class, cnd);

		Pager pager = new Pager();
		pager.setPageSize(GlobalConstants.DEFAULT_PAGE_SIZE);
		pager.setPageNumber(pageIndex);
		pager.setRecordCount(count);
		qr.setPager(pager);

		return Result.newObjectResult(qr.getList(), pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@At("/select")
	public Result select (@Param("id") int id) {

		ChannelInfo channel = dao.fetch(ChannelInfo.class, id);

		if (channel == null) {
			return Result.newErrorResult("not exist");
		} else {
			return Result.newObjectResult(channel);
		}
	}

	@At("/insert")
	public Result insert (@Param("..") ChannelInfo channel) {

		String key = Key.createKey(32);
		channel.setKey(key);
		channel.setCreateTime(new Date());

		channel = dao.insert(channel);

		return Result.newObjectResult(channel);
	}

	@At("/update")
	public Result update (@Param("..") ChannelInfo param) {

		ChannelInfo channel = dao.fetch(ChannelInfo.class, param.getId());

		if (channel == null) {
			return Result.newErrorResult("not exist");
		}

		channel.setName(param.getName());
		channel.setNickname(param.getNickname());
		channel.setDelayDays(param.getDelayDays());
		channel.setMemo(param.getMemo());
		channel.setEnable(param.isEnable());
		channel.setOldRelleaseKey(param.getOldRelleaseKey());
		channel.setOldSecretKey(param.getOldSecretKey());

		dao.update(channel);

		return Result.newSuccessResult();
	}
}
