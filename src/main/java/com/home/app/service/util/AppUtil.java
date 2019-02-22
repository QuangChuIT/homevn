package com.home.app.service.util;

import com.home.app.service.kernel.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class AppUtil {

	public static String formatDate(Date datetime) throws Exception{
		return getApp().formatDate(datetime);
	}

	public static String formatDate(Date datetime, String pattern) throws Exception {
		return getApp().formatDate(datetime, pattern);
	}

	public static Date convertDate(String stringDate) throws Exception{
		return getApp().convertDate(stringDate);
	}

	public static Date convertDate(String stringDate, String pattern) throws Exception{
		return getApp().convertDate(stringDate, pattern);
	}

	/*public static JSONObject authenWithCMS(HttpServletRequest request) throws Exception{
		return getApp().authenWithCMS(request);
	}*/

	public static long getUserId(HttpServletRequest request) throws Exception{
		return getApp().getUserId(request);
	}

	public static String getUserName(HttpServletRequest request) throws Exception{
		return getApp().getUserName(request);
	}

	public static JSONObject getUser(HttpServletRequest request) throws Exception{
		return getApp().getUser(request);
	}

	public static boolean isSigned(HttpServletRequest request) throws Exception{
		return getApp().isSigned(request);
	}
	
	public static App getApp(){
		return _app;
	}
	
	public void setApp(App app){
		_app = app;
	}
	
	private static App _app;
}
