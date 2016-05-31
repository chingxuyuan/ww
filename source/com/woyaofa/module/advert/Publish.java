
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
import com.woyaofa.data.advert.PublishInfo;
import com.woyaofa.exchange.web.Result;

@IocBean
@At("/advert/publish")
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
public class Publish {

	@Inject
	Dao dao = null;

	@At("/list")
	public Result list (@Param("advertName") String advertName, @Param("ruleName") String ruleName, @Param("channelName") String channelName, @Param("pageindex") int pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		Cnd cnd = null;

		if (Strings.isBlank(advertName) == false) {
			cnd = Cnd.where("advertName", "=", advertName);
		}

		if (Strings.isBlank(ruleName) == false) {
			if (cnd == null) {
				cnd = Cnd.where("ruleName", "=", ruleName);
			} else {
				cnd = cnd.and("ruleName", "=", ruleName);
			}
		}

		if (Strings.isBlank(channelName) == false) {
			if (cnd == null) {
				cnd = Cnd.where("channelName", "=", channelName);
			} else {
				cnd = cnd.and("channelName", "=", channelName);
			}
		}

		Pager pager = dao.createPager(pageIndex, GlobalConstants.DEFAULT_PAGE_SIZE);
		int count = dao.count(PublishInfo.class, cnd);
		List<PublishInfo> items = dao.query(PublishInfo.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@At("/select")
	public Result select (@Param("id") int id) {

		PublishInfo publish = dao.fetch(PublishInfo.class, id);

		if (publish == null) {
			return Result.newErrorResult("not exist");
		} else {
			return Result.newObjectResult(publish);
		}
	}

	@At("/insert")
	public Result insert (@Param("..") PublishInfo publish) {

		publish.setCreateTime(new Date());
		publish = dao.insert(publish);
		return Result.newObjectResult(publish);
	}

	@At("/update")
	public Result update (@Param("..") PublishInfo param) {

		PublishInfo publish = dao.fetch(PublishInfo.class, param.getId());

		if (publish == null) {
			return Result.newErrorResult("not exist");
		}

		publish.setMemo(param.getMemo());
		publish.setEnable(param.isEnable());

		dao.update(publish);

		return Result.newSuccessResult();
	}
}
