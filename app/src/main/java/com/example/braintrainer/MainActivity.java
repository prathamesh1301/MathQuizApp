package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    int correctansLocation;
    TextView statusTextView,scoreTextView;
    TextView qnTextView,timerTextView;
    int score=0;
    int numberofQuestions=0;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;

    public void playAgainButton(View view){
        score=0;
        newQuestion();
        numberofQuestions=0;
        playAgain.setVisibility(View.INVISIBLE);
        timerTextView.setText("59s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuestions));
        new CountDownTimer(59100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                statusTextView.setText("DONE!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void newQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        qnTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        correctansLocation=rand.nextInt(4);
        int ans=a+b;
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==correctansLocation){
                answers.add(ans);
            }else{
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==ans){
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public  void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(correctansLocation))){
            statusTextView.setText("Correct!");
            score++;

        }else{
            statusTextView.setText("Wrong:(");

        }
        numberofQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuestions));
        newQuestion();
    }
    public void goButtonClicked(View view){
        goButton.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.goButton);
        qnTextView=findViewById(R.id.qnTextView);

        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgain=findViewById(R.id.playAgain);
        statusTextView=findViewById(R.id.statusTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        playAgainButton(findViewById(R.id.playAgain));


    }
}