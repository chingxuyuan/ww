
package com.woyaofa.data.system;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("sys_channel_info")
public class ChannelInfo {

	@Id
	protected int		id					= 0;
	@Column("c_name")
	protected String	name				= null;
	@Column("c_nickname")
	protected String	nickname			= null;
	@Column("c_key")
	protected String	key					= null;
	@Column("c_old_release_key")
	protected String	oldRelleaseKey		= null;
	@Column("c_old_secret_key")
	protected String	oldSecretKey		= null;
	@Column("c_create_time")
	protected Date		createTime			= null;
	@Column("c_delay_days")
	protected int		delayDays			= 0;
	@Column("c_enable")
	protected boolean	enable				= false;
	@Column("c_memo")
	protected String	memo				= null;
	@Column("c_active_count")
	protected long		activeCount			= 0;
	@Column("c_task_publish_count")
	protected long		taskPublishCount	= 0;
	@Column("c_task_empty_count")
	protected long		taskEmptyCount		= 0;

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

	public String getNickname () {

		return nickname;
	}

	public void setNickname (String nickname) {

		this.nickname = nickname;
	}

	public String getKey () {

		return key;
	}

	public void setKey (String key) {

		this.key = key;
	}

	public String getOldRelleaseKey () {

		return oldRelleaseKey;
	}

	public void setOldRelleaseKey (String oldRelleaseKey) {

		this.oldRelleaseKey = oldRelleaseKey;
	}

	public String getOldSecretKey () {

		return oldSecretKey;
	}

	public void setOldSecretKey (String oldSecretKey) {

		this.oldSecretKey = oldSecretKey;
	}

	public Date getCreateTime () {

		return createTime;
	}

	public void setCreateTime (Date createTime) {

		this.createTime = createTime;
	}

	public int getDelayDays () {

		return delayDays;
	}

	public void setDelayDays (int delayDays) {

		this.delayDays = delayDays;
	}

	public boolean isEnable () {

		return enable;
	}

	public void setEnable (boolean enable) {

		this.enable = enable;
	}

	public String getMemo () {

		return memo;
	}

	public void setMemo (String memo) {

		this.memo = memo;
	}

	public long getActiveCount () {

		return activeCount;
	}

	public void setActiveCount (long activeCount) {

		this.activeCount = activeCount;
	}

	public long getTaskPublishCount () {

		return taskPublishCount;
	}

	public void setTaskPublishCount (long taskPublishCount) {

		this.taskPublishCount = taskPublishCount;
	}

	public long getTaskEmptyCount () {

		return taskEmptyCount;
	}

	public void setTaskEmptyCount (long taskEmptyCount) {

		this.taskEmptyCount = taskEmptyCount;
	}

}
