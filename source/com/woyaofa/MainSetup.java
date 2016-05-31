
package com.woyaofa;

import java.io.File;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.json.Json;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.woyaofa.data.old.OldDeviceInfo;
import com.woyaofa.data.system.UserInfo;
import com.woyaofa.util.Area;

public class MainSetup implements Setup {

	@Override
	public void destroy (NutConfig config) {

	}

	@Override
	public void init (NutConfig config) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("main setup:" + config.getAppRoot());

		try {
			String serverRoot = config.getAppRoot();
			File file = new File(serverRoot + "/WEB-INF/config.json");
			GlobalVariable gv = Json.fromJsonFile(GlobalVariableEx.class, file);
			gv.setServerRoot(serverRoot);
			GlobalVariable.instance(gv);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Ioc ioc = config.getIoc();
		Dao dao = ioc.get(Dao.class);
		Daos.createTablesInPackage(dao, "com.woyaofa.data", false);

		if (dao.count(UserInfo.class) == 0) {
			this.executeAddionSQLs(dao);
			this.createAdmin(dao);
		}

		List<OldDeviceInfo> devices = dao.query(OldDeviceInfo.class, Cnd.where("lastArea", "is", null));

		for (OldDeviceInfo device : devices) {
			String ip = device.getLastIP();
			String area = Area.getAreaFromIP(ip);
			device.setLastArea(area);
			dao.update(device);
		}
	}

	protected void createAdmin (Dao dao) {

		UserInfo user = new UserInfo();

		user.setUsername("admin");
		user.setPassword("admin");
		user.setEnable(true);

		dao.insert(user);
	}

	protected void executeAddionSQLs (Dao dao) {

		GlobalVariable gv = GlobalVariable.instance();

		if (gv == null) {
			return;
		}

		String[] sqls = gv.getSqls();

		if (sqls == null) {
			return;
		}

		for (String s : sqls) {
			try {
				Sql sql = Sqls.create(s);
				dao.execute(sql);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

class GlobalVariableEx extends GlobalVariable {

	public GlobalVariableEx() {

	}
}

class Backwork implements Runnable {

	protected Dao dao = null;

	public Backwork(Dao dao) {
		this.dao = dao;
	}

	@Override
	public void run () {

		List<OldDeviceInfo> devices = dao.query(OldDeviceInfo.class, Cnd.where("lastArea", "is", "null"));

		for (OldDeviceInfo device : devices) {
			String ip = device.getLastIP();
			String area = Area.getAreaFromIP(ip);
			device.setLastArea(area);
			dao.update(device);
		}

	}

}
