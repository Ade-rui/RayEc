package com.ray.rayec.example.generators;

import com.example.annotations.EntryGenerator;
import com.ray.ray_core.wechat.templates.WXEntryTemplate;

/**
 * Created by wrf on 2018/1/31.
 */

@EntryGenerator(

        packageName = "com.ray.rayec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
