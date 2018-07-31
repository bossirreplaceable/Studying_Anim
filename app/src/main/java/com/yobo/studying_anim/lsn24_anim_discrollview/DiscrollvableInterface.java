package com.yobo.studying_anim.lsn24_anim_discrollview;

public interface DiscrollvableInterface {

	/**
	 * 当滑动的时候调用该方法，用来控制里面的控件执行相应的动画
	 * @param ratio
	 */
	public void onDiscrollve(float ratio);

	/**
	 * 重置view的属性----恢复view的原来属性
	 */
	public void onResetDiscrollve();

}

