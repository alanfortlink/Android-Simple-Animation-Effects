package com.apps.libs.alanfortlink.androidsimpleanimationeffects;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SimpleAnimation {
	
	public static int hp = 0;
	
	public static final int DEFAULT_ANIM_DURATION = 500;
	public static final int FAST_ANIM_DURATION = 250;
	public static final int SLOW_ANIM_DURATION = 1000;

	public enum Anim{SLIDE_LEFT_RIGHT, SLIDE_RIGHT_LEFT, SLIDE_TOP_BOTTOM, SLIDE_BOTTOM_TOP, FADE, FLIP_HORIZONTAL, FLIP_VERTICAL, SCALE_HORIZONTAL, SCALE_VERTICAL, SCALE_FULL};

	public static void replaceContent(Context context, final ViewGroup container, final View actualView, final View newView, Anim animType, final AnimationActionListener action){
		replaceContent(context, container, actualView, newView, animType, action, DEFAULT_ANIM_DURATION);
	}
	
	public static void replaceContent(Context context, final ViewGroup container, final View actualView, final View newView, Anim animType, final AnimationActionListener action, int duration){
		Animation[] anims = AnimationFactory.getAnimations(context, animType, duration);

		final Animation animIn = anims[0];
		final Animation animOut = anims[1];

		animOut.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				if(action instanceof MultiAnimationActionListener){
					((MultiAnimationActionListener) action).onAnimationBegin();
				}

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				if(action != null){
					action.run();

					if(action instanceof MultiAnimationActionListener){
						((MultiAnimationActionListener) action).onAnimationMiddle();
					}
				}

				container.removeView(actualView);
				container.addView(newView);

				animIn.setAnimationListener(new Animation.AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						if(action instanceof MultiAnimationActionListener){
							((MultiAnimationActionListener) action).onAnimationEnd();
						}

					}
				});

				newView.startAnimation(animIn);

			}
		});

		actualView.startAnimation(animOut);
	}

		
	public static void removeView(final Context context, final ViewGroup parent, final View view, final Anim animType, final AnimationActionListener action){
		removeView(context, parent, view, animType, action, DEFAULT_ANIM_DURATION);
	}

	
	public static void removeView(final Context context, final ViewGroup parent, final View view, final Anim animType, final AnimationActionListener action, int duration){
		Animation[] anims = AnimationFactory.getAnimations(context, animType, duration);

		final Animation animOut = anims[1];
		
		final Handler mHandler = new Handler();
		
		animOut.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				if(action instanceof MultiAnimationActionListener){
					((MultiAnimationActionListener) action).onAnimationBegin();
				}
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if(action != null){
					action.run();
				}
				if(action instanceof MultiAnimationActionListener){
					((MultiAnimationActionListener) action).onAnimationEnd();
				}
				
				
				mHandler.post(new Runnable() {
					
					@Override
					public void run() {
					
						if(parent != null && view != null)
							parent.removeView(view);
						
					}
				});
			}
		});
		
		view.startAnimation(animOut);		
	}
	
	public static void addView(final Context context, final ViewGroup parent, final View view, final Anim animType, final AnimationActionListener action){
		addView(context, parent, view, animType, action, DEFAULT_ANIM_DURATION);
	}
	
	public static void addView(final Context context, final ViewGroup parent, final View view, final Anim animType, final AnimationActionListener action, int duration){
		Animation[] anims = AnimationFactory.getAnimations(context, animType, duration);

		final Animation animIn = anims[0];
		
		animIn.setAnimationListener(new com.apps.libs.alanfortlink.androidsimpleanimationeffects.Animation(action));
		
		parent.addView(view);
		view.bringToFront();
		view.startAnimation(animIn);		
	}
	
	public static void animateView(Context context, final View view, final Anim animType,  final AnimationActionListener action, int duration, boolean keepChanges){
		Animation[] anims = AnimationFactory.getAnimations(context, animType, duration);

		final Animation anim = anims[0];
		anim.setFillAfter(keepChanges);
		
		anim.setAnimationListener(new com.apps.libs.alanfortlink.androidsimpleanimationeffects.Animation(action));
		
		view.startAnimation(anim);
	}
	
	public static void animateView(Context context, final View view, final Anim animType,  final AnimationActionListener action, boolean keepChanges){
		animateView(context, view, animType, action, DEFAULT_ANIM_DURATION, keepChanges);
	}
	
	public static void animateViews(Context context, final Anim animType, final AnimationActionListener action, boolean keepChanges, boolean linked, final View... views){
		animateViews(context, animType, action, keepChanges, linked, DEFAULT_ANIM_DURATION, views);
	}
	
	public static void animateViews(Context context, final Anim animType, final AnimationActionListener action, boolean keepChanges, boolean linked, int duration, final View... views){
		
		if(linked){
			
			for(int i = 0; i<views.length; i++){
				Animation animation = AnimationFactory.getAnimations(context, animType, duration)[0];
				animation.setAnimationListener(new com.apps.libs.alanfortlink.androidsimpleanimationeffects.Animation(action));
				animation.setFillAfter(keepChanges);
				animation.setStartOffset(animation.getDuration()*(i));
				
				views[i].startAnimation(animation);
			}
			
		}else{
			Animation anim = AnimationFactory.getAnimations(context, animType, duration)[0];
			anim.setFillAfter(keepChanges);
			anim.setAnimationListener(new com.apps.libs.alanfortlink.androidsimpleanimationeffects.Animation(action));
			for(View view : views){
				view.startAnimation(anim);
			}
		}
		
	}
	
	public interface AnimationActionListener{
		public void run();
	}

	public interface MultiAnimationActionListener extends AnimationActionListener{
		public void onAnimationBegin();
		public void onAnimationEnd();
		public void onAnimationMiddle();
	}
	
}
