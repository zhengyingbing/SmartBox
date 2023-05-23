package com.bing.box.fragment;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.box.activity.WidgetsActivity;
import com.bing.box.base.BaseFragment;
import com.bing.box.R;
import com.bing.box.bean.Menu2Bean;
import com.bing.box.callback.OnListFragmentItemClickListener;
import com.bing.box.recyclerview.CommonDecoration;
import com.bing.box.recyclerview.ListFragmentAdapter;
import com.bing.box.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private ListFragmentAdapter mAdapter;
    private List<Menu2Bean> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.fragment_list_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.addItemDecoration(new CommonDecoration(AppUtils.getApp()));
    }

    @Override
    public void initData() {
        list.clear();
        list.add(new Menu2Bean("按钮", "各种各样的按钮", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("文字", "酷炫的文字", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("输入框", "个性的输入框", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("loading", "各种动画loading", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("复选框", "不同的复选框", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("进度条", "各种进度条", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("列表", "自定义列表集合", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("图形", "柱状图折线图饼状图", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("动画", "有意思的动画", R.mipmap.ic_menu1_0));
        list.add(new Menu2Bean("震动按钮", "各种震动", R.mipmap.ic_menu1_0));

        mAdapter = new ListFragmentAdapter(AppUtils.getApp(), list);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(new OnListFragmentItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                switch (position){
                    case 0:
                        WidgetsActivity.start(getActivity(), "button");
                        break;
                    case 1:
                        WidgetsActivity.start(getActivity(), "text");
                        break;
                    default:
                        Toast.makeText(getActivity(), "xx", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected String getTitle() {
        return "组件库";
    }
}
