package com.ray.ray_ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by wrf on 2018/2/8.
 */

public class SectionBean extends SectionEntity<SectionContentItemEntity>{

    public boolean isMore;
    public int id = -1;

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

}
