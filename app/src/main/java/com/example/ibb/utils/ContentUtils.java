package com.example.ibb.utils;

/**
 * Created by ASUS on 2018/5/29.
 */

public class ContentUtils {
    public static String buildString(String content) {
        if (content == null || !content.contains(FinalUtils.IMGS_BASE)) return content;
        StringBuilder sb = new StringBuilder();
        String[] split = content.split(FinalUtils.IMGS_BASE + "/");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.contains(".png")) {
                String[] pngs = s.split(".png");
                sb.append(FinalUtils.IMGS_BASE + "/");
                sb.append(pngs[0]);
                sb.append(".png");
                if (pngs.length == 2) sb.append("...").append(pngs[1]);
            } else if (s.contains(".jpg")) {
                String[] jpgs = s.split(".jpg");
                sb.append(FinalUtils.IMGS_BASE + "/");
                sb.append(jpgs[0]);
                sb.append(".jpg");
                if (jpgs.length == 2) sb.append("...").append(jpgs[1]);
            } else if (s.contains(".jpeg")) {
                String[] jpegs = s.split(".jpeg");
                sb.append(FinalUtils.IMGS_BASE + "/");
                sb.append(jpegs[0]);
                sb.append(".jpeg");
                if (jpegs.length == 2) sb.append("...").append(jpegs[1]);
            } else {
                sb.append(s);
            }
            sb.append("...");
        }
        return sb.toString();
    }

    //123...http://image.ibbtech.cn/image/adasdsadasd.jpg...45666
    public static String ellipsizeString(String content) {
        String newContent = buildString(content);
        if (newContent==null) return content;
        if (!newContent.contains(FinalUtils.IMGS_BASE)) return content;
        String[] split = newContent.split("\\.\\.\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String ss = split[i];
            if (!ss.contains(FinalUtils.IMGS_BASE)) {
                sb.append(ss);
            } else {
                sb.append("...");
            }
        }
        return sb.toString();
    }
}


