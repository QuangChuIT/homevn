/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.home.app.service.kernel.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class CookieUtil {

	public static String get(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return null;
		}

		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];

			String cookieName = cookie.getName();

			if (cookieName.equalsIgnoreCase(name)) {
				return cookie.getValue();
			}
		}

		return null;
	}
	
	public static String cookieAsString(HttpServletRequest request){
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies == null){
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < cookies.length; i ++){
			Cookie cookie = cookies[i];
			
			sb.append(cookie.getName());
			sb.append("=");
			sb.append(cookie.getValue());
			sb.append("; ");
		}
		
		return sb.toString();
	}

}