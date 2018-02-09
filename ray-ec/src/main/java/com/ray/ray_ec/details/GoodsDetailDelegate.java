package com.ray.ray_ec.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_ec.R;

/**
 * Created by wrf on 2018/2/7.
 */

public class GoodsDetailDelegate extends MammonDelegate{

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Toast.makeText(getContext(), ""+mGoodsId, Toast.LENGTH_SHORT).show();
    }

    public static GoodsDetailDelegate create(int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }
}
