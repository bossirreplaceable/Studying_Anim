package com.yobo.studying_anim.lsn34_canvas_searchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yobo.studying_anim.R;

public class J_Activity extends AppCompatActivity  {


    private SearchView searchView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_activity);
        searchView=findViewById(R.id.j_search);
        searchView.setControler(new SearchControl());
    }

    public void startAnim(View v){
        searchView.startAnim();
    }

    public void resetAnim(View v){
        searchView.stopAnim();
    }





}
