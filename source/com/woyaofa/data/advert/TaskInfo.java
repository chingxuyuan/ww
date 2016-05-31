
package com.woyaofa.data.advert;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

@Table("adv_task_info")
@View("view_adv_task_info")
public class TaskInfo {

	@Id
	protected int		id			= 0;
	@Column("c_device_id")
	protected int		deviceId	= 0;
	@Column("c_brand")
	@Readonly
	protected String	brand		= null;
	@Column("c_model")
	@Readonly
	protected String	model		= null;
	@Column("c_plugin_id")
	protected int		pluginId	= 0;
	@Column("c_advert_id")
	protected int		advertId	= 0;
	@Column("c_advert_name")
	@Readonly
	protected String	advertName	= null;
	@Column("c_advert_type")
	@Readonly
	protected int		advertType	= 0;
	@Column("c_publish_id")
	protected int		publishId	= 0;
	@Column("c_publish_name")
	@Readonly
	protected String	publishName	= null;
	@Column("c_advert_time")
	protected Date		advertTime	= null;
	@Column("c_report_time")
	protected Date		reportTime	= null;

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

	public int getPluginId () {

		return pluginId;
	}

	public void setPluginId (int pluginId) {

		this.pluginId = pluginId;
	}

	public int getAdvertId () {

		return advertId;
	}

	public void setAdvertId (int advertId) {

		this.advertId = advertId;
	}

	public String getAdvertName () {

		return advertName;
	}

	public void setAdvertName (String advertName) {

		this.advertName = advertName;
	}

	public int getAdvertType () {

		return advertType;
	}

	public void setAdvertType (int advertType) {

		this.advertType = advertType;
	}

	public int getPublishId () {

		return publishId;
	}

	public void setPublishId (int publishId) {

		this.publishId = publishId;
	}

	public String getPublishName () {

		return publishName;
	}

	public void setPublishName (String publishName) {

		this.publishName = publishName;
	}

	public Date getAdvertTime () {

		return advertTime;
	}

	public void setAdvertTime (Date advertTime) {

		this.advertTime = advertTime;
	}

	public Date getReportTime () {

		return reportTime;
	}

	public void setReportTime (Date reportTime) {

		this.reportTime = reportTime;
	}

}
