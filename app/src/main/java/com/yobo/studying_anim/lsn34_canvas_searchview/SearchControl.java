package com.yobo.studying_anim.lsn34_canvas_searchview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class SearchControl extends BaseControler {
    private RectF rectF;
    private int cx, cy, cr;
    //j=cr-cr*cos45; 通过j我们可以得到把手与圆形的交点
    private int j;
    private int m=250;

    SearchControl() {
        rectF = new RectF();
    }
    @Override
    public void drawCanvas(Canvas canvas, Paint paint) {

        switch (mState) {
            case STATE_ANIM_NONE:
                drawNormalView(canvas, paint);
                break;
            case STATE_ANIM_START:
                drawStartView(canvas, paint);
                break;
            case STATE_ANIM_STOP:
                drawStopView(canvas, paint);
                break;
        }

    }

    private void drawStopView(Canvas canvas, Paint paint) {
    }

    /**
     * 开始绘制动画状态
     * 有三个部分
     * 1.圆形，在前0.5内逐渐的小时
     *  把手，在前0,5内只不断的跟着圆形移动
     * 2.在后0.5部分，圆形消失，把手会不断的变短至消失
     * 3.从0-1的过程中，放大镜下面一直会有一条逐渐变成的直线
     */
    private void drawStartView(Canvas canvas, Paint paint) {

        Log.e("SearchView-----","mpro="+mpro);
        //将放大镜位置定在屏幕的中央
        cx=getWidth()/2;
        cy=getHeight()/2;
        cr=getWidth()/20;
        j= (int) (cr-cr*Math.cos(45));

        //将核心参照物 不断的向右匀速移动
        rectF.left=cx-cr+m*mpro;
        rectF.right=cx+cr+m*mpro;
        rectF.top=cy-cr;
        rectF.bottom=cy+cr;

        canvas.save();
        //mpro变化值 0-0.5
        //弧形角度值 360-0（1-0）
        //把手长度不变
        if (mpro<0.5) {
            canvas.drawArc(rectF,45,-(1-2*mpro)*360,false,paint);
            canvas.drawLine(rectF.right-j,rectF.bottom-j,rectF.right+j,rectF.bottom+j,paint);
        }else{
            //mro变化值 0.5-1
            //把手长度 1-0  坐标在一直变大0-1
            //函数  2*mpro-1
            float k=2*mpro-1;
            canvas.drawLine(rectF.right-j+2*j*k,rectF.bottom-j+2*j*k,rectF.right+j,rectF.bottom+j,paint);
        }
        //绘制第三部分 一直在变长的直线
        //mpro变化值 0-1
        //直线的变化方式为，以初始化时，圆形的cx为中心点，向两侧扩张，那么startX会从 cx-250*mpro  ,stopX就是不断移动矩形把手的stopX。
        canvas.drawLine(cx-250*mpro,rectF.bottom+j,rectF.right+j,rectF.bottom+j,paint);

        canvas.restore();

    }

    //初始化状态的完整状态。
    private void drawNormalView(Canvas canvas, Paint paint) {
        //通过将画布保存和恢复，可以实现不断的清除前面绘制的图像，绘制新的图形和位置
        canvas.save();

        //将放大镜位置定在屏幕的中央
        cx=getWidth()/2;
        cy=getHeight()/2;
        cr=getWidth()/20;

        //绘制一个全圆的弧度
        rectF.left=cx-cr;
        rectF.right=cx+cr;
        rectF.top=cy-cr;
        rectF.bottom=cy+cr;
        canvas.drawArc(rectF,0,360,false,paint);

        //绘制一个放大镜的把手
        //通过旋转画布以后，你可以通过绘制横的直线来实现45偏移 的斜线
        canvas.rotate(45,cx,cy);
        canvas.drawLine(cx+cr,cy,cx+cr*2,cy,paint);
        canvas.restore();
    }

    @Override
    public void startAnim() {
        super.startAnim();
        mState = STATE_ANIM_START;
    }

    @Override
    public void stopAnim() {
        super.stopAnim();
        mState = STATE_ANIM_NONE;

    }
}
