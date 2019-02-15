package com.dynamic.task.dynamictask.service.impl;

import com.dynamic.task.dynamictask.service.TaskTestService;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/14
 */
@Service
public class TaskTestServiceImpl implements TaskTestService {

	@Override
	public void doTaskTest() {
		System.out.println("dotask"+new Date());
	}
}
