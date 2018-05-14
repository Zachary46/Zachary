package com.zlf.demo.moudle.common.login;


import com.zlf.demo.base.BaseView;
import com.zlf.demo.model.UserModel;

/**
 * @Author: Zachary
 * @Date: 2018/5/11/011
 * @Description:
 */
public interface LoginView extends BaseView {

    void onLoginResult(UserModel userModel);

}
