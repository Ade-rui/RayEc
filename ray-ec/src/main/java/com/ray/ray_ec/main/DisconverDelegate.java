package com.ray.ray_ec.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ray.ray_core.delegates.bottom.BottomItemDelegate;
import com.ray.ray_ec.R;

/**
 * Created by wrf on 2018/2/1.
 */

public class DisconverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
