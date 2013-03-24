package com.beedieapp.model;

public class QueryItem {
	public String title, subTitle, imgUrl, content;
	public QueryItem(String title, String subTitle, String imgUrl, String content){
		this.title = title;
		this.subTitle = subTitle;
		this.imgUrl = imgUrl;
		this.content = content;
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
}
