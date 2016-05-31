
package com.woyaofa.data.advert;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("adv_advert_info")
public class AdvertInfo {

	@Id
	protected int		id				= 0;
	@Column("c_name")
	protected String	name			= null;
	@Column("c_type")
	protected int		type			= 0;
	@Column("c_create_time")
	protected Date		createTime		= null;
	@Column("c_price")
	protected double	price			= 0.0;
	@Column("c_enable")
	protected boolean	enable			= false;
	@Column("c_package_name")
	protected String	packageName		= null;
	@Column("c_active_script")
	protected String	activeScript	= null;
	@Column("c_system")
	protected boolean	system			= false;
	@Column("c_replace")
	protected boolean	replace			= false;
	@Column("c_resource_url")
	protected String	resourceURL		= null;
	@Column("c_target_url")
	protected String	targetURL		= null;
	@Column("c_show_count")
	protected int		showCount		= 0;
	@Column("c_show_interval")
	protected int		showInterval	= 0;
	@Column("c_memo")
	protected String	memo			= null;

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

	public int getType () {

		return type;
	}

	public void setType (int type) {

		this.type = type;
	}

	public Date getCreateTime () {

		return createTime;
	}

	public void setCreateTime (Date createTime) {

		this.createTime = createTime;
	}

	public double getPrice () {

		return price;
	}

	public void setPrice (double price) {

		this.price = price;
	}

	public boolean isEnable () {

		return enable;
	}

	public void setEnable (boolean enable) {

		this.enable = enable;
	}

	public String getPackageName () {

		return packageName;
	}

	public void setPackageName (String packageName) {

		this.packageName = packageName;
	}

	public String getActiveScript () {

		return activeScript;
	}

	public void setActiveScript (String activeScript) {

		this.activeScript = activeScript;
	}

	public boolean isSystem () {

		return system;
	}

	public void setSystem (boolean system) {

		this.system = system;
	}

	public boolean isReplace () {

		return replace;
	}

	public void setReplace (boolean replace) {

		this.replace = replace;
	}

	public String getResourceURL () {

		return resourceURL;
	}

	public void setResourceURL (String resourceURL) {

		this.resourceURL = resourceURL;
	}

	public String getTargetURL () {

		return targetURL;
	}

	public void setTargetURL (String targetURL) {

		this.targetURL = targetURL;
	}

	public int getShowCount () {

		return showCount;
	}

	public void setShowCount (int showCount) {

		this.showCount = showCount;
	}

	public int getShowInterval () {

		return showInterval;
	}

	public void setShowInterval (int showInterval) {

		this.showInterval = showInterval;
	}

	public String getMemo () {

		return memo;
	}

	public void setMemo (String memo) {

		this.memo = memo;
	}

}
