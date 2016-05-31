
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
import com.woyaofa.data.advert.AdvertInfo;
import com.woyaofa.exchange.web.Result;
import com.woyaofa.util.Key;

@IocBean
@At("/advert/advert")
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
public class Advert {

	@Inject
	Dao dao = null;

	@At("/list")
	public Result list (@Param("name") String name, @Param("pageindex") int pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		Cnd cnd = Strings.isBlank(name) ? null : Cnd.where("name", "=", name);
		Pager pager = dao.createPager(pageIndex, GlobalConstants.DEFAULT_PAGE_SIZE);
		int count = dao.count(AdvertInfo.class, cnd);
		List<AdvertInfo> items = dao.query(AdvertInfo.class, cnd, pager);

		return Result.newObjectResult(items, pageIndex, count, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/temp/apk", "8192", "utf-8", "20000", "102400000" })
	@POST
	@Ok("raw")
	@At("/upload-apk")
	public String uploadAvatarAPK (@Param("apk") TempFile tf, @Attr(scope = Scope.SESSION, value = "user") int userId, AdaptorErrorContext err) {

		if (err != null && err.getAdaptorErr() != null) {
			return Json.toJson(Result.newErrorResult("invalid file size"));
		} else if (tf == null) {
			return Json.toJson(Result.newErrorResult("empty file"));
		}

		String key = Key.createKey(32) + ".apk";

		try {
			GlobalVariable gv = GlobalVariable.instance();
			String filename = gv.getServerRoot() + "/" + gv.getApkUploadDir() + key;
			System.out.println("upload apk:" + filename);
			tf.getFile().renameTo(new File(filename));

			return Json.toJson(Result.newSuccessResult(key));
		} catch (Exception e) {
			return Json.toJson(Result.newErrorResult(e.getLocalizedMessage()));
		}
	}

	@At("/new-apk-advert")
	public Result newApkAdvert (@Param("..") AdvertInfo advert) {

		//advert.setType(GlobalConstants.ADVERT_TYPE_INSTALL);
		advert.setCreateTime(new Date());
		advert = dao.insert(advert);
		return Result.newObjectResult(advert);
	}

	@At("/update-apk-advert")
	public Result updateApkAdvert (@Param("..") AdvertInfo advert) {

		//advert.setType(GlobalConstants.ADVERT_TYPE_INSTALL);
		advert.setCreateTime(new Date());
        advert.setPackageName(null);
        advert.setResourceURL(null);
		dao.updateIgnoreNull(advert);
		return Result.newObjectResult(advert);
	}

	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/temp/image", "8192", "utf-8", "20000", "102400000" })
	@POST
	@Ok("raw")
	@At("/upload-img")
	public String uploadAvatarIMG (@Param("img") TempFile tf, @Attr(scope = Scope.SESSION, value = "user") int userId, AdaptorErrorContext err) {

		if (err != null && err.getAdaptorErr() != null) {
			return Json.toJson(Result.newErrorResult("invalid file size"));
		} else if (tf == null) {
			return Json.toJson(Result.newErrorResult("empty file"));
		}

		String key = Key.createKey(32) + ".png";

		try {
			GlobalVariable gv = GlobalVariable.instance();
			String filename = gv.getServerRoot() + "/" + gv.getImageUploadDir() + key;
			System.out.println("upload image:" + filename);
			tf.getFile().renameTo(new File(filename));

			return Json.toJson(Result.newSuccessResult(key));
		} catch (Exception e) {
			return Json.toJson(Result.newErrorResult(e.getLocalizedMessage()));
		}
	}

	@At("/data-by-id")
	public Result avdertInfoById (@Param("id") int id) {

        System.out.println("start");
        AdvertInfo channel = null;
        try {
              channel = dao.fetch(AdvertInfo.class, id);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("end");

		if (channel == null) {
			return Result.newErrorResult("not exist");
		} else {
			return Result.newObjectResult(channel);
		}

	}

	@At("/new-url-advert")
	public Result newURLAdvert (@Param("..") AdvertInfo advert) {

		//advert.setType(GlobalConstants.ADVERT_TYPE_BANNER);
		advert.setCreateTime(new Date());
		advert = dao.insert(advert);
		return Result.newObjectResult(advert);
	}

	@At("/update-url-advert")
	public Result updateURLAdvert (@Param("..") AdvertInfo advert) {

		advert.setType(GlobalConstants.ADVERT_TYPE_BANNER);
		advert.setCreateTime(new Date());
		advert = dao.insert(advert);
		return Result.newObjectResult(advert);
	}
}
