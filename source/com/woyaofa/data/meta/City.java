
package com.woyaofa.data.meta;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("meta_city")
@PK({"area", "name"})
public class City {

	@Column("c_area")
	protected String	area	= null;

	@Column("c_name")
	protected String	name	= null;

	public String getArea () {

		return area;
	}

	public void setArea (String area) {

		this.area = area;
	}

	public String getName () {

		return name;
	}

	public void setName (String name) {

		this.name = name;
	}
}
