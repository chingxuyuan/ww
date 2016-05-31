
package com.woyaofa.exchange.device;

public class DeviceData {

	protected String	brand		= null;
	protected String	model		= null;
	protected String	IMEI		= null;
	protected String	IMSI		= null;
	protected String	os			= null;
	protected String	version		= null;
	protected String	ISP			= null;
	protected String	channelKey	= null;
	protected String	pluginKey	= null;

	public String getBrand () {

		return brand;
	}

	public void setBrand (String brand) {

		this.brand = brand;
	}

	public String getModel () {

		return model;
	}

	public void setModel (String model) {

		this.model = model;
	}

	public String getIMEI () {

		return IMEI;
	}

	public void setIMEI (String iMEI) {

		IMEI = iMEI;
	}

	public String getIMSI () {

		return IMSI;
	}

	public void setIMSI (String iMSI) {

		IMSI = iMSI;
	}

	public String getOs () {

		return os;
	}

	public void setOs (String os) {

		this.os = os;
	}

	public String getVersion () {

		return version;
	}

	public void setVersion (String version) {

		this.version = version;
	}

	public String getISP () {

		return ISP;
	}

	public void setISP (String iSP) {

		ISP = iSP;
	}

	public String getChannelKey () {

		return channelKey;
	}

	public void setChannelKey (String channelKey) {

		this.channelKey = channelKey;
	}

	public String getPluginKey () {

		return pluginKey;
	}

	public void setPluginKey (String pluginKey) {

		this.pluginKey = pluginKey;
	}

}
