package com.example.kojey.lookuct;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

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
        Initialize();
        slide = (ViewFlipper) findViewById(R.id.imageFlipper);
        setID();
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
        Utils.changeSlideshowState(); // Button is pressed so change the state of the slideshow
        ImageButton ib = (ImageButton) findViewById(R.id.but_play);

        if(Utils.isSlideShowOn()){
            index=0;    // reset index
            ib.setImageResource(res[1]);
            slide.setFlipInterval(500);
            slide.startFlipping();
        }else{
            ib.setImageResource(res[0]);
            slide.stopFlipping();
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
        for(int i=0; i<Images.MAX_IMAGE_NUMBER; i++){
            imageID[i]=getResources().getIdentifier(Images.getImageSource(i),"drawable",getPackageName());
            iV = new ImageView(this);
            iV.setLayoutParams(imageView.getLayoutParams());
            iV.setImageResource(imageID[i]);
            tV = new TextView(this);
            tV.setGravity(textView.getGravity());
            tV.setLayoutParams(textView.getLayoutParams());
            tV.setText(Images.getImageTitle(i));
            tV.setTextAppearance(getApplicationContext(),R.style.imageTitleStyle);

            tV.setBackgroundResource(R.color.imageTitleBackground);
            rL = new RelativeLayout(this);
            rL.setLayoutParams(relativeLayout.getLayoutParams());
            rL.addView(tV);
            rL.addView(iV);
            slide.addView(rL);
        }

    }

}
