package com.example.tictactoegame;


import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView header;

    int Player_o = 0;
    int Player_x = 1;

    int activePlayer = Player_o;

    int[] fillesPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        header.setText("O turn");

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (!isGameActive) {
            return;
        }

        Button clickbtn = findViewById(view.getId());
        int clickTag = Integer.parseInt(view.getTag().toString());

        if (fillesPos[clickTag] != -1) {
            return;
        }

        fillesPos[clickTag] = activePlayer;

        if (activePlayer == Player_o) {
            clickbtn.setText("O");
            clickbtn.setBackground(getDrawable(android.R.color.holo_blue_dark));
            activePlayer = Player_x;
            header.setText("X turn");
        } else {
            clickbtn.setText("X");
            clickbtn.setBackground(getDrawable(android.R.color.holo_red_dark));
            activePlayer = Player_o;
            header.setText("O turn");

        }
        chekForWin();
    }

    private void chekForWin() {
        int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


        for (int i = 0; i < 8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if (fillesPos[val0] == fillesPos[val1] && fillesPos[val1] == fillesPos[val2]) {
                if (fillesPos[val0] != -1) {
                    isGameActive = false;

                   if (fillesPos[val0] == Player_o) {
                    showDialog("O is winner");
                   } else {
                    showDialog("X is winnner");
                   }
                }
            }
        }
    }


    private void showDialog(String winnerText) {
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartgame();
                    }
                })
                .show();
    }

    private void restartgame() {
        activePlayer = Player_o;
        header.setText("O turn");
        fillesPos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackground(getDrawable(android.R.color.darker_gray));
        btn1.setBackground(getDrawable(android.R.color.darker_gray));
        btn2.setBackground(getDrawable(android.R.color.darker_gray));
        btn3.setBackground(getDrawable(android.R.color.darker_gray));
        btn4.setBackground(getDrawable(android.R.color.darker_gray));
        btn5.setBackground(getDrawable(android.R.color.darker_gray));
        btn6.setBackground(getDrawable(android.R.color.darker_gray));
        btn7.setBackground(getDrawable(android.R.color.darker_gray));
        btn8.setBackground(getDrawable(android.R.color.darker_gray));

        isGameActive = true;
    }
}