package com.apps.libs.alanfortlink.androidsimpleanimationeffects;

import android.view.animation.Animation.AnimationListener;

import com.apps.libs.alanfortlink.androidsimpleanimationeffects.SimpleAnimation.AnimationActionListener;
import com.apps.libs.alanfortlink.androidsimpleanimationeffects.SimpleAnimation.MultiAnimationActionListener;

public class Animation extends android.view.animation.Animation implements AnimationListener{
	
	AnimationActionListener action;
	
	public Animation(final SimpleAnimation.AnimationActionListener action){
		this.action = action;
	}

	@Override
	public void onAnimationStart(android.view.animation.Animation animation) {
		if(action instanceof MultiAnimationActionListener){
			((MultiAnimationActionListener) action).onAnimationBegin();
		}
	}
	
	@Override
	public void onAnimationRepeat(android.view.animation.Animation animation) {
		
		
	}
	
	@Override
	public void onAnimationEnd(android.view.animation.Animation animation) {
		if(action != null){
			action.run();
		}
		if(action instanceof MultiAnimationActionListener){
			((MultiAnimationActionListener) action).onAnimationEnd();
		}
	}

}
