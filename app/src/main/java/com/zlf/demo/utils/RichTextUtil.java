package com.zlf.demo.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Describe：
 * Author：yul
 * Created Time：下午12:01
 * Modify：yul
 * Modify Time：下午12:01
 * Remark：
 */
public class RichTextUtil {
    public static String createRichText(int color,String content){
        StringBuffer sb = new StringBuffer();
        sb.append("<font color='");
        sb.append(color).append("'>");
        sb.append(content).append("</font></br>");
        return  sb.toString();
    }

    public static SpannableString setForeground(int color, int start, int end, SpannableString string) {
        string.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }
    public static SpannableString setAbsoluteSizeSpan(int px, int start, int end, SpannableString string) {
        string.setSpan(new AbsoluteSizeSpan(px),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }

}
