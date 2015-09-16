package com.example.kojey.lookuct;

/**
 * Created by kojey on 2015/09/05.
 */
public class Images {
    public static final int MAX_IMAGE_NUMBER = 4;
    private static String imageSource [] = new String[MAX_IMAGE_NUMBER];
    private static String imageTitle [] = new String[MAX_IMAGE_NUMBER];

    /**
     * Gives name and title to images
     */
    public static void set(){
        for(int i=0; i<MAX_IMAGE_NUMBER; i++){
            imageSource[i]= "i"+i;
            imageTitle[i]="Title "+i;
        }
    }
    public static String getImageSource (int i){
        return imageSource[i];
    }
    public static String getImageTitle (int i){
        return imageTitle[i];
    }
}
