
package com.woyaofa.module.advert;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.Scope;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import com.woyaofa.GlobalConstants;
import com.woyaofa.GlobalVariable;
import com.woyaofa.data.advert.PluginInfo;
import com.woyaofa.exchange.web.Result;
import com.woyaofa.util.Key;

@IocBean
@At("/advert/plugin")
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
public class Plugin {

	@Inject
	Dao dao = null;

	@At("/list")
	public Result list (@Param("name") String name, @Param("pageindex") int pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		Cnd cnd = Strings.isBlank(name) ? null : Cnd.where("name", "=", name);
		Pager pager = dao.createPager(pageIndex, GlobalConstants.DEFAULT_PAGE_SIZE);
		int count = dao.count(PluginInfo.class, cnd);
		List<PluginInfo> items = dao.query(PluginInfo.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/temp/apk", "8192", "utf-8", "20000", "102400000" })
	@POST
	@Ok("raw")
	@At("/upload-apk")
	public String uploadPluginAPK (@Param("apk") TempFile tf, @Attr(scope = Scope.SESSION, value = "user") int userId, AdaptorErrorContext err) {

		if (err != null && err.getAdaptorErr() != null) {
			return Json.toJson(Result.newErrorResult("invalid file size"));
		} else if (tf == null) {
			return Json.toJson(Result.newErrorResult("empty file"));
		}

		String key = Key.createKey(32) + ".apk";

		try {
			GlobalVariable gv = GlobalVariable.instance();
			String filename = gv.getServerRoot() + "/" + gv.getPluginUploadDir() + key;
			System.out.println("upload apk:" + filename);
			tf.getFile().renameTo(new File(filename));

			return Json.toJson(Result.newSuccessResult(key));
		} catch (Exception e) {
			return Json.toJson(Result.newErrorResult(e.getLocalizedMessage()));
		}
	}

	@At("/new-plugin")
	public Result newPlugin (@Param("..") PluginInfo plugin) {

		plugin.setCreateTime(new Date());
		plugin = dao.insert(plugin);
		plugin.setKey(Key.createKey(32));
		return Result.newObjectResult(plugin);
	}
}
