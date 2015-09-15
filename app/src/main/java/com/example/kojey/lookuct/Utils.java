package com.example.kojey.lookuct;

/**
 * Created by kojey on 2015/09/05.
 */
public class Utils {

    private static boolean slideShowOn = false;

    /**
     * Give the status on the slideshow
     * @return the status of the slide show
     */
    public static boolean isSlideShowOn() {
        return slideShowOn;
    }

    /**
     * Change the status of the slideshow
     */
    public static void changeSlideshowState() {
        if(Utils.slideShowOn)Utils.slideShowOn=false;
        else Utils.slideShowOn=true;
    }
}
