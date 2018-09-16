package com.yobo.studying_anim;

import android.content.Intent;
import android.icu.util.JapaneseCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yobo.studying_anim.lsn24_anim_discrollview.A_Activity;
import com.yobo.studying_anim.lsn25_anim_parallaxsplash.SplashActivity;
import com.yobo.studying_anim.lsn29_scroll_qqmenu.D_Activity;
import com.yobo.studying_anim.lsn29_scroll_qqmenu.D_Activity1;
import com.yobo.studying_anim.lsn29_scroll_qqmenu.D_Activity2;
import com.yobo.studying_anim.lsn31_shaper.F1_Activity;
import com.yobo.studying_anim.lsn31_shaper.F2_Activity;
import com.yobo.studying_anim.lsn31_shaper.F_Activity;
import com.yobo.studying_anim.lsn32_maskfilter.G_Activity;
import com.yobo.studying_anim.lsn32_maskfilter.G_RGB_Activity;
import com.yobo.studying_anim.lsn33_canvas.H_Activity;
import com.yobo.studying_anim.lsn34_canvas2.I_Activity;
import com.yobo.studying_anim.lsn34_canvas_searchview.J_Activity;
import com.yobo.studying_anim.lsn35_wave_path.K_Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 动画框架，给每个子View 添加一个父View
     * @param v
     */
    public void clickAnim1(View v) {
        Intent intent = new Intent(this, A_Activity.class);
        startActivity(intent);
    }

    /**
     * 动画框架，重写layoutInfalte
     * @param v
     */
    public void clickAnim2(View v) {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }

    /**
     * 侧滑菜单的动画效果
     * @param v
     */
    public void slideMenu(View v) {

        Intent intent = new Intent(this, D_Activity.class);
        startActivity(intent);

    }

    /**
     * 滑动删除的 弹回效果
     * @param v
     */
    public void slideDelete(View v) {

        Intent intent = new Intent(this, D_Activity1.class);
        startActivity(intent);
    }

    /**
     * Paint画笔的高级技能
     * 渲染 Shader：
     * BimapShader位图的图像渲染器
     *     圆形头像，镜像，重复图片等效果
     * LinearGradient线性渲染
     *    歌词渲染
     * RadialGradient环形渲染
     *     水波纹效果，充电水波纹扩散效果、调色板
     * SweepGradient梯度渲染(扫描渲染)
     *     微信等雷达扫描效果。手机卫士垃圾扫描
     * ComposeShader组合渲染
     * <p>
     * 可以绘制图片、颜色块、文字
     * canvas.drawCircle()
     * canvas.drawRect()
     * canvas.drawOval()
     * <p>
     * 作业：实现水波纹效果，充电水波纹扩散效果
     * 作业2：微信等雷达扫描效果。手机卫士垃圾扫描
     */
    public void clickPaint(View v) {
        Intent intent = new Intent(this, D_Activity2.class);
        startActivity(intent);
    }

    public void circlePic(View v) {
        Intent intent = new Intent(this, F_Activity.class);
        startActivity(intent);
    }

    /**
     * 放大镜效果
     * @param v
     */
    public void zoomImage(View v) {
        Intent intent = new Intent(this, F1_Activity.class);
        startActivity(intent);
    }
    public void gradient(View v) {
        Intent intent = new Intent(this, F2_Activity.class);
        startActivity(intent);
    }
    public void blurMaskFilter(View v) {
        Intent intent = new Intent(this, G_Activity.class);
        startActivity(intent);
    }
    public void clickCanvas(View v) {
        Intent intent = new Intent(this, H_Activity.class);
        startActivity(intent);
    }
    public void clickCanvas1(View v) {
        Intent intent = new Intent(this, I_Activity.class);
        startActivity(intent);
    }
    public void rgbFilter(View v) {
        Intent intent = new Intent(this, G_RGB_Activity.class);
        startActivity(intent);
    }
    public void searchView(View v){
        Intent intent=new Intent(this, J_Activity.class);
        startActivity(intent);
    }
    public void waveView(View v){
        Intent intent=new Intent(this, K_Activity.class);
        startActivity(intent);
    }
}


