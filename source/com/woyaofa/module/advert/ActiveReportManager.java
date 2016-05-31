
package com.woyaofa.module.advert;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.woyaofa.data.advert.ActiveReport;
import com.woyaofa.data.advert.PublishInfo;
import com.woyaofa.data.advert.TaskInfo;
import com.woyaofa.data.system.ChannelInfo;
import com.woyaofa.data.system.DeviceInfo;
import com.woyaofa.exchange.device.AdvertUsage;

public class ActiveReportManager {

	protected List<Runnable>				tasks		= null;
	protected Semaphore						semaphore	= null;
	protected Thread						thread		= null;

	protected static ActiveReportManager	manager		= null;

	public static ActiveReportManager instance () {

		if (manager == null) {
			manager = new ActiveReportManager();
		}

		return manager;
	}

	protected ActiveReportManager() {

		tasks = new LinkedList<Runnable>();
		semaphore = new Semaphore(1);

		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
		}

		thread = new Thread(new Runnable() {

			@Override
			public void run () {

				doTask();

			}
		});

		thread.start();
	}

	public void updateRegister (final Dao dao,  final Date now, final DeviceInfo device,final ChannelInfo channel) {

		Runnable run = new Runnable() {

			@Override
			public void run () {

				channel.setActiveCount(channel.getActiveCount() + 1);
				dao.update(channel);

				ActiveReport report = dao.fetch(ActiveReport.class, Cnd.where("channelId", "=", device.getChannelId()).and("date", "=", now));

				if (report == null) {
					report = new ActiveReport();
					report.setDate(now);
					report.setChannnelId(device.getChannelId());
					report.setActiveCount(channel.getActiveCount());
					report.setActiveCountOnDay(1);
					report.setActiveNewOnDay(1);
					report.setActiveCountOnDay(1);
					dao.insert(report);
				} else {
					report.setActiveCount(channel.getActiveCount());
					report.setActiveNewOnDay(report.getActiveNewOnDay() + 1);
					report.setActiveCountOnDay(report.getActiveCountOnDay() + 1);
					dao.update(report);
				}
			}
		};

		synchronized (this) {
			tasks.add(run);
		}

		semaphore.release();
	}

	public void updateActive (final  Dao dao,final Date now,final DeviceInfo device,final ChannelInfo channel) {

		if (device.isFlagActiveOnDay()) {
			return;
		}

		Runnable run = new Runnable() {

			@Override
			public void run () {

				ActiveReport report = dao.fetch(ActiveReport.class, Cnd.where("channelId", "=", device.getChannelId()).and("date", "=", now));

				if (report == null) {
					report = new ActiveReport();
					report.setDate(now);
					report.setChannnelId(device.getChannelId());
					report.setActiveCount(channel.getActiveCount());
					report.setActiveCountOnDay(1);
					report.setActiveNewOnDay(1);
					report.setActiveCountOnDay(1);
					dao.insert(report);
				} else {
					report.setActiveCount(channel.getActiveCount());
					report.setActiveCountOnDay(report.getActiveCountOnDay() + 1);

					if (device.isFlagActive2Day()) {
						report.setAliveAfter2Days(report.getAliveAfter2Days() + 1);

						if (device.isFlagActive7Day()) {
							report.setAliveAfter7Days(report.getAliveAfter7Days() + 1);

							if (device.isFlagActive30Day()) {
								report.setAliveAfter30Days(report.getAliveAfter30Days() + 1);
							}
						}
					}

					dao.update(report);
				}

				device.setFlagActiveOnDay(true);
				dao.update(device);
			}
		};

		synchronized (this) {
			tasks.add(run);
		}

		semaphore.release();
	}

	public void updateTask (final Dao dao,final Date now,final DeviceInfo device,final TaskInfo task) {

		Runnable run = new Runnable() {

			@Override
			public void run () {

				ActiveReport report = dao.fetch(ActiveReport.class, Cnd.where("channelId", "=", device.getChannelId()).and("date", "=", now));

				if (report == null) {
					report = new ActiveReport();
					report.setDate(now);
					report.setChannnelId(device.getChannelId());
					report.setTaskPublishCountOnDay(1);
					dao.insert(report);
				} else {
					report.setTaskPublishCountOnDay(report.getTaskPublishCountOnDay() + 1);
					dao.update(report);
				}
			}
		};

		synchronized (this) {
			tasks.add(run);
		}

		semaphore.release();
	}

	public void updateUsage (Dao dao, Date now, DeviceInfo device, PublishInfo publish, AdvertUsage usage) {

	}

	protected void doTask () {

		for (;;) {
			try {
				semaphore.acquire();
				Runnable task = null;

				synchronized (this) {
					task = tasks.remove(0);
				}

				task.run();
			} catch (Exception e) {

			}
		}
	}
}
