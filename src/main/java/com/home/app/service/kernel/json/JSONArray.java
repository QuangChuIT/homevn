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

package com.home.app.service.kernel.json;



/**
 * @author Brian Wing Shun Chan
 */
public interface JSONArray {

	boolean getBoolean(int index);

	double getDouble(int index);

	int getInt(int index);

	JSONArray getJSONArray(int index);

	JSONObject getJSONObject(int index);

	long getLong(int index);

	String getString(int index);

	boolean isNull(int index);

	String join(String separator) throws JSONException;

	JSONArray join(JSONArray jsonArray) throws Exception;

	int length();

	JSONArray put(boolean value);

	JSONArray put(double value);

	JSONArray put(int value);

	JSONArray put(long value);

	JSONArray put(JSONArray value);

	JSONArray put(JSONObject value);

	JSONArray put(String value);

	String toString();

}