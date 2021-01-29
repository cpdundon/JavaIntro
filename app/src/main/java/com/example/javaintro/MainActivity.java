package com.example.javaintro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    private MaterialTextView tvDisplay;
    private MaterialButton btnC;
    private MaterialButton btnDel;
    private MaterialButton btnPer;
    private MaterialButton btn1;
    private MaterialButton btn2;
    private MaterialButton btn3;
    private MaterialButton btn4;
    private MaterialButton btn5;
    private MaterialButton btn6;
    private MaterialButton btn7;
    private MaterialButton btn8;
    private MaterialButton btn9;
    private MaterialButton btn0;

    private String strDisplay = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator);
        tvDisplay = findViewById(R.id.tv_display);
        btnC = findViewById(R.id.btn_c);
        btnDel = findViewById(R.id.btn_del);
        btnPer = findViewById(R.id.btn_per);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn0 = findViewById(R.id.btn_0);

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intCount++;
                tvDisplay.setText("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btnPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView((MaterialButton) v);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strCurrDisp = tvDisplay.getText().toString();
                tvDisplay.setText(strCurrDisp.substring(0,strCurrDisp.length()-1));
            }
        });
    }

    private void updateView(MaterialButton btn) {
        tvDisplay.setText(tvDisplay.getText().toString() + btn.getText().toString());
    }
}