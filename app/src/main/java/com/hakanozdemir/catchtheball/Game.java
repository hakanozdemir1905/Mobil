package com.hakanozdemir.catchtheball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {
    //Tanımlamalar
    Handler handler;
    Runnable runnableFutbol,runnableBasketbol,runnableBomba;
    Button buttonRestart,buttonMenu,buttonResume,buttonPause;
    TextView textViewScore,textViewTime;
    Typeface tf1,tf2,tf3;

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,
            imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,
            imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,
            imageView18,imageView19,imageView20;

    ImageView imageView21,imageView22,imageView23,imageView24,imageView25,
            imageView26,imageView27,imageView28,imageView29,imageView30,imageView31,
            imageView32,imageView33,imageView34,imageView35,imageView36,imageView37,
            imageView38,imageView39,imageView40;

    ImageView imageView41,imageView42,imageView43,imageView44,imageView45,
            imageView46,imageView47,imageView48,imageView49,imageView50,imageView51,
            imageView52,imageView53,imageView54,imageView55,imageView56,imageView57,
            imageView58,imageView59,imageView60;

    ImageView [] futbolArray;
    ImageView[] basketbolArray;
    ImageView[] bombaArray;

    int score=0;
    long START_TIME_IN_MILLIS = 10000;
    long timeLeftInMillis=START_TIME_IN_MILLIS;
    CountDownTimer countDownTimer;
    boolean isTimerRunning=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
        hideImageFutbol();
        hideImageBasketbol();
        hideImageBomba();
        startTimer();



        //Button OnClickListeners
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimerRunning==true){
                    pauseTimer();
                    handler.removeCallbacks(runnableFutbol);
                    handler.removeCallbacks(runnableBasketbol);
                    handler.removeCallbacks(runnableBomba);
                }
                else{
                    startTimer();
                    hideImageFutbol();
                    hideImageBasketbol();
                    hideImageBomba();
                }
            }
        });
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this,MainActivity.class);
                startActivity(intent);
            }
        });
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restartTimer();
            }
        });
        buttonResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                hideImageFutbol();
                hideImageBasketbol();
                hideImageBomba();
            }
        });


    }//onCreate



    //StartMethod

    private void startTimer(){
        isTimerRunning=true;
        buttonMenu.setVisibility(View.INVISIBLE);
        buttonRestart.setVisibility(View.INVISIBLE);
        buttonResume.setVisibility(View.INVISIBLE);
        Drawable drawablePause= getResources().getDrawable(R.drawable.ic_action_pause);
        buttonPause.setBackgroundDrawable(drawablePause);

        //Sayaç Oluşturma
        countDownTimer =new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish(){
                isTimerRunning=false;
                buttonMenu.setVisibility(View.INVISIBLE);
                buttonRestart.setVisibility(View.INVISIBLE);
                buttonPause.setVisibility(View.INVISIBLE);
                textViewTime.setText("Time Off!");

                handler.removeCallbacks(runnableFutbol);
                for(ImageView image : futbolArray){
                    image.setVisibility(View.INVISIBLE);
                }

                handler.removeCallbacks(runnableBasketbol);
                for(ImageView image : basketbolArray){
                    image.setVisibility(View.INVISIBLE);
                }

                handler.removeCallbacks(runnableBomba);
                for(ImageView image : bombaArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Intent intent= new Intent(Game.this,Sonuc.class);
                intent.putExtra("SCORE",score);
                startActivity(intent);
            }
        }.start();

    }


    //Pause Method
    private void pauseTimer(){
        if(isTimerRunning==true){
            countDownTimer.cancel();
            isTimerRunning=false;

            buttonRestart.setVisibility(View.VISIBLE);
            buttonMenu.setVisibility(View.VISIBLE);
            buttonResume.setVisibility(View.VISIBLE);
            Drawable drawablePlay=getResources().getDrawable(R.drawable.ic_action_play);
            buttonPause.setBackgroundDrawable(drawablePlay);
        }
        else{
            isTimerRunning=true;
        }
    }


    //Restart Method
    private void restartTimer(){
        timeLeftInMillis=START_TIME_IN_MILLIS;
        updateCountDownText();
        //Restart Activity
        Intent intentGame= getIntent();
        finish();
        startActivity(intentGame);
        buttonMenu.setVisibility(View.INVISIBLE);
        buttonRestart.setVisibility(View.INVISIBLE);
        buttonResume.setVisibility(View.INVISIBLE);
        buttonPause.setVisibility(View.VISIBLE);
    }


    //Sayaç Text güncelleme
    private void updateCountDownText(){

        textViewTime.setText("Left: "+timeLeftInMillis/1000);
    }


    //Hide Images
    public void hideImageFutbol(){
        handler = new Handler();
        runnableFutbol=new Runnable(){
            @Override
            public void run() {
                for(ImageView image : futbolArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i= random.nextInt(19); //0-19 arası değer üretecek.
                futbolArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnableFutbol,750); //Topun gözükme süresi
            }
        };
        handler.post(runnableFutbol);

    }


    public void hideImageBasketbol() {
        handler = new Handler();
        runnableBasketbol= new Runnable(){
            @Override
            public void run() {
                for (ImageView image : basketbolArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(19);
                basketbolArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnableBasketbol, 750); //Topun gözükme süresi
            }
        };
        handler.post(runnableBasketbol);

    }


    public void hideImageBomba() {
        handler = new Handler();
        runnableBomba= new Runnable(){
            @Override
            public void run() {
                for (ImageView image : bombaArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(19);
                bombaArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnableBomba, 750); //Topun gözükme süresi
            }
        };
        handler.post(runnableBomba);

    }

    //ImageView Click Methods
    public void scoreArttirFutbol(View view){

        if(isTimerRunning==true){
            score=score+10;
            textViewScore.setText("Score: "+score);
        }
        else{
            //pause anında score arttırma metodu devre dışı.
        }

    }

    public void scoreArttirBasketbol(View view){

        if(isTimerRunning==true){
            score=score+20;
            textViewScore.setText("Score: "+score);
        }

    }

    public void scoreAzaltBomba(View view){

        if(isTimerRunning==true){
            score=score-30;
            textViewScore.setText("Score: "+score);
        }

    }

    private void init(){
        //Button
        buttonPause = findViewById(R.id.button_pause);
        buttonRestart=findViewById(R.id.button_restart);
        buttonMenu=findViewById(R.id.button_menu);
        buttonResume=findViewById(R.id.button_resume);
        //TextView
        textViewScore=findViewById(R.id.text_view_score);
        textViewTime=findViewById(R.id.text_view_time);
        //ImageView - Futbol
        imageView1= findViewById(R.id.image_view1);
        imageView2= findViewById(R.id.image_view2);
        imageView3= findViewById(R.id.image_view3);
        imageView4= findViewById(R.id.image_view4);
        imageView5= findViewById(R.id.image_view5);
        imageView6= findViewById(R.id.image_view6);
        imageView7= findViewById(R.id.image_view7);
        imageView8= findViewById(R.id.image_view8);
        imageView9= findViewById(R.id.image_view9);
        imageView10= findViewById(R.id.image_view10);
        imageView11= findViewById(R.id.image_view11);
        imageView12= findViewById(R.id.image_view12);
        imageView13= findViewById(R.id.image_view13);
        imageView14= findViewById(R.id.image_view14);
        imageView15= findViewById(R.id.image_view15);
        imageView16= findViewById(R.id.image_view16);
        imageView17= findViewById(R.id.image_view17);
        imageView18= findViewById(R.id.image_view18);
        imageView19= findViewById(R.id.image_view19);
        imageView20= findViewById(R.id.image_view20);
        //ImageView - Basketbol
        imageView21= findViewById(R.id.image_view21);
        imageView22= findViewById(R.id.image_view22);
        imageView23= findViewById(R.id.image_view23);
        imageView24= findViewById(R.id.image_view24);
        imageView25= findViewById(R.id.image_view25);
        imageView26= findViewById(R.id.image_view26);
        imageView27= findViewById(R.id.image_view27);
        imageView28= findViewById(R.id.image_view28);
        imageView29= findViewById(R.id.image_view29);
        imageView30= findViewById(R.id.image_view30);
        imageView31= findViewById(R.id.image_view31);
        imageView32= findViewById(R.id.image_view32);
        imageView33= findViewById(R.id.image_view33);
        imageView34= findViewById(R.id.image_view34);
        imageView35= findViewById(R.id.image_view35);
        imageView36= findViewById(R.id.image_view36);
        imageView37= findViewById(R.id.image_view37);
        imageView38= findViewById(R.id.image_view38);
        imageView39= findViewById(R.id.image_view39);
        imageView40= findViewById(R.id.image_view40);
        //ImageView Bomba
        imageView41= findViewById(R.id.image_view41);
        imageView42= findViewById(R.id.image_view42);
        imageView43= findViewById(R.id.image_view43);
        imageView44= findViewById(R.id.image_view44);
        imageView45= findViewById(R.id.image_view45);
        imageView46= findViewById(R.id.image_view46);
        imageView47= findViewById(R.id.image_view47);
        imageView48= findViewById(R.id.image_view48);
        imageView49= findViewById(R.id.image_view49);
        imageView50= findViewById(R.id.image_view50);
        imageView51= findViewById(R.id.image_view51);
        imageView52= findViewById(R.id.image_view52);
        imageView53= findViewById(R.id.image_view53);
        imageView54= findViewById(R.id.image_view54);
        imageView55= findViewById(R.id.image_view55);
        imageView56= findViewById(R.id.image_view56);
        imageView57= findViewById(R.id.image_view57);
        imageView58= findViewById(R.id.image_view58);
        imageView59= findViewById(R.id.image_view59);
        imageView60= findViewById(R.id.image_view60);

        //ImageArray
        futbolArray = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,
                imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,
                imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,
                imageView18,imageView19,imageView20};

        basketbolArray = new ImageView[]{imageView21,imageView22,imageView23,imageView24,imageView25,
                imageView26,imageView27,imageView28,imageView29,imageView30,imageView31,
                imageView32,imageView33,imageView34,imageView35,imageView36,imageView37,
                imageView38,imageView39,imageView40};

        bombaArray = new ImageView[]{imageView41,imageView42,imageView43,imageView44,imageView45,
                imageView46,imageView47,imageView48,imageView49,imageView50,imageView51,
                imageView52,imageView53,imageView54,imageView55,imageView56,imageView57,
                imageView58,imageView59,imageView60};

        //Fonts
        tf1=Typeface.createFromAsset(getAssets(),"fonts/regular.ttf");
        tf2=Typeface.createFromAsset(getAssets(),"fonts/hand.ttf");
        tf3=Typeface.createFromAsset(getAssets(),"fonts/medium.ttf");
        //Yazı Tipi değişimi
        buttonMenu.setTypeface(tf2);
        buttonRestart.setTypeface(tf2);
        buttonResume.setTypeface(tf2);
        textViewTime.setTypeface(tf1);
        textViewScore.setTypeface(tf1);

    }//init


}
