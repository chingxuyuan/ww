
package com.woyaofa.exchange.device;

import java.util.Date;

public class AdvertUsage {

	protected String	key			= null;
	protected int		taskId		= 0;
	protected int		publishId	= 0;
	protected Date		advertTime	= null;
	protected int		showed		= 0;
	protected int		clicked		= 0;
	protected boolean	installed	= false;

	public String getKey () {

		return key;
	}

	public void setKey (String key) {

		this.key = key;
	}

	public int getTaskId () {

		return taskId;
	}

	public void setTaskId (int taskId) {

		this.taskId = taskId;
	}

	public int getPublishId () {

		return publishId;
	}

	public void setPublishId (int publishId) {

		this.publishId = publishId;
	}

	public Date getAdvertTime () {

		return advertTime;
	}

	public void setAdvertTime (Date advertTime) {

		this.advertTime = advertTime;
	}

	public int getShowed () {

		return showed;
	}

	public void setShowed (int showed) {

		this.showed = showed;
	}

	public int getClicked () {

		return clicked;
	}

	public void setClicked (int clicked) {

		this.clicked = clicked;
	}

	public boolean isInstalled () {

		return installed;
	}

	public void setInstalled (boolean installed) {

		this.installed = installed;
	}
}
