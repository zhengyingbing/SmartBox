package com.bing.box.fragment;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.bing.box.R;
import com.bing.box.activity.BoxBrowserActivity;
import com.bing.box.base.BaseFragment;
import com.bing.box.utils.AssetUtils;
import com.bing.box.utils.CommonDialog;
import com.bing.box.utils.Logger;
import com.google.android.material.tabs.TabLayout;

public class TabFragment1 extends BaseFragment implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab1;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.tab1_btn_tablayout).setOnClickListener(this);
        view.findViewById(R.id.tab1_btn_viewpager2).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        CommonDialog dialog = new CommonDialog(getActivity());
        switch (v.getId()){
            case R.id.tab1_btn_tablayout:
                dialog.setTitle("TabLayout说明")
                        .setContent("1314141413123")
                        .setConfirmClickListener(() -> {
                    dialog.cancel();
                }).show();
                break;
            case R.id.tab1_btn_viewpager2:
                String path = AssetUtils.getFilePath("viewpager2.html");
                Logger.i("跳转路径：" + path);
                BoxBrowserActivity.start(getActivity(), path, "Viewpager2");
                break;
            default:
        }
    }
}
