package com.yobo.studying_anim.lsn25_anim_parallaxsplash;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.yobo.studying_anim.R;

public class SplashActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b_activity);
		
		ParallaxContainer container =  findViewById(R.id.parallax_container);
		container.setUp(R.layout.view_intro_1,
				R.layout.view_intro_2,
				R.layout.view_intro_3,
				R.layout.view_intro_4,
				R.layout.view_intro_5,
				R.layout.view_login);
		
		//设置动画
		ImageView iv_man =  findViewById(R.id.iv_man);
		iv_man.setBackgroundResource(R.drawable.b_man_run);
		container.setIv_man(iv_man);
		
	}


}
