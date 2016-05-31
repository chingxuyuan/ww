
package com.woyaofa.data.system;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

@Table("sys_device_info")
@View("view_sys_device_info")
public class DeviceInfo {

	@Id
	protected int		id				= 0;
	@Column("c_brand")
	protected String	brand			= null;
	@Column("c_model")
	protected String	model			= null;
	@Column("c_imei")
	protected String	IMEI			= null;
	@Column("c_imsi")
	protected String	IMSI			= null;
	@Column("c_key")
	protected String	key				= null;
	@Column("c_os")
	protected String	os				= null;
	@Column("c_version")
	protected String	version			= null;
	@Column("c_isp")
	protected String	ISP				= null;
	@Column("c_network")
	protected String	network			= null;
	@Column("c_channel_id")
	protected int		channelId		= 0;
	@Column("c_channel_name")
	@Readonly
	protected String	channelName		= null;
	@Column("c_plugin_id")
	protected int		pluginId		= 0;
	@Column("c_plugin_name")
	@Readonly
	protected String	pluginName		= null;
	@Column("c_last_ip")
	protected String	lastIP			= null;
	@Column("c_last_area")
	protected String	lastArea		= null;
	@Column("c_active_time")
	protected Date		activeTime		= null;
	@Column("c_last_active_time")
	protected Date		lastActiveTime	= null;
	@Column("c_enable")
	protected boolean	enable			= false;
	@Column("c_flag_active_2_day")
	protected boolean	flagActive2Day	= false;
	@Column("c_flag_active_7_day")
	protected boolean	flagActive7Day	= false;
	@Column("c_flag_active_30_day")
	protected boolean	flagActive30Day	= false;
	@Column("c_flag_empty_task")
	protected boolean	flagEmptyTask	= false;
	@Column("c_flag_active_on_day")
	protected boolean	flagActiveOnDay	= false;

	public int getId () {

		return id;
	}

	public void setId (int id) {

		this.id = id;
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

	public String getKey () {

		return key;
	}

	public void setKey (String key) {

		this.key = key;
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

	public int getChannelId () {

		return channelId;
	}

	public void setChannelId (int channelId) {

		this.channelId = channelId;
	}

	public String getChannelName () {

		return channelName;
	}

	public void setChannelName (String channelName) {

		this.channelName = channelName;
	}

	public int getPluginId () {

		return pluginId;
	}

	public void setPluginId (int pluginId) {

		this.pluginId = pluginId;
	}

	public String getPluginName () {

		return pluginName;
	}

	public void setPluginName (String pluginName) {

		this.pluginName = pluginName;
	}

	public String getLastIP () {

		return lastIP;
	}

	public void setLastIP (String lastIP) {

		this.lastIP = lastIP;
	}

	public String getNetwork () {

		return network;
	}

	public void setNetwork (String network) {

		this.network = network;
	}

	public String getLastArea () {

		return lastArea;
	}

	public void setLastArea (String lastArea) {

		this.lastArea = lastArea;
	}

	public Date getActiveTime () {

		return activeTime;
	}

	public void setActiveTime (Date activeTime) {

		this.activeTime = activeTime;
	}

	public Date getLastActiveTime () {

		return lastActiveTime;
	}

	public void setLastActiveTime (Date lastActiveTime) {

		this.lastActiveTime = lastActiveTime;
	}

	public boolean isEnable () {

		return enable;
	}

	public void setEnable (boolean enable) {

		this.enable = enable;
	}

	public boolean isFlagActive2Day () {

		return flagActive2Day;
	}

	public void setFlagActive2Day (boolean flagActive2Day) {

		this.flagActive2Day = flagActive2Day;
	}

	public boolean isFlagActive7Day () {

		return flagActive7Day;
	}

	public void setFlagActive7Day (boolean flagActive7Day) {

		this.flagActive7Day = flagActive7Day;
	}

	public boolean isFlagActive30Day () {

		return flagActive30Day;
	}

	public void setFlagActive30Day (boolean flagActive30Day) {

		this.flagActive30Day = flagActive30Day;
	}

	public boolean isFlagEmptyTask () {

		return flagEmptyTask;
	}

	public void setFlagEmptyTask (boolean flagEmptyTask) {

		this.flagEmptyTask = flagEmptyTask;
	}

	public boolean isFlagActiveOnDay () {

		return flagActiveOnDay;
	}

	public void setFlagActiveOnDay (boolean flagActiveOnDay) {

		this.flagActiveOnDay = flagActiveOnDay;
	}

}
