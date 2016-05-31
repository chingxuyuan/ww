
package com.woyaofa.data.advert;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("adv_plugin_info")
public class PluginInfo {

	@Id
	protected int		id			= 0;
	@Column("c_name")
	protected String	name		= null;
	@Column("c_version")
	protected String	version		= null;
	@Column("c_package_name")
	protected String	packageName	= null;
	@Column("c_key")
	protected String	key			= null;
	@Column("c_create_time")
	protected Date		createTime	= null;
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

	public String getVersion () {

		return version;
	}

	public void setVersion (String version) {

		this.version = version;
	}

	public String getPackageName () {

		return packageName;
	}

	public void setPackageName (String packageName) {

		this.packageName = packageName;
	}

	public String getKey () {

		return key;
	}

	public void setKey (String key) {

		this.key = key;
	}

	public Date getCreateTime () {

		return createTime;
	}

	public void setCreateTime (Date createTime) {

		this.createTime = createTime;
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
