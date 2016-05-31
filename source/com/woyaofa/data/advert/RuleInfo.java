
package com.woyaofa.data.advert;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("adv_rule_info")
public class RuleInfo {

	@Id
	protected int		id			= 0;
	@Column("c_name")
	protected String	name		= null;
	@Column("c_price")
	protected double	price		= 0.0;
	@Column("c_dest_os")
	protected String	destOS		= null;
	@Column("c_has_sim")
	protected boolean	hasSIM		= false;
	@Column("c_isp")
	protected String	ISP			= null;
	@Column("c_network")
	protected String	network		= null;
	@Column("c_brand")
	protected String	brand		= null;
	@Column("c_city")
	protected String	city		= null;
	@Column("c_create_time")
	protected Date		createTime	= null;
	@Column("c_start_date")
	@ColDefine(customType = "Date")
	protected Date		startDate	= null;
	@Column("c_end_date")
	@ColDefine(customType = "Date")
	protected Date		endDate		= null;
	@Column("c_start_time")
	@ColDefine(customType = "Time")
	protected Date		startTime	= null;
	@Column("c_end_time")
	@ColDefine(customType = "Time")
	protected Date		endTime		= null;
	@Column("c_interval")
	protected int		interval	= 0;
	@Column("c_memo")
	protected String	memo		= null;
	@Column("c_enable")
	protected boolean	enable		= false;

	public int getId () {

		return id;
	}

	public void setId (int id) {

		this.id = id;
	}

	public String getName () {

		return name;
	}

	public void setName (String name) {

		this.name = name;
	}

	public double getPrice () {

		return price;
	}

	public void setPrice (double price) {

		this.price = price;
	}

	public String getDestOS () {

		return destOS;
	}

	public void setDestOS (String destOS) {

		this.destOS = destOS;
	}

	public boolean isHasSIM () {

		return hasSIM;
	}

	public void setHasSIM (boolean hasSIM) {

		this.hasSIM = hasSIM;
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

	public String getBrand () {

		return brand;
	}

	public void setBrand (String brand) {

		this.brand = brand;
	}

	public String getCity () {

		return city;
	}

	public void setCity (String city) {

		this.city = city;
	}

	public Date getCreateTime () {

		return createTime;
	}

	public void setCreateTime (Date createTime) {

		this.createTime = createTime;
	}

	public Date getStartDate () {

		return startDate;
	}

	public void setStartDate (Date startDate) {

		this.startDate = startDate;
	}

	public Date getEndDate () {

		return endDate;
	}

	public void setEndDate (Date endDate) {

		this.endDate = endDate;
	}

	public Date getStartTime () {

		return startTime;
	}

	public void setStartTime (Date startTime) {

		this.startTime = startTime;
	}

	public Date getEndTime () {

		return endTime;
	}

	public void setEndTime (Date endTime) {

		this.endTime = endTime;
	}

	public int getInterval () {

		return interval;
	}

	public void setInterval (int interval) {

		this.interval = interval;
	}

	public String getMemo () {

		return memo;
	}

	public void setMemo (String memo) {

		this.memo = memo;
	}

	public boolean isEnable () {

		return enable;
	}

	public void setEnable (boolean enable) {

		this.enable = enable;
	}

}
