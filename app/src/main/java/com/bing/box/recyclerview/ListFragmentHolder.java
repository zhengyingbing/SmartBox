package com.bing.box.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.box.R;

public class ListFragmentHolder extends RecyclerView.ViewHolder {

    private TextView mTitleText, mDescText;
    private ImageView mImg;
    private View mLayout;

    public ListFragmentHolder(@NonNull View itemView) {
        super(itemView);
        mLayout = itemView;
        mTitleText = itemView.findViewById(R.id.item_list_fragment_tv);
        mDescText = itemView.findViewById(R.id.item_list_fragment_desc);
        mImg = itemView.findViewById(R.id.item_list_fragment_iv);
    }

    public TextView getTitleText() {
        return mTitleText;
    }

    public TextView getDescText() {
        return mDescText;
    }

    public ImageView getImg() {
        return mImg;
    }

    public View getLayout() {
        return mLayout;
    }
}
