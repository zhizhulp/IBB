package com.example.ibb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 张凯雅 on 2017/12/27.
 */

public class NumberUtils {
    /**
     * 验证手机号码
     *
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145、155 电信号码段:133、153、180、189
     *
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone){

//        String telRegex="[1][3458]\\d{9}";
//        if (IStringUtils.isEmpty(cellphone)){
//            return false;
//        }else {
//            return  cellphone.matches(telRegex);
//        }
            Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");
            Matcher matcher = pattern.matcher(cellphone);
            return matcher.matches();
    }


}
