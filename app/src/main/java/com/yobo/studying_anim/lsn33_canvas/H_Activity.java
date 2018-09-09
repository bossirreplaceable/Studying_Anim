package com.yobo.studying_anim.lsn33_canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class H_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CanvasView canvasView=new CanvasView(this,null);
        setContentView(canvasView);
    }
}
