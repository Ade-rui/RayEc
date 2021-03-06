package com.ray.ray_core.activities;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.ray.ray_core.R;
import com.ray.ray_core.app.Mamoon;
import com.ray.ray_core.delegates.MammonDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by wrf on 2018/1/18.
 */

public abstract class PorxyActivity extends SupportActivity {

    public abstract MammonDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
