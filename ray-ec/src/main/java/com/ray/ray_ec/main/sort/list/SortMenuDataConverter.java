package com.ray.ray_ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ray.ray_core.ui.recycler.DataConverter;
import com.ray.ray_core.ui.recycler.ItemType;
import com.ray.ray_core.ui.recycler.MultipleFields;
import com.ray.ray_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by wrf on 2018/2/8.
 */

public class SortMenuDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> list = new ArrayList<>();

        JSONArray jsonArray = ((JSONObject) JSON.parseObject(jsonData()).get("data")).getJSONArray("list");

        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer id = jsonObject.getInteger("id");
            String name = jsonObject.getString("name");

            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,name)
                    .setField(MultipleFields.TAG,false)
                    .build();
            list.add(entity);

            //默认选中一个
            list.get(0).setField(MultipleFields.TAG,true);
        }

        return list;
    }
}
