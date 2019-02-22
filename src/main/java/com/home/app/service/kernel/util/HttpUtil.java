package com.home.app.service.kernel.util;


import com.home.app.service.kernel.json.JSONObject;

public class HttpUtil {

    public static JSONObject authen(String url, String cookies){
        return getHttp().authen(url, cookies);
    }

    public static String getMethod(String url, String[][] params) throws Exception{
        return getHttp().getMethod(url, params);
    }

    public static boolean getImage(String url, String pathFile) throws Exception{
        return getHttp().getImage(url, pathFile);
    }

    public static byte[] getMethod(String url, String[][] params, String contentType) throws Exception{
        return getHttp().getMethod(url, params, contentType);
    }

    public static <T> T postMethod(Class<T> tClass, String url, String[][] params) throws Exception{
        return getHttp().postMethod(tClass, url, params);
    }

    /**
     * @see return value is type String or byte[]
     *
     * */

    public static <T> T getMethod(Class<T> tClass, String url, String contentType, String[][] headers, String[][] params) throws Exception{
        return getHttp().getMethod(tClass, url, contentType, headers, params);
    }

    public static String encodeURL(String url){
        return getHttp().encodeURL(url);
    }

    public static String encodeURL(String url, boolean escapeSpaces){
        return getHttp().encodeURL(url, escapeSpaces);
    }

    public void setHttp(Http _http){
        http = _http;
    }

    public static Http getHttp(){
        return http;
    }

    private static Http http;

}