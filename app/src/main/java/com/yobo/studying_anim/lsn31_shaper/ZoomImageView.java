package com.yobo.studying_anim.lsn31_shaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yobo.studying_anim.R;

public class ZoomImageView extends View {


    private final static int SCALE = 2;
    private Bitmap bitmap;
    private final static int RADIUS = 230;
    private final ShapeDrawable drawable;
    private Matrix matrix=new Matrix();

    public ZoomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.f_circle_pic);
        /*
         *创建一个放大两倍的图片
         *filter=true,将放大后的图片过滤掉，只显示放大镜内部的影像
         */
        Bitmap bmp = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * SCALE, bitmap.getHeight() * SCALE, true);
        //创建一个圆形的放大镜渲染区域
        BitmapShader shader = new BitmapShader(bmp, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setShader(shader);
        drawable.setBounds(0, 0, RADIUS, RADIUS);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        drawable.draw(canvas);
        postInvalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        matrix.setTranslate(RADIUS-x* SCALE,RADIUS-y*SCALE);
        drawable.getPaint().getShader().setLocalMatrix(matrix);


        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                drawable.setBounds(0,0,0,0);
                invalidate();
                break;
            default:
                drawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
                invalidate();
                break;

        }

        drawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
        invalidate();

        return true;


    }
}
