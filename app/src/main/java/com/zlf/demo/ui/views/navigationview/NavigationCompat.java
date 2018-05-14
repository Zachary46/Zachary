package com.zlf.demo.ui.views.navigationview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.zlf.demo.R;


/**
 * Project Name：BountyHunter
 * Describe：
 * Created By：yul
 * Modify：yul
 * Modify Time：14:36
 * Remark：
 */
public class NavigationCompat {
    public static boolean LOW_KITKAT = Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT/* && Build.VERSION.SDK_INT
    < Build.VERSION_CODES.LOLLIPOP*/;

    private int width;
    private int height;
    private Paint paint;
    private static boolean drawPrimaryDark;

    public static NavigationCompat init(View view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        NavigationCompat compat = new NavigationCompat();
        Context context = view.getContext();
        TypedArray a = null;
        try {
            a = context.obtainStyledAttributes(attrs, R.styleable.NavigationView, defStyleAttr, defStyleRes);
            drawPrimaryDark = a.getBoolean(R.styleable.NavigationView_drawPrimaryDark, false);
            if (drawPrimaryDark) {
                compat.width = view.getResources().getDisplayMetrics().widthPixels;
                compat.height = getStatusHeight(context);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setColor(a.getColor(R.styleable.NavigationView_colorPrimaryDark, ContextCompat.getColor(context, R
                        .color.colorPrimaryDark)));
                compat.paint = paint;
            }
            int paddingTop = view.getPaddingTop();
            int dp24 = getStatusHeight(context);
            if (dp24 == -1) {
                dp24 = dpToPx(view.getResources(), 24);
            }
            if (LOW_KITKAT) {
                if (paddingTop >= dp24) {
                    view.setPadding(view.getPaddingLeft(), paddingTop - dp24, view.getPaddingRight(), view
                            .getPaddingBottom());
                }
            } else {
                int yellow_start = a.getColor(R.styleable.NavigationView_colorStart, ContextCompat.getColor(context,
                        R.color.colorPrimary));
                int yellow_end = a.getColor(R.styleable.NavigationView_colorEnd, ContextCompat.getColor(context,
                        R.color.colorPrimaryDark));
               /* int yellow_end = ContextCompat.getColor(context, R
                        .color.yellow_37);*/
                LinearGradient lg = new LinearGradient(0, 0, compat.width, compat.height, yellow_start, yellow_end,
                        Shader.TileMode.MIRROR);
                compat.paint.setShader(lg);
                boolean enable = a.getBoolean(R.styleable.NavigationView_enablePadding, true);
                if (enable && a.getBoolean(R.styleable.NavigationView_forceSkipLoLLiPop, false) && Build.VERSION
                        .SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    return compat;
                }
                if (paddingTop != dp24 && enable) {
                    view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + dp24, view.getPaddingRight(), view
                            .getPaddingBottom());
                }
            }
        } finally {
            if (null != a) a.recycle();
        }
        return compat;
    }

    private static int getStatusHeight(Context context) {
        int statusHeight;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            statusHeight = dpToPx(context.getResources(), 24);
        }
        return statusHeight;
    }

    private static int dpToPx(Resources resources, int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, resources.getDisplayMetrics());
    }

    void drawPrimaryDark(Canvas canvas) {
        if (null != paint) {
            canvas.drawRect(0, 0, width, height, paint);
        }
    }
}
