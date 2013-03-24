package com.beedieapp.controller;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

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
		
			ListFragment newFragment = new ListFragment();
//		  if (activity.getSupportFragmentManager().findFragmentById(android.R.id.tabcontent) == null) {
			  if(itemPosition == 0){
//			FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//			ft.add(R.id.tabcontent, new ListFragment(), null);
//			ft.commit();
//			activity.getSupportFragmentManager().executePendingTransactions();
			  
			  newFragment.setLabel("Twitter");
			  }else if(itemPosition == 1){
				  newFragment.setLabel("Facebook");
			  }
			  else if(itemPosition == 2){
				  newFragment.setLabel("Flickr");
			  }else if(itemPosition == 3){
				  newFragment.setLabel("Facebook");
			  }
			  FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
			  if (activity.getSupportFragmentManager().findFragmentById(android.R.id.tabcontent) == null) {
				  ft.add(android.R.id.tabcontent, newFragment).commit();
			  }else{
				  ft.replace(android.R.id.tabcontent, newFragment).commit();
			  }
	          
//		  }
		  
		return false;
	}

}
