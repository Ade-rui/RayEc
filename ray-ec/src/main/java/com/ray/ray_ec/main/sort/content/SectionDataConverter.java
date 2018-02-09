package com.ray.ray_ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2018/2/8.
 */

public class SectionDataConverter {

    public List<SectionBean> convert(String json){
        final List<SectionBean> dataList = new ArrayList<>();

        JSONArray datas = JSON.parseObject(json).getJSONArray("data");
        int size = datas.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = datas.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.id = id;
            sectionTitleBean.isMore = true;
            dataList.add(sectionTitleBean);

            final JSONArray goods = data.getJSONArray("goods");
            //商品内容循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.mGoodsId = goodsId;
                itemEntity.mGoodsName = goodsName;
                itemEntity.mGoodsThumb = goodsThumb;
                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }
        }

        return dataList;
    }

}
