package com.ray.ray_ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.ui.recycler.MultipleFields;
import com.ray.ray_core.ui.recycler.MultipleItemEntity;
import com.ray.ray_ec.details.GoodsDetailDelegate;

/**
 * Created by wrf on 2018/2/7.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final MammonDelegate DELEGATE;

    private IndexItemClickListener(MammonDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(MammonDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
