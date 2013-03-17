package com.beedieapp.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import com.beedieapp.R;

public class ListFragment extends SherlockFragment {
	
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//        String[] items = new String[2];
//        items[0] = "Title 1";
//        items[1] = "Title 2";
//        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items));
//        
//	}
	protected String label = "";
	public void setLabel(String label){
		this.label = label;
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        // Retrieve the TextView widget that will display results.
        TextView mResults = (TextView)v.findViewById(R.id.results);

        // This allows us to later extend the text buffer.
        mResults.setText(label, TextView.BufferType.EDITABLE);


        return v;
    }
}
