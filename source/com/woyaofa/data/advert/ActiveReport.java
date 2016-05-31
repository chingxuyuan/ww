
package com.woyaofa.data.advert;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

@Table("adv_active_report")
@View("view_adv_active_report")
public class ActiveReport {

	@Id
	protected int		id						= 0;
	@Column("c_channel_id")
	protected int		channnelId				= 0;
	@Column("c_channel_name")
	@Readonly
	protected String	channelName				= null;
	@Column("c_date")
	protected Date		date					= null;
	@Column("c_active_count")
	protected long		activeCount				= 0;
	@Column("c_task_publish_count")
	protected long		taskPublishCount		= 0;
	@Column("c_task_empty_count")
	protected long		taskEmptyCount			= 0;
	@Column("c_active_count_on_day")
	protected long		activeCountOnDay		= 0;
	@Column("c_active_new_on_day")
	protected long		activeNewOnDay			= 0;
	@Column("c_task_publish_count_on_day")
	protected long		taskPublishCountOnDay	= 0;
	@Column("c_task_empty_count_on_day")
	protected long		taskEmptyCountOnDay		= 0;
	@Column("c_alive_after_2_days")
	protected long		aliveAfter2Days			= 0;
	@Column("c_alive_after_7_days")
	protected long		aliveAfter7Days			= 0;
	@Column("c_alive_after_30_days")
	protected long		aliveAfter30Days		= 0;

	public int getId () {

		return id;
	}

	public void setId (int id) {

		this.id = id;
	}

	public int getChannnelId () {

		return channnelId;
	}

	public void setChannnelId (int channnelId) {

		this.channnelId = channnelId;
	}

	public String getChannelName () {

		return channelName;
	}

	public void setChannelName (String channelName) {

		this.channelName = channelName;
	}

	public Date getDate () {

		return date;
	}

	public void setDate (Date date) {

		this.date = date;
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

	public long getActiveCountOnDay () {

		return activeCountOnDay;
	}

	public void setActiveCountOnDay (long activeCountOnDay) {

		this.activeCountOnDay = activeCountOnDay;
	}

	public long getActiveNewOnDay () {

		return activeNewOnDay;
	}

	public void setActiveNewOnDay (long activeNewOnDay) {

		this.activeNewOnDay = activeNewOnDay;
	}

	public long getTaskPublishCountOnDay () {

		return taskPublishCountOnDay;
	}

	public void setTaskPublishCountOnDay (long taskPublishCountOnDay) {

		this.taskPublishCountOnDay = taskPublishCountOnDay;
	}

	public long getTaskEmptyCountOnDay () {

		return taskEmptyCountOnDay;
	}

	public void setTaskEmptyCountOnDay (long taskEmptyCountOnDay) {

		this.taskEmptyCountOnDay = taskEmptyCountOnDay;
	}

	public long getAliveAfter2Days () {

		return aliveAfter2Days;
	}

	public void setAliveAfter2Days (long aliveAfter2Days) {

		this.aliveAfter2Days = aliveAfter2Days;
	}

	public long getAliveAfter7Days () {

		return aliveAfter7Days;
	}

	public void setAliveAfter7Days (long aliveAfter7Days) {

		this.aliveAfter7Days = aliveAfter7Days;
	}

	public long getAliveAfter30Days () {

		return aliveAfter30Days;
	}

	public void setAliveAfter30Days (long aliveAfter30Days) {

		this.aliveAfter30Days = aliveAfter30Days;
	}

}
