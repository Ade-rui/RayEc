package com.ray.ray_ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.net.RestClient;
import com.ray.ray_core.net.callback.ISuccess;
import com.ray.ray_core.ui.recycler.MultipleItemEntity;
import com.ray.ray_ec.R;
import com.ray.ray_ec.R2;
import com.ray.ray_ec.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wrf on 2018/2/8.
 */

public class SortListDelegate extends MammonDelegate {

    @BindView(R2.id.layout_ry_sort_menu)
    RecyclerView recyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        recyclerView.setItemAnimator(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list.php")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new SortMenuDataConverter().setJson(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortMenuAdapter adapter = new SortMenuAdapter(data, delegate);
                        recyclerView.setAdapter(adapter);

                    }
                })
                .build()
                .get();
    }

}
