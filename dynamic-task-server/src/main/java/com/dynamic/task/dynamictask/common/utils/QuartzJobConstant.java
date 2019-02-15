package com.dynamic.task.dynamictask.common.utils;

/**
 * 项目名称:dynamic-task 描述: 创建人:ryw 创建时间:2019/2/15
 */
public class QuartzJobConstant {

	/**调度中*/
	public static final int STATE_NORMAL =1;
	/**挂起*/
	public static final int STATE_PAUSED =2;
	/**删除*/
	public static final int STATE_NONE =0;
	/**任务出错*/
	public static final int STATE_ERROR=3;
	/**任务已锁*/
	public static final int STATE_BLOCKED = 4;
	/**已完成*/
	public static final int STATE_COMPLETE = 5;

}
