
package com.woyaofa.data.old;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("sys_old_device_info")
public class OldDeviceInfo {

	@Id
	protected int		id				= 0;
	@Column("c_brand")
	protected String	brand			= null;
	@Column("c_model")
	protected String	model			= null;
	@Column("c_os")
	protected String	os				= null;
	@Column("c_version")
	protected String	version			= null;
	@Column("c_mac")
	protected String	MAC				= null;
	@Column("c_release_key")
	protected String	releaseKey		= null;
	@Column("c_secret_key")
	protected String	secretKey		= null;
	@Column("c_channel_id")
	protected int		channelId		= 0;
	@Column("c_has_sim")
	protected boolean	hasSIM			= false;
	@Column("c_network")
	protected String	network			= null;
	@Column("c_isp")
	protected String	ISP				= null;
	@Column("c_imei")
	protected String	imei			= null;
	@Column("c_imsi")
	protected String	imsi			= null;
	@Column("c_system")
	protected boolean	system			= false;
	@Column("c_last_ip")
	protected String	lastIP			= null;
	@Column("c_last_area")
	protected String	lastArea		= null;
	@Column("c_active_time")
	protected Date		activeTime		= null;
	@Column("c_last_active_time")
	protected Date		lastActiveTime	= null;

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

	public String getMAC () {

		return MAC;
	}

	public void setMAC (String mAC) {

		MAC = mAC;
	}

	public String getReleaseKey () {

		return releaseKey;
	}

	public void setReleaseKey (String releaseKey) {

		this.releaseKey = releaseKey;
	}

	public String getSecretKey () {

		return secretKey;
	}

	public void setSecretKey (String secretKey) {

		this.secretKey = secretKey;
	}

	public int getChannelId () {

		return channelId;
	}

	public void setChannelId (int channelId) {

		this.channelId = channelId;
	}

	public boolean isHasSIM () {

		return hasSIM;
	}

	public void setHasSIM (boolean hasSIM) {

		this.hasSIM = hasSIM;
	}

	public String getNetwork () {

		return network;
	}

	public void setNetwork (String network) {

		this.network = network;
	}

	public String getISP () {

		return ISP;
	}

	public void setISP (String iSP) {

		ISP = iSP;
	}

	public String getImei () {

		return imei;
	}

	public void setImei (String imei) {

		this.imei = imei;
	}

	public String getImsi () {

		return imsi;
	}

	public void setImsi (String imsi) {

		this.imsi = imsi;
	}

	public boolean isSystem () {

		return system;
	}

	public void setSystem (boolean system) {

		this.system = system;
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

}
