package com.yobo.studying_anim.lsn34_canvas2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yobo.studying_anim.R;

public class I_Activity extends AppCompatActivity {


    private ImageView iv1;
    private RevealDrawable reveal;
    private int level = 10000;
    private ImageView iv;
    private int[] mImgIds = new int[] { //7涓?
            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline,

            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline
    };
    private int[] mImgIds_active = new int[] {
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active,
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active
    };

    public Drawable[] revealDrawables;
    private RevealHorizontalScrollView hzv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i_activity);
        initData();
        initView();
    }


    private void initData(){
        revealDrawables = new Drawable[mImgIds.length];
    }

    private void initView()
    {
        for (int i = 0; i < mImgIds.length; i++)
        {
            RevealDrawable rd = new RevealDrawable(
                    getResources().getDrawable(mImgIds_active[i]),
                    getResources().getDrawable(mImgIds[i]),
                    0);
            revealDrawables[i] = rd;
        }
        hzv =  findViewById(R.id.i_scrollview);
        hzv.addDrawables(revealDrawables);
    }


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.i_activity);
//        iv1 = findViewById(R.id.i_iv);
//        Drawable selectDrawable = getResources().getDrawable(R.drawable.bullseye_active);
//        Drawable unSelctDrawable = getResources().getDrawable(R.drawable.bullseye);
//        reveal = new RevealDrawable(selectDrawable, unSelctDrawable, 0);
//        iv1.setImageDrawable(reveal);
//        iv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                level -= 500;
//                reveal.setLevel(level);
//            }
//        });
//
//    }
}
