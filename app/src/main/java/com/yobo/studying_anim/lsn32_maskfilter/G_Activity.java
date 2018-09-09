package com.yobo.studying_anim.lsn32_maskfilter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class G_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlurMaskFilterView view = new BlurMaskFilterView(this, null);
        setContentView(view);
    }
}
