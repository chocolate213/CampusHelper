package cn.jxzhang.campushelper.ui;

import android.animation.Animator;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.adapter.DemoViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;

    private DemoFragment currentFragment;
    private DemoViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initBottomNagigation();
    }

    private void initBottomNagigation() {
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_home, R.drawable.ic_maps_local_home, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_contacts, R.drawable.ic_maps_local_contact, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_discover, R.drawable.ic_maps_local_discover, R.color.color_tab_3);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_me, R.drawable.ic_maps_local_me, R.color.color_tab_4);

        // Add items
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
        // Or force the titles to be hidden (against Material Design guidelines, too!)
        bottomNavigation.setForceTitlesHide(true);

        // Use colored navigation with circle reveal effect
        //        bottomNavigation.setColored(true);

        //// Set current item programmatically
        bottomNavigation.setCurrentItem(1);
        //// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        //// Add or remove notification for each item
        //        bottomNavigation.setNotification("1", 3);
        //// OR
        AHNotification notification = new AHNotification.Builder()
                .setText("100")
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_back))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_text))
                .build();
        bottomNavigation.setNotification(notification, 1);
        //
        //// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (currentFragment == null) {
                    currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    currentFragment.refresh();
                    return true;
                }

                if (currentFragment != null) {
                    currentFragment.willBeHidden();
                }

                viewPager.setCurrentItem(position, false);
                currentFragment = adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();

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

        adapter = new DemoViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();
    }
}
