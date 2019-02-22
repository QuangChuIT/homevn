package com.home.app.service.kernel.json;

import net.minidev.json.JSONValue;
import net.minidev.json.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class JSONSmartArrayImpl implements JSONArray{

	public JSONSmartArrayImpl(){
		_jsonArray = new net.minidev.json.JSONArray();
	}
	
	public JSONSmartArrayImpl(String json) throws JSONException {
		try{
			_jsonArray = (net.minidev.json.JSONArray) JSONValue.parse(json);
		}
		catch(Exception e){
			throw new JSONException();
		}
	}

	public JSONSmartArrayImpl(File json) throws JSONException {
		try{
			JSONParser jsonParser = new JSONParser();
			_jsonArray = (net.minidev.json.JSONArray)jsonParser.parse(new FileReader(json));
		}
		catch(Exception e){
			throw new JSONException();
		}
	}
	
	public JSONSmartArrayImpl(net.minidev.json.JSONArray jsonArray){
		_jsonArray = jsonArray;
	}
	
	@Override
	public boolean getBoolean(int index) {
		return (boolean)_jsonArray.get(index);
	}

	@Override
	public double getDouble(int index) {	
		return (double)_jsonArray.get(index);
	}

	@Override
	public int getInt(int index) {
		return (int)_jsonArray.get(index);
	}
	
	public net.minidev.json.JSONArray getJSONArray(){
		return _jsonArray;
	}

	@Override
	public JSONArray getJSONArray(int index) {	
		net.minidev.json.JSONArray jsonArray = (net.minidev.json.JSONArray)_jsonArray.get(index);
		
		if(jsonArray == null){
			return null;
		}
		
		return new JSONSmartArrayImpl(jsonArray);
	}

	@Override
	public JSONObject getJSONObject(int index) {
		Object jsonObject = _jsonArray.get(index);
		
		if(jsonObject == null){
			return null;
		}
		
		return new JSONSmartObjectImpl(jsonObject);
	}

	@Override
	public long getLong(int index) {
		return (long)_jsonArray.get(index);
	}

	@Override
	public String getString(int index) {		
		return (String)_jsonArray.get(index);
	}

	@Override
	public boolean isNull(int index) {
		Object jsonArray = _jsonArray.get(index);

		return jsonArray == null;

	}

	@Override
	public String join(String separator) throws JSONException {
		try{
			net.minidev.json.JSONArray jsonArray = (net.minidev.json.JSONArray) JSONValue.parse(separator);
			
			_jsonArray.add(jsonArray);
			
			return _jsonArray.toJSONString();
		}
		catch(Exception e){
			throw new JSONException();
		}
	}

	public JSONArray join(JSONArray jsonArray) throws Exception{
		try{

			if(jsonArray == null || jsonArray.length() == 0){
				return this;
			}

			int length = jsonArray.length();

			for(int i = 0; i < length; i ++){
				put(jsonArray.getJSONObject(i));
			}

			return this;
		}
		catch (Exception e){
			throw new Exception(e);
		}
	}

	@Override
	public int length() {
		return _jsonArray.size();
	}

	@Override
	public JSONArray put(boolean value) {
		_jsonArray.add(value);
		return this;	
	}

	@Override
	public JSONArray put(double value) {
		_jsonArray.add(value);
		return this;
	}

	@Override
	public JSONArray put(int value) {
		_jsonArray.add(value);
		return this;
	}

	@Override
	public JSONArray put(long value) {
		_jsonArray.add(value);
		return this;
	}

	@Override
	public JSONArray put(JSONArray value) {
		_jsonArray.add(((JSONSmartArrayImpl)value).getJSONArray());
		
		return this;
	}

	@Override
	public JSONArray put(JSONObject value) {
		_jsonArray.add(((JSONSmartObjectImpl)value).getJSONObject());
		
		return this;
	}

	@Override
	public JSONArray put(String value) {
		_jsonArray.add(value);
		return this;
	}

	public String toString() throws JSONException {
		return _jsonArray.toJSONString();
	}
	
	private net.minidev.json.JSONArray _jsonArray;
}
