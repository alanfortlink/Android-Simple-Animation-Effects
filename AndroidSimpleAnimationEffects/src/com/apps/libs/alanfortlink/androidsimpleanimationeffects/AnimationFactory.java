package com.apps.libs.alanfortlink.androidsimpleanimationeffects;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.apps.libs.alanfortlink.androidsimpleanimationeffects.SimpleAnimation.Anim;
import com.apps.libs.alanfortlink.androidsimpleanimationeffects.R;

public class AnimationFactory {
	
	public static Animation[] getAnimations(Context context, Anim animType, int duration){
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

		case FLIP_HORIZONTAL:
			idIn = R.anim.flip_horizontal_to_edges;
			idOut = R.anim.flip_horizontal_to_center;

			break;

		case FLIP_VERTICAL:
			idIn = R.anim.flip_vertical_to_edges;
			idOut = R.anim.flip_vertical_to_center;

			break;
			
		case SCALE_HORIZONTAL:
			idIn = R.anim.scale_in_horizontal;
			idOut = R.anim.scale_out_horizontal;

			break;
			
		case SCALE_VERTICAL:
			idIn = R.anim.scale_in_vertical;
			idOut = R.anim.scale_out_vertical;

			break;
			
		case SCALE_FULL:
			idIn = R.anim.scale_in_full;
			idOut = R.anim.scale_out_full;

			break;

		default:
			idIn = R.anim.fade_in;
			idOut = R.anim.fade_out;

			break;
		}

		Animation animations[] = new Animation[]{
				AnimationUtils.loadAnimation(context, idIn),
				AnimationUtils.loadAnimation(context, idOut)
		};
		
		for(Animation animation : animations)
			animation.setDuration(duration);

		return animations;

	}


}
