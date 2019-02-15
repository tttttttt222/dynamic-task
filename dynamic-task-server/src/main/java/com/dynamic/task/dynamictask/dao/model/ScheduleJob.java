package com.dynamic.task.dynamictask.dao.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/14
 */
@Getter
@Setter
@ToString
public class ScheduleJob {

	public static final int STATUS_RUNNING = 1;
	public static final int STATUS_NOT_RUNNING = 0;
	public static final int CONCURRENT_IS = 1;
	public static final int CONCURRENT_NOT = 0;
	private Long jobId;

	private Date createTime;

	private Date updateTime;
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 任务分组
	 */
	private String jobGroup;
	/**
	 * 任务状态 是否启动任务
	 */
	private int jobStatus;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
	private String beanClass;
	/**
	 * 任务是否有状态
	 */
	private int isConcurrent;
	/**
	 * spring bean
	 */
	private String springId;
	/**
	 * 任务调用的方法名
	 */
	private String methodName;

}
