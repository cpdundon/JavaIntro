package com.example.javaintro.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.button.MaterialButton;

public class MainViewModel extends ViewModel {
    private Operator operator = new OperatorPlus();
    private Double previousValue = 0.0;
    private MutableLiveData<String> mldTotal = new MutableLiveData<String>();
    public LiveData<String> ldTotal = (LiveData) mldTotal;
    public LiveData<String> getDisplay() {
        return ldTotal;
    }

    private MutableLiveData<Boolean> mlbFresh = new MutableLiveData<Boolean>();
    public LiveData<Boolean> ldFresh = (LiveData) mlbFresh;

    public void execute(String value) {
        if (operator instanceof OperatorPlus) {
            previousValue += Double.parseDouble(value);
            mldTotal.setValue(previousValue.toString());
        } else if (operator instanceof OperatorMinus) {
            previousValue -= Double.parseDouble(value);
            mldTotal.setValue(previousValue.toString());
        } else if (operator instanceof OperatorMultiply) {
            previousValue *= Double.parseDouble(value);
            mldTotal.setValue(previousValue.toString());
        } else if (operator instanceof OperatorDivide) {
            if (Double.parseDouble(value) == 0.0) {
                mldTotal.setValue("0");
            } else {
                previousValue /= Double.parseDouble(value);
                mldTotal.setValue(previousValue.toString());
            }
        } else {
            mldTotal.setValue("0");
        }

        //mlbFresh = (Boolean) true;
    }

    public void setOperator(Operator operator, Double screenValue) {
        this.operator = operator;
        this.previousValue = screenValue;
        mldTotal.setValue("0");
    }

    public void changeSign(String oldValue) {
        if (oldValue.equals("0")) {
            return;
        }

        boolean isPositive = !oldValue.substring(0,1).equals("-");
        String sT;

        if (isPositive) {
            sT = "-" + oldValue;
        } else {
            sT = oldValue.substring(1);
        }

        mldTotal.setValue(sT);
    }

    public String pctCalc(Double currentValue) {
        return String.valueOf(currentValue * 100.0);
    }
}