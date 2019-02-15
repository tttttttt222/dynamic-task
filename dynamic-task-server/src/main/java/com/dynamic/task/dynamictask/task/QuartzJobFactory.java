package com.dynamic.task.dynamictask.task;

import com.dynamic.task.dynamictask.config.bean.SpringBeanUtil;
import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/14
 */
@Slf4j
public class QuartzJobFactory implements Job {



	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		if (scheduleJob.getIsDubbo() == 1) {
			SpringBeanUtil.getBean("dubboTaskProcess",DubboTaskProcess.class).invokMethod(scheduleJob);
		} else {
			SpringBeanUtil.getBean("normalTaskProcess",NormalTaskProcess.class).invokMethod(scheduleJob);
		}

	}
}
