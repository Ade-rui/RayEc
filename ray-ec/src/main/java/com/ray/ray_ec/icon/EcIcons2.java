package com.ray.ray_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by wrf on 2018/1/17.
 */

public enum EcIcons2 implements Icon {
    icon_scan('\ue649');

    private char character;

    EcIcons2(char character){
        this.character = character;
    }

    public String key() {
        return this.name().replace('_', '-');
    }

    public char character() {
        return this.character;
    }
}
