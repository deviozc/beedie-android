package com.beedieapp.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.beedieapp.model.QueryItem;

public class DataHelper {
	// trim string while preserve full words
	public static String softTrimString(String string, int length){
		String result = string;
		if(string == null || string.trim().length() == 0){
	        return result;
	    }
		StringBuffer sb = new StringBuffer(string);
		if(sb.length() > length){
			int endIndex = sb.indexOf(" ",length);
			result = string.substring(0, endIndex);
		}
		return result;
		
	}
	public static List<Map<String, String>> toListItems(List<QueryItem> items){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for(QueryItem item : items){
			result.add(item.toListItem());
		}
		return result;
	}
}
