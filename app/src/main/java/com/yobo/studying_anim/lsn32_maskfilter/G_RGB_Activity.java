package com.yobo.studying_anim.lsn32_maskfilter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class G_RGB_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RGB_FilterView view = new RGB_FilterView(this, null);
        setContentView(view);
    }
}
