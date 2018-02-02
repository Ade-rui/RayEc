package com.ray.ray_ec.sign;

import com.alibaba.fastjson.JSONObject;
import com.ray.ray_core.app.AccountManager;
import com.ray.ray_ec.database.DatabaseManager;
import com.ray.ray_ec.database.UserProfile;

/**
 * Created by wrf on 2018/1/30.
 */

public class SignHander {

    public static void onSignUp(String response,ISignListener iSignListener){
        final JSONObject object = JSONObject.parseObject(response).getJSONObject("data");
        Long userId = object.getLong("userId");
        String name = object.getString("name");
        String avatar = object.getString("avatar");
        String gender = object.getString("gender");
        String address = object.getString("address");

        UserProfile user = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insertOrReplace(user);

        AccountManager.setSignState(true);
        if(iSignListener!=null){
            iSignListener.onSignUpSuccess();
        }
    }

    public static void onSignIn(String response,ISignListener iSignListener){
        final JSONObject object = JSONObject.parseObject(response).getJSONObject("data");
        Long userId = object.getLong("userId");
        String name = object.getString("name");
        String avatar = object.getString("avatar");
        String gender = object.getString("gender");
        String address = object.getString("address");

        UserProfile user = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insertOrReplace(user);

        AccountManager.setSignState(true);
        if(iSignListener!=null){
            iSignListener.onSignInSuccess();
        }
    }

}
