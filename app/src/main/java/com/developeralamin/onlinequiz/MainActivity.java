package com.developeralamin.onlinequiz;

import static com.developeralamin.onlinequiz.SplashScreen.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue = 20;
    ProgressBar progressBar;

    List<ModelClass> allQuetionsList;
    ModelClass modelClass;
    int index = 0;

    TextView card_quetion, optiona, optionb, optionc, optiond;
    CardView cardOA, cardOB, cardOC, cardOD;
    LinearLayout nextBtn;

    int correctCount = 0;
    int wrongCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hooks();

        allQuetionsList = list;
        Collections.shuffle(allQuetionsList);
        modelClass = list.get(index);

        setAllData();

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.timeout_dialog);

                dialog.findViewById(R.id.btn_tryagain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, SplashScreen.class));
                    }
                });
                dialog.show();
            }
        }.start();
    }

    private void setAllData() {

        card_quetion.setText(modelClass.getQuetion());
        optiona.setText(modelClass.getoA());
        optionb.setText(modelClass.getoB());
        optionc.setText(modelClass.getoC());
        optiond.setText(modelClass.getoD());
    }

    private void Hooks() {

        progressBar = findViewById(R.id.quiz_timer);
        card_quetion = findViewById(R.id.card_quetion);
        optiona = findViewById(R.id.card_optiona);
        optionb = findViewById(R.id.card_optionb);
        optionc = findViewById(R.id.card_optionc);
        optiond = findViewById(R.id.card_optiond);

        cardOA = findViewById(R.id.cardA);
        cardOB = findViewById(R.id.cardB);
        cardOC = findViewById(R.id.cardC);
        cardOD = findViewById(R.id.cardD);

        nextBtn = findViewById(R.id.nextBtn);
    }

    public void Correct() {


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                index++;
                modelClass = list.get(index);
                setAllData();
            }
        });
    }

    public void Wrong(CardView cardOA) {

        cardOA.setCardBackgroundColor(getResources().getColor(R.color.red));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongCount++;
                if (index < list.size() - 1) {
                    index++;
                    modelClass = list.get(index);
                    setAllData();
                    resetColor();
                } else {
                    GameWon();
                }
            }
        });
    }

    private void GameWon() {
        startActivity(new Intent(MainActivity.this, WonActivity.class));

    }

    public void enableButton() {
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }

    public void disableButton() {
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void resetColor() {
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void optionClickA(View view) {
        if (modelClass.getoA().equals(modelClass.getAns())) {
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < list.size() - 1) {

            } else {
                GameWon();
            }

        } else {
            Wrong(cardOA);
        }
    }

    public void optionClickB(View view) {
        if (modelClass.getoB().equals(modelClass.getAns())) {
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < list.size() - 1) {
                index++;
                modelClass = list.get(index);
                setAllData();
                resetColor();
            } else {
                GameWon();
            }

        } else {
            Wrong(cardOB);
        }
    }

    public void optionClickC(View view) {
        if (modelClass.getoC().equals(modelClass.getAns())) {
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < list.size() - 1) {
                index++;
                modelClass = list.get(index);
                setAllData();
                resetColor();
            } else {
                GameWon();
            }

        } else {
            Wrong(cardOC);
        }
    }

    public void optionClickD(View view) {
        if (modelClass.getoD().equals(modelClass.getAns())) {
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < list.size() - 1) {
                index++;
                modelClass = list.get(index);
                setAllData();
                resetColor();
            } else {
                GameWon();
            }

        } else {
            Wrong(cardOD);
        }
    }
}