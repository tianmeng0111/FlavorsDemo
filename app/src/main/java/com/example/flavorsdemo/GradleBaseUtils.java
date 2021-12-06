package com.example.flavorsdemo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import static com.example.flavorsdemo.CommonBaseApplication.getContext;

public class GradleBaseUtils {
    private static final String TAG = "GradleBaseUtils";

    /**
     * 获取不同的渠道名
     */
    public static String getGrandValue(String key) {
        String utmSource = "";
        try {
            // 获取 <application> 中的 <meta-data>
            final ApplicationInfo appInfo = getContext().getPackageManager().getApplicationInfo(
                    getContext().getPackageName(),
                    PackageManager.GET_META_DATA);
            // 获取渠道名称 (utm_source)
            utmSource = appInfo.metaData.getString(key);
        } catch (Exception e) {
            Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName() + "--Exception：" + e);
        }
        return utmSource;
    }

    public static boolean getGrandBooleanValue(String key) {
        boolean utmSource = false;
        try {
            // 获取 <application> 中的 <meta-data>
            final ApplicationInfo appInfo = getContext().getPackageManager()
                    .getApplicationInfo(getContext().getPackageName(),
                            PackageManager.GET_META_DATA);
            // 获取渠道名称 (utm_source)
            utmSource = appInfo.metaData.getBoolean(key);
        } catch (Exception e) {
            Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName() + "--Exception：" + e);
        }
        return utmSource;
    }

    public static String getAppName() {
        return getGrandValue("k_appName");
    }

    public static String getPKGNAME() {
        return getGrandValue("PKGNAME");
    }

}
