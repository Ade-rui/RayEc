package com.ray.ray_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.ray.ray_core.app.AccountManager;
import com.ray.ray_core.app.IUserChecker;
import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.ui.launcher.ScrollLauncherTag;
import com.ray.ray_core.util.storage.MamoonPreference;
import com.ray.ray_core.util.timer.BaseTimerTask;
import com.ray.ray_core.util.timer.ITimerListener;
import com.ray.ray_ec.R;
import com.ray.ray_ec.R2;

import java.text.MessageFormat;
import java.util.EventListener;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Created by wrf on 2018/1/25.
 */

public class LauncherDelegate extends MammonDelegate implements ITimerListener {

    @BindView(R2.id.tv_timer)
    AppCompatTextView tvTimer;

    private Timer timer;

    private int count = 5;;

    private ILauncherListener mILauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @OnClick(R2.id.tv_timer)
    void onClickTimerView(){
        if(timer!=null) {
            timer.cancel();
            timer = null;
            isShowScroll();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
    }

    private void initView() {
        timer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        timer.schedule(task,0,1000);
    }


    @Override
    public void onTimer() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(tvTimer!=null){
                    tvTimer.setText(MessageFormat.format("跳过\n{0}s",count));
                    count--;
                    if(count<0){
                        timer.cancel();
                        timer = null;
                        isShowScroll();
                    }
                }
            }
        });
    }

    private void isShowScroll() {
        if(!MamoonPreference.getAppFlag(ScrollLauncherTag.IS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        }else {
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
}
