package com.doit8.util.version;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.doit8.sleep.util.Const;
import com.doit8.util.StreamTool;
import com.doit8.util.version.pojo.VersionPojo;

import android.util.Log;


public class VersionService {
	private static final String TAG="VersionService";
	/**
	 * 获取新版本
	 * @return
	 */
	public static VersionPojo getNewVersion()
	{
		String path=Const.VERSION_SERVICE_URL;
		try
		{
			URL url=new URL(path);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode()==200)
			{
				InputStream inStream=conn.getInputStream();
				VersionPojo version=parseVersionPojo4JSON(inStream);
				return version;
			}
		}catch(Exception ex)
		{
			Log.e(TAG, "registUser error:"+ex.getMessage());
		}
		return null;
	}
	
	private static VersionPojo parseVersionPojo4JSON(InputStream inStream) throws Exception
	{
		byte[] data=StreamTool.read(inStream);
		String jsonStr=new String(data);
		jsonStr=StreamTool.removeBOM(jsonStr);
		JSONArray array=new JSONArray(jsonStr);
		for(int i=0;i<array.length();i++)
		{
			VersionPojo version=new VersionPojo();
			JSONObject jsonObj=array.getJSONObject(i);
			version.setVersion(jsonObj.getString("version"));
			version.setVersionName(jsonObj.getString("version_name"));
			version.setVersionDesc(jsonObj.getString("version_desc"));
			version.setVersionUrl(jsonObj.getString("version_url"));
			return version;
		}
		return null;
	}

}
