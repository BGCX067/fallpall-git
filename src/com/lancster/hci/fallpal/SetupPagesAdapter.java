package com.lancster.hci.fallpal;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SetupPagesAdapter extends FragmentPagerAdapter {

	List<Fragment> myFragmentList;
	
	public SetupPagesAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.myFragmentList = fragments;
	}

	@Override
	public Fragment getItem(int index) {
		return myFragmentList.get(index);
	}

	@Override
	public int getCount() {
		return myFragmentList.size();
	}	
}
