package com.yobo.studying_anim.lsn34_canvas2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class RevealHorizontalScrollView extends HorizontalScrollView implements View.OnTouchListener {

    private LinearLayout linearLayout;
    private int item_width;

    public RevealHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(params);
        setOnTouchListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View child = linearLayout.getChildAt(0);
        item_width = child.getWidth();
        int centerX = getWidth() / 2;
        centerX = centerX - item_width / 2;
        linearLayout.setPadding(centerX, 0, centerX, 0);
    }
    public void addDrawables(Drawable[] realItems) {
        for (int i = 0; i < realItems.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setImageDrawable(realItems[i]);
            linearLayout.addView(iv);
            if (i == 0) {
                realItems[i].setLevel(5000);
            }
        }
        addView(linearLayout);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            reveal();
        }
        return false;
    }

    private void reveal() {
        int scrollX = getScrollX();
        int index_left = scrollX / item_width;
        int index_right = index_left + 1;
        float radio = 5000f / item_width;

        for (int i = 0; i < linearLayout.getChildCount(); i++) {

            //左边那个图片，level 0-5000；
            //右边那个图片，level 10000-5000；
            if (i == index_left || i == index_right) {
                ImageView iv_left = (ImageView) linearLayout.getChildAt(index_left);
                iv_left.setImageLevel((int) (5000f - scrollX % item_width * radio));
                if (index_right < linearLayout.getChildCount()) {
                    ImageView iv_right = (ImageView) linearLayout.getChildAt(index_right);
                    iv_right.setImageLevel((int) (10000 - scrollX % item_width * radio));
                }
            } else {
                ImageView iv = (ImageView) linearLayout.getChildAt(i);
                iv.setImageLevel(0);
            }
        }


    }
}
