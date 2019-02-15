package com.dynamic.task.dynamictask.task;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.dynamic.task.dynamictask.config.bean.SpringBeanUtil;
import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/14
 */
@Component
@Slf4j
public class DubboTaskProcess {


	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	private RegistryConfig registryConfig;

	private int defaultTimeOut = 30000;//30秒

	public void invokMethod(ScheduleJob scheduleJob) {
		try {
			ReferenceConfig<GenericService> genericReferenceConfig = createGenericReferenceConfig(scheduleJob);
			genericReferenceConfig.get()
					.$invoke(scheduleJob.getMethodName(), new String[]{}, new Object[]{});
		} catch (Exception e) {
			log.error("rpc调用异常", e);
		}

	}


	public ReferenceConfig<GenericService> createGenericReferenceConfig(ScheduleJob scheduleJob) {
		try {
			ReferenceConfig<GenericService> service = new ReferenceConfig<>();
			service.setApplication(applicationConfig);
			service.setRegistry(registryConfig);
			service.setInterface(scheduleJob.getBeanClass());
			service.setVersion("1.0.0");
			service.setTimeout(defaultTimeOut);
			service.setRetries(0);
			service.setCheck(false);
			service.setGeneric(true);
			return service;
		} catch (Exception e) {
			log.error("创建dubbo消费端失败", e);
			return null;
		}
	}


}
