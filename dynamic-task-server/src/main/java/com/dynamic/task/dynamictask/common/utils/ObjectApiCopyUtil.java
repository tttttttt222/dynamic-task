package com.dynamic.task.dynamictask.common.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: ryw
 * Date:   2018/8/3 11:17
 */
public class ObjectApiCopyUtil {


    /**
     *
     * @param source
     * @param tClazz
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T copyObject(S source, Class<T> tClazz, TurnMethodNameInterface turnMethodName) {
        if (source == null || tClazz == null)
            return null;
        try {
            Class<?> sClass = source.getClass();
            T t = tClazz.newInstance();

            Method[] tMethods = tClazz.getDeclaredMethods();
            for (Method tMethod : tMethods) {
                tMethod.setAccessible(true);
                String tMethodName = tMethod.getName();
                if (tMethodName.contains("set")) {
                    String sMethodName;
                    if(turnMethodName == null){
                        sMethodName = new TurnNormalMethodName().turnSMethodNameFromMap(tMethodName);
                    }else{
                        sMethodName = turnMethodName.turnSMethodNameFromMap(tMethodName);
                    }
                    try {
                        Method sMethod = sClass.getDeclaredMethod(sMethodName);
                        sMethod.setAccessible(true);
                        Object res = sMethod.invoke(source);
                        String tMethodType = tMethod.getParameterTypes()[0].getName();
                        String sMethodType = sMethod.getReturnType().getName();
                        if (tMethodType.equals(sMethodType)) {
                            tMethod.invoke(t, res);
                        } else {
                            tMethod.invoke(t, String.valueOf(res));
                        }

                    } catch (Exception e) {

                    }
                }

            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    public static <S, T> List<T> copyObjects(List<S> source, Class<T> tClazz, TurnMethodNameInterface turnMethodName) {
        ArrayList<T> list = new ArrayList<T>();
        for (S s : source) {
            list.add(copyObject(s, tClazz, turnMethodName));
        }
        return list;
    }
}
