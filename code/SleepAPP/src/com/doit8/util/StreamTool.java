package com.doit8.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class StreamTool {

	public static byte[] read(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len = inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
	public static String removeBOM(String str)
	{
		while(str.startsWith("\ufeff"))
		{
			str=str.substring(1);
		}
		return str;
	}
	
	public static String parseResultCodeJSON(InputStream inStream) throws Exception {
		String result="-1";
		byte[] data=StreamTool.read(inStream);
		String jsonStr=new String(data);
		jsonStr=StreamTool.removeBOM(jsonStr);
		//String jsonStr1="[{number:\"1-530-840-8396\",rpvalue:\"11\"}]";
		JSONArray array=new JSONArray(jsonStr);
		for(int i=0;i<array.length();i++)
		{
			JSONObject jsonObj=array.getJSONObject(i);
			result=jsonObj.getString("result");
		}
		return result;
	}
	
	public static Map<String,String> convertJSONtoResultMap(InputStream inStream) throws Exception
	{
		Map<String,String> resultMap=new HashMap<String,String>();
		byte[] data=StreamTool.read(inStream);
		String jsonStr=new String(data);
		
		jsonStr=StreamTool.removeBOM(jsonStr);
		JSONArray array=new JSONArray(jsonStr);
		for(int i=0;i<array.length();i++)
		{
			JSONObject jsonObj=array.getJSONObject(i);
			Iterator<?> itr=jsonObj.keys();
			while(itr.hasNext())
			{
				String key=itr.next().toString();
				resultMap.put(key,jsonObj.getString(key));
			}
		}
		return resultMap;
	}
	/*public static void main(String[] str)
	{
		Map<String,String> resultMap=new HashMap<String,String>();
		String jsonStr="[{\"result\":\"1\"},{\"nickname\":\"caihao\"}]";
		jsonStr=StreamTool.removeBOM(jsonStr);
		JSONArray array;
		try {
			array = new JSONArray(jsonStr);
			for(int i=0;i<array.length();i++)
			{
				JSONObject jsonObj=array.getJSONObject(i);
				Iterator<?> itr=jsonObj.keys();
				while(itr.hasNext())
				{
					String key=itr.next().toString();
					resultMap.put(key,jsonObj.getString(key));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
