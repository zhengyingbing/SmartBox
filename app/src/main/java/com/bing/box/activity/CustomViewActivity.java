package com.bing.box.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.box.R;
import com.bing.box.base.BaseTitleActivity;

public class CustomViewActivity extends BaseTitleActivity {

    private static final String TAG1 = "EXTRA1";
    private static final String TAG2 = "EXTRA2";
    private static final String TAG3 = "EXTRA3";

    @Override
    public int getLayoutId() {
        return R.layout.activity_customview;
    }

    @Override
    public void initView() {
        LinearLayout layout = findViewById(R.id.activity_customview_layout);
        TextView mTextView = findViewById(R.id.activity_customview_desc);
        Intent intent = getIntent();
        switch (intent.getIntExtra(TAG1, -1)){
            case 0:
                break;
            case 1:
                break;
            default:

        }
    }

    @Override
    public void initData() {

    }

    public static void start(Context context, int id, String title, String desc) {
        Intent starter = new Intent(context, CustomViewActivity.class);
        starter.putExtra(TAG1, id);
        starter.putExtra(TAG2, title);
        starter.putExtra(TAG3, desc);
        context.startActivity(starter);
    }

    @Override
    public String getTitleStr() {
        return "自定义View";
    }
}
