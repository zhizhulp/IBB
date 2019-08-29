package com.example.ibb.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * 通用工具类
 *
 * @author gaobingbing
 */
public class CommonUtils {

    /** 上次点击的时间 **/
    private static long lastClickTime;

    /**
     * 防暴力点击
     *
     * @return ture:多次点击，不处理
     */
    public static boolean isFastDoubleClick(){
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;

        if(0 < timeD && timeD < 500) {
            lastClickTime = time;
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean hasMore(long curPage,long maxPage){
        return curPage < maxPage;
    }

    /**
     * <pre>
     * 控制EditText输入小数点之后的位数
     * editText.setFilters(new InputFilter[] { MilesUtils.getInputFilter(2,8) });
     * </pre>
     *
     * @param digLength 小数点之后保留位数
     * @param intLength 小数点之前保留位数
     */
    public static InputFilter getInputFilter(final int digLength, final int intLength){
        // 设置小数位数控制
        InputFilter lengthfilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend){
                // 删除等特殊字符，直接返回
                if("".equals(source.toString())) {
                    return null;
                } else {
                    String dValue = dest.toString();
                    if(dValue.contains(".")) {// 有小数点
                        int dotIndex = dValue.indexOf(".");

                        // 输入的数字在小数点前面
                        if(dstart <= dotIndex) {
                            if(dValue.substring(0,dotIndex).length() > intLength - 1) {
                                return source.subSequence(0,0);
                            }
                            return null;
                        } else {
                            String[] splitArray = dValue.split("\\.");

                            if(splitArray.length > 1) {
                                String dotValue = splitArray[1];
                                int diff = dotValue.length() + 1 - digLength;
                                if(diff > 0) {
                                    return source.subSequence(start,end - diff);
                                }
                            }
                        }
                    } else {// 无小数点
                        if(TextUtils.equals(".",source)) {// 输入小数点，不处理
                            return null;
                        }
                        if(dValue.length() > intLength - 1) {
                            return source.subSequence(0,0);
                        }
                    }
                    return null;
                }
            }

        };
        return lengthfilter;
    }

    /**
     * 判断是否为空，为空，返回""
     *
     * @param str 传入的串
     * @return 传入null时返回""
     */
    public static String getText(String str){
        if(str == null || str.length() == 0) {
            return "";
        }
        return str;
    }

    /**
     * 获取组件值，去空格
     *
     * @param textView 可传值AutoCompleteTextView、EditText、TextView
     * @return String或者""
     */
    public static String getText(TextView textView){
        String str = "";
        if(textView != null) {
            str = textView.getText().toString().trim();
        }
        return str;
    }

}
