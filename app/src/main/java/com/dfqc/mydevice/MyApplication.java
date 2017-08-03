package com.dfqc.mydevice;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by pan on 2017/6/27.
 * >#
 */

public class MyApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Context getInstance() {
        return mContext;
    }
    /**
          * 分包
          * @param base
          */
//            @Override
//protected void attachBaseContext(Context base) {
// super.attachBaseContext(base);
//    MultiDex.install(this);
// }
}
