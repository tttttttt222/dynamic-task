package com.dynamic.task.dynamictask.common.utils;

/**
 * 项目名称:baofoo-fopay-api
 * 描述:
 * 创建人:ryw
 * 创建时间:2018/8/3
 */
public class TurnNormalMethodName implements TurnMethodNameInterface{


    @Override
    public String turnSMethodNameFromMap(String tMethodName) {
        return "get"+tMethodName.substring(3,tMethodName.length());
    }
}
