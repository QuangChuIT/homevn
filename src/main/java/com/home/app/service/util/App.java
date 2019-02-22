package com.home.app.service.util;

import com.home.app.service.kernel.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface App {

	String formatDate(Date datetime) throws Exception;

	String formatDate(Date datetime, String pattern) throws Exception;

	Date convertDate(String stringDate) throws Exception;

	Date convertDate(String stringDate, String pattern) throws Exception;

	/*JSONObject authenWithCMS(HttpServletRequest request) throws Exception;*/

	long getUserId(HttpServletRequest request) throws Exception;

	String getUserName(HttpServletRequest request) throws Exception;

	JSONObject getUser(HttpServletRequest request) throws Exception;
	
	boolean isSigned(HttpServletRequest request) throws Exception;
}
