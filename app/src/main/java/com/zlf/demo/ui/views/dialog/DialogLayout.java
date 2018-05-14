package com.zlf.demo.ui.views.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlf.demo.R;


/**
 * Project Name：BountyHunter
 * Describe：
 * Created By：yul
 * Modify：yul
 * Modify Time：16:20
 * Remark：
 */
public class DialogLayout extends ProgressDialog {
    private RotateAnimation mRotationAnim;
    private ImageView ivLoading;
    private TextView tvLoading;


    public DialogLayout(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_progress);
        setProgressStyle(R.style.dialog);
        initialize();
    }

    private void initialize() {

        ivLoading = (ImageView) findViewById(R.id.ivLoading);
        tvLoading = (TextView) findViewById(R.id.tvLoading);
        mRotationAnim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotationAnim.setDuration(750);
        mRotationAnim.setInterpolator(new LinearInterpolator());
        mRotationAnim.setRepeatCount(Animation.INFINITE);
        mRotationAnim.setRepeatMode(Animation.INFINITE);
        setCanceledOnTouchOutside(false);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void show() {
        super.show();
        if(null!=mRotationAnim){
            ivLoading.setAnimation(mRotationAnim);
            ivLoading.startAnimation(mRotationAnim);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(mRotationAnim!=null){
            ivLoading.clearAnimation();
        }
    }
}
