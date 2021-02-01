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
    private String operator = "";
    private boolean fresh = false;
    private String[] operatorIds = new String[] {Integer.toString(R.id.btn_opr_div), Integer.toString (R.id.btn_opr_x),
            Integer.toString(R.id.btn_opr_minus), Integer.toString(R.id.btn_opr_plus)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator);
        LinearLayout llTop = findViewById(R.id.ll_top_parent);

        tvDisplay = findViewById(R.id.tv_display);
        tvDisplay.setText("0");

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
                            tvDisplay.setText("0");
                        }
                    });

                    continue;
                }

                if(mB.getId() == R.id.btn_p_m) {
                    mB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            changeSign((MaterialButton) v);
                        }
                    });

                    continue;
                }

                if(mB.getId() == R.id.btn_del) {
                    mB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String strCurrDisp = tvDisplay.getText().toString();
                            if (strCurrDisp.length() <= 1) {
                                tvDisplay.setText("0");
                            } else if (((Integer)strCurrDisp.length()).equals(2) &&
                                strCurrDisp.substring(0, 1).equals("-")) {
                                tvDisplay.setText("0");
                            }
                            else {
                                tvDisplay.setText(strCurrDisp.substring(0,strCurrDisp.length()-1));
                            }
                        }
                    });

                    continue;
                }

                if(mB.getId() == R.id.btn_equals) {
                    mB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            execute((MaterialButton) v);                        }
                    });

                    continue;
                }

                if(mB.getId() == R.id.btn_pct) {
                    mB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            double currentValue = Double.parseDouble((String) tvDisplay.getText());
                            tvDisplay.setText(String.valueOf(currentValue * 100));
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
        String root= (String) tvDisplay.getText();

        if (root.equals("0") || fresh) {
            root = "";
        }

        if (root.equals("") && ((String) btn.getText()).equals(".")) {
            root = "0";
        }

        root += btn.getText();
        tvDisplay.setText(root);
        fresh = false;
    }

    private void processOperator(MaterialButton btn) {
        priorValue = Double.parseDouble((String) tvDisplay.getText());
        operator = (String) btn.getText();
        tvDisplay.setText("0");
    }

    private void execute(MaterialButton btn) {
        double currentValue = Double.parseDouble((String) tvDisplay.getText());
        String output;
        if (operator.equals("+")) {
            output = String.valueOf(currentValue + priorValue);
        } else if (operator.equals("-")) {
            output = String.valueOf(priorValue - currentValue);
        } else if (operator.equals("*")) {
            output = String.valueOf(priorValue * currentValue);
        } else if (operator.equals("/")) {
            if (currentValue == 0.0) {
                output = "0";
            } else {
                output = String.valueOf(priorValue / currentValue);
            }
        } else {
            output = "0";
        }
        tvDisplay.setText(output);
        fresh = true;
    }

    private void changeSign(MaterialButton btn) {
        String oldValue = (String) tvDisplay.getText();
        if (oldValue.equals("0")) {
            return;
        }

        boolean isPositive = !oldValue.substring(0,1).equals("-");
        String sT;

        if (isPositive) {
            sT = "-" + oldValue;
        } else {
            sT = oldValue.substring(1, oldValue.length());
        }

        tvDisplay.setText(sT);
    }
}