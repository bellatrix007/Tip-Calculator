package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // required components and variables
    private EditText bill;
    private TextView tv_tip1, tv_tip2, tv_tip3, tv_tipx, tv_total1, tv_total2, tv_total3,
            tv_totalx, seekVal;
    private SeekBar seekBar;
    private double bill_amount, tip1, tip2, tip3, tipx, total1, total2, total3, totalx, tipxper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // default value of seekbar is 18
        tipxper = 18;

        // set up components
        bill = findViewById(R.id.bill);
        tv_tip1 = findViewById(R.id.tip1);
        tv_tip2 = findViewById(R.id.tip2);
        tv_tip3 = findViewById(R.id.tip3);
        tv_tipx = findViewById(R.id.tipx);
        tv_total1 = findViewById(R.id.total1);
        tv_total2 = findViewById(R.id.total2);
        tv_total3 = findViewById(R.id.total3);
        tv_totalx = findViewById(R.id.totalx);
        seekBar = findViewById(R.id.seekBar);
        seekVal = findViewById(R.id.seekval);

        // handle when text is changed
        bill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                // get the bill
                bill_amount = Double.parseDouble(bill.getText().toString());
                // change all the tip and total values
                updateUI();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipxper = progress;
                // change UI on change of seekbar
                updateSeekUI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateUI() {

        // calculate tips
        tip1 = Math.round(10 * bill_amount) / 100.0;
        tip2 = Math.round(15 * bill_amount) / 100.0;
        tip3 = Math.round(20 * bill_amount) / 100.0;
        tipx = Math.round(tipxper * bill_amount) / 100.0;
        tv_tip1.setText(tip1+"");
        tv_tip2.setText(tip2+"");
        tv_tip3.setText(tip3+"");
        tv_tipx.setText(tipx+"");

        // calculate total
        total1 = bill_amount + tip1;
        total2 = bill_amount + tip2;
        total3 = bill_amount + tip3;
        totalx = bill_amount + tipx;
        tv_total1.setText(total1+"");
        tv_total2.setText(total2+"");
        tv_total3.setText(total3+"");
        tv_totalx.setText(totalx+"");
    }

    private void updateSeekUI() {

        // update seekval
        seekVal.setText(tipxper + "%");
        // update tip and total
        tipx = bill_amount * tipxper /100.0;
        totalx = bill_amount + tipx;

        tv_tipx.setText(tipx+"");
        tv_totalx.setText(totalx+"");
    }
}
