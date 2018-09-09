package com.yobo.studying_anim.lsn31_shaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yobo.studying_anim.R;

public class F_Activity extends AppCompatActivity {
    CircleImageView circlePic;
    GradientView tv_song;
    private boolean isOpen = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_circle_activity);
        initView();
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
    private void initView() {
        tv_song = findViewById(R.id.f_song);
        circlePic = findViewById(R.id.f_circlePic);
        circlePic.setPicResourceID(R.drawable.f_circle_pic);
        tv_song.setText("不要停止思考啊，张-----");

        tv_song.setListener(new GradientView.CompleteSongListener() {
            @Override
            public void songCompelete() {
                if (isOpen) {
                    tv_song.setText("我需要刻意练习一下自己的注意力，和思考能力!!!");
                }
            }
        });
    }


    public void changeText(View v) {
        isOpen = !isOpen;
    }
}
