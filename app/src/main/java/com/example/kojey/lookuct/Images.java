package com.example.kojey.lookuct;

/**
 * Created by kojey on 2015/09/05.
 */
public class Images {
    public static final int MAX_IMAGE_NUMBER = 3;
    private static String imageSource [] = new String[MAX_IMAGE_NUMBER];
    private static String imageTitle [] = new String[MAX_IMAGE_NUMBER];

    public static void set(){
        for(int i=0; i<MAX_IMAGE_NUMBER; i++){
           // imageSource[i]= "play";
            imageTitle[i]="Title "+i;
        }
        imageSource[0]= "previous";
        imageSource[1]= "play";
        imageSource[2]= "next";
    }
    public static String getImageSource (int i){
        return imageSource[i];
    }
    public static String getImageTitle (int i){
        return imageTitle[i];
    }
}
