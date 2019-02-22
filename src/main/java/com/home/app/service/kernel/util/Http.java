package com.home.app.service.kernel.util;

import com.home.app.service.kernel.json.JSONObject;

public interface Http {

    JSONObject authen(String url, String cookies);

    String getMethod(String url, String[][] params) throws Exception;

    byte[] getMethod(String url, String[][] params, String contentType) throws Exception;

    boolean getImage(String url, String pathFile) throws Exception;

    <T> T postMethod(Class<T> tClass, String url, String[][] params) throws Exception;

    <T> T getMethod(Class<T> tClass, String url, String contentType, String[][] headers, String[][] params) throws Exception;

    String encodeURL(String url);

    String encodeURL(String url, boolean escapeSpaces);
}