package com.doit8.sleep.pojo;

import com.doit8.sleep.R;
import android.content.Context;
import android.content.res.Resources;


public class SubjectFactory{
	private static Subject subject1;
	private static Subject subject2;
	private static Subject subject3;
	private static Subject subject4;
	private static Subject subject5_a;
	private static Subject subject5_b;
	private static Subject subject5_c;
	private static Subject subject5_d;
	private static Subject subject5_e;
	private static Subject subject5_f;
	private static Subject subject5_g;
	private static Subject subject5_h;
	private static Subject subject5_i;
	private static Subject subject5_j;
	private static Subject subject6;
	private static Subject subject7;
	private static Subject subject8;
	private static Subject subject9;
	public static Subject createSubjects(Context context) {
		Resources res=context.getResources();
		if(subject1==null)
		{
			subject1=new Subject("topic1",res.getString(R.string.topic1),res.getStringArray(R.array.SleepTime));
		}
		if(subject2==null)
		{
			subject2=new Subject("topic2",res.getString(R.string.topic2),res.getStringArray(R.array.ToSleepTime));
			subject2.setPreviouSubject(subject1);
		}
		if(subject3==null)
		{
			subject3=new Subject("topic3",res.getString(R.string.topic3),res.getStringArray(R.array.SleepTime));
			subject3.setPreviouSubject(subject2);
		}
		if(subject4==null)
		{
			subject4=new Subject("topic4",res.getString(R.string.topic4),res.getStringArray(R.array.ActualSleepTime));
			subject4.setPreviouSubject(subject3);
		}
		if(subject5_a==null)
		{
			subject5_a=new Subject("topic5_a",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_a),res.getStringArray(R.array.SleepTrouble));
			subject5_a.setPreviouSubject(subject4);
		}
		if(subject5_b==null)
		{
			subject5_b=new Subject("topic5_b",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_b),res.getStringArray(R.array.SleepTrouble));
			subject5_b.setPreviouSubject(subject5_a);
		}
		if(subject5_c==null)
		{
			subject5_c=new Subject("topic5_c",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_c),res.getStringArray(R.array.SleepTrouble));
			subject5_c.setPreviouSubject(subject5_b);
		}
		if(subject5_d==null)
		{
			subject5_d=new Subject("topic5_d",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_d),res.getStringArray(R.array.SleepTrouble));
			subject5_d.setPreviouSubject(subject5_c);
		}
		if(subject5_e==null)
		{
			subject5_e=new Subject("topic5_e",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_e),res.getStringArray(R.array.SleepTrouble));
			subject5_e.setPreviouSubject(subject5_d);
		}
		if(subject5_f==null)
		{
			subject5_f=new Subject("topic5_f",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_f),res.getStringArray(R.array.SleepTrouble));
			subject5_f.setPreviouSubject(subject5_e);
		}
		if(subject5_g==null)
		{
			subject5_g=new Subject("topic5_g",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_g),res.getStringArray(R.array.SleepTrouble));
			subject5_g.setPreviouSubject(subject5_f);
		}
		if(subject5_h==null)
		{
			subject5_h=new Subject("topic5_h",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_h),res.getStringArray(R.array.SleepTrouble));
			subject5_h.setPreviouSubject(subject5_g);
		}
		if(subject5_i==null)
		{
			subject5_i=new Subject("topic5_i",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_i),res.getStringArray(R.array.SleepTrouble));
			subject5_i.setPreviouSubject(subject5_h);
		}
		if(subject5_j==null)
		{
			subject5_j=new Subject("topic5_j",res.getString(R.string.topic5)+"\n"+res.getString(R.string.topic5_j),res.getStringArray(R.array.SleepTrouble));
			subject5_j.setPreviouSubject(subject5_i);
		}
		if(subject6==null)
		{
			subject6=new Subject("topic6",res.getString(R.string.topic6),res.getStringArray(R.array.SleepQuality));
			subject6.setPreviouSubject(subject5_j);
		}
		if(subject7==null)
		{
			subject7=new Subject("topic7",res.getString(R.string.topic7),res.getStringArray(R.array.SleepTrouble));
			subject7.setPreviouSubject(subject6);
		}
		if(subject8==null)
		{
			subject8=new Subject("topic8",res.getString(R.string.topic8),res.getStringArray(R.array.SleepTrouble));
			subject8.setPreviouSubject(subject7);
		}
		if(subject9==null)
		{
			subject9=new Subject("topic9",res.getString(R.string.topic9),res.getStringArray(R.array.NoEnoughEnergy));
			subject9.setPreviouSubject(subject8);
		}
		subject1.setNextSubject(subject2);
		subject2.setNextSubject(subject3);
		subject3.setNextSubject(subject4);
		subject4.setNextSubject(subject5_a);
		subject5_a.setNextSubject(subject5_b);
		subject5_b.setNextSubject(subject5_c);
		subject5_c.setNextSubject(subject5_d);
		subject5_d.setNextSubject(subject5_e);
		subject5_e.setNextSubject(subject5_f);
		subject5_f.setNextSubject(subject5_g);
		subject5_g.setNextSubject(subject5_h);
		subject5_h.setNextSubject(subject5_i);
		subject5_i.setNextSubject(subject5_j);
		subject5_j.setNextSubject(subject6);
		subject6.setNextSubject(subject7);
		subject7.setNextSubject(subject8);
		subject8.setNextSubject(subject9);
		return subject1;
	}
	
}
