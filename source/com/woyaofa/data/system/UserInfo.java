
package com.woyaofa.data.system;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("sys_user_info")
public class UserInfo {

	@Id
	protected int		id			= 0;
	@Name
	@Column("c_username")
	protected String	username	= null;
	@Column("c_password")
	protected String	password	= null;
	@Column("c_enable")
	protected boolean	enable		= false;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public boolean isEnable() {

		return enable;
	}

	public void setEnable(boolean enable) {

		this.enable = enable;
	}

}
