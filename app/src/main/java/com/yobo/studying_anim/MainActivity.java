package com.yobo.studying_anim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yobo.studying_anim.lsn24_anim_discrollview.A_Activity;
import com.yobo.studying_anim.lsn25_anim_parallaxsplash.SplashActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickAnim1(View v) {
        Intent intent = new Intent(this, A_Activity.class);
        startActivity(intent);
    }

    public void clickAnim2(View v) {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }


}


