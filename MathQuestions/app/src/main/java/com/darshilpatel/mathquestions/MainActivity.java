package com.darshilpatel.mathquestions;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


int score;

    int finalAnswers;
    int a;
    int b;


    Button startButton;

    public void start (View view){

        startButton.setVisibility(View.INVISIBLE);

    }

    public void chooseAnswer (View view) {

        EditText userText = (EditText) findViewById(R.id.userText);

        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);

        String userString;

        try {
            userString = userText.getText().toString();
            int finalUser = Integer.parseInt(userString);


        if(finalUser == finalAnswers) {
            resultTextView.setText("Correct!");
            generateNum();
            score++;



        } else {
            resultTextView.setText("Wrong");
        }
    }catch(Exception e){

        }
        scoreTextView.setText(Integer.toString(score) );
    }


    //@Override


    public void generateNum() {
        setContentView(R.layout.activity_main);


        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);

        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

        Random rand = new Random();

        a  = rand.nextInt(99);
        b = rand.nextInt(99);


        Random secondRand = new Random();
        int opertaorGenerated = secondRand.nextInt(3);

        if (opertaorGenerated == 2){

            sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
            finalAnswers = a + b;

        } else if (opertaorGenerated == 0) {

            sumTextView.setText(Integer.toString(a) + " x " + Integer.toString(b));
            finalAnswers = a * b;
        } else if(opertaorGenerated == 1) {
            sumTextView.setText(Integer.toString(a) + " - " + Integer.toString(b));
            finalAnswers = a - b;
        } else {
            sumTextView.setText(Integer.toString(a) + " / " + Integer.toString(b));
            finalAnswers = a /b;
        }


    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateNum();
    }
}
