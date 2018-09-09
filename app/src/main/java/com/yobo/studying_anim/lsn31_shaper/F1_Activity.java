package com.yobo.studying_anim.lsn31_shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class F1_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZoomImageView zoomView = new ZoomImageView(this, null);
        setContentView(zoomView);
    }
}
