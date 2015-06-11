package com.n1amr.android.project;

import java.util.Locale;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tabs extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this
     * becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.tabs);

	// Set up the action bar.
	final ActionBar mActionBar = getActionBar();
	mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	// Create the adapter that will return a fragment for each of the three
	// primary sections of the activity.
	mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

	// Set up the ViewPager with the sections adapter.
	mViewPager = (ViewPager) findViewById(R.id.tabspager);
	mViewPager.setAdapter(mSectionsPagerAdapter);

	// When swiping between different sections, select the corresponding
	// tab. We can also use ActionBar.Tab#select() to do this if we have
	// a reference to the Tab.
	mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
		    @Override
		    public void onPageSelected(int position) {
			mActionBar.setSelectedNavigationItem(position);
		    }
		});

	// For each of the sections in the app, add a tab to the action bar.
	for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
	    // Create a tab with text corresponding to the page title defined by
	    // the adapter. Also specify this Activity object, which implements
	    // the TabListener interface, as the callback (listener) for when
	    // this tab is selected.
	    Tab mTab = mActionBar.newTab();
	    mTab.setText(mSectionsPagerAdapter.getPageTitle(i));
	    mTab.setTabListener(this);
	    mActionBar.addTab(mTab);
	}
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab,
	    FragmentTransaction fragmentTransaction) {
	// When the given tab is selected, switch to the corresponding page in
	// the ViewPager.
	mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab,
	    FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab,
	    FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

	String[] tabsNames = { "Timeline", "Mentions", "Direct Meassages" };

	public SectionsPagerAdapter(FragmentManager fm) {
	    super(fm);
	}

	@Override
	public Fragment getItem(int position) {
	    // getItem is called to instantiate the fragment for the given page.
	    // Return a PlaceholderFragment (defined as a static inner class
	    // below).
	    return PlaceholderFragment.newInstance(position + 1);
	}

	@Override
	public int getCount() {
	    // Count of Pages.
	    return tabsNames.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
	    Locale mLocale = Locale.getDefault();
	    if (position < tabsNames.length)
		return tabsNames[position].toUpperCase(mLocale);
	    return null;
	}
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(int sectionNumber) {
	    PlaceholderFragment mPlaceholderFragment = new PlaceholderFragment();
	    Bundle args = new Bundle();
	    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
	    mPlaceholderFragment.setArguments(args);
	    return mPlaceholderFragment;
	}

	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater mLayoutInflater,
		ViewGroup container, Bundle savedInstanceState) {
	    View rootView = mLayoutInflater.inflate(R.layout.tabs_fragment,
		    container, false);
	    return rootView;
	}
    }

}
