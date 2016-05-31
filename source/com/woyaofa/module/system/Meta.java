
package com.woyaofa.module.system;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.woyaofa.data.meta.Area;
import com.woyaofa.data.meta.Brand;
import com.woyaofa.data.meta.City;
import com.woyaofa.data.meta.ISP;
import com.woyaofa.data.meta.Network;
import com.woyaofa.data.meta.OS;
import com.woyaofa.exchange.web.Result;

@IocBean
@At("/system/meta")
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { "user", "/" }))
public class Meta {

	@Inject
	Dao dao = null;

	@At("/insert-area")
	public Result insertArea (@Param("name") String name) {

		Area item = new Area();
		item.setName(name);
		dao.insert(item);
		return Result.newSuccessResult();
	}

	@At("/update-area")
	public Result updateArea (@Param("old_name") String oldName, @Param("new_name") String newName) {

		Area old_item = dao.fetch(Area.class, oldName);

		if (old_item == null) {
			return Result.newErrorResult("no exist");
		}

		Area new_item = new Area();
		new_item.setName(newName);
		dao.insert(new_item);
		dao.delete(old_item);

		return Result.newSuccessResult();
	}

	@At("/delete-area")
	public Result deleteArea (@Param("name") String name) {

		long count = dao.delete(Area.class, name);

		if (count == 0) {
			return Result.newErrorResult("no exist");
		} else {
			dao.clear(City.class, Cnd.where("area", "=", name));
			return Result.newSuccessResult();
		}
	}

	@At("/list-area")
	public Result listArea () {

		List<Area> items = dao.query(Area.class, null);
		return Result.newObjectResult(items, 0, items.size(), 1);
	}

	@At("/insert-city")
	public Result insertCity (@Param("area") String area, @Param("name") String name) {

		City item = new City();
		item.setArea(area);
		item.setName(name);
		dao.insert(item);

		return Result.newSuccessResult();
	}

	@At("/update-city")
	public Result updateCity (@Param("area") String area, @Param("old_name") String oldName, @Param("new_name") String newName) {

		City old_item = dao.fetch(City.class, Cnd.where("area", "=", area).and("name", "=", oldName));

		if (old_item == null) {
			return Result.newErrorResult("no exist");
		}

		City new_item = new City();
		new_item.setArea(area);
		new_item.setName(newName);
		dao.insert(new_item);
		dao.delete(old_item);

		return Result.newSuccessResult();
	}

	@At("/delete-city")
	public Result deleteCity (@Param("area") String area, @Param("name") String name) {

		City item = dao.fetch(City.class, Cnd.where("area", "=", area).and("name", "=", name));

		if (item == null) {
			return Result.newErrorResult("no exist");
		}

		dao.delete(item);

		return Result.newSuccessResult();
	}

	@At("/list-city")
	public Result listCity (@Param("area") String area) {

		List<City> items = dao.query(City.class, Cnd.where("area", "=", area));
		return Result.newObjectResult(items, 0, items.size(), 1);
	}

	@At("/insert-brand")
	public Result insertBrand (@Param("name") String name) {

		Brand item = new Brand();
		item.setName(name);
		dao.insert(item);

		return Result.newSuccessResult();
	}

	@At("/update-brand")
	public Result updateBrand (@Param("old_name") String oldName, @Param("new_name") String newName) {

		Brand old_item = dao.fetch(Brand.class, oldName);

		if (old_item == null) {
			return Result.newErrorResult("no exist");
		}

		Brand new_item = new Brand();
		new_item.setName(newName);
		dao.insert(new_item);
		dao.delete(old_item);

		return Result.newSuccessResult();
	}

	@At("/delete-brand")
	public Result deleteBrand (@Param("name") String name) {

		long count = dao.delete(Brand.class, name);

		if (count == 0) {
			return Result.newErrorResult("no exist");
		} else {
			return Result.newSuccessResult();
		}
	}

	@At("/list-brand")
	public Result listBrand () {

		List<Brand> items = dao.query(Brand.class, null);
		return Result.newObjectResult(items, 0, items.size(), 1);
	}

	@At("/insert-isp")
	public Result insertISP (@Param("name") String name) {

		ISP item = new ISP();
		item.setName(name);
		dao.insert(item);

		return Result.newSuccessResult();
	}

	@At("/update-isp")
	public Result updateISP (@Param("old_name") String oldName, @Param("new_name") String newName) {

		ISP old_item = dao.fetch(ISP.class, oldName);

		if (old_item == null) {
			return Result.newErrorResult("no exist");
		}

		ISP new_item = new ISP();
		new_item.setName(newName);
		dao.insert(new_item);
		dao.delete(old_item);

		return Result.newSuccessResult();
	}

	@At("/delete-isp")
	public Result deleteISP (@Param("name") String name) {

		long count = dao.delete(ISP.class, name);

		if (count == 0) {
			return Result.newErrorResult("no exist");
		} else {
			return Result.newSuccessResult();
		}
	}

	@At("/list-isp")
	public Result listISP () {

		List<ISP> items = dao.query(ISP.class, null);
		return Result.newObjectResult(items, 0, items.size(), 1);
	}

	@At("/insert-network")
	public Result insertNetwork (@Param("name") String name) {

		Network item = new Network();
		item.setName(name);
		dao.insert(item);

		return Result.newSuccessResult();
	}

	@At("/update-network")
	public Result updateNetwork (@Param("old_name") String oldName, @Param("new_name") String newName) {

		Network old_item = dao.fetch(Network.class, oldName);

		if (old_item == null) {
			return Result.newErrorResult("no exist");
		}

		Network new_item = new Network();
		new_item.setName(newName);
		dao.insert(new_item);
		dao.delete(old_item);

		return Result.newSuccessResult();
	}

	@At("/delete-network")
	public Result deleteNetwork (@Param("name") String name) {

		long count = dao.delete(Network.class, name);

		if (count == 0) {
			return Result.newErrorResult("no exist");
		} else {
			return Result.newSuccessResult();
		}
	}

	@At("/list-network")
	public Result listNetwork () {

		List<Network> items = dao.query(Network.class, null);
		return Result.newObjectResult(items, 0, items.size(), 1);
	}

	@At("/insert-os")
	public Result insertOS (@Param("name") String name) {

		OS item = new OS();
		item.setName(name);
		dao.insert(item);

		return Result.newSuccessResult();
	}

	@At("/update-os")
	public Result updateOS (@Param("old_name") String oldName, @Param("new_name") String newName) {

		OS old_item = dao.fetch(OS.class, oldName);

		if (old_item == null) {
			return Result.newErrorResult("no exist");
		}

		OS new_item = new OS();
		new_item.setName(newName);
		dao.insert(new_item);
		dao.delete(old_item);

		return Result.newSuccessResult();
	}

	@At("/delete-os")
	public Result deleteOS (@Param("name") String name) {

		long count = dao.delete(OS.class, name);

		if (count == 0) {
			return Result.newErrorResult("no exist");
		} else {
			return Result.newSuccessResult();
		}
	}

	@At("/list-os")
	public Result listOS () {

		List<OS> items = dao.query(OS.class, null);
		return Result.newObjectResult(items, 0, items.size(), 1);
	}
}
