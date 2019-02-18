package com.dynamic.task.dynamictask;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dynamic.task.dynamictask.manager.QuartzManager;
import com.dynamic.task.dynamictask.service.TaskTestService;
import com.dynamic.task.facade.DynamicTaskFacade;
import com.dynamic.task.facade.model.QuartzScheduleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicTaskApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class TaskTest {


	@Reference
	private DynamicTaskFacade dynamicTaskFacade;

	@Test
	public void beanTest() {
		QuartzScheduleJob quartzScheduleJob = new QuartzScheduleJob();
		quartzScheduleJob.setJobId(3L);
		quartzScheduleJob.setJobName("testName3");
		quartzScheduleJob.setJobGroup("testGroup3");
		dynamicTaskFacade.runQuartzJobNow(quartzScheduleJob);
	}


}
