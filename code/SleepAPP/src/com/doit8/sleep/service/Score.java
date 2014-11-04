package com.doit8.sleep.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doit8.sleep.pojo.SelectedItem;

public class Score {
	public static Map<String, Integer> scoreMap=new HashMap<String, Integer>();
	public static boolean isSelectTopic(String topicId)
	{
		List<String> selectTopics=new ArrayList<String>();
		selectTopics.add("topic2");
		selectTopics.add("topic4");
		selectTopics.add("topic5_a");
		selectTopics.add("topic5_b");
		selectTopics.add("topic5_c");
		selectTopics.add("topic5_d");
		selectTopics.add("topic5_e");
		selectTopics.add("topic5_f");
		selectTopics.add("topic5_g");
		selectTopics.add("topic5_h");
		selectTopics.add("topic5_i");
		selectTopics.add("topic5_j");
		selectTopics.add("topic6");
		selectTopics.add("topic7");
		selectTopics.add("topic8");
		selectTopics.add("topic9");
		if(selectTopics.contains(topicId))
		{
			return true;
		}
		return false;
	}
	
	public static int getScoreByItems(SelectedItem selectedItem)
	{
		int score=0;
		String topicId=selectedItem.getTopicId();
		if(isSelectTopic(topicId))
		{
			switch(selectedItem.getSelectted())
			{
				case 0:
					score=0;
					break;
				case 1:
					score=1;
					break;
				case 2:
					score=2;
					break;
				case 3:
					score=3;
					break;
				default:
					score=0;
			}
		}
		else if("topic1".equals(topicId)||"topic3".equals(topicId))
		{
			score=selectedItem.getSelectted()+1;
		}
		selectedItem.setScore(score);
		scoreMap.put(topicId, score);
		return score;
	}
	
	public static int getAllScore()
	{
		int scorePSQI=0;
		if(scoreMap.size()<18)
		{
			scorePSQI=-1;
		}
		else
		{
			//Ⅰ睡眠质量
			int score1=scoreMap.get("topic6").intValue();
			//Ⅱ入睡时间
			int score2=0;
			int scoreSub2=scoreMap.get("topic2").intValue();
			int scoreSub5_a=scoreMap.get("topic5_a").intValue();
			int sumSub2_Sub5a=scoreSub2+scoreSub5_a;
			if(sumSub2_Sub5a>=5)
			{
				score2=3;
			}
			else if(sumSub2_Sub5a>=3)
			{
				score2=2;
			}
			else if(sumSub2_Sub5a>=1)
			{
				score2=1;
			}
			else if(sumSub2_Sub5a==0)
			{
				score2=0;
			}
			//Ⅲ 睡眠时间
			int score3=scoreMap.get("topic4").intValue();
			//Ⅳ睡眠效率
			int score4=0;
			int bedtime=getBedTime(scoreMap.get("topic3").intValue(),scoreMap.get("topic1").intValue());
			int sleeptime=0;
			if(scoreMap.get("topic4").intValue()==0)
			{
				sleeptime=7;
			}
			if(scoreMap.get("topic4").intValue()==1)
			{
				sleeptime=6;
			}
			if(scoreMap.get("topic4").intValue()==2)
			{
				sleeptime=5;
			}
			if(scoreMap.get("topic4").intValue()==3)
			{
				sleeptime=4;
			}
			if(bedtime!=0)
			{
				float sleepEffic=sleeptime/bedtime;
				if(sleepEffic>=0.85)
				{
					score4=0;
				}
				else if(sleepEffic>=0.75)
				{
					score4=1;
				}
				else if(sleepEffic>=0.65)
				{
					score4=2;
				}
				else if(sleepEffic<0.65)
				{
					score4=3;
				}
			}
			else
			{
				score4=3;
			}
			//Ⅴ睡眠障碍
			int score5=0;
			int sum5b_5j=scoreMap.get("topic5_b").intValue()+scoreMap.get("topic5_c").intValue()+
			scoreMap.get("topic5_d").intValue()+scoreMap.get("topic5_e").intValue()+scoreMap.get("topic5_f").intValue()+
			scoreMap.get("topic5_g").intValue()+scoreMap.get("topic5_h").intValue()+scoreMap.get("topic5_i").intValue()+
			scoreMap.get("topic5_j").intValue();
			if(sum5b_5j>=19)
			{
				score5=3;
			}else if(sum5b_5j>=10)
			{
				score5=2;
			}
			else if(sum5b_5j>=1)
			{
				score5=1;
			}
			else if(sum5b_5j==0)
			{
				score5=0;
			}
			//Ⅵ催眠药物
			int score6=scoreMap.get("topic7").intValue();
			//Ⅶ日间功能障碍
			int score7=0;
			int sum8_9=scoreMap.get("topic8").intValue()+scoreMap.get("topic8").intValue();
			if(sum8_9>=5)
			{
				score7=3;
			}else if(sum8_9>=3)
			{
				score7=2;
			}
			else if(sum8_9>=1)
			{
				score7=1;
			}
			else if(sum8_9==0)
			{
				score7=0;
			}
			//PSQI总分=成份Ⅰ+成份Ⅱ+成份Ⅲ+成份Ⅳ+成份Ⅴ+成份Ⅵ+成份Ⅶ
			scorePSQI=score1+score2+score3+score4+score5+score6+score7;	
		}
		return scorePSQI;
	}
	
	public static int getBedTime(int upbedtime,int gotobedtime)
	{
		int getSleetpTime=0;
		if(upbedtime-gotobedtime>=0)
		{
			getSleetpTime=upbedtime-gotobedtime;
		}
		else
		{
			getSleetpTime=24-gotobedtime+upbedtime;
		}
		return getSleetpTime;
	}
}
