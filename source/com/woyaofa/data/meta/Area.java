
package com.woyaofa.data.meta;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("meta_area")
public class Area {

	@Name
	@Column("c_name")
	protected String name = null;

	public String getName () {

		return name;
	}

	public void setName (String name) {

		this.name = name;
	}
}
