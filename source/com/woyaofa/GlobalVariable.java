
package com.woyaofa;

import org.nutz.json.JsonIgnore;

public class GlobalVariable {

	@JsonIgnore
	protected String				serverRoot		= null;
	protected String				apkUploadDir	= null;
	protected String				pluginUploadDir	= null;
	protected String				imageUploadDir	= null;
	protected String[]				sqls			= null;

	protected static GlobalVariable	gv				= null;

	protected GlobalVariable() {

	}

	public static GlobalVariable instance () {

		return gv;
	}

	public static void instance (GlobalVariable o) {

		gv = o;
	}

	public String getServerRoot () {

		return serverRoot;
	}

	public void setServerRoot (String serverRoot) {

		this.serverRoot = serverRoot;
	}

	public String getApkUploadDir () {

		return apkUploadDir;
	}

	public void setApkUploadDir (String apkUploadDir) {

		this.apkUploadDir = apkUploadDir;
	}

	public String getPluginUploadDir () {

		return pluginUploadDir;
	}

	public void setPluginUploadDir (String pluginUploadDir) {

		this.pluginUploadDir = pluginUploadDir;
	}

	public String getImageUploadDir () {

		return imageUploadDir;
	}

	public void setImageUploadDir (String imageUploadDir) {

		this.imageUploadDir = imageUploadDir;
	}

	public String[] getSqls () {

		return sqls;
	}

	public void setSqls (String[] sqls) {

		this.sqls = sqls;
	}

}
