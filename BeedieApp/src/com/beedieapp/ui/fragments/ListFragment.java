package com.beedieapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;
import com.beedieapp.R;
import com.beedieapp.model.BeedieNewsListModel;

public class ListFragment extends SherlockListFragment {
	 String[] data ={
			   "January", 
			   "February", 
			   "March", 
			   "April",
			   "May", 
			   "June", 
			   "July", 
			   "August",
			   "September", 
			   "October", 
			   "November", 
			   "December"
			 };
	 BeedieNewsListModel model;

	 public void setModel(BeedieNewsListModel model){
		 this.model = model;
	 }
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  this.model.execute();
	  while(!this.model.isReady()){}
	  SimpleAdapter myListAdapter = new SimpleAdapter(getActivity(), model.getList(),
              android.R.layout.simple_list_item_2,
              new String[] {"title", "subtitle"},
              new int[] {android.R.id.text1,
                         android.R.id.text2});
//	  ListAdapter myListAdapter = new ArrayAdapter<String>(
//	    getActivity(),
//	    android.R.layout.simple_list_item_1,
//	    data);
	  setListAdapter(myListAdapter);
	 }
	protected String label = "";
	public void setLabel(String label){
		this.label = label;
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        return v;
    }
}
