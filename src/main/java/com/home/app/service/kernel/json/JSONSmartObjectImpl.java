package com.home.app.service.kernel.json;

import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import net.minidev.json.JSONValue;

import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class JSONSmartObjectImpl implements JSONObject {

	public JSONSmartObjectImpl(){
		_jsonObject = new net.minidev.json.JSONObject();
	}
	
	public JSONSmartObjectImpl(String json){ 
		try{

			_jsonObject = (net.minidev.json.JSONObject) JSONValue.parse(json);
		}
		catch(Exception e){
			_log.error(e);
		}
	}
	
	public JSONSmartObjectImpl(net.minidev.json.JSONObject jsonObj) {
		_jsonObject = jsonObj;
	}

	public JSONSmartObjectImpl(Object jsonObject){
		_jsonObject = (net.minidev.json.JSONObject)jsonObject;
	}
	
	@Override
	public boolean getBoolean(String key) {
		return (Boolean)_jsonObject.get(key);
	}

	@Override
	public double getDouble(String key) {
		return (Double) _jsonObject.get(key);
	}

	@Override
	public int getInt(String key) {
		Number number = _jsonObject.getAsNumber(key);
		
		return number.intValue();
	}

	@Override
	public long getLong(String key) {
		Number number = _jsonObject.getAsNumber(key);
		
		return number.longValue();		
	}

    @Override
    public Date getDate(String key){
        Object object = _jsonObject.get(key);

        if (object instanceof Number){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(getLong(key) * 1000);

            return calendar.getTime();
        }
        else if(object instanceof Date){
            return (Date)object;
        }
        else{
            return null;
        }
    }

    public double getDouble(String key, double defaultValue){
        try{
            return getDouble(key);
        }
        catch (NullPointerException NPE){
            return defaultValue;
        }
    }

    public  int getInt(String key, int defaultValue){
        try{
            return getInt(key);
        }
        catch (NullPointerException NPE){
            return defaultValue;
        }
    }

    public long getLong(String key, long defaultValue){
        try{
            return getLong(key);
        }
        catch (NullPointerException NPE){
            return defaultValue;
        }
    }

    public Date getDate(String key, Date defaultValue){
        try{
            return getDate(key);
        }
        catch (NullPointerException NPE){
            return defaultValue;
        }
    }

    @Override
    public JSONArray getJSONArray(String key) {
        net.minidev.json.JSONArray jsonArray = (net.minidev.json.JSONArray)_jsonObject.get(key);

        if(jsonArray == null){
            return null;
        }

        return new JSONSmartArrayImpl(jsonArray);
    }

    public net.minidev.json.JSONObject getJSONObject(){
        return _jsonObject;
    }

    @Override
    public JSONObject getJSONObject(String key) {
        Object jsonObject = _jsonObject.get(key);

        if(jsonObject == null){
            return null;
        }

        return new JSONSmartObjectImpl((net.minidev.json.JSONObject)jsonObject);
    }

	@Override
	public String getString(String key) {
	
		Object jsonObject = _jsonObject.get(key);
		
		if(jsonObject == null){
			return null;
		}
		else if (jsonObject instanceof String) {
			return (String) jsonObject;			
		}
		else if(jsonObject instanceof net.minidev.json.JSONObject) {
			 return ((net.minidev.json.JSONObject)jsonObject).toJSONString();
		}
		else if(jsonObject instanceof net.minidev.json.JSONArray){
			return ((net.minidev.json.JSONArray)jsonObject).toJSONString();
		}
		else {
			return JSONValue.toJSONString(jsonObject);
		}		
	}

	@Override
	public boolean has(String key) {
		return _jsonObject.containsKey(key);
	}

	@Override
	public boolean isNull(String key) {
		return _jsonObject == null;

	}

	@Override
	public Iterator<String> keys() {
		return (Iterator<String>)_jsonObject.keySet();
	}

	@Override
	public int length() {
		return _jsonObject.size();
	}

	@Override
	public JSONArray names() {
		return null;
	}

	@Override
	public JSONObject put(String key, boolean value) {
		try{
			_jsonObject.put(key, value);
		}
		catch(Exception e){
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return this;
	}

	@Override
	public JSONObject put(String key, double value) {
		try{
			_jsonObject.put(key, value);
		}
		catch(Exception e){
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return this;
	}

	@Override
	public JSONObject put(String key, int value) {
		try{
			_jsonObject.put(key, value);
		}
		catch(Exception e){
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return this;
	}

	@Override
	public JSONObject put(String key, long value) {
		try{
			_jsonObject.put(key, value);
		}
		catch(Exception e){
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return this;
	}

	@Override
	public JSONObject put(String key, Date value) {
		try{
			_jsonObject.put(key, value);
		}
		catch(Exception e){
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return this;
	}

	@Override
	public JSONObject put(String key, JSONArray value) {
		try{
			_jsonObject.put(key, value);
		}
		catch(Exception e){
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return this;
	}

	@Override
	public JSONObject put(String key, JSONObject value) {
		try{
			_jsonObject.put(key, value);
		}
		catch(Exception e){
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return this;
	}

	@Override
	public JSONObject put(String key, String value) {
		try{
			_jsonObject.put(key, value);
		}
		catch(Exception e){
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return this;
	}

	@Override
	public Object remove(String key) {
		return _jsonObject.remove(key);
	}

	@Override
	public String toString() throws JSONException {
		return _jsonObject.toJSONString();
	}

	@Override
	public Writer write(Writer writer) throws JSONException {
		return null;
	}
	
	private net.minidev.json.JSONObject _jsonObject;
	private static Log _log = LogFactoryUtil.getLog(JSONSmartObjectImpl.class);

}
