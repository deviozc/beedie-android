package com.beedieapp.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.androidquery.util.XmlDom;

public class BeedieNewsListModel extends AsyncTask<String, Void, String>{
	private final static String BEEDIE_NEWS_FEED = "http://beedie.sfu.ca/blog/category/main-news/feed/";
	private AQuery aq;
	private List<QueryItem> items;
	private Boolean ready;

	public BeedieNewsListModel() {
		items = new ArrayList<QueryItem>();
		ready = false;
	}

	public void init() {
//		aq.ajax(BEEDIE_NEWS_FEED, XmlPullParser.class, this, "callback");
		
		try {
			InputStream stream = downloadUrl(BEEDIE_NEWS_FEED); 
			Log.w("stream", stream.toString());
			this.parse(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		aq.ajax(BEEDIE_NEWS_FEED, XmlDom.class, "callback1");
	}
	private InputStream downloadUrl(String urlString) throws IOException {
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
	private void parse(InputStream in) throws IOException{
		try {
            XmlPullParser xpp = Xml.newPullParser();
            xpp.setInput(in, null);
            int eventType = xpp.getEventType();
            String title = "";
			String subTitle = "";
			String content = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				
				if (eventType == XmlPullParser.START_TAG) {
					
					String tag = xpp.getName();
//					Log.w("tag", tag);
					if ("title".equals(tag)) {
						title = xpp.nextText();
					} else if ("content".equals(tag)) {
						content = xpp.nextText();
					} else if ("description".equals(tag)) {
						subTitle = xpp.nextText();
					}
				}else if(eventType == XmlPullParser.END_TAG){
					String tag = xpp.getName();
					Log.w("tag", tag);
					if ("item".equals(tag)) {
						items.add(new QueryItem(title, subTitle, "", content));
						Log.w("title", title);
					}
				}
				eventType = xpp.next();
			}
			ready = true;
            
        }catch (Exception e) {
        	Log.w("Error", e.getMessage());
		}finally{
			in.close();
		}
	}
	public boolean isReady(){
		return ready;
	}
	public void callback1(String url, XmlDom xml, AjaxStatus status){
		Log.w("url", url.toString());
		Log.w("xml", xml.toString());
		List<XmlDom> entries = xml.tags("item");               
        List<String> titles = new ArrayList<String>();
        
        String imageUrl = null;
        
        for(XmlDom entry: entries){
        	String title = entry.text("title");
			String subTitle = entry.text("description");
			String content = entry.text("content");
			Log.w("title", title);
//                titles.add(entry.text("title"));
//                imageUrl = entry.tag("content", "type", "image/jpeg").attr("src");
        }
                
	}

	public void callback(String url, XmlPullParser xpp, AjaxStatus status) {
		try {
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String title = "";
				String subTitle = "";
				String content = "";
				if (eventType == XmlPullParser.START_TAG) {

					String tag = xpp.getName();

					if ("title".equals(tag)) {
						title = xpp.nextText();
					} else if ("content".equals(tag)) {
						content = xpp.nextText();
					} else if ("description".equals(tag)) {
						subTitle = xpp.nextText();
					}
				}else if(eventType == XmlPullParser.END_TAG){
					String tag = xpp.getName();
					if ("item".equals(tag)) {
						items.add(new QueryItem(title, subTitle, "", content));
						Log.w("title", title);
					}
				}
				eventType = xpp.next();
			}
			
			ready = true;

		} catch (Exception e) {
			AQUtility.report(e);
		}
	}

	public List<Map<String, String>> getList() {
//		while(!this.ready){}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for(QueryItem item:items){
			Map<String, String> data = new HashMap<String, String>();
			data.put("title", item.getTitle());
			data.put("subtitle", item.getSubTitle());
			result.add(data);
		}
		return result;
	}

	public void getContent(String id) {

	}

	@Override
	protected String doInBackground(String... arg0) {
		init();
		return null;
	}
}
