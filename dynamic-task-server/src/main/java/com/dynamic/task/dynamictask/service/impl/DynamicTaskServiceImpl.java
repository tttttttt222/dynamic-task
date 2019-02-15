package com.dynamic.task.dynamictask.service.impl;

import com.dynamic.task.dynamictask.dao.mapper.DynamicTaskDao;
import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import com.dynamic.task.dynamictask.service.DynamicTaskService;
import java.util.List;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/15
 */
@Service
@Slf4j
public class DynamicTaskServiceImpl implements DynamicTaskService {


	@Autowired
	private DynamicTaskDao dynamicTaskDao;

	public List<ScheduleJob> getDynamicTaskList(){
          return dynamicTaskDao.getAll();
	}

}
