package com.yobo.studying_anim.lsn31_shaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.yobo.studying_anim.R;

public class CircleImageView extends AppCompatImageView {


    private Paint mPaint;
    private Bitmap bitmap;
    private int picResourceID;

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        /*
        通过自定义属性，获取到设置的图片内容
        BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(R.styleable.CircleImageView_my_src);
         */
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        picResourceID = a.getResourceId(R.styleable.CircleImageView_my_src, -1);
        if (picResourceID <= 0) {
            assert attrs != null;
            //如果自定义my_src没获取到图片，那通过系统默认方法获取ImageView 系统 src内的图片
            picResourceID = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);
        }
        showLog("获取到的图片ResourceId=" + picResourceID);

        a.recycle();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

    }


    private void showLog(String msg) {
        Log.e("F--circleImage--", msg);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        showLog("onDraw方法调用了");
        bitmap = BitmapFactory.decodeResource(getResources(), picResourceID);
        /*
        获取控件自身的大小，在onMeasure方法中获取不到
         */
        Bitmap pic;
        int vWidth = getMeasuredWidth();
        int vHeight = getMeasuredHeight();
            /*
        1.如果imageView 的宽高都比bitmap的宽高小，那就对图片进行缩小，缩小比例选最小个
        2.如果imageView 的宽高有一项比bitmap的宽高大，那就对图片就行放大，放大比例选最大那个
        3.如果是从自定义属性那里获取的图片，要对图片进行缩放，否则不缩放
         */
        int picWidth = bitmap.getWidth();
        int picheight = bitmap.getHeight();
        float scale;
        if (picheight > vHeight && picWidth > vWidth) {
            scale = Math.min((float) vHeight / picheight, (float) vWidth / picWidth);
        } else {
            scale = Math.max((float) vHeight / picheight, (float) vWidth / picWidth);
        }
        Matrix m = new Matrix();
        m.setScale(scale, scale);
        pic = Bitmap.createBitmap(bitmap, 0, 0, picWidth, picheight, m, true);
        bitmap = null;
        /*
         位图渲染模式
        Shader.TileMode.CLAMP;  从图片的x，y方向的最后一个像素开始拉伸，极光效果
        Shader.TileMode.MIRROR; 镜像填充
        Shader.TileMode.REPEAT; 重复填充
         */
        BitmapShader bitmapShader = null;
        if (pic != null) {
            bitmapShader = new BitmapShader(pic, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
            mPaint.setShader(bitmapShader);
        }
        //实现方式一
        //取宽高的最小值，作为圆的半径
        float radius = Math.min(picheight, picWidth) * scale / 2;
        //  通过勾股定理，算出斜边，斜边减去圆的直径/2，得到画笔的宽度
        double hypot = Math.hypot(vHeight, vWidth);
        float paintWidth = ((float) hypot - 2 * radius) / 2;
        showLog("缩放的比例=" + scale + "画笔的宽度=" + paintWidth + ",控件的宽度=" + vWidth + ",控件的高度" + vHeight + ",图片的高度" + picWidth + ",图片的高度" + picheight);
        mPaint.setStrokeWidth(paintWidth);
        canvas.drawCircle(picWidth * scale / 2f, picheight * scale / 2f, radius, mPaint);
        //如果不设置背景，会出现重叠现象

        //实现方式二
//        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//        shapeDrawable.getPaint().setShader(bitmapShader);
//        shapeDrawable.setBounds(0,0,Math.min(vHeight,vWidth),Math.min(vHeight,vWidth));
//        shapeDrawable.draw(canvas);
    }


    /**
     * 可以将图片的资源方式改变，直接从网络获取图片，进行设置
     *
     * @param picResourceID
     */
    public void setPicResourceID(int picResourceID) {
        this.picResourceID = picResourceID;
    }
}
