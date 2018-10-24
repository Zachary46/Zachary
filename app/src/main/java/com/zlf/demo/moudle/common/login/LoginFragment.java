package com.zlf.demo.moudle.common.login;

import android.view.View;
import android.widget.Button;
import com.zlf.demo.R;
import com.zlf.demo.base.LoadSirFragment;
import com.zlf.demo.model.UserModel;

/**
 * @Author: Zachary
 * @Date: 2018/5/11/011
 * @Description:
 */
public class LoginFragment extends LoadSirFragment<LoginView, LoginPresenter> implements LoginView {


    private Button button;

    @Override
    public LoginPresenter createPresneter() {
        return new LoginPresenter();
    }

    @Override
    public LoginView createView() {
        return this;
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
    protected void initView(View view) {
        button = view.findViewById(R.id.retryyyyyy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresneter().login("","");
            }
        });
    }

    @Override
    protected void loadData() {
        getPresneter().login("","");
    }

    @Override
    public void reloadData(View v) {
        getPresneter().login("","");
    }
}
