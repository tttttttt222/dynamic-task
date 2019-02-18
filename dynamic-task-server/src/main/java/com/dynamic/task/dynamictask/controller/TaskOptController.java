package com.dynamic.task.dynamictask.controller;

import com.dynamic.task.facade.DynamicTaskFacade;
import com.dynamic.task.facade.model.QuartzScheduleJob;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/18
 */
@RestController
@Slf4j
public class TaskOptController {

	@Autowired
	private DynamicTaskFacade dynamicTaskFacade;

	@RequestMapping("/jobStartNow")
	public void jobStartNow() {
		QuartzScheduleJob quartzScheduleJob = new QuartzScheduleJob();
		quartzScheduleJob.setJobId(3L);
		quartzScheduleJob.setJobName("testName3");
		quartzScheduleJob.setJobGroup("testGroup3");
		dynamicTaskFacade.runQuartzJobNow(quartzScheduleJob);
		return ;
	}


}
