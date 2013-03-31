package com.beedieapp.controller;

import java.util.Observable;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.beedieapp.model.BeedieNewsListModel;
import com.beedieapp.model.ListModel;
import com.beedieapp.model.ModelTask;
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
//		Observable ob = new Observable();
//		currentModel = new BeedieNewsListModel(ob);
//		this.refreshModel();
	}
	public NavigationItemManager(FragmentActivity activity){
		this.activity = activity;
	}
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		
			ListFragment newFragment = new ListFragment();
			ListModel model = new BeedieNewsListModel(newFragment);
			
//		  if (activity.getSupportFragmentManager().findFragmentById(android.R.id.tabcontent) == null) {
			  if(itemPosition == 0){
//				  currentModel = new BeedieNewsListModel();
				  newFragment.setLabel("Twitter");
			  }else if(itemPosition == 1){
//				  currentModel = new BeedieNewsListModel();
				  newFragment.setLabel("Facebook");
			  }
			  else if(itemPosition == 2){
//				  currentModel = new BeedieNewsListModel();
				  newFragment.setLabel("Flickr");
			  }else if(itemPosition == 3){
//				  currentModel = new BeedieNewsListModel();
				  newFragment.setLabel("Pinterest");
			  }
			  ModelTask task = new ModelTask();
			  task.execute(model);
			  FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
			  if (activity.getSupportFragmentManager().findFragmentById(android.R.id.tabcontent) == null) {
				  ft.add(android.R.id.tabcontent, newFragment).commit();
			  }else{
				  ft.replace(android.R.id.tabcontent, newFragment).commit();
			  }
	          
//		  }
		  
		return false;
	}
//	public ListModel getModel(){
//		return currentModel;
//	}
	public void refreshModel(){
		
	}

}
