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

import java.io.Writer;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Brian Wing Shun Chan
 */
public interface JSONObject {

	boolean getBoolean(String key);

	double getDouble(String key);

	int getInt(String key);

	long getLong(String key);

    Date getDate(String key);

    double getDouble(String key, double defaultValue);

    int getInt(String key, int defaultValue);

    long getLong(String key, long defaultValue);

    Date getDate(String key, Date defaultValue);

	String getString(String key);

    JSONArray getJSONArray(String key);

    JSONObject getJSONObject(String key);

	boolean has(String key);

	boolean isNull(String key);

	Iterator<String> keys();

	int length();

	JSONArray names();

	JSONObject put(String key, boolean value);

	JSONObject put(String key, double value);

	JSONObject put(String key, int value);

	JSONObject put(String key, long value);

	JSONObject put(String key, Date value);

	JSONObject put(String key, JSONArray value);

	JSONObject put(String key, JSONObject value);

	JSONObject put(String key, String value);

	Object remove(String key);

	String toString();

	Writer write(Writer writer) throws JSONException;

}