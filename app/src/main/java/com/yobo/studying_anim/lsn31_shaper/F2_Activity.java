package com.yobo.studying_anim.lsn31_shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class F2_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GradientView1 zoomView = new GradientView1(this, null);
        setContentView(zoomView);
    }
}
