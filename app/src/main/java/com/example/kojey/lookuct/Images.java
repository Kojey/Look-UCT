package com.example.kojey.lookuct;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kojey on 2015/09/05.
 */
public class Images {
    public static final int MAX_IMAGE_NUMBER = 11;
    public static int childDisplayed = 0;
    private static ArrayList<File> imageSource = new ArrayList<>();
    private static String imageTitle [] = new String[MAX_IMAGE_NUMBER];

    /**
     * Gives name and title to images
     */
    public static void set(){
        imageSource = loadImageFromSD();
        imageTitle[0] = "A top view of the Campus";
        imageTitle[1] = "Upper Campus : Jameson Plazza";
        imageTitle[2] = "Inside a lecture theater";
        imageTitle[3] = "Inside one of the libraries";
        imageTitle[4] = "Inside one the Computer Labs";
        imageTitle[5] = "Inside one the Electrical Labs";
        imageTitle[6] = "Inside one the Chimistry Labs";
        imageTitle[7] = "Vula: the campus in your pocket";
        imageTitle[8] = "Residence Close to campus : Maquard & Tugwel";
        imageTitle[9] = "Jammie shuttle: free transport on campus";
        imageTitle[10] = "You might be one of them in few year.";

    }
    public static File getImageSource (int i){
        return imageSource.get(i);
    }
    public static String getImageTitle (int i){
        return imageTitle[i];
    }


    private static ArrayList<File> loadImageFromSD (){
        File root = Environment.getExternalStorageDirectory();
        String dir = "/look@uct/i";
        String endDir = ".jpg";
        ArrayList<File> a = new ArrayList<>();
        for (int i=0; i<MAX_IMAGE_NUMBER; i++) {
            a.add(new File(root+dir+i+endDir));
        }
        return a;
    }
}
