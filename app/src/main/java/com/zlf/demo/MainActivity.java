package com.zlf.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.zlf.demo.base.LoadSirActivity;
import com.zlf.demo.model.UserModel;
import com.zlf.demo.moudle.common.bind.BindingActivity;
import com.zlf.demo.moudle.common.login.LoginActivity;
import com.zlf.demo.moudle.common.login.LoginPresenter;
import com.zlf.demo.moudle.common.login.LoginView;


public class MainActivity extends LoadSirActivity<LoginView,LoginPresenter> implements LoginView{

    Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolBar().setTitle("Zachary Demo");
    }

    @Override
    public LoginPresenter createPresneter() {
        return new LoginPresenter();
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    public void initView() {
        button = findViewById(R.id.retryyyyyy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresneter().login("","");
            }
        });
        button2 = findViewById(R.id.retryyyyyy2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BindingActivity.class));
            }
        });
    }

    @Override
    public void onLoginResult(UserModel result) {
        button.setText(result.name+"      "+result.age);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }



    @Override
    public void loadData() {
        getPresneter().login("","");
    }

    @Override
    public void reloadData(View v) {
        getPresneter().login("","");
    }
}
