package com.zlf.demo.moudle.common.bind;

import android.view.View;

import com.zlf.demo.R;
import com.zlf.demo.base.BaseBindingFragment;
import com.zlf.demo.databinding.FragmentBindBinding;
import com.zlf.demo.model.UserModel;
import com.zlf.demo.moudle.common.login.LoginPresenter;
import com.zlf.demo.moudle.common.login.LoginView;

public class BindFragment extends BaseBindingFragment<LoginView,LoginPresenter,FragmentBindBinding> implements LoginView{

    @Override
    public void reloadData(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bind;
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
    protected void loadData(){
        getPresneter().login("","");
    }

    @Override
    public void onLoginResult(UserModel userModel) {
        mBinding.setModel(userModel);
    }
}
