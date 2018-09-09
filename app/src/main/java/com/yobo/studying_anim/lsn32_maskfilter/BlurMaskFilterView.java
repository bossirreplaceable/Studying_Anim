package com.yobo.studying_anim.lsn32_maskfilter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yobo.studying_anim.R;

public class BlurMaskFilterView extends View {


    private Paint myPaint;
    private Bitmap bmp;

    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setColor(Color.RED);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 3;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.f_circle_pic, options);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //使用遮罩效果需要关闭 硬件加速效果（硬件加速效果会关闭一些图像渲染功能）
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        /*
         *模糊遮罩效果
         */
        @SuppressLint("DrawAllocation") BlurMaskFilter blur1 = new BlurMaskFilter(60, BlurMaskFilter.Blur.NORMAL);
        myPaint.setMaskFilter(blur1);
        canvas.drawBitmap(bmp, 0, 0, myPaint);
        @SuppressLint("DrawAllocation") BlurMaskFilter blur2 = new BlurMaskFilter(60, BlurMaskFilter.Blur.INNER);
        myPaint.setMaskFilter(blur2);
        canvas.drawBitmap(bmp, 500, 0, myPaint);
        @SuppressLint("DrawAllocation") BlurMaskFilter blur3 = new BlurMaskFilter(60, BlurMaskFilter.Blur.OUTER);
        myPaint.setMaskFilter(blur3);
        canvas.drawBitmap(bmp, 0, 500, myPaint);
        @SuppressLint("DrawAllocation") BlurMaskFilter blur14 = new BlurMaskFilter(60, BlurMaskFilter.Blur.SOLID);
        myPaint.setMaskFilter(blur14);
        canvas.drawBitmap(bmp, 500, 500, myPaint);
        /*
         *浮雕遮罩效果
         *  @param direction 光源位置 array of 3 scalars [x, y, z] specifying the direction of the light source
         * @param ambient    制定周围背景光源 0...1 amount of ambient light
         * @param specular   镜面反射系数coefficient for specular highlights (e.g. 8)
         * @param blurRadius 指定模糊半径amount to blur before applying lighting (e.g. 3)
         * @return           the emboss maskfilter
         */
        @SuppressLint("DrawAllocation") EmbossMaskFilter emboss1 = new EmbossMaskFilter(new float[]{0,1050,20},
                0.8f,8,50);
        myPaint.setMaskFilter(emboss1);
        canvas.drawBitmap(bmp, 0, 1000, myPaint);

    }
}
