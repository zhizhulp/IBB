package com.example.ibb.utils;

/**
 * Created by Administrator on 2018/1/3.
 */

public class IStringUtils {


    /**
     * 判断单个字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {

        if (str == null || str.length() == 0 || str.equals(""))
            return true;
        else
            return false;
    }

    /**
     * 判断多个字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String... str) {
        for(String ostr : str){
            if (ostr == null || ostr.length() == 0 || ostr.equals(""))
                return true;
        }
        return false;
    }







}
