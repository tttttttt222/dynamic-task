package com.dynamic.task.dynamictask.task;

import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/14
 */
@DisallowConcurrentExecution
@Slf4j
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskManager.invokMethod(scheduleJob);
	}
}
