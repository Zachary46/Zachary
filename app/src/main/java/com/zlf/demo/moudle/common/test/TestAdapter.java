package com.zlf.demo.moudle.common.test;



import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlf.demo.R;
import java.util.ArrayList;


public class TestAdapter extends BaseQuickAdapter<String,BaseViewHolder>{


    public TestAdapter(int layoutResId,ArrayList<String> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text,item);
        helper.addOnClickListener(R.id.text);
    }
}
