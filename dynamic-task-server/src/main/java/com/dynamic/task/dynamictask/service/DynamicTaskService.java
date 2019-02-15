package com.dynamic.task.dynamictask.service;

import com.dynamic.task.dynamictask.dao.mapper.DynamicTaskDao;
import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/15
 */
public interface DynamicTaskService {

	List<ScheduleJob> getDynamicTaskList();

}
