package com.ray.ray_ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ray.ray_core.delegates.bottom.BottomItemDelegate;
import com.ray.ray_ec.R;
import com.ray.ray_ec.main.sort.content.ContentDelegate;
import com.ray.ray_ec.main.sort.list.SortListDelegate;

/**
 * Created by wrf on 2018/2/1.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final SortListDelegate listDelegate = new SortListDelegate();
        getSupportDelegate().loadRootFragment(R.id.container_sort_list, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.container_sort_content, ContentDelegate.newInstance(1));
    }
}
