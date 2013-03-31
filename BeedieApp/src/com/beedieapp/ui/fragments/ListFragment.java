package com.beedieapp.ui.fragments;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;
import com.beedieapp.R;
import com.beedieapp.controller.NavigationItemManager;
import com.beedieapp.helper.DataHelper;
import com.beedieapp.model.BeedieNewsListModel;
import com.beedieapp.model.ListModel;
import com.beedieapp.model.QueryItem;

public class ListFragment extends SherlockListFragment implements Observer {
	Dialog dialog;
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  dialog = ProgressDialog.show(this.getActivity(), "Loading", "Retrieving Information...");
//	  ListModel model = NavigationItemManager.getInstance().getModel();
//	  model.execute();
//	  while(!model.isReady()){}
	  
//	  ListAdapter myListAdapter = new ArrayAdapter<String>(
//	    getActivity(),
//	    android.R.layout.simple_list_item_1,
//	    data);
	  
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
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable observable, Object ob) {
		if(ob instanceof List){
			List<Map<String, String>> list = DataHelper.toListItems((List<QueryItem>) ob);
			this.setList(list);
			Log.w("ready", "Is ready");
			if(dialog != null){
				dialog.dismiss();
			}
		}
	}
	private void setList(List<Map<String, String>> list){
		final SimpleAdapter myListAdapter = new SimpleAdapter(ListFragment.this.getActivity(), list,
	              R.layout.custom_list_item,
	              new String[] {"title", "subtitle"},
	              new int[] {android.R.id.text1,
	                         android.R.id.text2});
		this.getActivity().runOnUiThread(new Runnable() {
		    public void run() {
		    	setListAdapter(myListAdapter);
		        dialog.dismiss();
		    }
		});
		
	}
}
