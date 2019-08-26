package com.steelkiwi.cropiwa.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yarolegovich on 06.02.2017.
 * https://github.com/yarolegovich
 */
public class CropIwaLog {

    private static final String LOG_TAG = CropIwaLog.class.getSimpleName();

    private static boolean LOG_ON = true;
    private static List<String> breadcrumbs = new ArrayList<>(200);

    public static void breadcrumb(String b) {
        breadcrumbs.add(b);
    }

    public static String getBreadcrumbs() {
        if (breadcrumbs.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (String b : breadcrumbs) {
            builder.append(b).append('\n');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static String d(String formatStr, Object ...args) {
        if (LOG_ON) {
            String message = String.format(formatStr, args);
            Log.d(LOG_TAG, message);
            return message;
        }
        return "";
    }

    public static String e(String message, Throwable e) {
        if (LOG_ON) {
            Log.e(LOG_TAG, message, e);
        }
        return message;
    }

    public static void setEnabled(boolean enabled) {
        LOG_ON = enabled;
    }
}
