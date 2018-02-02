package com.ray.rayec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.net.RestClient;
import com.ray.ray_core.net.callback.IError;
import com.ray.ray_core.net.callback.IFailure;
import com.ray.ray_core.net.callback.ISuccess;

/**
 * Created by wrf on 2018/1/18.
 */

public class ExampleDelegate extends MammonDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        test();
    }

    private void test(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i("mydata",response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String errorMessage) {
                        Log.i("mydata",errorMessage);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.i("mydata","onFailure");
                    }
                })
                .build()
                .get();
    }
}
