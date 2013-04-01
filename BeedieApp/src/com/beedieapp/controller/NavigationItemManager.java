package com.beedieapp.controller;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.beedieapp.model.BeedieNewsListModel;
import com.beedieapp.model.ListModel;
import com.beedieapp.model.ModelTask;
import com.beedieapp.ui.fragments.ListFragment;

public class NavigationItemManager implements ActionBar.OnNavigationListener{
	private static NavigationItemManager instance = null;
	private FragmentActivity activity = null;
	private ModelTask task = new ModelTask();
	private ListModel model;
	public static NavigationItemManager getInstance(){
		if(instance == null){
			instance = new NavigationItemManager();
		}
		return instance;
	}
	private NavigationItemManager(){
//		Observable ob = new Observable();
//		currentModel = new BeedieNewsListModel(ob);
//		this.refreshModel();
//		initNewTask(new BeedieNewsListModel());
	}
	private NavigationItemManager(FragmentActivity activity){
//		this();
		this.activity = activity;
	}
	public void setActivity(FragmentActivity activity){
		this.activity = activity;
	}
	public void onDestroy(){
		if(task != null){
			task.cancel(true);
		}
		task = null;
	}
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		
			ListFragment newFragment = new ListFragment();
//			model = new BeedieNewsListModel(newFragment);
			task = new ModelTask();
//		  if (activity.getSupportFragmentManager().findFragmentById(android.R.id.tabcontent) == null) {
			  if(itemPosition == 0){
//				  currentModel = new BeedieNewsListModel();
				  newFragment.setLabel("Twitter");
				  model = new BeedieNewsListModel(newFragment);
				  initNewTask(new BeedieNewsListModel(newFragment));
			  }else if(itemPosition == 1){
//				  currentModel = new BeedieNewsListModel();
				  newFragment.setLabel("Facebook");
				  initNewTask(new BeedieNewsListModel(newFragment));
			  }
			  else if(itemPosition == 2){
//				  currentModel = new BeedieNewsListModel();
				  newFragment.setLabel("Flickr");
				  initNewTask(new BeedieNewsListModel(newFragment));
			  }else if(itemPosition == 3){
//				  currentModel = new BeedieNewsListModel();
				  newFragment.setLabel("Pinterest");
				  initNewTask(new BeedieNewsListModel(newFragment));
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
	private void initNewTask(ListModel model){
//		task.cancel(true);
		Log.w("Writing", "init");
		task.execute(model);
	}
//	public ListModel getModel(){
//		return currentModel;
//	}
	public void refreshModel(){
		
	}

}
