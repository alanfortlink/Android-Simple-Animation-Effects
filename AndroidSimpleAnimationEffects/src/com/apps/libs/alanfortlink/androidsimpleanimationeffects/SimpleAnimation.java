package com.apps.libs.alanfortlink.androidsimpleanimationeffects;

import android.content.Context;
import android.graphics.Interpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SimpleAnimation {

	public enum Anim{SLIDE_LEFT_RIGHT, SLIDE_RIGHT_LEFT, SLIDE_TOP_BOTTOM, SLIDE_BOTTOM_TOP, FADE};
	
	public static void animate(Context context, final ViewGroup container, final View actualView, final View newView, Anim animType, final AnimationAction action){
		Animation[] anims = getAnimations(context, animType);
		
		final Animation animIn = anims[0];
		final Animation animOut = anims[1];
		
		animOut.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				if(action != null){
					action.run();
				}
				
				container.removeView(actualView);
				container.addView(newView);
				
				newView.startAnimation(animIn);
			}
		});
		
		actualView.startAnimation(animOut);
	}
	
	public interface AnimationAction{
		public void run();
	}

	public static Animation[] getAnimations(Context context, Anim animType){
		int idIn, idOut;

		switch(animType){

		case SLIDE_LEFT_RIGHT:
			idIn = R.anim.slide_left_center;
			idOut = R.anim.slide_center_right;

			break;

		case SLIDE_RIGHT_LEFT:
			idIn = R.anim.slide_right_center;
			idOut = R.anim.slide_center_left;

			break;

		case SLIDE_TOP_BOTTOM:
			idIn = R.anim.slide_top_center;
			idOut = R.anim.slide_center_bottom;

			break;

		case SLIDE_BOTTOM_TOP:
			idIn = R.anim.slide_bottom_center;
			idOut = R.anim.slide_center_top;

			break;
			
		case FADE:
			idIn = R.anim.fade_in;
			idOut = R.anim.fade_out;
			
			break;

		default:
			idIn = R.anim.slide_left_center;
			idOut = R.anim.slide_center_right;

			break;
		}

		Animation animations[] = new Animation[]{
				AnimationUtils.loadAnimation(context, idIn),
				AnimationUtils.loadAnimation(context, idOut)
		};
		
		return animations;

	}
}
