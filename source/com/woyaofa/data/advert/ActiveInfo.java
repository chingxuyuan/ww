
package com.woyaofa.data.advert;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

@Table("adv_active_info")
@View("view_adv_active_info")
public class ActiveInfo {

	@Id
	protected int		id			= 0;
	@Column("c_device_id")
	protected int		deviceId	= 0;
	@Column("c_plugin_id")
	protected int		pluginId	= 0;
	@Column("c_active_time")
	protected Date		activeTime	= null;
	@Column("c_channelName")
	@Readonly
	protected String	channelName	= null;
	@Column("c_brand")
	@Readonly
	protected String	brand		= null;
	@Column("c_model")
	@Readonly
	protected String	model		= null;
	@Column("c_imei")
	@Readonly
	protected String	IMEI		= null;
	@Column("c_imsi")
	@Readonly
	protected String	IMSI		= null;
	@Column("c_last_isp")
	@Readonly
	protected String	lastIP		= null;
	@Column("c_last_area")
	@Readonly
	protected String	lastArea	= null;
	@Column("c_isp")
	@Readonly
	protected String	ISP			= null;
	@Column("c_network")
	@Readonly
	protected String	network		= null;

	public int getId () {

		return id;
	}

	public void setId (int id) {

		this.id = id;
	}

	public int getDeviceId () {

		return deviceId;
	}

	public void setDeviceId (int deviceId) {

		this.deviceId = deviceId;
	}

	public int getPluginId () {

		return pluginId;
	}

	public void setPluginId (int pluginId) {

		this.pluginId = pluginId;
	}

	public Date getActiveTime () {

		return activeTime;
	}

	public void setActiveTime (Date activeTime) {

		this.activeTime = activeTime;
	}

	public String getChannelName () {

		return channelName;
	}

	public void setChannelName (String channelName) {

		this.channelName = channelName;
	}

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

	public String getLastIP () {

		return lastIP;
	}

	public void setLastIP (String lastIP) {

		this.lastIP = lastIP;
	}

	public String getLastArea () {

		return lastArea;
	}

	public void setLastArea (String lastArea) {

		this.lastArea = lastArea;
	}

	public String getISP () {

		return ISP;
	}

	public void setISP (String iSP) {

		ISP = iSP;
	}

	public String getNetwork () {

		return network;
	}

	public void setNetwork (String network) {

		this.network = network;
	}

}
