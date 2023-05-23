package com.bing.box;

import android.os.Bundle;
import android.widget.Toast;

import com.bing.box.base.BaseTitleActivity;
import com.bing.box.fragment.GroupFragment;
import com.bing.box.fragment.ListFragment;
import com.bing.box.fragment.MainFragment;
import com.bing.box.fragment.MineFragment;
import com.bing.box.view.MainBottomMenu;

public class MainActivity extends BaseTitleActivity {

    private final MainBottomMenu[] menuViews = new MainBottomMenu[4];
    private final int[] menuIds = {R.id.main_menu1, R.id.main_menu2, R.id.main_menu3, R.id.main_menu4};

    ListFragment listFragment = new ListFragment();
    MainFragment mainFragment = new MainFragment();
    GroupFragment groupFragment = new GroupFragment();
    MineFragment mineFragment = new MineFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 4; i++){
            menuViews[i] = findViewById(menuIds[i]);
            menuViews[i].setMenuClickListener(i, new MainBottomMenu.MenuClickListener() {
                @Override
                public void onClick(int index, String titleStr) {
                    setDefault();

                    switch (index){
                        case 0:
                            chargeFragment(mainFragment);
                            break;
                        case 1:
                            chargeFragment(listFragment);
                            break;
                        case 2:
                            chargeFragment(groupFragment);
                            break;
                        case 3:
                            chargeFragment(mineFragment);
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "未找到指定模块", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        chargeFragment(mainFragment);
        menuViews[0].setSelected();
        menuViews[0].setInitListener(new MainBottomMenu.InitListener() {
            @Override
            public boolean showTip() {
                return true;
            }
        });
    }

    @Override
    public int getViewId() {
        return R.id.main_framelayout;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void setDefault(){
        for (MainBottomMenu menu : menuViews){
            menu.setDefault();
        }
    }
}