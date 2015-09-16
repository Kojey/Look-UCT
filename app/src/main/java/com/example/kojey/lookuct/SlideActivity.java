package com.example.kojey.lookuct;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import java.io.File;

public class SlideActivity extends AppCompatActivity {
    String[] imageName = new String[2];
    int[] res = new int[2];
    boolean lastImage = false;
    ViewFlipper slide;
    ImageView iV;
    TextView tV;
    RelativeLayout rL;

    private void Initialize() {

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
        BitmapDrawable bit = new BitmapDrawable(getResources(), Images.getImageSource(0).getAbsolutePath());
        imageView.setImageDrawable(bit);

        slide.setDisplayedChild(Images.childDisplayed);
        slide.setInAnimation(this, R.anim.slide_out_left);
        slide.setOutAnimation(this, R.anim.slide_in_right);
        slide.getInAnimation().setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int displayedChild = slide.getDisplayedChild();
                int childCount = slide.getChildCount();
                if (displayedChild == childCount - 1) {
                    if(lastImage==false){
                        slide.stopFlipping();
                        ImageButton ib = (ImageButton) findViewById(R.id.but_play);
                        ib.setImageResource(res[0]);
                    }else lastImage=false;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
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

    @Override
    protected void onStop() {
        Images.childDisplayed = slide.getDisplayedChild();
        super.onStop();
    }

    public void prevSlide(View view) {
        slide.showPrevious();
    }

    public void playStop(View view) {
        ImageButton ib = (ImageButton) findViewById(R.id.but_play);
        if (slide.isFlipping()) {   // reset index
            ib.setImageResource(res[0]);
            slide.stopFlipping();
        } else {
            ib.setImageResource(res[1]);
            slide.setFlipInterval(100);
            if(slide.getDisplayedChild() == slide.getChildCount()-1)
                lastImage =true;
            slide.startFlipping();
        }

    }

    public void nextSlide(View view) {
        slide.showNext();
    }

    private void setID() {
        res[0] = getResources().getIdentifier(imageName[0], "drawable", getPackageName());
        res[1] = getResources().getIdentifier(imageName[1], "drawable", getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.imageSlide);
        TextView textView = (TextView) findViewById(R.id.imageTitle);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        File imageFile;
        BitmapDrawable bit;
        for (int i = 1; i < Images.MAX_IMAGE_NUMBER; i++) {
            tV = new TextView(this);
            tV.setGravity(textView.getGravity());
            tV.setLayoutParams(textView.getLayoutParams());
            tV.setText(Images.getImageTitle(i));
            tV.setTextAppearance(getApplicationContext(), R.style.imageTitleStyle);
            tV.setBackgroundResource(R.color.imageTitleBackground);
            tV.setBackground(textView.getBackground().getCurrent());

            iV = new ImageView(this);
            iV.setLayoutParams(imageView.getLayoutParams());
            bit = new BitmapDrawable(getResources(), Images.getImageSource(i).getAbsolutePath());
            iV.setImageDrawable(bit);

            rL = new RelativeLayout(this);
            rL.setLayoutParams(relativeLayout.getLayoutParams());
            rL.addView(iV);
            rL.addView(tV);
            slide.addView(rL);
        }

    }

}
