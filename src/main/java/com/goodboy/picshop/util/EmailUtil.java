package com.goodboy.picshop.util;

import java.util.ArrayList;
import java.util.List;

public class EmailUtil {
    /**
     * 解析 备注内容,返回map
     *
     * @param str 参数格式如下： 33@44@55
     * @param regex 分割符号
     * @return list
     */
    @SuppressWarnings("unchecked")
    public static List<String> parseContent(String str, String regex){
//判断null
        if(null==str){
            return null;
        }
        List list = new ArrayList();
//分割为 数组
        String aa[] = str.split(regex);
        if(null!=aa && aa.length>0){
            for(int i=0;i<aa.length;i++){
                list.add(aa[i]);
            }
        }
        return list;
    }
}
