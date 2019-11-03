package com.doumuecommerce.management;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.doumuecommerce.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ManageActivity extends AppCompatActivity {

    private BottomNavigationView bnView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        bnView = findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());

        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        /** BottomNavigationView 点击事件监听 **/
        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                /** 跳转指定页面：Fragment **/
                switch (menuId) {
                    case R.id.tab_one:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_two:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_three:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        /** ViewPager 滑动事件监听 **/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                /** 将滑动到的页面对应的 menu 设置为选中状态 **/
                bnView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
