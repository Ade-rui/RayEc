package com.ray.ray_ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ray.ray_core.delegates.bottom.BottomItemDelegate;
import com.ray.ray_core.delegates.web.WebDelegate;
import com.ray.ray_core.delegates.web.WebDelegateImpl;
import com.ray.ray_core.delegates.web.event.Event;
import com.ray.ray_core.delegates.web.event.EventManager;
import com.ray.ray_ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl webDelegate = WebDelegateImpl.create("index2.html");
        EventManager.getInstance().addEvent("test", new Event() {
            @Override
            public String execute(String params) {
                Toast.makeText(context, params, Toast.LENGTH_SHORT).show();
                return null;
            }
        });
        webDelegate.setTopDelegate(this.getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.container_discover,webDelegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
