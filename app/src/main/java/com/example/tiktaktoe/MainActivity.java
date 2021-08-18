package com.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Initializing
    int activePlayer = 0;
    //All positions
    int[] cellState = {10, 10, 10, 10, 10, 10, 10, 10, 10};
    //Winner Positions
    int[][] winPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    TextView imgX;
    TextView imgO;
    ImageView winnerx;
    ImageView winnerO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgO = findViewById(R.id.textO);
        imgX = findViewById(R.id.textX);
    }

    //Listener
    public void imgOnclick(View v) {
        //Casting ImageViews
        ImageView animeImg = (ImageView) v;
        int tag = Integer.parseInt(animeImg.getTag().toString());
        //Position activePlayer
        cellState[tag] = activePlayer;

        //Set Animation
        animeImg.setTranslationY(-1500);
        if (activePlayer == 0) {
            animeImg.setImageResource(R.drawable.o);
            activePlayer = 1;
        } else {
            animeImg.setImageResource(R.drawable.x);
            activePlayer = 0;
        }
        animeImg.animate().translationYBy(1500).rotation(3600).setDuration(300);

    //Initializing when Player wins
        for (int[] i :
                winPosition) {
            if (
                    cellState[i[0]] == cellState[i[1]]
                            && cellState[i[1]] == cellState[i[2]]
                            && cellState[i[0]] != 10
            ) {
                boardScore();
                resetCellState();
                resetGame();
            }
        }
    }
    //when one round is finished,reseting
    public void resetGame() {
        ConstraintLayout ctl = findViewById(R.id.container);
        for (int i = 0; i < ctl.getChildCount(); i++) {
            if (ctl.getChildAt(i) instanceof ImageView) {
                ImageView img = (ImageView) ctl.getChildAt(i);
                if (img.getTag() != null) {
                    img.setImageDrawable(null);
                }
            }
        }
    }
    //reseting positions
    public void resetCellState() {
        for (int i = 0; i < cellState.length; i++) {
            cellState[i] = 10;
        }
    }

    @SuppressLint("SetTextI18n")
    //Counting how many times players wins
    public void boardScore() {
        if (activePlayer == 0) {
            int playerXWin = Integer.parseInt(imgX.getText().toString());
            playerXWin++;
            imgX.setText(playerXWin + "");
        } else {
            int playerOWin = Integer.parseInt(imgO.getText().toString());
            playerOWin++;
            imgO.setText(playerOWin + "");
        }
    }
    //Initializing Button reset
    public void resetBtn(View vm) {
        imgX.setText("0");
        imgO.setText("0");
        winnerO.animate().alpha(0);
        winnerx.animate().alpha(0);
    }
    //Initializing Button show winner
    public void showWinnerX(View vn) {
        winnerx = findViewById(R.id.xIsWin);
        winnerO = findViewById(R.id.vIsWin);
        if (activePlayer == 1) {
            winnerx.animate().alpha(1).setDuration(500);
        } else {
            winnerO.animate().alpha(1).setDuration(500);
        }
    }


}