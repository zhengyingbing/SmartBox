package com.bing.box.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.box.R;
import com.bing.box.callback.OnListFragmentItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ButtonsActivityAdapter extends RecyclerView.Adapter<ButtonsActivityHolder> {

    private List<String> mDatas = new ArrayList<>();
    private Context mContext;
    private OnListFragmentItemClickListener clickListener;

    public ButtonsActivityAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    @NonNull
    @Override
    public ButtonsActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ButtonsActivityHolder holder = new ButtonsActivityHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_activity_buttons, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonsActivityHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.getTitleText().setText(mDatas.get(position));
        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null){
                    clickListener.OnItemClick(position);
                }
            }
        });
        holder.getLayout().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:

                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setClickListener(OnListFragmentItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
