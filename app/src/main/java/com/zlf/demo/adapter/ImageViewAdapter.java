package com.zlf.demo.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ImageViewAdapter {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    @BindingAdapter({"thumbUrl"})
    public static void loadThumbImage(ImageView view, String thumb) {
        Glide.with(view.getContext())
                .load(thumb)
                .into(view);
    }

}
