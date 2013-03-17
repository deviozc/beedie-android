package com.beedieapp.controller;

import android.R;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.beedieapp.ui.fragments.ListFragment;

public class NavigationItemManager implements ActionBar.OnNavigationListener{
	private static NavigationItemManager instance = null;
	private FragmentActivity activity = null;
	public static NavigationItemManager getInstance(){
		if(instance == null){
			instance = new NavigationItemManager();
		}
		return instance;
	}
	public NavigationItemManager(){
		
	}
	public NavigationItemManager(FragmentActivity activity){
		this.activity = activity;
	}
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		if(itemPosition == 0){
			FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
			ft.add(R.id.tabcontent, new ListFragment(), null);
			ft.commit();
			activity.getSupportFragmentManager().executePendingTransactions();
		}
		return false;
	}

}
