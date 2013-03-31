package com.beedieapp.model;

import java.util.HashMap;
import java.util.Map;

public class QueryItem {
	public String title, subTitle, imgUrl, content;
	public long id;
	public QueryItem(String title, String subTitle, String imgUrl, String content, long id){
		this.title = title;
		this.subTitle = subTitle;
		this.imgUrl = imgUrl;
		this.content = content;
		this.id = id;
	}
	public long getID(){
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public String getContent() {
		return content;
	}
	public Map<String, String> toListItem(){
		Map<String, String> data = new HashMap<String, String>();
		data.put("title", this.getTitle());
		data.put("subtitle", this.getSubTitle());
		return data;
	}
}
