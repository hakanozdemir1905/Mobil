package com.hakanozdemir.catchtheball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Sonuc extends AppCompatActivity {

    //Tanımlamalar
    TextView textViewGameOver,textViewScore,textViewHighScore;
    Button buttonRestartGame;
    Typeface tf1,tf2,tf3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        init();
        saveScore();
        //clearScore();



        buttonRestartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restartGame();
            }
        });

    }


    public void restartGame(){
        Intent intentGame=new Intent(Sonuc.this,Game.class);
        startActivity(intentGame);
    }


    public void saveScore(){
        Intent intent= getIntent();
        int storedScore=intent.getIntExtra("SCORE",0);
        textViewScore.setText("Score: "+storedScore);

        SharedPreferences myPref = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore= myPref.getInt("HIGH_SCORE",0);

        if(storedScore > highScore){
            textViewHighScore.setText("High Score: "+storedScore);
            //Save
            SharedPreferences.Editor editor = myPref.edit();
            editor.putInt("HIGH_SCORE",storedScore);
            editor.commit();

            Toast toast = Toast.makeText(Sonuc.this, "New HighScore: "+storedScore, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,0);
            toast.show();
        }
        else{
            textViewHighScore.setText("High Score: "+highScore);
        }

    }

    //clear HighScore
    public void clearScore(){
        SharedPreferences myPref = this.getSharedPreferences("GAME_DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = myPref.edit();
        editor.clear();
        editor.commit();
    }


    private void init(){
        textViewGameOver=findViewById(R.id.text_view_game_over);
        textViewHighScore=findViewById(R.id.text_view_high_score);
        textViewScore=findViewById(R.id.text_view_score);

        buttonRestartGame=findViewById(R.id.button_restart_game);

        tf1=Typeface.createFromAsset(getAssets(),"fonts/mainland.ttf");
        tf2=Typeface.createFromAsset(getAssets(),"fonts/graffiti.otf");
        tf3=Typeface.createFromAsset(getAssets(),"fonts/medium.ttf");

        textViewGameOver.setTypeface(tf1);
        textViewScore.setTypeface(tf3);
        textViewHighScore.setTypeface(tf3);


    }
}
