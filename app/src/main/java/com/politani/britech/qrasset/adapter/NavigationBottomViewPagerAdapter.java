package com.politani.britech.qrasset.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.politani.britech.qrasset.fragment.AboutFragment;
import com.politani.britech.qrasset.fragment.BaseFragmentBottomNavigation;
import com.politani.britech.qrasset.fragment.ProfileFragment;
import com.politani.britech.qrasset.fragment.ScanBarcodeFragment;

import java.util.ArrayList;

/**
 *
 */
public class NavigationBottomViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> fragments = new ArrayList<>();
	private BaseFragmentBottomNavigation currentFragment;

	public NavigationBottomViewPagerAdapter(FragmentManager fm) {
		super(fm);
//
		fragments.clear();
		fragments.add(ScanBarcodeFragment.newInstance(0));
		fragments.add(ProfileFragment.newInstance(1));
		fragments.add(AboutFragment.newInstance(2));
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
			currentFragment = ((BaseFragmentBottomNavigation) object);
		}
		super.setPrimaryItem(container, position, object);
	}

	/**
	 * Get the current fragment
	 */
	public Fragment getCurrentFragment() {
		return this.currentFragment;
	}
}