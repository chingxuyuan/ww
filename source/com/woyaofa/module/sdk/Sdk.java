package com.woyaofa.module.sdk;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.google.gson.Gson;
import com.woyaofa.data.advert.AdvertInfo;
import com.woyaofa.exchange.web.Result;

@IocBean
@Fail("http:500")
@Filters
public class Sdk {

	@Inject
	Dao dao = null;

	/**
	 * 随机获取一个广告
	 * 
	 * @return
	 */
	@At("/sdk/randAdvert")
	@Ok("json")
	public String randAdvert(int type) {
		/**
		 * http://www.cnblogs.com/hfww/archive/2011/07/08/2223359.html
		 */
		Sql sql = Sqls.create("SELECT * FROM adv_advert_info where c_type = "+ type);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Integer> lid = new ArrayList<Integer>();
				while (rs.next()){
					int rid = rs.getInt(1);					
					lid.add(rid);
				}
				Random rand = new Random();
				int index = rand.nextInt(lid.size());  
				return lid.get(index);
			}
		});
		dao.execute(sql);
		int rid = sql.getInt();
		AdvertInfo advert = dao.fetch(AdvertInfo.class, rid);
		String json = new Gson().toJson(advert);	
		return json;
	}
	
	@At("/sdk/updateAdvert")
	public Result updateApkAdvert (@Param("..") AdvertInfo advert) {
		System.out.print("updateAdvert-------------"+advert.getId());
		System.out.print("updateAdvert-------------"+advert.getPackageName());
		dao.updateIgnoreNull(advert);
		return Result.newObjectResult(advert);
	}
}
