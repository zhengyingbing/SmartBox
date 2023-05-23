package com.bing.box.fragment;

import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.bing.box.R;
import com.bing.box.adapter.MainPagerAdapter;
import com.bing.box.base.BaseFragment;
import com.bing.box.utils.Logger;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainFragment extends BaseFragment {
    private ViewPager2 viewPager2;
    private TabLayoutMediator mediator;
    private String[] titles = {"标题1", "标题2", "标题3", "标题4"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
        TabLayout tabLayout = view.findViewById(R.id.fragment_main_tablayout);
        View tabBar = view.findViewById(R.id.fragment_main_tabbar);
        viewPager2 = view.findViewById(R.id.fragment_main_viewpager);
        viewPager2.setAdapter(new MainPagerAdapter(getChildFragmentManager(), getLifecycle(), titles));
        viewPager2.registerOnPageChangeCallback(changeCallback);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(100));
//        transformer.addTransformer(new Transformer());
        viewPager2.setUserInputEnabled(true);
        viewPager2.setPageTransformer(transformer);
        viewPager2.requestDisallowInterceptTouchEvent(false);
        //禁用预加载
//        viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                Logger.i("mediator.onConfigureTab");
                TextView tabView = new TextView(getActivity());

                int[][] states = new int[2][];
                states[0] = new int[]{android.R.attr.state_selected};
                states[1] = new int[]{};

                int[] colors = new int[]{R.color.grey_66, R.color.grey_ee};
                ColorStateList colorStateList = new ColorStateList(states, colors);
                tabView.setText(titles[position]);
                tabView.setTextSize(16);
                tabView.setGravity(Gravity.CENTER);
                tabView.setTextColor(colorStateList);

                tab.setCustomView(tabView);
            }
        });
        mediator.attach();
    }

    private ViewPager2.OnPageChangeCallback changeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            viewPager2.setCurrentItem(position);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            Logger.i("changeCallback.onPageSelected:" + position);
            viewPager2.setCurrentItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    @Override
    public void initData() {

    }

    @Override
    protected String getTitle() {
        return "主页";
    }

    @Override
    public void onDestroy() {
        viewPager2.unregisterOnPageChangeCallback(changeCallback);
        mediator.detach();
        super.onDestroy();
    }
}
