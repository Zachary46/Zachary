package com.zlf.demo.moudle.common.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zlf.demo.R;
import com.zlf.demo.base.BaseFragment;
import com.zlf.demo.utils.ToastUtil;
import java.util.ArrayList;
import butterknife.BindView;

public class TestFragment extends BaseFragment {

    @BindView(R.id.rxv)
    RecyclerView rxv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    TestAdapter testAdapter;
    ArrayList<String> data;

    @Override
    public int getLayoutId() {
        return R.layout.fg_test;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        data=new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            data.add("item"+i);
        }
        testAdapter = new TestAdapter(R.layout.item_test,data);
        rxv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rxv.setAdapter(testAdapter);
        testAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.showToast(getActivity(),"item"+position);
            }
        });
        testAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.showToast(getActivity(),"item child"+position);
            }
        });
        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.d("TestFragmentt", "onLoadMore");
                for (int i = 0; i <30 ; i++) {
                    data.add("item"+i);
                }
                testAdapter.notifyDataSetChanged();
                srl.finishLoadMore();
                if (data.size()>90) srl.setNoMoreData(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.d("TestFragmentt", "onRefresh");
                data.clear();
                for (int i = 0; i <30 ; i++) {
                    data.add("item"+i);
                }
                testAdapter.notifyDataSetChanged();
                srl.finishRefresh();
            }
        });
    }
}
