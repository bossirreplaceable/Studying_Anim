package com.yobo.studying_anim.lsn31_shaper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;


public class GradientView extends AppCompatTextView {


    private TextPaint mPaint;
    private float textWidth;
    private float deltaX = 20;
    private LinearGradient gradient;
    private Matrix matrix;
    private float translationX;

    private CompleteSongListener listener;

    public GradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }


    private void showLog(String msg) {
        Log.e("F--GradientView--", msg);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = getPaint();//获取绘制文字的画笔

        textWidth = mPaint.measureText(getText().toString());//计算出一个字体的宽度
        float oneTextWidht = textWidth / getText().toString().length();
        showLog("字体的宽度=" + oneTextWidht + ",字体的数量=" + getText().length() + ",字体的总宽度=" + textWidth
        +",内容的行数："+getLineCount()+",行数的高度："+getLineHeight()+",字体的大小："+getTextSize()+",控件的高度："+getMeasuredHeight());


        /*
        实例化一个线性渐变的渲染器
         */
        gradient = new LinearGradient(-2 * oneTextWidht, 0, 0, oneTextWidht,
                new int[]{0xff228B22, 0xffff0000, 0xffff0000}, new float[]{0, 0.5f, 1}, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
        matrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        translationX += deltaX;
        matrix.setTranslate(translationX, 0);
        gradient.setLocalMatrix(matrix);
        /*
         * 增加刷新的时间间隔，节省内存资源
         */

        if (translationX < textWidth + 100) {
            postInvalidateDelayed(130);
        } else {
            if (listener != null) {
                translationX = 0;
                listener.songCompelete();
            }
        }
    }

    public void setListener(CompleteSongListener listener) {
        this.listener = listener;
    }
    /**
     * 一句话渲染完成的监听，以便渲染下一句歌词
     */
    interface CompleteSongListener {
        void songCompelete();
    }
}
