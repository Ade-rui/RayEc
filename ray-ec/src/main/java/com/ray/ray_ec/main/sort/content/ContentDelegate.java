package com.ray.ray_ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.net.RestClient;
import com.ray.ray_core.net.callback.ISuccess;
import com.ray.ray_core.ui.recycler.BaseDecoration;
import com.ray.ray_ec.R;
import com.ray.ray_ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wrf on 2018/2/8.
 */

public class ContentDelegate extends MammonDelegate {

    private static final String CONTENT_ID = "content_id";
    private int contentId = -1;
    private List<SectionBean> list;

    @BindView(R2.id.layout_ry_sort_content)
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            contentId = args.getInt(CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        RestClient.builder()
                .url("sort_content_list.php?contentId=" + contentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        list = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content,
                                        R.layout.item_section_header, list);
                        recyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .get();
    }


}
