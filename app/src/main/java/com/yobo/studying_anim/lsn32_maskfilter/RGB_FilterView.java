package com.yobo.studying_anim.lsn32_maskfilter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yobo.studying_anim.R;

/**
 * 《滤镜效果和颜色通道过滤》
 * <p>
 * 1.自定义控件
 * 2.动画--属性动画、补间动画、自绘动画（ValueAnimator+onDraw）
 * 3.渲染效果--高级渲染、滤镜效果、颜色通道过滤（矩阵变换--高等数学，颜色矩阵）
 * <p>
 * 一、滤镜效果
 * 对图像进行一定的过滤加工处理。使用Paint设置滤镜效果。
 * 1.Alpha滤镜处理
 * MaskFilter
 * paint.setMaskFilter(maskfilter)
 * (1).模糊遮罩滤镜（BlurMaskFilter）
 * (2).浮雕遮罩滤镜（EmbossMaskFilter）
 * <p>
 * 2.颜色RGB的滤镜处理
 * ColorMatrix
 * 滤镜的所有处理效果都是通过颜色矩阵的变换实现的。
 * 比如：美颜相机实现的特效（高光、复古、黑白）
 * <p>
 * （1）什么是矩阵？
 * R
 * G>>1
 * B>>2
 * <p>
 * (2)例子：通过矩阵变换讲一个图片、颜色块，过滤掉其中的红色、绿色（只留下蓝色）
 * <p>
 * （3）色彩运算
 * 1.色彩的平移运算（加法运算）
 * <p>
 * 2.色彩的缩放运算（乘法运算）
 * <p>
 * 运用：反相效果
 * RGB=100，200，250
 * RGB=155，55，5
 * 效果：1.黑白图片处理
 * 2.色彩发色效果
 * <p>
 * 研究ColorMatrix的API
 * 1、构造方法
 * //		ColorMatrix matrix = new ColorMatrix(new float[]{
 * //		ColorMatrix matrix = new ColorMatrix();
 * //		matrix.set(src)
 * 2.设置色彩的缩放函数
 * matrix.setScale(1, 1, 1.4f, 1);
 * 3.设置饱和度
 * //饱和度设置（1，是原来不变；0灰色；>1增加饱和度）
 * matrix.setSaturation(progress);
 * 4.色彩旋转函数
 * /**
 * axis,代表绕哪一个轴旋转，0,1,2
 * (0红色轴，1绿色，2蓝色)
 * degrees：旋转的度数
 */

/**
 * matrix.setRotate(0, progress);
 * 5.ColorFilter使用的子类
 * ColorMatrixColorFilter：色彩矩阵的颜色顾虑器。
 * LightingColorFilter：过滤颜色和增强色彩的方法。（光照颜色过滤器）
 * PorterDuffColorFilter：图形混合滤镜（图形学的一个理论飞跃）
 * <p>
 * Xfermode
 * <p>
 * 作业：把图片里面的红绿蓝分别抽出显示颜色。
 */

public class RGB_FilterView extends View {

    private Paint paint;
    private Bitmap bmp;

    public RGB_FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 5;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.g_girl, options);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        //半透明化图片
        ColorMatrix matrix = new ColorMatrix(new float[]{
                1f, 0, 0, 0, 0,
                0, 1f, 0, 0, 0,
                0, 0, 1f, 0, 0,
                0, 0, 0, 0.5f, 0
        });
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(bmp, 0, 0, paint);
        //去掉所有红色
        ColorMatrix matrix1 = new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,
                0, 1f, 0, 0, 0,
                0, 0, 1f, 0, 0,
                0, 0, 0, 1f, 0
        });
        paint.setColorFilter(new ColorMatrixColorFilter(matrix1));
        canvas.drawBitmap(bmp, 500, 0, paint);
        //反相，255-rgb的值=相反的rgb值
        ColorMatrix matrix2 = new ColorMatrix(new float[]{
                -1f, 0, 0, 0, 255,
                0, -1f, 0, 0, 255,
                0, 0, -1f, 0, 255,
                0, 0, 0, 1f, 0
        });
        paint.setColorFilter(new ColorMatrixColorFilter(matrix2));
        canvas.drawBitmap(bmp, 0, 400, paint);
        //颜色增强效果（可以看作是美白效果）---颜色的缩放效果
        ColorMatrix matrix3 = new ColorMatrix(new float[]{
                1.3f, 0, 0, 0, 0,
                0, 1.3f, 0, 0, 0,
                0, 0, 1.3f, 0, 0,
                0, 0, 0, 1f, 0
        });
        paint.setColorFilter(new ColorMatrixColorFilter(matrix3));
        canvas.drawBitmap(bmp, 500, 0, paint);
        //处理图片为黑白图片（图像学：如何让图片成为灰色即黑白？R+G+B=1）
        /**
         *
         去色原理：只要把RGB三通道的色彩信息设置成一样；即：R＝G
         ＝B，那么图像就变成了灰色，并且，为了保证图像亮度不变，
         同一个通道中的R+G+B=1:如：0.213+0.715+0.072＝1；
         RGB=0.213, 0.715, 0.072；
         三个数字是根据色彩光波频率及色彩心理学计算出来的.
         */

        ColorMatrix matrix4 = new ColorMatrix(new float[]{
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0, 0, 0, 1f, 0
        });
//        paint.setColorFilter(new ColorMatrixColorFilter(matrix4));
//        canvas.drawBitmap(bmp, 500, 400, paint);
        //反色效果，比如将红色和绿色交换位置
        ColorMatrix matrix5 = new ColorMatrix(new float[]{
                0, 1f, 0, 0, 0,
                1f, 0, 0, 0, 0,
                0, 0, 1f, 0, 0,
                0, 0, 0, 1f, 0
        });
        paint.setColorFilter(new ColorMatrixColorFilter(matrix5));
        canvas.drawBitmap(bmp, 0, 800, paint);

        //复古效果
        ColorMatrix matrix6 = new ColorMatrix(new float[]{
                1 / 2f, 1 / 2f, 1 / 2f, 0, 0,
                1 / 3f, 1 / 3f, 1 / 3f, 0, 0,
                1 / 4f, 1 / 4f, 1 / 4f, 0, 0,
                0, 0, 0, 1f, 0
        });
//        paint.setColorFilter(new ColorMatrixColorFilter(matrix6));
//        canvas.drawBitmap(bmp, 500, 800, paint);

        /**
         * 上面用的用的是核心理论的基本方法实现的颜色矩阵变换
         * google其实已经给我们封装好了上面的方法
         */

        //颜色饱和度函数，0-灰色，1-原色，>1过度饱和
        //代替上面的矩阵实现灰色图片效果
        ColorMatrix matrix8=new ColorMatrix();
        matrix8.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix8));
        canvas.drawBitmap(bmp, 500, 400, paint);

        //颜色矩阵缩放，比如将上面的复古效果用下面的缩放函数实现为：
        ColorMatrix matrix7=new ColorMatrix();
        matrix7.setScale(1/2f,1/3f,1/4f,1);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix7));
        canvas.drawBitmap(bmp, 500, 800, paint);

        //颜色旋转函数，将R(0)、G(1)、B(2)分别代替xyz轴，以R为中心轴时图像将在绿色和蓝色之间来回变动
        ColorMatrix matrix9=new ColorMatrix();
        matrix9.setRotate(1,30f);
//        paint.setColorFilter(new ColorMatrixColorFilter(matrix9));
//        canvas.drawBitmap(bmp,0,1200,paint);

        /**
         * 上面全用的是ColorMatrixColorFilter 色彩矩阵的颜色过滤器
         * LightingColorFilter 过滤颜色和增强颜色的函数 （可用于模拟简单照明效果的滤色镜。）
         * 适用于点击按钮的颜色变化效果
         * mul 要过滤的颜色
         * add 将该颜色增强多少
         */
        LightingColorFilter colorFilter=new LightingColorFilter(0x0000ff,100);
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(bmp,500,1200,paint);

        /**
         *
         *PorterDuffColorFilter 一种滤色器，可用于使用单一颜色和特定颜色对源像素进行着色
         *类似模板效果
         */
        PorterDuffColorFilter duffColorFilter=new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        paint.setColorFilter(duffColorFilter);
        canvas.drawBitmap(bmp,0,1200,paint);


    }
}