package com.ray.ray_ec.main;

import android.graphics.Color;

import com.ray.ray_core.delegates.bottom.BaseBottomDelegate;
import com.ray.ray_core.delegates.bottom.BottomItemDelegate;
import com.ray.ray_core.delegates.bottom.BottomTabBean;
import com.ray.ray_core.delegates.bottom.ItemBuilder;
import com.ray.ray_ec.main.discover.DisconverDelegate;
import com.ray.ray_ec.main.index.IndexDelegate;
import com.ray.ray_ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by wrf on 2018/2/1.
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    protected LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder itemBuilder) {
        LinkedHashMap<BottomTabBean, BottomItemDelegate> delegates = new LinkedHashMap<>();
        delegates.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        delegates.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        delegates.put(new BottomTabBean("{fa-compass}","发现"),new DisconverDelegate());
        delegates.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShopCarDelegate());
        delegates.put(new BottomTabBean("{fa-user}","我的"),new PersonalDelegate());
        return itemBuilder.addItems(delegates).build();
    }

    @Override
    protected int setDelegateIndex() {
        return 0;
    }

    @Override
    protected int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

}
