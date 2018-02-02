package com.ray.ray_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ray.ray_core.app.AccountManager;
import com.ray.ray_core.app.IUserChecker;
import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.ui.launcher.LauncherHolderCreator;
import com.ray.ray_core.ui.launcher.ScrollLauncherTag;
import com.ray.ray_core.util.storage.MamoonPreference;
import com.ray.ray_ec.R;

import java.util.ArrayList;

/**
 * Created by wrf on 2018/1/25.
 */

public class LauncherScrollDelegate extends MammonDelegate {

    private ConvenientBanner<Integer> banner;
    private final ArrayList<Integer> IMAGES = new ArrayList<>();

    private ILauncherListener mILauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        banner = new ConvenientBanner<Integer>(getActivity());
        return banner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    private void initBanner(){

        IMAGES.add(R.mipmap.launcher_01);
        IMAGES.add(R.mipmap.launcher_02);
        IMAGES.add(R.mipmap.launcher_03);
        IMAGES.add(R.mipmap.launcher_04);
        IMAGES.add(R.mipmap.launcher_05);
        banner.setPages(new LauncherHolderCreator(),IMAGES)
                //数组第一个为正常时候的显示，数组第二个为选中的显示
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(onItemClickListener)
                //不可以循环
                .setCanLoop(false);
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(position == IMAGES.size() - 1){
                MamoonPreference.setAppFlag(ScrollLauncherTag.IS_FIRST_LAUNCHER_APP.name(),true);
                //检查用户是否登录
                AccountManager.checkAccount(new IUserChecker() {
                    @Override
                    public void onSignIn() {
                        if(mILauncherListener != null){
                            mILauncherListener.onLauncherFinsih(OnLauncherFinishTag.Sign);
                        }
                    }

                    @Override
                    public void onNotSignIn() {
                        if(mILauncherListener != null){
                            mILauncherListener.onLauncherFinsih(OnLauncherFinishTag.NOT_SIGN);
                        }
                    }
                });
            }
        }
    };
}
