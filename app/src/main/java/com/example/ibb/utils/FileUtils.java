package com.example.ibb.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by ASUS on 2018/5/18.
 */

public class FileUtils {
    public static File getAppFile(Context context, String type) {
        return context.getExternalFilesDir(type);
    }
}
