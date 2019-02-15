package com.dynamic.task.dynamictask.common.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 * @author shipotian
 * @since 2017/5/4
 */
public class ObjectCopyUtil<S, T> {


    public void copyObject(S source, T target) {
        if (source == null || target == null)
            return;
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> copyObjects(List<S> source, Class target) {
        List<T> list = new ArrayList<T>();
        if (source == null)
            return list;
        try {
            for (Object obj : source) {
                T nObj = (T) target.newInstance();

                BeanUtils.copyProperties(obj, nObj);
                list.add(nObj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}
