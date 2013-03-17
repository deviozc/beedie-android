package com.beedieapp.ui.fragments;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.beedieapp.R;

public class ListFragment extends SherlockListFragment {
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] items = new String[2];
        items[0] = "Title 1";
        items[1] = "Title 2";
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, items));
	}
}
