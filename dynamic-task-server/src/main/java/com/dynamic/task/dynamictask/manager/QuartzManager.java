package com.dynamic.task.dynamictask.manager;

import com.dynamic.task.dynamictask.dao.mapper.DynamicTaskDao;
import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import com.dynamic.task.dynamictask.task.QuartzJobFactory;
import com.dynamic.task.dynamictask.task.QuartzJobFactoryDisallowConcurrentExecution;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/14
 */
@Service
@Slf4j
public class QuartzManager {


	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;


	@Autowired
	private DynamicTaskDao dynamicTaskDao;

	@PostConstruct
	private void init() {
		List<ScheduleJob> jobList = dynamicTaskDao.getAll();
		for (ScheduleJob job : jobList) {
			addJob(job);
		}
	}


	public void addJob(ScheduleJob job) {
		if (job == null || ScheduleJob.STATUS_RUNNING != job.getJobStatus()) {
			return;
		}
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 不存在，创建一个
			if (null == trigger) {
				Class clazz = ScheduleJob.CONCURRENT_IS == job.getIsConcurrent() ? QuartzJobFactory.class
						: QuartzJobFactoryDisallowConcurrentExecution.class;

				JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup())
						.build();

				jobDetail.getJobDataMap().put("scheduleJob", job);

				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

				trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
						.withSchedule(scheduleBuilder).build();

				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				// Trigger已存在，那么更新相应的定时设置
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

				// 按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (Exception e) {
			log.error("向任务调度中添加定时任务异常！" + e.getMessage(), e);
		}
	}


	/**
	 * 获取所有计划中的任务列表
	 */
	public List<ScheduleJob> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(Integer.parseInt(triggerState.name()));
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}


	/**
	 * 暂停一个job
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}


	/**
	 * 恢复一个job
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}


	/**
	 * 立即执行job
	 */
	public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}


	/**
	 * 更新job时间表达式
	 */
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}


}
