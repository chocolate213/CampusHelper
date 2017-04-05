package cn.jxzhang.campushelper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.jxzhang.campushelper.ui.navigation.ContactFragment;
import cn.jxzhang.campushelper.ui.navigation.DiscoverFragment;
import cn.jxzhang.campushelper.ui.navigation.HomeFragment;
import cn.jxzhang.campushelper.ui.navigation.MeFragment;

/**
 * Created on 2017-04-03 22:09
 * <p>Title:       NavigationViewPagerAdapter</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public class NavigationViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    private Fragment currentFragment;

    public NavigationViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();

        fragments.add(HomeFragment.newInstance(0));
        fragments.add(ContactFragment.newInstance(1));
        fragments.add(DiscoverFragment.newInstance(2));
        fragments.add(MeFragment.newInstance(3));

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            switch (position) {
                case 0:
                    currentFragment = (HomeFragment) object;
                    break;
                case 1:
                    currentFragment = (ContactFragment) object;
                    break;
                case 2:
                    currentFragment = (DiscoverFragment) object;
                    break;
                case 3:
                    currentFragment = (MeFragment) object;
                    break;
                default:
                    currentFragment = (HomeFragment) object;
            }
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}
