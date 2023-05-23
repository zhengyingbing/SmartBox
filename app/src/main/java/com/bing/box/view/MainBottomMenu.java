package com.bing.box.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bing.box.R;
import com.bing.box.utils.ViewUtils;

public class MainBottomMenu extends LinearLayout {
    private View mView;
    private ImageView mImageView;
    private TextView mTipView;
    private TextView mTitleView;
    private MenuClickListener onClickListener;
    private int index;
    private Drawable drawableDefault, drawableSelect;

    public MainBottomMenu(Context context) {
        this(context, null);
    }

    public MainBottomMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MainBottomMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        setGravity(Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.view_iv_dot, this, true);
        mImageView = mView.findViewById(R.id.main_bottom_menu_iv);
        mTipView = mView.findViewById(R.id.main_bottom_menu_tip);
        mTitleView = mView.findViewById(R.id.main_bottom_menu_tv);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MainBottomMenu);
        drawableDefault = typedArray.getDrawable(R.styleable.MainBottomMenu_img_default);
        if (drawableDefault != null){
            mImageView.setBackground(drawableDefault);
        }
        drawableSelect = typedArray.getDrawable(R.styleable.MainBottomMenu_img_selected);
        String title = typedArray.getString(R.styleable.MainBottomMenu_text);
        if (!TextUtils.isEmpty(title)){
            mTitleView.setText(title);
        }
//        mTitleView.setTextColor(R.styleable.MainBottomMenu_textColor);
        int size = R.styleable.MainBottomMenu_textSize;
        if (size >= 10){
            mTitleView.setTextSize(size);
        }

        mTitleView.setVisibility(VISIBLE);
        mTipView.setVisibility(GONE);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ViewUtils.isFastDoubleClick(String.valueOf(mView.getId() + index))){
                    onClickListener.onClick(index, title);

                    if (drawableSelect != null){
                        mImageView.setBackground(drawableSelect);
                    }
                }
            }
        });
    }

    public void setInitListener(InitListener initListener) {
        mTitleView.setVisibility(initListener.showText() ? VISIBLE : GONE);
        mTipView.setVisibility(initListener.showTip() ? VISIBLE : GONE);
    }

    public void setTipCount(String countStr){
        try {
            int count = Integer.valueOf(countStr);
            if (count > R.styleable.MainBottomMenu_tipSize){
                mTipView.setText(countStr + "+");
            }else if (count <= 0){
                mTipView.setVisibility(GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
            mTipView.setVisibility(GONE);
        }
    }

    public void refresh(){
        //TODO 开始loading
    }

    public void refreshSuccess(){
        //TODO 结束loading
    }

    public void setDefault(){
        if (drawableDefault != null){
            mImageView.setBackground(drawableDefault);
        }
    }

    public void setSelected(){
        if (drawableSelect != null){
            mImageView.setBackground(drawableSelect);
        }
    }

    public interface InitListener{
        default boolean showText(){
            return true;
        }
        default boolean showTip(){
            return false;
        }
    }

    public interface MenuClickListener{
        void onClick(int index, String titleStr);
    }

    public void setMenuClickListener(int index, MenuClickListener onClickListener) {
        this.index = index;
        this.onClickListener = onClickListener;
    }
}
