package com.doit8.sleep;

import java.util.ArrayList;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MyPagerAdapter extends PagerAdapter{
	
	private ArrayList<View> views;
	private ArrayList<String> titles;
	
	public MyPagerAdapter(ArrayList<View> views,ArrayList<String> titles){
		this.views = views;
		this.titles = titles;
	}
	@Override
	public int getCount() {
		return this.views.size();
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView(views.get(position));
	}
	//页面view
	public Object instantiateItem(View container, int position) {
		((ViewPager)container).addView(views.get(position));
		return views.get(position);
	}
	@Override
	public void finishUpdate(View arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void startUpdate(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
