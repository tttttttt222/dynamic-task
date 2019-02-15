package com.dynamic.task.dynamictask.common.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称:baofoo-fopay-api
 * 描述:
 * 创建人:ryw
 * 创建时间:2018/8/3
 */
public class TurnMapMethodName implements TurnMethodNameInterface {

    private  final List<String> TYPE_STRING = Arrays
			.asList("Trans_no", "Trans_money", "To_acc_name", "To_acc_no", "To_bank_name", "To_pro_name", "To_city_name", "To_acc_dept", "Trans_summary",
            "Trans_batchid", "Trans_orderid");

    private  final List<String> TYPEUPSTRING = Arrays
			.asList("TransNo", "TransMoney", "TransAccName", "TransAccNo", "CardBankName", "CardProName", "CardCityName", "CardAccDept", "TransSummary",
            "TransBatchId", "TransOrderId");


    public String turnSMethodNameFromMap(String tMethodName) {
        String sMethodName = tMethodName;
        int length = sMethodName.length();
        if (sMethodName.contains("_")) {
            int index = TYPE_STRING.indexOf(sMethodName.substring(3, length));
            if (index != -1) {
                sMethodName = "get" + TYPEUPSTRING.get(index);
            } else {   //普通转换
                int i = sMethodName.indexOf("_");
                while (i != -1) {
                    sMethodName = sMethodName.substring(0, i) + sMethodName.substring(i + 1, i + 2).toUpperCase() + sMethodName.substring(i + 2, sMethodName.length());
                    i = sMethodName.indexOf("_");
                }
                sMethodName = "get" + sMethodName.substring(3, sMethodName.length());
            }

        } else {
            int index = TYPEUPSTRING.indexOf(sMethodName.substring(3, length));
            if (index != -1) {
                sMethodName = "get" + TYPE_STRING.get(index);
            } else {
                char[] tmp = sMethodName.toCharArray();
                for (int i = 0; i < tmp.length; i++) {
                    if (tmp[i] >= 65 && tmp[i] <= 90) {
                        sMethodName = sMethodName.substring(0, i) + "_" + sMethodName.substring(i, i + 1).toLowerCase() + sMethodName.substring(i + 1, sMethodName.length());
                        tmp = sMethodName.toCharArray();
                    }
                }
                sMethodName = "get" + sMethodName.substring(3, sMethodName.length());
            }
        }
        return sMethodName;
    }


}
