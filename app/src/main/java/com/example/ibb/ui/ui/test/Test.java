package com.example.ibb.ui.ui.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by ASUS on 2018/5/15.
 */

public class Test {
    public static void main(String[] args) {
       System.out.println ("\n".length());
    }

    public static String getTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return sdf.format(new Date(time * 1000));
    }

    public static String getHttpUrl(String content) {
        int indexOf = content.indexOf("http://");
        if (indexOf == -1) {
            return null;
        } else {
            int indexOfJpg = content.indexOf("jpg");
            if (indexOfJpg == -1) {
                return null;
            } else {
                return content.substring(indexOf, indexOfJpg + "jpg".length());
            }
        }
    }

    public static String buildString(String content) {
        StringBuilder sb=new StringBuilder();
        String[] split = content.split("http://image.ibbtech.cn/");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.contains(".png")) {
                String[] pngs = s.split(".png");
                sb.append("http://image.ibbtech.cn/");
                sb.append(pngs[0]);
                sb.append(".png");
                if(pngs.length==2) sb.append("\n").append(pngs[1]);
            } else if (s.contains(".jpg")) {
                String[] jpgs = s.split(".jpg");
                sb.append("http://image.ibbtech.cn/");
                sb.append(jpgs[0]);
                sb.append(".jpg");
                if(jpgs.length==2) sb.append("\n").append(jpgs[1]);
            } else if (s.contains(".jpeg")) {
                String[] jpegs = s.split(".jpeg");
                sb.append("http://image.ibbtech.cn/");
                sb.append(jpegs[0]);
                sb.append(".jpeg");
                if(jpegs.length==2) sb.append("\n").append(jpegs[1]);
            } else {
                sb.append(s);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
