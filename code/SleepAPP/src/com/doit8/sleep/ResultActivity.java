package com.doit8.sleep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Intent intent=getIntent();
        int allScore=intent.getIntExtra("allScore", 0);
        TextView tvResult3=(TextView)findViewById(R.id.tvResult3);
        tvResult3.setText("您的匹兹堡睡眠质量指数分为："+allScore+"，≥8分为存在睡眠障碍，最高分为21分，得分越高，睡眠障碍情况越严重！");
        
	}
}
