package com.dynamic.task.facade;

import com.dynamic.task.facade.model.QuartzScheduleJob;
import com.dynamic.task.facade.model.Response;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/15
 */
public interface DynamicTaskFacade {


	Response<Boolean> addQuartzJob(QuartzScheduleJob job);

	Response<Boolean> pauseQuartzJob(QuartzScheduleJob job);

	Response<Boolean> resumeQuartzJob(QuartzScheduleJob job);

	Response<Boolean> runQuartzJobNow(QuartzScheduleJob job);

	Response<Boolean> updateQuartzJobCron(QuartzScheduleJob job);

}
