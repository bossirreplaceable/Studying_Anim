package com.yobo.studying_anim.lsn33_canvas;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yobo.studying_anim.R;

public class CanvasView extends View {


    private Paint paint;
    private Bitmap bmp;

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.argb(255, 100, 50, 255));
        paint.setStrokeWidth(12);
        paint.setStyle(Paint.Style.STROKE);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 6;
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.g_girl, options);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
         *canvas 可以画点、直线、矩形、圆角矩形、圆、椭圆等各种图形。
         */
        canvas.drawLine(10, 10, 200, 10, paint);
        canvas.drawPoint(220, 10, paint);
        canvas.drawRect(250, 10, 500, 200, paint);
        canvas.drawCircle(375, 105, 95, paint);
        canvas.drawOval(0, 50, 200, 200, paint);
        canvas.drawRoundRect(new RectF(0, 50, 200, 200), 20, 20, paint);
//        canvas.drawTextRun(new char[]{'你','好','啊','小','子'},0,5,0,4,600,50,true,paint);
//        canvas.drawArc();
        /*
         * Path 也可以实现上面的各种图形
         */
        Path path = new Path();
//        path.lineTo();
        path.cubicTo(550, 10, 700, 200, 990, 10);
//        path.cubicTo();//贝塞尔曲线
//        path.addCircle();//圆形
//        path.addRect();//矩形
//        path.addRoundRect();//圆角矩形
//        path.addOval();//椭圆
//        path.addArc();//圆弧
//        path.addPath(path);
        canvas.drawPath(path, paint);

        /*
         * 结合区域迭代器使用（得到图形里面的所有的矩形区域）
         */
        //创建一块矩形的区域
//		Region region = new Region(100, 100, 400, 500);
//		Region region1 = new Region();
//		region1.setPath(path, region);//path的椭圆区域和矩形区域进行交集
//
//		//结合区域迭代器使用（得到图形里面的所有的矩形区域）
//		RegionIterator iterator = new RegionIterator(region1);
//
//		Rect rect = new Rect();
//		while (iterator.next(rect)) {
//			canvas.drawRect(rect, paint);
//		}
//		//合并
//		region.union(r);
//		region.op(r, Op.INTERSECT);//交集部分

        //--------------------Canvas变换技巧--------------------------
        //1.平移（Translate）
		RectF r = new RectF(0, 250, 300, 400);
		canvas.drawRect(r, paint);
		//将画布平移
		canvas.translate(90, 50);
		paint.setColor(Color.RED);
		//当canvas执行drawXXX的时候就会新建一个新的画布图层
		canvas.drawRect(r, paint);

		RectF r2 = new RectF(0, 250, 300, 400);
        paint.setColor(Color.BLUE);
//虽然新建了一个画布图层，但是还是会沿用之前设置的平移变换。不可逆的。（save和restore来解决）
		canvas.drawRect(r2, paint);


        //2.缩放Scale
		RectF r3 = new RectF(600, 250, 900, 500);
		canvas.drawRect(r3, paint);
		paint.setColor(Color.BLUE);
//        sx,sy：分别对x/y方向的一个缩放系数,画布的缩放会导致里面所有的绘制的东西都会有一个缩放效果
		canvas.scale(0.5f, 0.5f);
        paint.setColor(Color.BLUE);
		canvas.drawRect(r3, paint);


        canvas.scale(2f, 2f);
//        3.旋转Rotate
		RectF r4 = new RectF(0, 600, 300, 900);
		canvas.drawRect(r4, paint);
		paint.setColor(Color.RED);
		canvas.rotate(45, 150, 750);
		paint.setColor(Color.BLUE);
		canvas.drawRect(r4, paint);

        canvas.rotate(-45, 150, 750);
        //4.斜拉画布Skew
		RectF r5 = new RectF(400, 600, 750, 1000);
		canvas.drawRect(r5, paint);
		paint.setColor(Color.BLUE);
		//sx,sy倾斜度：X轴方向上倾斜60度，tan60=根号3
		canvas.skew(1.73f, 0);
		canvas.drawRect(r5, paint);
        //5.裁剪画布clip
        RectF r6 = new RectF(0, 1000, 400, 1400);
        canvas.drawRect(r6, paint);
        paint.setColor(Color.BLUE);
        canvas.clipRect(new Rect(0, 1000, 200, 1200));
        canvas.drawColor(Color.YELLOW);


    }
}
