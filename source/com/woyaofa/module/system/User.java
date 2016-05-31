
package com.woyaofa.module.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
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

import com.woyaofa.data.system.UserInfo;
import com.woyaofa.exchange.web.Result;

@IocBean
@At("/system/user")
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
public class User {

	@Inject
	Dao dao = null;

	@At("/login")
	@Filters
	public Result login (@Param("username") String username, @Param("password") String password, HttpSession session) {

		session.removeAttribute("user");

		UserInfo user = dao.fetch(UserInfo.class, Cnd.where("username", "=", username).and("password", "=", password));

		if (user == null) {
			return Result.newErrorResult("fail");
		} else if (user.isEnable() == false) {
			return Result.newErrorResult("forbidden");
		} else {
			session.setAttribute("user", user.getId());
			return Result.newSuccessResult();
		}
	}

	@At("/list")
	public Result list () {

		List<UserInfo> users = dao.query(UserInfo.class, null);
		return Result.newObjectResult(users, 0, users.size(), 1);
	}

	@At("create")
	public Result create (@Param("username") String username, @Param("password") String password) {

		if (Strings.isBlank(username) || Strings.isBlank(password)) {
			return Result.newErrorResult("empty username or password");
		}

		UserInfo user = new UserInfo();
		user.setUsername(username);
		user.setPassword(password);
		user.setEnable(true);

		user = dao.insert(user);

		if (user == null) {
			return Result.newErrorResult("create fail");
		} else {
			return Result.newObjectResult(user);
		}
	}

	@At("delete")
	public Result delete (@Param("id") int id, HttpSession session) {

		int myid = (int) session.getAttribute("user");

		if (id == myid) {
			return Result.newErrorResult("fail");
		}

		int count = dao.delete(User.class, id);

		if (count == 0) {
			return Result.newErrorResult("not exist");
		} else {
			return Result.newSuccessResult();
		}
	}
}
