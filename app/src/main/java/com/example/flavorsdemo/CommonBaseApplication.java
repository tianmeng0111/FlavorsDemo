package com.example.flavorsdemo;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joseph on 2017/2/16.
 */

public class CommonBaseApplication extends MultiDexApplication {

    protected static Context context;
    private static int mainThreadId;
    private static Thread mainThread;
    private static Handler handler;
    public static List<FragmentActivity> activities;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        context = getApplicationContext();
        //主线程id,获取当前方法运行线程id,此方法运行在主线程中,所以获取的是主线程id
        mainThreadId = android.os.Process.myTid();
        //主线程对象
        mainThread = Thread.currentThread();
    }

    public static Context getContext() {
        return context;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static Handler getHandler() {
        return handler;
    }

    //向集合中添加一个activity
    public static void addActivity(FragmentActivity activity) {
        if (activities == null) {
            //如果集合为空  则初始化
            activities = new ArrayList<>();
        }
        //将activity加入到集合中
        activities.add(activity);
    }

    //结束掉所有的Activity
    public static void finishAll() {
        //循环集合,  将所有的activity finish
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static List<FragmentActivity> getActivities(){
        return activities;
    }

    /**
     * 获取当前Activity
     */
    public FragmentActivity currentActivity() {
        FragmentActivity activity = activities.get(activities.size() - 1);
        return activity;
    }

    public static void removeActivity(FragmentActivity activity) {
        //移除已经销毁的Activity
        activities.remove(activity);
    }

    //Xt's Addition


}
