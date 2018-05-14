package com.zlf.demo.ui.views.navigationview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Project Name：BountyHunter
 * Describe：
 * Created By：yul
 * Modify：yul
 * Modify Time：14:35
 * Remark：
 */
public class NavigationView extends FrameLayout {

    NavigationCompat navigationHelper;

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        navigationHelper = NavigationCompat.init(this, attrs, defStyle, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        navigationHelper = NavigationCompat.init(this, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        navigationHelper.drawPrimaryDark(canvas);
    }
}
