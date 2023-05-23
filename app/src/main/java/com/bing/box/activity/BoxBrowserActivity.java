package com.bing.box.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bing.box.R;
import com.bing.box.utils.Logger;

public class BoxBrowserActivity extends Activity {

    private static final String URL = "webUrl";
    private static final String TITLE = "title";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.box_webview_dialog);
        webView = findViewById(R.id.box_webview_dialog_webview);
        ImageView closeBtn = findViewById(R.id.box_webview_dialog_btn);

        Intent intent = getIntent();
        String url = intent.getStringExtra(URL);
        if (TextUtils.isEmpty(url)){
            Logger.i("url is null");
            finish();
        }
        TextView titleView = findViewById(R.id.box_webview_dialog_title);
        titleView.setText("暂无标题");
        String title = intent.getStringExtra(TITLE);
        if (!TextUtils.isEmpty(title)){
            titleView.setText(title);
        }
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView.loadUrl(url);
        WebSettings settings = webView.getSettings();
        settings.setTextZoom(90);//缩放比例
//        settings.setTextSize(WebSettings.TextSize.NORMAL);
//        settings.setUseWideViewPort(true);//自适应
        settings.setLoadWithOverviewMode(true);//根据屏幕宽度自适应
        settings.setAllowContentAccess(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public static void start(Context context, String url, String title) {
        Intent starter = new Intent(context, BoxBrowserActivity.class);
        starter.putExtra(URL, url);
        starter.putExtra(TITLE, title);
        context.startActivity(starter);
    }
    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        theme.applyStyle(android.R.style.Theme_NoTitleBar_Fullscreen, true);
        return theme;
    }

}
