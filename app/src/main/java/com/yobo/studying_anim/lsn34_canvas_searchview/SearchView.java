package com.yobo.studying_anim.lsn34_canvas_searchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class SearchView extends View {
    private Paint paint;
    private BaseControler controler;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint =new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        setBackgroundColor(Color.GREEN);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        controler.drawCanvas(canvas,paint);

    }
    public void setControler(BaseControler controler) {
        this.controler=controler;
        controler.setSearView(this);
        invalidate();
    }
    public void startAnim(){
        if (controler != null) {
            controler.startAnim();
        }
    }
    public void stopAnim(){
        if (controler != null) {
            controler.stopAnim();
        }
    }






}
