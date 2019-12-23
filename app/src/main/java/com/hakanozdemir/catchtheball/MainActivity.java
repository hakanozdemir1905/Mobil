package com.hakanozdemir.catchtheball;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //Tanımlamalar

    TextView textViewTittle,textViewBasketbol,textViewFutbol,textViewBomba;
    Button buttonStart,buttonExit;
    Typeface tf1,tf2,tf3,tf4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        //OnClickListener - Butonlar
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                startActivity(intent);
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(Process.myPid());
                System.exit(1);
            }
        });


    }//onCreate

    private void init(){
        //Button
        buttonStart=findViewById(R.id.button_start);
        buttonExit=findViewById(R.id.button_exit);
        //TextView
        textViewTittle=findViewById(R.id.text_view_tittle);
        textViewBasketbol=findViewById(R.id.text_view_basketbol);
        textViewFutbol=findViewById(R.id.text_view_futbol);
        textViewBomba=findViewById(R.id.text_view_bomba);
        //Fonts Tanımlar
        tf1=Typeface.createFromAsset(getAssets(),"fonts/bass.ttf");
        tf2=Typeface.createFromAsset(getAssets(),"fonts/hand.ttf");
        tf3=Typeface.createFromAsset(getAssets(),"fonts/medium.ttf");
        tf4=Typeface.createFromAsset(getAssets(),"fonts/mainland.ttf");
        //Font Atamaları
        buttonStart.setTypeface(tf2);
        buttonExit.setTypeface(tf2);
        textViewTittle.setTypeface(tf4);
        textViewFutbol.setTypeface(tf3);
        textViewBasketbol.setTypeface(tf3);
        textViewBomba.setTypeface(tf3);

    }


}
