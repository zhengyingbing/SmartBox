package com.bing.box.base;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bing.box.R;

public abstract class BaseTitleActivity extends BaseActivity implements IBaseActivity {

    private ImageView mBackImg, mMoreImg;
    private TextView mTitleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = getLayoutInflater().inflate(R.layout.activity_base_title, null);

        mBackImg = contentView.findViewById(R.id.item_title_back);
        mMoreImg = contentView.findViewById(R.id.item_title_more);
        mTitleText = contentView.findViewById(R.id.item_title_title);
        FrameLayout frameLayout = contentView.findViewById(R.id.activity_base_framelayout);
        frameLayout.addView(getLayoutInflater().inflate(getLayoutId(), null));
        setContentView(contentView);
        if (TextUtils.isEmpty(getTitleStr())){
            contentView.findViewById(R.id.item_title_layout).setVisibility(View.GONE);
        }else{
            mTitleText.setText(getTitleStr());
        }
        initView();
        initData();
    }
    //隐身导航栏
    private void hideStatusNavigationBar() {
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            final int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN //hide statusBar
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY; //hide navigationBar
            decorView.setSystemUiVisibility(uiFlags);
        }
    }

    public void chargeFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getViewId(), fragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public String getTitleStr(){
        return "";
    }

    protected void showBackImg(){
        mBackImg.setVisibility(View.VISIBLE);
    }

    protected void showMoreImg(){
        mMoreImg.setVisibility(View.VISIBLE);
    }

    protected ImageView getBackImgView(){
        return mBackImg;
    }

    protected ImageView getMoreImgView(){
        return mMoreImg;
    }

}
