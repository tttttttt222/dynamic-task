package com.dynamic.task.dynamictask.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dynamic.task.dynamictask.common.utils.ObjectCopyUtil;
import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import com.dynamic.task.dynamictask.manager.QuartzManager;
import com.dynamic.task.facade.DynamicTaskFacade;
import com.dynamic.task.facade.model.QuartzScheduleJob;
import com.dynamic.task.facade.model.Response;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/15
 */
@Service
public class DynamicTaskFacadeImpl implements DynamicTaskFacade {

	@Autowired
	private QuartzManager quartzManager;

	public Response<Boolean> addQuartzJob(QuartzScheduleJob job) {
		Response<Boolean> response = new Response<>();
		try {
			ScheduleJob scheduleJob = new ScheduleJob();
			new ObjectCopyUtil<QuartzScheduleJob, ScheduleJob>().copyObject(job, scheduleJob);
			quartzManager.addJob(scheduleJob);
			response.setResult(true);
		} catch (Exception e) {
			response.setErrorCode("0000");
			response.setErrorMsg("接口异常" + e);
		}
		return response;
	}


	public Response<Boolean> pauseQuartzJob(QuartzScheduleJob job) {
		Response<Boolean> response = new Response<>();
		try {
			ScheduleJob scheduleJob = new ScheduleJob();
			new ObjectCopyUtil<QuartzScheduleJob, ScheduleJob>().copyObject(job, scheduleJob);
			quartzManager.pauseJob(scheduleJob);
			response.setResult(true);
		} catch (Exception e) {
			response.setErrorCode("0000");
			response.setErrorMsg("接口异常" + e);
		}
		return response;
	}


	public Response<Boolean> resumeQuartzJob(QuartzScheduleJob job) {
		Response<Boolean> response = new Response<>();
		try {
			ScheduleJob scheduleJob = new ScheduleJob();
			new ObjectCopyUtil<QuartzScheduleJob, ScheduleJob>().copyObject(job, scheduleJob);
			quartzManager.resumeJob(scheduleJob);
			response.setResult(true);
		} catch (Exception e) {
			response.setErrorCode("0000");
			response.setErrorMsg("接口异常" + e);
		}
		return response;
	}


	public Response<Boolean> runQuartzJobNow(QuartzScheduleJob job) {
		Response<Boolean> response = new Response<>();
		try {
			ScheduleJob scheduleJob = new ScheduleJob();
			new ObjectCopyUtil<QuartzScheduleJob, ScheduleJob>().copyObject(job, scheduleJob);
			quartzManager.runAJobNow(scheduleJob);
			response.setResult(true);
		} catch (Exception e) {
			response.setErrorCode("0000");
			response.setErrorMsg("接口异常" + e);
		}
		return response;
	}


	public Response<Boolean> updateQuartzJobCron(QuartzScheduleJob job) {
		Response<Boolean> response = new Response<>();
		try {
			ScheduleJob scheduleJob = new ScheduleJob();
			new ObjectCopyUtil<QuartzScheduleJob, ScheduleJob>().copyObject(job, scheduleJob);
			quartzManager.updateJobCron(scheduleJob);
			response.setResult(true);
		} catch (Exception e) {
			response.setErrorCode("0000");
			response.setErrorMsg("接口异常" + e);
		}
		return response;
	}


}
