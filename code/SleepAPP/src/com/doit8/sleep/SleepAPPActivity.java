package com.doit8.sleep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.doit8.sleep.pojo.SelectedItem;
import com.doit8.sleep.pojo.Subject;
import com.doit8.sleep.pojo.SubjectFactory;
import com.doit8.sleep.service.Score;
import com.doit8.util.version.UpdateManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SleepAPPActivity extends Activity {
	TextView topicDescTV;
	ListView answerListView;
	Subject curSubject;//当前的题目
	ArrayAdapter adapter;
	List answerItems;
	Map selectItemMap;//选择项结果
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	final Context context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //判断是否最新版本
		UpdateManager updManager=new UpdateManager(this);
		updManager.checkUpdate();
		
		
        topicDescTV=(TextView)findViewById(R.id.txtDes);
        answerListView = (ListView) findViewById(R.id.lists);
        curSubject=SubjectFactory.createSubjects(this);
        topicDescTV.setText(curSubject.getTopic());
        String[] answer=curSubject.getAnswers();
        answerItems=new ArrayList();
        answerItems.addAll(Arrays.asList(answer));
        adapter=new  ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,answerItems);
        answerListView.setAdapter(adapter);
        answerListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        // 增加ListViewItem的点击事件
        answerListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				if(curSubject!=null)
				{
					SelectedItem selectedItem=new SelectedItem();
					selectedItem.setTopicId(curSubject.getTopicId());
					selectedItem.setSelectted(arg2);
					int selctScore=Score.getScoreByItems(selectedItem);
					selectedItem.setScore(selctScore);
				}
				if(curSubject.getNextSubject()!=null)
				{
					curSubject=curSubject.getNextSubject();
					topicDescTV.setText(curSubject.getTopic());
					String[] answer=curSubject.getAnswers();
					answerItems.clear();
					answerItems.addAll(Arrays.asList(answer));
					answerListView.setItemChecked(arg2, false);
					adapter.notifyDataSetChanged();// 通知listView刷新数据
				}
				else
				{
					int allScore=Score.getAllScore();
					
					Intent intent = new Intent(SleepAPPActivity.this,
							ResultActivity.class);
					intent.putExtra("allScore", allScore);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					//Toast.makeText(context, "您的匹兹堡睡眠质量指数分为："+allScore+"，≥8分为存在睡眠障碍，最高分为21分，得分越高，睡眠障碍情况越严重！", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
    public void onClickPreviouTopic(View view)
    {
		if(curSubject.getPreviouSubject()!=null)
		{
			curSubject=curSubject.getPreviouSubject();
			topicDescTV.setText(curSubject.getTopic());
			String[] answer=curSubject.getAnswers();
			answerItems.clear();
			answerItems.addAll(Arrays.asList(answer));
			adapter.notifyDataSetChanged();// 通知listView刷新数据
			//显示已选择的结果
			selectItemMap=Score.getSelectItemMap();
			if(selectItemMap.containsKey(curSubject.getTopicId()))
			{
				SelectedItem selectedItem=(SelectedItem)selectItemMap.get(curSubject.getTopicId());
				answerListView.setItemChecked(selectedItem.getSelectted(), true);
			}
		}
		else
		{
			Toast.makeText(this, getResources().getString(R.string.info_isFirstTopic), Toast.LENGTH_SHORT).show();
		}
    }
    public void onClickNextTopic(View view)
    {
		if(curSubject.getNextSubject()!=null)
		{
			curSubject=curSubject.getNextSubject();
			topicDescTV.setText(curSubject.getTopic());
			String[] answer=curSubject.getAnswers();
			answerItems.clear();
			answerItems.addAll(Arrays.asList(answer));
			adapter.notifyDataSetChanged();// 通知listView刷新数据
			//显示已选择的结果
			selectItemMap=Score.getSelectItemMap();
			if(selectItemMap.containsKey(curSubject.getTopicId()))
			{
				SelectedItem selectedItem=(SelectedItem)selectItemMap.get(curSubject.getTopicId());
				answerListView.setItemChecked(selectedItem.getSelectted(), true);
			}
		}
		else
		{
			Toast.makeText(this, getResources().getString(R.string.info_isLastTopic), Toast.LENGTH_SHORT).show();
		}

    }
}