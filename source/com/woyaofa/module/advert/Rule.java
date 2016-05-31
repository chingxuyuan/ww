
package com.woyaofa.module.advert;

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
import com.woyaofa.data.advert.RuleInfo;
import com.woyaofa.exchange.web.Result;

@IocBean
@At("/advert/rule")
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
public class Rule {

	@Inject
	Dao dao = null;

	@At("/list")
	public Result list (@Param("name") String name, @Param("pageindex") int pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		Cnd cnd = Strings.isBlank(name) ? null : Cnd.where("name", "=", name);
		QueryResult qr = new QueryResult();
		qr.setList(dao.query(RuleInfo.class, cnd));
		int count = dao.count(RuleInfo.class, cnd);

		Pager pager = new Pager();
		pager.setPageSize(GlobalConstants.DEFAULT_PAGE_SIZE);
		pager.setPageNumber(pageIndex);
		pager.setRecordCount(count);
		qr.setPager(pager);

		return Result.newObjectResult(qr.getList(), pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@At("/select")
	public Result select (@Param("id") int id) {

		RuleInfo rule = dao.fetch(RuleInfo.class, id);

		if (rule == null) {
			return Result.newErrorResult("not exist");
		} else {
			return Result.newObjectResult(rule);
		}
	}

	@At("/insert")
	public Result insert (@Param("..") RuleInfo rule) {



		rule.setCreateTime(new Date());
        try {
            rule = dao.insert(rule);
        }catch (Exception e)
        {

            System.out.println(""+ e.getMessage().toString());
        }
        System.out.println("hahah");


		return Result.newObjectResult(rule);
	}

	@At("/update")
	public Result update (@Param("..") RuleInfo param) {

		RuleInfo rule = dao.fetch(RuleInfo.class, param.getId());

		if (rule == null) {
			return Result.newErrorResult("not exist");
		}

		rule.setName(param.getName());
		rule.setPrice(param.getPrice());
		rule.setDestOS(param.getDestOS());
		rule.setHasSIM(param.isHasSIM());
		rule.setISP(param.getISP());
		rule.setBrand(param.getBrand());
		rule.setCity(param.getCity());
		rule.setNetwork(param.getNetwork());
		rule.setCreateTime(new Date());
		rule.setStartDate(param.getStartDate());
		rule.setEndDate(param.getEndDate());
		rule.setStartTime(param.getStartTime());
		rule.setEndTime(param.getEndTime());
		rule.setEnable(param.isEnable());
		rule.setInterval(param.getInterval());
		rule.setMemo(param.getMemo());

		dao.update(rule);

		return Result.newSuccessResult();
	}
}
