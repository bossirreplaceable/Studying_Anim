package com.yobo.studying_anim.lsn31_shaper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GradientView1 extends View {

    private final Paint paint;
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    private int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
    private ComposeShader composeShader;


    public GradientView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        radialGradient = new RadialGradient(200, 200, 100, colors, null, Shader.TileMode.REPEAT);
		paint.setShader(radialGradient);
		canvas.drawCircle(200, 200, 200, paint);
//
		sweepGradient = new SweepGradient(600, 600, colors, null);
		paint.setShader(sweepGradient);
		canvas.drawCircle(600, 600, 200, paint);

		composeShader = new ComposeShader(radialGradient, sweepGradient, PorterDuff.Mode.SRC_OVER);
        paint.setShader(composeShader);
        canvas.drawRect(700, 700, 1000, 1000, paint);

    }
}
