package com.bing.box.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.box.R;

public class ButtonsActivityHolder extends RecyclerView.ViewHolder {

    private TextView mTitleText;
    private View mLayout;

    public ButtonsActivityHolder(@NonNull View itemView) {
        super(itemView);
        mLayout = itemView;
        mTitleText = itemView.findViewById(R.id.item_buttons_activity_content);
    }

    public TextView getTitleText() {
        return mTitleText;
    }

    public void setTitleText(TextView mTitleText) {
        this.mTitleText = mTitleText;
    }

    public View getLayout() {
        return mLayout;
    }
}
