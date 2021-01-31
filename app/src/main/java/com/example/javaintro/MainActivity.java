package com.example.javaintro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private MaterialTextView tvDisplay;
    private double priorValue = 0.0;
    private String[] operatorIds = new String[] {Integer.toString(R.id.btn_equals), Integer.toString(R.id.btn_opr_div),
            Integer.toString (R.id.btn_opr_x), Integer.toString(R.id.btn_opr_minus), Integer.toString(R.id.btn_opr_plus)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator);
        LinearLayout llTop = findViewById(R.id.ll_top_parent);

        tvDisplay = findViewById(R.id.tv_display);
        for(int i = 0; i < llTop.getChildCount(); i++)
        {
            if(llTop.getChildAt(i) instanceof LinearLayout)
            {
                processButtons((LinearLayout) llTop.getChildAt(i));
            }
        }

    }

    private void processButtons(LinearLayout ll) {
        for(int i = 0; i < ll.getChildCount(); i++)
        {
            if(ll.getChildAt(i) instanceof MaterialButton)
            {
                MaterialButton mB = (MaterialButton) ll.getChildAt(i);

                if(mB.getId() == R.id.btn_clr) {
                    mB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tvDisplay.setText("");
                        }
                    });

                    continue;
                }

                if(mB.getId() == R.id.btn_del) {
                    mB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String strCurrDisp = tvDisplay.getText().toString();
                            tvDisplay.setText(strCurrDisp.substring(0,strCurrDisp.length()-1));
                        }
                    });

                    continue;
                }

                if(Arrays.asList(operatorIds).contains(Integer.toString(mB.getId()))) {
                    mB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            processOperator((MaterialButton) v);
                        }
                    });

                    continue;
                }

                mB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateView((MaterialButton) v);
                    }
                });
            }
        }
    }

    private void updateView(MaterialButton btn) {
        tvDisplay.append(btn.getText());
    }

    private void processOperator(MaterialButton btn) {
        tvDisplay.setText("Start Operation");
    }
}