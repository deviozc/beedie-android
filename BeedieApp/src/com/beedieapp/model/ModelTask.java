package com.beedieapp.model;

import android.os.AsyncTask;

public class ModelTask extends AsyncTask<ListModel, Void, String>{
	ListModel[] models;
	@Override
	protected String doInBackground(ListModel... params) {
		this.models = params;
		for(ListModel model : models){
			model.init();
		}
		return null;
	}

}
