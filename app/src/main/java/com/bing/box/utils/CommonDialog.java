package com.bing.box.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bing.box.R;

/**
 * @author: zhengyingbing
 * @desc: 公共弹框
 * @date: 2022/10/20 15:28
 */
public class CommonDialog extends Dialog {

    private Button cancelBtn, confirmBtn;
    private TextView titleTv, contentTv;
    private String titleStr, contentStr, cancelBtnStr, confirmBtnStr;
    private OnConfirmClickListener confirmClickListener;
    private OnCancelClickListener cancelClickListener;


    public CommonDialog(Context context) {
        super(context, R.style.BoxTransparent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_dialog1);
        setCanceledOnTouchOutside(false);
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //全屏
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                uiOptions |= 0x00001000;
                getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
        initView();
        initData();
    }

    private void initView() {
        titleTv = findViewById(R.id.box_dialog_title);
        contentTv = findViewById(R.id.box_dialog_content);
        cancelBtn = findViewById(R.id.box_dialog_cancel);
        confirmBtn = findViewById(R.id.box_dialog_confirm);

        StateListDrawable stateListDrawable = ColorUtils.getBtnDrawable();
        if (stateListDrawable.getState().length > 0){
            confirmBtn.setBackground(stateListDrawable);
        }
        titleTv.setText(titleStr);
        contentTv.setText(contentStr);

        if (!TextUtils.isEmpty(cancelBtnStr)){
            cancelBtn.setText(cancelBtnStr);
        }
        if (!TextUtils.isEmpty(confirmBtnStr)){
            confirmBtn.setText(confirmBtnStr);
        }
    }

    private void initData(){
        if (confirmClickListener != null){
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmClickListener.confirm();
                    dismiss();
                }
            });
        }

        if (cancelClickListener != null){
            cancelBtn.setVisibility(View.VISIBLE);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelClickListener.cancel();
                    dismiss();
                }
            });
        }
    }

    public CommonDialog setTitle(String title){
        if(TextUtils.isEmpty(title)){
            titleStr = "温馨提示";
        }else{
            titleStr = title;
        }
        return this;
    }

    public CommonDialog setContent(String content){
        if(TextUtils.isEmpty(content)){
            contentStr = "内容一共七个字";
        }else{
            contentStr = content;
        }
        return this;
    }
    public CommonDialog setCancelBtnStr(String str){
        if(!TextUtils.isEmpty(str)){
            cancelBtnStr = str;
        }
        return this;
    }
    public CommonDialog setConfirmBtnStr(String str){
        if(!TextUtils.isEmpty(str)){
            confirmBtnStr = str;
        }
        return this;
    }

    public CommonDialog setConfirmClickListener(OnConfirmClickListener confirmClickListener) {
        this.confirmClickListener =  confirmClickListener;
        return this;
    }

    public CommonDialog setCancelClickListener(OnCancelClickListener cancelClickListener) {
        this.cancelClickListener =  cancelClickListener;
        return this;
    }

    public interface OnConfirmClickListener{
        void confirm();
    }

    public interface OnCancelClickListener{
        void cancel();
    }

}
