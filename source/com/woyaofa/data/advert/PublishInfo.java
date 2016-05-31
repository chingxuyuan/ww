
package com.woyaofa.data.advert;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

@Table("adv_publish_info")
@View("view_adv_publish_info")
public class PublishInfo {

	@Id
	protected int		id				= 0;
	@Column("c_name")
	protected String	name			= null;
	@Column("c_advert_id")
	protected int		advertId		= 0;
	@Column("c_channel_id")
	protected int		channelId		= 0;
	@Column("c_rule_id")
	protected int		ruleId			= 0;
	@Column("c_create_time")
	protected Date		createTime		= null;
	@Column("c_enable")
	protected boolean	enable			= false;
	@Column("c_published_count")
	protected long		publishedCount	= 0;
	@Column("c_report_count")
	protected long		reportCount		= 0;
	@Column("c_memo")
	protected String	memo			= null;
	@Column("c_advert_name")
	@Readonly
	protected String	advertName		= null;
	@Column("c_channel_name")
	@Readonly
	protected String	channelName		= null;
	@Column("c_rule_name")
	@Readonly
	protected String	ruleName		= null;

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

	public int getAdvertId () {

		return advertId;
	}

	public void setAdvertId (int advertId) {

		this.advertId = advertId;
	}

	public int getChannelId () {

		return channelId;
	}

	public void setChannelId (int channelId) {

		this.channelId = channelId;
	}

	public int getRuleId () {

		return ruleId;
	}

	public void setRuleId (int ruleId) {

		this.ruleId = ruleId;
	}

	public Date getCreateTime () {

		return createTime;
	}

	public void setCreateTime (Date createTime) {

		this.createTime = createTime;
	}

	public boolean isEnable () {

		return enable;
	}

	public void setEnable (boolean enable) {

		this.enable = enable;
	}

	public long getPublishedCount () {

		return publishedCount;
	}

	public void setPublishedCount (long publishedCount) {

		this.publishedCount = publishedCount;
	}

	public long getReportCount () {

		return reportCount;
	}

	public void setReportCount (long reportCount) {

		this.reportCount = reportCount;
	}

	public String getMemo () {

		return memo;
	}

	public void setMemo (String memo) {

		this.memo = memo;
	}

	public String getAdvertName () {

		return advertName;
	}

	public void setAdvertName (String advertName) {

		this.advertName = advertName;
	}

	public String getChannelName () {

		return channelName;
	}

	public void setChannelName (String channelName) {

		this.channelName = channelName;
	}

	public String getRuleName () {

		return ruleName;
	}

	public void setRuleName (String ruleName) {

		this.ruleName = ruleName;
	}

}
