package com.example.kojey.lookuct;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kojey on 2015/09/05.
 */
public class Images {
    public static final int MAX_IMAGE_NUMBER = 15;
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
        imageTitle[2] = "Suitable environment for learning. Inside a lecture theater";
        imageTitle[3] = "Good research facilities: Chemical Engineering Lab";
        imageTitle[4] = "Electrical Lab";
        imageTitle[5] = "Machine Labs";
        imageTitle[6] = "Control & Instrumentation Lab";
        imageTitle[7] = "Equiped Compter science Lab";
        imageTitle[8] = "Equiped Libraries";
        imageTitle[9] = "Residence Close to campus : Maquard & Tugwel";
        imageTitle[10] = "Another Res.";
        imageTitle[11] = "Free transport from Res to campus";
        imageTitle[12] = "UCT offers varieties of sport activities: Football";
        imageTitle[13] = "VolleyBall";
        imageTitle[14] = "Fun Event on Campus";

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
