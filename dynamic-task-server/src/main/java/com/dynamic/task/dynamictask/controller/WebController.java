package com.dynamic.task.dynamictask.controller;

import com.dynamic.task.dynamictask.dao.model.ScheduleJob;
import com.dynamic.task.dynamictask.service.DynamicTaskService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 项目名称:demo 描述: 创建人:ryw 创建时间:2019/1/14
 */

@Controller
@RequestMapping
@Slf4j
public class WebController {

	@Autowired
	private DynamicTaskService dynamicTaskService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		List<ScheduleJob> dynamicTaskList = dynamicTaskService.getDynamicTaskList();
		map.addAttribute("dynamicTaskList", dynamicTaskList);
		return "index";
	}

}
