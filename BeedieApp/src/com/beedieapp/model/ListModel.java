package com.beedieapp.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import android.os.AsyncTask;
import android.util.Log;

public abstract class ListModel extends Observable{
	protected boolean ready = false;
	public ListModel() {
	}
	public ListModel(Observer... observers) {
		for(Observer observer : observers){
			this.addObserver(observer);
		}
	}
	public abstract void init();
	public abstract List<QueryItem> getList();
	protected InputStream downloadUrl(String urlString) throws IOException {
	    URL url = new URL(urlString);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setReadTimeout(10000 /* milliseconds */);
	    conn.setConnectTimeout(15000 /* milliseconds */);
	    conn.setRequestMethod("GET");
	    conn.setDoInput(true);
	    // Starts the query
	    conn.connect();
	    return conn.getInputStream();
	}
	public abstract boolean isReady();
	public void ready(){
		this.setChanged();
		this.notifyObservers(getList());
		this.ready = true;
	}
	
}
