package com.example.kojey.lookuct;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SlideActivity extends AppCompatActivity {
    String [] imageName = new String[2];
    int [] res = new int[2];
    int [] imageID = new int[Images.MAX_IMAGE_NUMBER];
    int index = 0;
    ViewFlipper slide;
    ImageView iV;
    TextView tV;
    RelativeLayout rL;

    private void Initialize(){

        imageName[0] = "play";
        imageName[1] = "stop";
        Images.set();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        getSupportActionBar().hide();
        Initialize();
        slide = (ViewFlipper) findViewById(R.id.imageFlipper);
        setID();
        ImageView imageView = (ImageView) findViewById(R.id.imageSlide);
        TextView textView = (TextView) findViewById(R.id.imageTitle);
        textView.setText(Images.getImageTitle(0));
        BitmapDrawable bit = new BitmapDrawable(getResources(),Images.getImageSource(0).getAbsolutePath());
        imageView.setImageDrawable(bit);
    /*
        slide.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
        slide.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_left));

        Animation.AnimationListener mAnimationListener = new Animation.AnimationListener(){
            public void onAnimationStart(Animation animation) {
                //animation started event
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (slide.getDisplayedChild()==Images.MAX_IMAGE_NUMBER){
                    slide.stopFlipping();
                }
            }
        };
       slide.getInAnimation().setAnimationListener(mAnimationListener);
       */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_slide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void prevSlide(View view) {
       slide.showPrevious();
    }

    public void playStop(View view) {
        ImageButton ib = (ImageButton) findViewById(R.id.but_play);

        if(slide.isFlipping()){   // reset index
            ib.setImageResource(res[0]);
            slide.stopFlipping();
        }else {
            ib.setImageResource(res[1]);
            slide.setFlipInterval(2000);
            slide.startFlipping();
        }

    }

    public void nextSlide(View view) {
        slide.showNext();
    }

    private void setID() {
        res[0] = getResources().getIdentifier(imageName[0],"drawable",getPackageName());
        res[1] = getResources().getIdentifier(imageName[1],"drawable",getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.imageSlide);
        TextView textView = (TextView) findViewById(R.id.imageTitle);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        File imageFile;
        BitmapDrawable bit;
        for(int i=1; i<Images.MAX_IMAGE_NUMBER; i++){
            tV = new TextView(this);
            tV.setGravity(textView.getGravity());
            tV.setLayoutParams(textView.getLayoutParams());
            tV.setText(Images.getImageTitle(i));
            tV.setTextAppearance(getApplicationContext(),R.style.imageTitleStyle);
            tV.setBackgroundResource(R.color.imageTitleBackground);

            iV = new ImageView(this);
            iV.setLayoutParams(imageView.getLayoutParams());
            bit = new BitmapDrawable(getResources(),Images.getImageSource(i).getAbsolutePath());
            iV.setImageDrawable(bit);

            rL = new RelativeLayout(this);
            rL.setLayoutParams(relativeLayout.getLayoutParams());
            rL.addView(tV);
            rL.addView(iV);
            slide.addView(rL);
        }

    }


    public void onAnimationEnd(Animation animation, ViewFlipper viewFlipper) {
        //TODO animation stopped event
        if (viewFlipper.getDisplayedChild()==Images.MAX_IMAGE_NUMBER){
            viewFlipper.stopFlipping();
        }
    }
}
