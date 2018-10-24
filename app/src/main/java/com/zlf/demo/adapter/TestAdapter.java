package com.zlf.demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chaychan.library.BottomBarLayout;
import com.zlf.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestAdapter extends RecyclerView.Adapter {




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fl_content)
        FrameLayout flContent;
        @BindView(R.id.bbl)
        BottomBarLayout bbl;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.fl_content, R.id.bbl})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.fl_content:
                    break;
                case R.id.bbl:
                    break;
            }
        }

    }


}
