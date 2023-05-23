package com.bing.box.base;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bing.box.R;

import java.lang.reflect.Field;

public abstract class BaseFragment extends Fragment implements IBaseFragment {

    protected BaseTitleActivity activity;
    private ImageView backImg;
    private ImageView moreImg;
    private TextView titleView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (BaseTitleActivity) context;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_base, container, false);
        View view = inflater.inflate(getLayoutId(), container, false);

        initView(view);
        FrameLayout frameLayout = layout.findViewById(R.id.fragment_base_framelayout);
        frameLayout.addView(view);

        backImg = layout.findViewById(R.id.item_title_back);
        moreImg = layout.findViewById(R.id.item_title_more);
        titleView = layout.findViewById(R.id.item_title_title);
        String title = getTitle();
        if (TextUtils.isEmpty(title)){
            layout.findViewById(R.id.item_title_layout).setVisibility(View.GONE);
        }else{
            titleView.setText(title);
        }
        return layout;
    }

    public void chargeFragment(BaseFragment fragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(activity.getViewId(), fragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDetach() {

//        try {
//            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        super.onDetach();
    }

    protected String getTitle(){
        return "";
    }

    protected void setMoreImgRes(int resId){
        moreImg.setImageResource(resId);
    }

    protected void setBackImgRes(int resId){
        backImg.setImageResource(resId);
    }

    public ImageView getBackImg() {
        return backImg;
    }

    public ImageView getMoreImg() {
        return moreImg;
    }
}
