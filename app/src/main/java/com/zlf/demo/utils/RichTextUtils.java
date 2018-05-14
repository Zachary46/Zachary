package com.zlf.demo.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RichTextUtils {
	
    private static String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
    private static final Pattern WEB_PATTERN = Pattern.compile(regex);
    private static final Pattern MENTION_PATTERN = Pattern.compile("@[\\u4e00-\\u9fa5a-zA-Z0-9_]+");
    private static final Pattern TOPIC_PATTERN = Pattern.compile("#([^\\#|.]+)#");


    public static SpannableString getRichText(final Context context, String text){
        SpannableString string = new SpannableString(text);
        if(!TextUtils.isEmpty(text)){
            final int link_color = ContextCompat.getColor(context, com.zachary.base.R.color.red);
            final int mention_color = ContextCompat.getColor(context, com.zachary.base.R.color.red);
            Matcher matcher_two= MENTION_PATTERN.matcher(text);
            while (matcher_two.find()){
                final String name  = matcher_two.group();
             //   Log.d("RichTextUtils", "==========="+name.substring(1));
                string.setSpan(new ClickableSpan() {
                    public void onClick(View widget) {
                        /*Intent intent = new Intent(context, MineActivity.class);
                        intent.putExtra(GlobalStaticConstant.USER_NAME, name.substring(1));
                        context.startActivity(intent);*/
                    }

                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                        ds.setColor(mention_color);
                    }
                }, matcher_two.start(), matcher_two.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            final Matcher matcher_topic= TOPIC_PATTERN.matcher(text);
            while (matcher_topic.find()){
                final String name  = matcher_topic.group();

                string.setSpan(new ClickableSpan() {
                    public void onClick(View widget) {
                        Pattern p=Pattern.compile("\\#(.*?)\\#");
                        Matcher m=p.matcher(name);
                        while (m.find()){
                            /*Log.d("RichTextUtils", "==========="+m.group(1));
                            Intent intent=new Intent(context, TopicDtailActivity.class);
                            intent.putExtra("topic_name",m.group(1));
                            context.startActivity(intent);*/
                        }

                    }

                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                        ds.setColor(mention_color);
                    }
                }, matcher_topic.start(), matcher_topic.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            Matcher matcher= WEB_PATTERN.matcher(text);
            while (matcher.find()){
                final String url  = matcher.group();
                string.setSpan(new ClickableSpan() {
                    public void onClick(View widget) {

                    }

                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                        ds.setColor(link_color);
                    }
                }, matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return  string;
    }

    public static String createRichText(int color,String content){
        StringBuffer sb = new StringBuffer();
        sb.append("<font color='");
        sb.append(color).append("'>");
        sb.append(content).append("</font></br>");
        return  sb.toString();
    }

    public static SpannableString SpannableString(int color,int start,int end,SpannableString string) {
        string.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }
}
