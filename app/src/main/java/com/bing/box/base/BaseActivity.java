package com.bing.box.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bing.box.R;
import com.bing.box.utils.StatusBarUtils;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtils.setStatusBarColor(this, R.color.white);
//        hideStatusNavigationBar();
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            decorView.getWindowInsetsController().hide(WindowInsets.Type.systemBars());
            decorView.getWindowInsetsController().hide(WindowInsets.Type.navigationBars()
            | WindowInsets.Type.statusBars() | WindowInsets.Type.systemBars());
            decorView.getWindowInsetsController().setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
            //通知视窗，应用会处理任务系统视窗，意思就是全屏
            getWindow().setDecorFitsSystemWindows(false);
        }else{
//            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.STATUS_BAR_HIDDEN;
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
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

}
