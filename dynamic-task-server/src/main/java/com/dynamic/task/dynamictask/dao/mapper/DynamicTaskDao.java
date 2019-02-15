package com.dynamic.task.dynamictask.dao.mapper;

import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import java.util.List;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/14
 */
public interface DynamicTaskDao {

	List<ScheduleJob> getAll();

}
