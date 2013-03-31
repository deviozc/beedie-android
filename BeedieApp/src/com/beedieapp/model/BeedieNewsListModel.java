package com.beedieapp.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.xmlpull.v1.XmlPullParser;

import com.beedieapp.helper.DataHelper;
import com.beedieapp.ui.fragments.ListFragment;
import com.google.gson.stream.JsonReader;

import android.annotation.SuppressLint;
import android.text.Html;
import android.util.Log;
import android.util.Xml;

public class BeedieNewsListModel extends ListModel {
	private final static String BEEDIE_NEWS_FEED = "http://beedie.sfu.ca/blog/?wpapi=cat&category=main-news&dev=1";
	private List<QueryItem> items;

	public BeedieNewsListModel() {
		super();
		items = new ArrayList<QueryItem>();
	}
	public BeedieNewsListModel(Observer... observers) {
		super(observers);
		items = new ArrayList<QueryItem>();
	}
	@Override
	public void init() {
		if(ready){
			return;
		}
		try {
			InputStream stream = downloadUrl(BEEDIE_NEWS_FEED);
			Log.w("stream", stream.toString());
			this.parse(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressLint("NewApi")
	private void parse(InputStream in) throws IOException {
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("posts")) {
				break;
			} else {
				reader.skipValue();
			}
		}
		reader.beginArray();
		while (reader.hasNext()) {
			items.add(readItem(reader));
		}
		reader.endArray();
		this.ready();

		in.close();
		reader.endObject();
	}

	private QueryItem readItem(JsonReader reader) throws IOException {
		String title = "";
		String subTitle = "";
		String content = "";
		long id = -1;
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("id")) {
				id = reader.nextLong();
			} else if (name.equals("title")) {
				title = reader.nextString();
//			} else if (name.equals("content")) {
//				content = reader.nextString();
			} else if (name.equals("excerpt")){
				subTitle = Html.fromHtml(reader.nextString()).toString();
			} else {
				reader.skipValue();
			}
		}
		// Log.w("Title", title);
		reader.endObject();
		return new QueryItem(title, subTitle, "", content, id);
	}

	// private void parse(InputStream in) throws IOException{
	// try {
	// XmlPullParser xpp = Xml.newPullParser();
	// xpp.setInput(in, null);
	// int eventType = xpp.getEventType();
	// String title = "";
	// String subTitle = "";
	// String content = "";
	// while (eventType != XmlPullParser.END_DOCUMENT) {
	//
	// if (eventType == XmlPullParser.START_TAG) {
	//
	// String tag = xpp.getName();
	// // Log.w("tag", tag);
	// if ("title".equals(tag)) {
	// title = xpp.nextText();
	// } else if ("content".equals(tag)) {
	// content = xpp.nextText();
	// } else if ("description".equals(tag)) {
	// subTitle = xpp.nextText();
	// }
	// }else if(eventType == XmlPullParser.END_TAG){
	// String tag = xpp.getName();
	// Log.w("tag", tag);
	// if ("item".equals(tag)) {
	// items.add(new QueryItem(title, subTitle, "", content));
	// Log.w("title", title);
	// }
	// }
	// eventType = xpp.next();
	// }
	// ready = true;
	//
	// }catch (Exception e) {
	// Log.w("Error", e.getMessage());
	// }finally{
	// in.close();
	// }
	// }
	@Override
	public boolean isReady() {
		return ready;
	}
	@Override
	public List<QueryItem> getList() {
		// while(!this.ready){}
		return items;
	}

	public void getContent(String id) {

	}
}
