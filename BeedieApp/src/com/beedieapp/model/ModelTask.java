package com.beedieapp.model;

import android.os.AsyncTask;
import android.util.Log;

public class ModelTask extends AsyncTask<ListModel, Void, ListModel[]>{
	ListModel[] models;
	@Override
	protected ListModel[] doInBackground(ListModel... params) {
		this.models = params;
		if(isCancelled()){
			return null;
		}
		for(ListModel model : models){
			Log.w("Writing", "Loading");
			model.init();
			Log.w("Writing", "Loading1");
			if(!model.isReady()){
				
			}
		}
		return models;
	}
	@Override
	protected void onCancelled(){
		Log.w("Writing", "Cacnelled");
	}
	@Override
	protected void onPostExecute (ListModel[] result){
		for(ListModel model: result){
			model.ready();
			Log.w("Writing", "ready");
		}
	}

}
