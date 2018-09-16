package com.yobo.studying_anim.lsn34_canvas2;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

public class RevealDrawable extends Drawable {
    private Drawable unSelectDrawable;
    private int orientation;
    private Drawable selectDrawable;
    private int myLevel = 10000;

    public RevealDrawable(Drawable select, Drawable unSelect, int orientation) {
        this.selectDrawable = select;
        this.unSelectDrawable = unSelect;
        this.orientation = orientation;
    }
    /**
     * 1.从10000-5000-0 图片经历了从全灰-全彩-全灰的过程
     * 2.通过getIntrinsicWidth()方法将该view的大小固定成图片本身的大小
     * 3.然后通过onLevelChange()中level的变化，来绘制半灰半彩的效果
     * 4.通过图层的save()和reStore()方法可以消除裁剪后图层的大小问题
     * 5.
     *
     * @param canvas
     */
    @Override
    public void draw(@NonNull Canvas canvas) {

        myLevel=getLevel();

        if (myLevel == 10000) {
            unSelectDrawable.draw(canvas);
        } else if (myLevel == 5000) {
            selectDrawable.draw(canvas);
        } else if (myLevel <= 0) {
            unSelectDrawable.draw(canvas);
        } else {
            Rect bounds = getBounds();
            int w = bounds.width();
            int h = bounds.height();
            /*
             * radio 1到0,0到-1
             */
            float radio = (myLevel / 5000f) - 1f;

            //先绘制灰色的部分
            canvas.save();
            int widh = (int) (w * Math.abs(radio));
            Rect outRect = new Rect();
            @SuppressLint("RtlHardcoded") int gravity = radio>0?Gravity.RIGHT:Gravity.LEFT;
            Gravity.apply(gravity,widh,h,bounds,outRect);
            canvas.clipRect(outRect);
            unSelectDrawable.draw(canvas);
            canvas.restore();
            //绘制彩色部分
            canvas.save();
            int width1=w- (int) (w*Math.abs(radio));
            Rect outRect1 = new Rect();
            @SuppressLint("RtlHardcoded") int gravity1 = radio>0?Gravity.LEFT:Gravity.RIGHT;
            Gravity.apply(gravity1,width1,h,bounds,outRect1);
            canvas.clipRect(outRect1);
            selectDrawable.draw(canvas);
            canvas.restore();
        }


    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        selectDrawable.setBounds(bounds);
        unSelectDrawable.setBounds(bounds);

    }

    @Override
    protected boolean onLevelChange(int level) {
        invalidateSelf();
        return true;
    }

    @Override
    public int getIntrinsicHeight() {
        return Math.max(unSelectDrawable.getIntrinsicHeight(), selectDrawable.getIntrinsicHeight());
    }

    @Override
    public int getIntrinsicWidth() {
        return Math.max(unSelectDrawable.getIntrinsicWidth(), selectDrawable.getIntrinsicWidth());
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }



    @SuppressLint("WrongConstant")
    @Override
    public int getOpacity() {
        return 0;
    }
}
