package com.yobo.studying_anim.lsn25_anim_parallaxsplash;

/**
 * 视差动画播放时参数的控制
 */
public class ParallaxViewTag {

	private int index;
	protected float xIn;
	protected float xOut;
	protected float yIn;
	protected float yOut;
	protected float alphaIn;
	protected float alphaOut;
	
	
	@Override
	public String toString() {
		return "ParallaxViewTag [index=" + index + ", xIn=" + xIn + ", xOut="
				+ xOut + ", yIn=" + yIn + ", yOut=" + yOut + ", alphaIn="
				+ alphaIn + ", alphaOut=" + alphaOut + "]";
	}

	
}
