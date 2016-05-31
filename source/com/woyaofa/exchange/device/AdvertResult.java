
package com.woyaofa.exchange.device;

import java.util.Date;

public class AdvertResult {

	protected int		taskId			= 0;
	protected int		publishId		= 0;
	protected int		type			= 0;
	protected Date		startDate		= null;
	protected Date		endDate			= null;
	protected Date		startTime		= null;
	protected Date		endTime			= null;
	protected int		interval		= 0;
	protected String	packageName		= null;
	protected String	activeScript	= null;
	protected boolean	replace			= false;
	protected String	targetURL		= null;
	protected int		showCount		= 0;
	protected int		showInterval	= 0;

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

	public int getType () {

		return type;
	}

	public void setType (int type) {

		this.type = type;
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

	public boolean isReplace () {

		return replace;
	}

	public void setReplace (boolean replace) {

		this.replace = replace;
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

}
