package com.ray.ray_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.net.RestClient;
import com.ray.ray_core.net.callback.ISuccess;
import com.ray.ray_core.util.log.MammonLogger;
import com.ray.ray_ec.R;
import com.ray.ray_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wrf on 2018/1/29.
 */

public class SignInDelegate extends MammonDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if(checkForm()){
            RestClient.builder()
                    .url("http://localhost:8080/restservice/api/user_profile.php")
                    .params("email",mEmail.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            MammonLogger.json("mydata",response);
                            SignHander.onSignIn(response,mISignListener);
                        }
                    })
                    .build()
                    .post();;
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLinkSignUp(){
        start(new SignUpDelegate());
    }

    private boolean checkForm(){
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("邮箱格式不正确");
            isPass = false;
        }else {
            mEmail.setError(null);
        }

        if(password.isEmpty() || password.length() > 6){
            mPassword.setError("请填写正确的密码，不少于6位");
            isPass = false;
        }else {
            mPassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
