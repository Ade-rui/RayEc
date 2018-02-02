package com.ray.rayec.example.generators;

import com.example.annotations.AppRegisterGenerator;
import com.ray.ray_core.wechat.templates.WXAppRegisterTemplate;

/**
 * Created by wrf on 2018/1/31.
 */

@AppRegisterGenerator(

        packageName = "com.ray.rayec.example",
        appRegisterTemplate = WXAppRegisterTemplate.class

)
public interface AppRegister {
}
