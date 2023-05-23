package com.bing.box.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bing.box.fragment.TabFragment1;
import com.bing.box.fragment.TabFragment2;
import com.bing.box.fragment.TabFragment3;
import com.bing.box.fragment.TabFragment4;

public class MainPagerAdapter extends FragmentStateAdapter {

    private String[] titles;

    public MainPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    public MainPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String[] titles) {
        super(fragmentManager, lifecycle);
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
            case 2:
                return new TabFragment3();
            case 3:
                return new TabFragment4();
            default:
                return new TabFragment1();
        }
    }

    @Override
    public int getItemCount() {
        if (titles == null){
            return -1;
        }
        return titles.length;
    }


}
