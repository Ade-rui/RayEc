package com.ray.ray_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.ray.ray_core.delegates.MammonDelegate;
import com.ray.ray_core.net.RestClient;
import com.ray.ray_core.net.RestService;
import com.ray.ray_core.net.callback.ISuccess;
import com.ray.ray_core.util.log.MammonLogger;
import com.ray.ray_ec.R;
import com.ray.ray_ec.R2;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wrf on 2018/1/29.
 */

public class SignUpDelegate extends MammonDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword;

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLinkSignIn(){
        start(new SignInDelegate());
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if(checkForm()){
            RestClient.builder()
                    .url("http://localhost:8080/restservice/api/user_profile.php")
                    .params("name",mName.getText().toString())
                    .params("email",mEmail.getText().toString())
                    .params("phone",mPhone.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            MammonLogger.json("mydata",response);
                            SignHander.onSignUp(response,mISignListener);
                        }
                    })
                    .build()
                    .post();;


            //Toast.makeText(getContext(), "验证成功", Toast.LENGTH_SHORT).show();
        }
    }

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    private boolean checkForm(){
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if(name.isEmpty()){
            mName.setError("请输入姓名");
            isPass = false;
        }else{
            mName.setError(null); //必须得置为null 要不错误提示不消失
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("邮箱格式不正确");
            isPass = false;
        }else {
            mEmail.setError(null);
        }

        if(phone.isEmpty() || phone.length() != 11){
            mPhone.setError("请填写正确手机号码");
            isPass = false;
        }else {
            mPhone.setError(null);
        }

        if(password.isEmpty() || password.length() > 6){
            mPassword.setError("请填写正确的密码，不少于6位");
            isPass = false;
        }else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() > 6 || !rePassword.equals(password)) {
            mPassword.setError("密码验证错误");
            isPass = false;
        }else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
