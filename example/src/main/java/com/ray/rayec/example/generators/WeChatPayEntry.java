package com.ray.rayec.example.generators;

import com.example.annotations.PayEntryGenerator;
import com.ray.ray_core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by wrf on 2018/1/31.
 */

@PayEntryGenerator(

        packageName = "com.ray.rayec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
