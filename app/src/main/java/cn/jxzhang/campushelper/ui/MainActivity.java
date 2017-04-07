package cn.jxzhang.campushelper.ui;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import butterknife.BindView;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.adapter.NavigationViewPagerAdapter;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;
import cn.jxzhang.campushelper.ui.navigation.HomeFragment;

public class MainActivity extends BaseAppCompatActivity {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;

    private NavigationViewPagerAdapter adapter;

    private static final int CURRENT_PAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBottomNavigation();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    private void initBottomNavigation() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_home, R.drawable.ic_maps_local_home, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_contacts, R.drawable.ic_maps_local_contact, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_discover, R.drawable.ic_maps_local_discover, R.color.color_tab_3);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_me, R.drawable.ic_maps_local_me, R.color.color_tab_4);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#009688"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Force the titles to be displayed (against Material Design guidelines!)
        bottomNavigation.setForceTitlesDisplay(true);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(CURRENT_PAGE);

        //// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        AHNotification notification = new AHNotification.Builder()
                .setText("3")
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_back))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_text))
                .build();

        bottomNavigation.setNotification(notification, 0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (wasSelected) {
                    return true;
                }

                viewPager.setCurrentItem(position, false);
                return true;
            }
        });
        
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                Log.w(getClass().getName(), "position:" + y);
                // Manage the new y position
            }
        });

        viewPager.setOffscreenPageLimit(4);
        adapter = new NavigationViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(CURRENT_PAGE);
    }
}
