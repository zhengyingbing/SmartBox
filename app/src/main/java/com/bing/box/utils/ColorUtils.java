 package com.bing.box.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;

/**
 * @author gaor
 * @className: ColorUtils
 * @description TODO 描述
 * @date 2021/12/3 18:24
 */
public class ColorUtils {

    private static float CORNERRADIUS = 5f;

    public static GradientDrawable getDrawableColor(String color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(pxToDp(CORNERRADIUS));
        if (color.startsWith("#")){
            gradientDrawable.setColor(Color.parseColor(color));
        }else{
            gradientDrawable.setColor(Color.parseColor("#" + color));
        }
        return gradientDrawable;
    }

    @SuppressLint("WrongConstant")
    public static StateListDrawable getStateListDrawable(String defaultColor, String pressedColor) {
        GradientDrawable normal = new GradientDrawable();
        if (defaultColor.startsWith("#")) {
            normal.setColor(Color.parseColor(defaultColor));
        } else {
            normal.setColor(Color.parseColor("#" + defaultColor));
        }
        normal.setCornerRadius(pxToDp(CORNERRADIUS));
        normal.setGradientType(GradientDrawable.RECTANGLE);

        GradientDrawable pressed = new GradientDrawable();
        if (pressedColor.startsWith("#")) {
            pressed.setColor(Color.parseColor(pressedColor));
        } else {
            pressed.setColor(Color.parseColor("#" + pressedColor));
        }
        pressed.setCornerRadius(pxToDp(CORNERRADIUS));
        pressed.setGradientType(GradientDrawable.RECTANGLE);

        StateListDrawable bg = new StateListDrawable();
        bg.addState(new int[]{android.R.attr.state_pressed}, pressed);
        bg.addState(new int[]{}, normal);
        return bg;
    }

    private static float pxToDp(float px){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px,
                AppUtils.getApp().getResources().getDisplayMetrics());
    }

    @SuppressLint("WrongConstant")
    public static StateListDrawable getBtnDrawable(boolean enabled) {
        StateListDrawable bg = new StateListDrawable();
        try{
            GradientDrawable unableDrawable = new GradientDrawable();
            unableDrawable.setColor(Color.parseColor("#eeeeee"));
            unableDrawable.setCornerRadius(pxToDp(CORNERRADIUS));
            unableDrawable.setGradientType(GradientDrawable.RECTANGLE);

            GradientDrawable defaultDrawable = new GradientDrawable();
            defaultDrawable.setColor(Color.parseColor("#666666"));
            defaultDrawable.setCornerRadius(pxToDp(CORNERRADIUS));
            defaultDrawable.setGradientType(GradientDrawable.RECTANGLE);

            GradientDrawable pressedDrawable = new GradientDrawable();
            pressedDrawable.setColor(Color.parseColor("#333333"));
            pressedDrawable.setCornerRadius(pxToDp(CORNERRADIUS));
            pressedDrawable.setGradientType(GradientDrawable.RECTANGLE);


            if (enabled){
                bg.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
                bg.addState(new int[]{android.R.attr.state_enabled}, defaultDrawable);
                bg.addState(new int[]{}, defaultDrawable);
            }else{
                bg.addState(new int[]{android.R.attr.state_pressed}, unableDrawable);
                bg.addState(new int[]{android.R.attr.state_enabled}, unableDrawable);
                bg.addState(new int[]{}, unableDrawable);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return bg;
    }

    public static StateListDrawable getBtnDrawable() {
        return getBtnDrawable(true);
    }
}
