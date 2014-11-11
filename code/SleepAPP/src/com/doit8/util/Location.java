package com.doit8.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class Location {
	Activity context;
	public String city=""; 
	public android.location.Location location;
	public Location(Activity context)
	{
		this.context=context;
		openGPSSettings();
		getLocation();
	}
	
	private void openGPSSettings() {  
        LocationManager alm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);  
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {  
            Toast.makeText(context, "GPS模块正常", Toast.LENGTH_SHORT).show();  
            return;  
        }  
  
        Toast.makeText(context, "请开启GPS！", Toast.LENGTH_SHORT).show();  
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);  
        context.startActivityForResult(intent,0); //此为设置完成后返回到获取界面  
  
    }  
	
	private void getLocation() {
		// 获取位置管理服务
		LocationManager locationManager;
		String serviceName = Context.LOCATION_SERVICE;
		locationManager = (LocationManager)context.getSystemService(serviceName);
		// 查找到服务信息
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
		criteria.setAltitudeRequired(false);//不要求海拔  
		criteria.setBearingRequired(false);//不要求方位  
		criteria.setCostAllowed(true);//允许有话费  
		criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

		String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
		//location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置 LocationManager.GPS_PROVIDER
		location = locationManager.getLastKnownLocation(provider);
	    if(location!=null)
	    {
	    	city=getAddress(location.getLongitude(),location.getLatitude());
	    }
	    //updateToNewLocation(location);
		// 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
		locationManager.requestLocationUpdates(provider, 100 * 1000, 500,mLocationListener);
	    
	    Toast.makeText(context, "当前城市是："+city, Toast.LENGTH_SHORT).show();  
	    
	}
	/** 注册定位事件 */
	private LocationListener mLocationListener = new LocationListener() {

		@Override
		public void onLocationChanged(android.location.Location location) {
			// TODO Auto-generated method stub
			if (location != null) {
	            try {
	                int longitude = (int) (1000000 * location.getLongitude());
	                int latitude = (int) (1000000 * location.getLatitude());
/*
	                *//** 保存当前经纬度 *//*
	                mCurLocation.put("longitude", location.getLongitude());
	                mCurLocation.put("latitude", location.getLatitude());

	                GeoPoint point = new GeoPoint(latitude, longitude);
	                *//** 查询该经纬度值所对应的地址位置信息 *//*
	                Weather_WelcomeActivity.this.mMKSearch
	                        .reverseGeocode(new GeoPoint(latitude, longitude));*/
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	public String getCity()
	{
		return city;
	}
	
/*	private void updateToNewLocation(Location location) {  
		  
        TextView tv1;  
        tv1 = (TextView) this.findViewById(R.id.tv1);  
        if (location != null) {  
            double  latitude = location.getLatitude();  
            double longitude= location.getLongitude();  
            tv1.setText("维度：" +  latitude+ "\n经度" + longitude);  
        } else {  
            tv1.setText("无法获取地理信息");  
        }  
  
    }  */
	
	public String getAddress(Double Longitude,Double Latitude){
        String url = "http://maps.google.com/maps/api/geocode/json?latlng="+Latitude+","+Longitude+"&language=zh_CN&sensor=false";
        HttpClient client = new DefaultHttpClient();
        StringBuilder sb = new StringBuilder();
        try {
                HttpResponse resp = client.execute(new HttpGet(url));
                HttpEntity he = resp.getEntity();
                BufferedReader br = new BufferedReader(new InputStreamReader(he.getContent()));
                String str = "";
                while((str=br.readLine())!=null){
                        sb.append(str);
                }
                
        } catch (ClientProtocolException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        try {
                JSONObject jo1 = new JSONObject(sb.toString());
                String str1 = jo1.getString("results");
                JSONArray arr1 = new JSONArray(str1);
                String str2 = arr1.get(0).toString();
                JSONObject jo2 = new JSONObject(str2);
                String str3 = jo2.getString("formatted_address");
                return str3;
//                Toast.makeText(LocationService.this, str3, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
                return "地址转换失败";
        }
	}
}


