package com.ray.ray_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.choices.divider.DividerItemDecoration;
import com.joanzapata.iconify.widget.IconTextView;
import com.ray.ray_core.delegates.bottom.BottomItemDelegate;
import com.ray.ray_core.net.RestClient;
import com.ray.ray_core.net.callback.ISuccess;
import com.ray.ray_core.ui.recycler.BaseDecoration;
import com.ray.ray_core.ui.recycler.MultipleFields;
import com.ray.ray_core.ui.recycler.MultipleItemEntity;
import com.ray.ray_core.ui.refresh.RefreshHandler;
import com.ray.ray_ec.R;
import com.ray.ray_ec.R2;
import com.ray.ray_ec.main.EcBottomDelegate;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wrf on 2018/2/1.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.layout_ry)
    RecyclerView recyclerView;
    @BindView(R2.id.layout_srf_index)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R2.id.tb_index)
    Toolbar toolbar;
    @BindView(R2.id.tv_icon_scan)
    IconTextView iconScan;
    @BindView(R2.id.et_search)
    AppCompatEditText etSearch;

    private RefreshHandler refreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        refreshHandler = RefreshHandler.create(swipeRefreshLayout,recyclerView,new IndexDataConverter());
    }

    private void initRefreshLayout(){
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        swipeRefreshLayout.setProgressViewOffset(true,120,300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        refreshHandler.firstPage("index.php");
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.app_background),5));
        EcBottomDelegate ecBottomDelegate = getParentDelegate();
        recyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }
}
