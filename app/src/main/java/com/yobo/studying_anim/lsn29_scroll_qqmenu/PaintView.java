package com.yobo.studying_anim.lsn29_scroll_qqmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PaintView extends View {


    private Paint mPaint;

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(255); //0-255 全透明-不透明

        /*
        设置画笔的样式
         */
//        mPaint.setStyle(Paint.Style.FILL);//填充
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStyle(Paint.Style.STROKE);//描边
        //设置画笔的宽度
        mPaint.setStrokeWidth(50);
        /*
        设置线帽的样式
         */
//        mPaint.setStrokeCap(Paint.Cap.BUTT);//没有效果
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形的帽子
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);//方形的帽子
        /*
        设置线条交界的形状
         */
//        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆弧
//        mPaint.setStrokeJoin(Paint.Join.MITER);//锐角，三角形交界处
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);//直线
//

        @SuppressLint("DrawAllocation") Path path=new Path();
        path.moveTo(100,100); //先移动到绘制的起点
        path.lineTo(300,100);
        path.lineTo(100,300);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path,mPaint);

        path.moveTo(100,400);
        path.lineTo(300,400);
        path.lineTo(100,700);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path,mPaint);

        path.moveTo(100,800);
        path.lineTo(300,800);
        path.lineTo(100,1200);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path,mPaint);








    }
}
