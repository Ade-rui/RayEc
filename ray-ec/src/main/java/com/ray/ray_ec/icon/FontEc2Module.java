package com.ray.ray_ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by wrf on 2018/1/17.
 */

public class FontEc2Module implements IconFontDescriptor{
    @Override
    public String ttfFileName() {
        return "iconfont2.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons2.values();
    }
}
