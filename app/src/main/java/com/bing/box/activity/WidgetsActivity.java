package com.bing.box.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.box.R;
import com.bing.box.base.BaseTitleActivity;
import com.bing.box.callback.OnListFragmentItemClickListener;
import com.bing.box.recyclerview.ButtonsActivityAdapter;
import com.bing.box.recyclerview.CommonDecoration;
import com.bing.box.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class WidgetsActivity extends BaseTitleActivity {

    private static final String TAG = "EXTRA1";
    RecyclerView mRecyclerView;
    ButtonsActivityAdapter mAdapter;
    List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_widgets_list;
    }

    @Override
    public void initView() {
        showBackImg();
        getBackImgView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView = findViewById(R.id.activity_widgets_list_recyclerview);
        TextView mTextView = findViewById(R.id.activity_widgets_list_textview);

        Intent intent = getIntent();
        if (intent != null){
            String extra1 = intent.getStringExtra(TAG);
            Logger.i("显示View：" + extra1);
            if (TextUtils.equals("button", extra1)){
                mTextView.setText("按钮组件库");
                for (int i = 0; i < 7; i++){
                    list.add("按钮" + i);
                }
            }else if (TextUtils.equals("text", extra1)){
                mTextView.setText("文字组件库");
                for (int i = 0; i < 7; i++){
                    list.add("文字" + i);
                }
            }
        }
    }

    @Override
    public void initData() {

        mAdapter = new ButtonsActivityAdapter(this, list);
        mRecyclerView.addItemDecoration(new CommonDecoration(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(new OnListFragmentItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                doClick(position);
            }
        });
    }

    public static void start(Context context, String desc) {
        Intent starter = new Intent(context, WidgetsActivity.class);
        starter.putExtra(TAG, desc);
        context.startActivity(starter);
    }

    private void doClick(int pos){
        switch (pos){
            case 0:
                CustomViewActivity.start(this, pos, "温度计", "");
                break;
            case 1:
                break;
            default:
        }
    }

    @Override
    public String getTitleStr() {
        return "按钮";
    }

}
