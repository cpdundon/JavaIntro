package com.example.javaintro.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.javaintro.R;
import com.example.javaintro.viewmodel.MainViewModel;
import com.example.javaintro.viewmodel.Operator;
import com.example.javaintro.viewmodel.OperatorDivide;
import com.example.javaintro.viewmodel.OperatorMinus;
import com.example.javaintro.viewmodel.OperatorMultiply;
import com.example.javaintro.viewmodel.OperatorPlus;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private MaterialTextView tvDisplay;
    private boolean fresh = false;
    private final String[] operatorIds = new String[] {Integer.toString(R.id.btn_opr_div), Integer.toString (R.id.btn_opr_x),
            Integer.toString(R.id.btn_opr_minus), Integer.toString(R.id.btn_opr_plus)};

    // Declare viewmodel
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

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

        // Observe data
        viewModel.getDisplay().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String display) {
                if (display.equals("0.0")) {
                    display = "0";
                }

                tvDisplay.setText(display);
            }
        });

        // Initialize the display
        viewModel.execute("0");
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
                            viewModel.setOperator(new OperatorMultiply(), 0.0);
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
                            deleteChar();
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
                            Double currentValue = Double.parseDouble(displayText());
                            tvDisplay.setText(viewModel.pctCalc(currentValue));
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

    private void deleteChar() {
        String strCurrDisp = displayText();
        if (strCurrDisp.length() <= 1) {
            tvDisplay.setText("0");
        } else if (((Integer)strCurrDisp.length()).equals(2) &&
            strCurrDisp.substring(0, 1).equals("-")) {
            tvDisplay.setText("0");
        }
        else {
            tvDisplay.setText(strCurrDisp.substring(0,strCurrDisp.length()-1));
        }
        fresh = false;
    }

    private void updateView(MaterialButton btn) {
        String root = displayText();

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
        Double priorValue = Double.parseDouble(displayText());
        String operatorStr = (String) btn.getText();
        Operator oper = new OperatorPlus();
        switch (operatorStr) {
            case "-":
                oper = new OperatorMinus();
                break;
            case "*":
                oper = new OperatorMultiply();
                break;
            case "/":
                oper = new OperatorDivide();
                break;
        }
        viewModel.setOperator(oper, priorValue);
    }

    private void execute(MaterialButton btn) {
        viewModel.execute(displayText());
        fresh = true;
    }

    private void changeSign(MaterialButton btn) {
        String oldValue = displayText();
        viewModel.changeSign(oldValue);
    }

    private String displayText() {
        return (String) tvDisplay.getText();
    }
}