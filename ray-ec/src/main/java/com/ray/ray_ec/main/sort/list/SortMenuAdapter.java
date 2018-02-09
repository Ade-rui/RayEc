package com.ray.ray_ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.ui.recycler.ItemType;
import com.ray.ray_core.ui.recycler.MultipleFields;
import com.ray.ray_core.ui.recycler.MultipleItemEntity;
import com.ray.ray_core.ui.recycler.MultipleRecyclerAdapter;
import com.ray.ray_core.ui.recycler.MultipleViewHolder;
import com.ray.ray_ec.R;
import com.ray.ray_ec.main.sort.content.ContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by wrf on 2018/2/8.
 */

public class SortMenuAdapter extends MultipleRecyclerAdapter {

    private int prePosition;
    private MammonDelegate delegate;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SortMenuAdapter(List<MultipleItemEntity> data,MammonDelegate delegate) {
        super(data);
        this.delegate = delegate;

        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_sort_menu);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, final MultipleItemEntity item) {
        super.convert(helper, item);

        switch (item.getItemType()){
            case ItemType.VERTICAL_MENU_LIST:
                final String name = item.getField(MultipleFields.TEXT);
                final boolean isSelected = item.getField(MultipleFields.TAG);
                TextView tvName = helper.getView(R.id.tv_sort_menu_name);
                View line = helper.getView(R.id.line_left_selected);
                View itemView = helper.itemView;

                if(isSelected){
                    line.setVisibility(View.VISIBLE);
                    line.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_primary));
                    tvName.setTextColor(ContextCompat.getColor(mContext,R.color.app_primary));
                    itemView.setBackgroundColor(Color.WHITE);
                }else{
                    line.setVisibility(View.INVISIBLE);
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));
                    tvName.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                }

                helper.setText(R.id.tv_sort_menu_name,name);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = helper.getAdapterPosition();
                        if(prePosition != currentPosition){
                            //还原上一下
                            getData().get(prePosition).setField(MultipleFields.TAG,false);
                            notifyItemChanged(prePosition);

                            //更新选中的item
                            item.setField(MultipleFields.TAG,true);
                            notifyItemChanged(currentPosition);

                            prePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                break;

            default:
                break;
        }

    }

    /*
        显示对应的content
     */
    private void showContent(int contentId) {
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate) {
        final MammonDelegate contentDelegate =
                SupportHelper.findFragment(SortMenuAdapter.this.delegate.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
