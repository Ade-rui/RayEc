package com.ray.rayec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.ray.ray_core.app.Mamoon;
import com.ray.ray_core.net.interceptors.DebugInterceptor;
import com.ray.ray_ec.database.DatabaseManager;
import com.ray.ray_ec.icon.FontEc2Module;
import com.ray.ray_ec.icon.FontEcModule;

/**
 * Created by wrf on 2018/1/17.
 */

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Mamoon.init(this)
                .withApiHost("http://127.0.0.1:8080/restservice/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withIcon(new FontEc2Module())
//                .withInterceptor(new DebugInterceptor("index",R.raw.data1))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("Mamoon")
            .configure();

        Logger.addLogAdapter(new AndroidLogAdapter());
        DatabaseManager.getInstance().init(this);
    }
}
