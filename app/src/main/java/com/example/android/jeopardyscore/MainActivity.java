package com.example.android.jeopardyscore;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    public static int winningsCountJ = 0;
    public static int winningsCountDJ = 0;
    public static int winningsCountTotal = 0;
    public static int winningsAmount = 0;
    public static boolean finalJeopardyCorrect = false;

    private Vibrator vibe;

    public int gameType = 0;
    public int fjWager = 0;
    public boolean wagerSubmitted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayWinnings(winningsAmount);

        // Layout declarations
        final LinearLayout scoreValues = findViewById(R.id.score_values);
        final RelativeLayout finalJeopardyWager = findViewById(R.id.final_jeopardy_wager);
        scoreValues.setVisibility(View.VISIBLE);
        finalJeopardyWager.setVisibility(View.GONE);

        vibe = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        //Determine what round is being played

        TextView jeopardyRound = findViewById(R.id.jeopardy);
        jeopardyRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameType = 0;
                setJeopardyRound();
                vibe.vibrate(20);
                scoreValues.setVisibility(View.VISIBLE);
                finalJeopardyWager.setVisibility(View.GONE);
            }
        });

        TextView doubleJeopardy = findViewById(R.id.double_jeopardy);
        doubleJeopardy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameType = 1;
                setDoubleJeopardyRound();
                vibe.vibrate(20);
                scoreValues.setVisibility(View.VISIBLE);
                finalJeopardyWager.setVisibility(View.GONE);
            }
        });

        TextView finalJeopardy = findViewById(R.id.final_jeopardy);
        finalJeopardy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameType = 2;
                vibe.vibrate(20);
                scoreValues.setVisibility(View.GONE);
                finalJeopardyWager.setVisibility(View.VISIBLE);
            }
        });


        Button submitWager = findViewById(R.id.submit_wager);
        submitWager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assessWager();
                vibe.vibrate(100);
                wagerSubmitted = true;
            }
        });

        //Button declaration for OnClickListener and OnLongClickListener

        Button score1 = findViewById(R.id.score_1);
        score1.setOnClickListener(this);
        score1.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        score1.setOnLongClickListener(this);
        Button score2 = findViewById(R.id.score_2);
        score2.setOnClickListener(this);
        score2.setOnLongClickListener(this);
        Button score3 = findViewById(R.id.score_3);
        score3.setOnClickListener(this);
        score3.setOnLongClickListener(this);
        Button score4 = findViewById(R.id.score_4);
        score4.setOnClickListener(this);
        score4.setOnLongClickListener(this);
        Button score5 = findViewById(R.id.score_5);
        score5.setOnClickListener(this);
        score5.setOnLongClickListener(this);


        Button correct = findViewById(R.id.correct);
        correct.setOnClickListener(this);
        Button incorrect = findViewById(R.id.incorrect);
        incorrect.setOnClickListener(this);

        TextView stats = findViewById(R.id.stats);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statsIntent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(statsIntent);
            }
        });

    }


    // getter methods to call variables between activities

    public static String getWinningsAmount(){
        return String.valueOf(winningsAmount);
    }

    public static String getWinningsCountJ(){
        return String.valueOf(winningsCountJ);
    }

    public static String getWinningsCountJPercent(){
        double percentVal = winningsCountJ / 30.0 * 100;
        String percentString = String.format("%.1f", percentVal);
        return String.valueOf(percentString) + "%";
    }

    public static String getWinningsCountDJ(){
        return String.valueOf(winningsCountDJ);
    }

    public static String getWinningsCountDJPercent(){
        double percentVal = winningsCountDJ / 30.0 * 100;
        String percentString = String.format("%.1f", percentVal);
        return String.valueOf(percentString) + "%";
    }

    public static String getTotalWinningsCountPercentage(){
        double percentVal = (winningsCountJ + winningsCountDJ) / 60.0 * 100;
        String percentString = String.format("%.1f", percentVal);
        return String.valueOf(percentString) + "%";
    }

    public static String getFinalJeopardyResult(){
        if (finalJeopardyCorrect){
            return "Yes";
        }
        else {
            return "No";
        }
    }



    //Add money on correct answer
    @Override
    public void onClick(View v) {
        //Jeopardy round
        if (gameType == 0) {
            switch (v.getId()) {
                case R.id.score_1:
                    winningsAmount += 200;
                    winningsCountJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_2:
                    winningsAmount += 400;
                    winningsCountJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_3:
                    winningsAmount += 600;
                    winningsCountJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_4:
                    winningsAmount += 800;
                    winningsCountJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_5:
                    winningsAmount += 1000;
                    winningsCountJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                default:
                    break;

            }
        }

        //Double Jeopardy Round
        else if (gameType == 1) {
            switch (v.getId()) {
                case R.id.score_1:
                    winningsAmount += 400;
                    winningsCountDJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_2:
                    winningsAmount += 800;
                    winningsCountDJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_3:
                    winningsAmount += 1200;
                    winningsCountDJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_4:
                    winningsAmount += 1600;
                    winningsCountDJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_5:
                    winningsAmount += 2000;
                    winningsCountDJ += 1;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                default:
                    break;
            }
        }

        else if (gameType == 2) {
            switch (v.getId()) {
                case R.id.correct:
                    winningsAmount += fjWager;
                    finalJeopardyCorrect = true;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;


                case R.id.incorrect:
                    winningsAmount -= fjWager;
                    finalJeopardyCorrect = false;
                    vibe.vibrate(50);
                    displayWinnings(winningsAmount);
                    break;

                default:
                    break;
            }
        }

    }

    //Subtract money for incorrect answer
    @Override
    public boolean onLongClick(View v) {
        //Jeopardy Round
        if (gameType == 0) {
            switch (v.getId()) {
                case R.id.score_1:
                    winningsAmount -= 200;
                    winningsCountJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_2:
                    winningsAmount -= 400;
                    winningsCountJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_3:
                    winningsAmount -= 600;
                    winningsCountJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_4:
                    winningsAmount -= 800;
                    winningsCountJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_5:
                    winningsAmount -= 1000;
                    winningsCountJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                default:
                    break;

            }
        }

        //Double Jeopardy Round
        else if (gameType == 1) {
            switch (v.getId()) {
                case R.id.score_1:
                    winningsAmount -= 400;
                    winningsCountDJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_2:
                    winningsAmount -= 800;
                    winningsCountDJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_3:
                    winningsAmount -= 1200;
                    winningsCountDJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_4:
                    if (assessWager())
                    winningsAmount -= 1600;
                    winningsCountDJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                case R.id.score_5:
                    winningsAmount -= 2000;
                    winningsCountDJ -= 1;
                    vibe.vibrate(100);
                    displayWinnings(winningsAmount);
                    break;

                default:
                    break;
            }
        }

        return true;
    }

    public void setJeopardyRound() {
        Button score1 = findViewById(R.id.score_1);
        score1.setText("200");

        Button score2 = findViewById(R.id.score_2);
        score2.setText("400");

        Button score3 = findViewById(R.id.score_3);
        score3.setText("600");

        Button score4 = findViewById(R.id.score_4);
        score4.setText("800");

        Button score5 = findViewById(R.id.score_5);
        score5.setText("1000");
    }


    public void setDoubleJeopardyRound() {
        Button score1 = findViewById(R.id.score_1);
        score1.setText("400");

        Button score2 = findViewById(R.id.score_2);
        score2.setText("800");

        Button score3 = findViewById(R.id.score_3);
        score3.setText("1200");

        Button score4 = findViewById(R.id.score_4);
        score4.setText("1600");

        Button score5 = findViewById(R.id.score_5);
        score5.setText("2000");
    }


    public void displayWinnings(int money){
        TextView winningsView = findViewById(R.id.winnings);
        winningsView.setText(String.valueOf(money));
    }

    public boolean assessWager(){

        final EditText finalWager = findViewById(R.id.wager);
        String stringWager = finalWager.getText().toString();


        if (stringWager == null || stringWager == "" || stringWager.isEmpty()) {
            Toast needValidWager = Toast.makeText(getApplicationContext(), "Please enter a valid wager", Toast.LENGTH_SHORT);
            needValidWager.show();
            return false;
        }

        else {
            fjWager = Integer.valueOf(stringWager);

            if (winningsAmount <= 0) {
                Toast noMoney = Toast.makeText(getApplicationContext(), "You do not have enough money to play Final Jeopardy ", Toast.LENGTH_SHORT);
                noMoney.show();
                return false;
            }

            else if (fjWager > winningsAmount || fjWager < 0) {
                Toast needValidWager = Toast.makeText(getApplicationContext(), "Please enter a valid wager", Toast.LENGTH_SHORT);
                needValidWager.show();
                return false;
            }

            else {
                Toast validWager = Toast.makeText(getApplicationContext(), "Valid Wager", Toast.LENGTH_SHORT);
                validWager.show();
                return true;
            }
        }
    }
}
