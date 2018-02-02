package com.ray.rayec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.ray.ray_core.activities.PorxyActivity;
import com.ray.ray_core.app.Mamoon;
import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_ec.launcher.ILauncherListener;
import com.ray.ray_ec.launcher.LauncherDelegate;
import com.ray.ray_ec.launcher.OnLauncherFinishTag;
import com.ray.ray_ec.main.EcBottomDelegate;
import com.ray.ray_ec.sign.ISignListener;
import com.ray.ray_ec.sign.SignInDelegate;
import com.ray.ray_ec.sign.SignUpDelegate;

public class ExampleActivity extends PorxyActivity implements ISignListener,ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null) {
            supportActionBar.hide();
        }
        Mamoon.getConfigurator().withActivity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public MammonDelegate setRootDelegate() {
        return new LauncherDelegate();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "SignIn", Toast.LENGTH_SHORT).show();
        startWithPop(new ExampleDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "SignUp", Toast.LENGTH_SHORT).show();
        startWithPop(new ExampleDelegate());
    }

    @Override
    public void onLauncherFinsih(OnLauncherFinishTag onLauncherFinishTag) {
        switch (onLauncherFinishTag){
            case Sign:
                Toast.makeText(this, "启动结束了，用户登录了", Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGN:
                Toast.makeText(this, "启动结束了，用户没有登录", Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
