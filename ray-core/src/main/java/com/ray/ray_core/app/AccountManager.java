package com.ray.ray_core.app;

import com.ray.ray_core.util.storage.MamoonPreference;

/**
 * Created by wrf on 2018/1/30.
 * 管理用户状态
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG;
    }

    public static void setSignState(boolean state){
        MamoonPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    public static boolean isSignIn(){
        return MamoonPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker iUserChecker){
        if(isSignIn()){
            iUserChecker.onSignIn();
        }else {
            iUserChecker.onNotSignIn();
        }
    }
}
