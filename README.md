Android-Simple-Animation-Effects
================================

A Simple lib to make animations with Android, changing values between two animations and somethings like this.

Using SimpleAnimation class, you will have a static method named animate.

animate method is written like :

void animate(Context, ViewGroup, View, View, Anim, AnimationAction)

The context I don't is going to be used to load the animation.

ViewGroup is the parent, there you will have the first View, the code will remore the first View and add the Second View,
then, Anim is a enum created in SimpleAnimation. This enum is the selected animation that you want to use.
AnimationAction has a method run, if you implement, the code will be executed in the middle of in/out animation.
