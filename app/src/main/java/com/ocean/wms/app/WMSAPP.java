package com.ocean.wms.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by James on 2020/1/7.
 */
public class WMSAPP extends Application { private static Context application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getApplication(){
        return application;
    }

}
