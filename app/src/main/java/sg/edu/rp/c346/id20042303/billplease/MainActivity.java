package sg.edu.rp.c346.id20042303.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView amountTxt;
    TextView paxTxt;
    ToggleButton svsBtn;
    ToggleButton gstBtn;
    TextView discountTxt;
    RadioGroup rdGroup;
    Button splitBtn;
    Button resetBtn;
    TextView totalTxt;
    TextView eachPayTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTxt = findViewById(R.id.amountTxt);
        paxTxt = findViewById(R.id.paxTxt);
        svsBtn = findViewById(R.id.svsBtn);
        gstBtn = findViewById(R.id.gstBtn);
        discountTxt = findViewById(R.id.discountTxt);
        rdGroup = findViewById(R.id.rdGroup);
        splitBtn = findViewById(R.id.splitBtn);
        resetBtn = findViewById(R.id.resetBtn);
        totalTxt = findViewById(R.id.totalTxt);
        eachPayTxt = findViewById(R.id.eachPayTxt);

        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amountTxt.getText().toString().trim().length()!=0 && paxTxt.getText().toString().trim().length()!=0){
                    double newAmt = 0.0;
                    if(!svsBtn.isChecked() && !gstBtn.isChecked()){
                        newAmt = Double.parseDouble(amountTxt.getText().toString());
                    }else if(svsBtn.isChecked() && !gstBtn.isChecked()){
                        newAmt = Double.parseDouble(amountTxt.getText().toString()) * 1.1;
                    }else if(!svsBtn.isChecked() && gstBtn.isChecked()){
                        newAmt = Double.parseDouble(amountTxt.getText().toString()) * 1.07;
                    }else{
                        newAmt = Double.parseDouble(amountTxt.getText().toString()) * 1.17;
                    }

                    if(discountTxt.getText().toString().trim().length() !=0){
                        newAmt *= 1 - Double.parseDouble(discountTxt.getText().toString()) / 100;
                    }

                    totalTxt.setText("$"+ String.format("%.2f", newAmt));
                    int numPax = Integer.parseInt(paxTxt.getText().toString());
                    int checkedRadioId = rdGroup.getCheckedRadioButtonId();
                    if(checkedRadioId == R.id.cashRadio){
                        if(numPax!=1){
                            eachPayTxt.setText("$" + String.format("%.2f", newAmt/numPax) + " By Cash");
                        }else{
                            eachPayTxt.setText("$" + String.format("%.2f", newAmt)+ " By Cash");
                        }
                    }else{
                        if(numPax!=1){
                            eachPayTxt.setText("$" + String.format("%.2f", newAmt/numPax)+ " By PayNow");
                        }else{
                            eachPayTxt.setText("$" + String.format("%.2f", newAmt)+ " By PayNow");
                        }
                    }

                }
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountTxt.setText("");
                paxTxt.setText("");
                svsBtn.setChecked(false);
                gstBtn.setChecked(false);
                discountTxt.setText("");
                
            }
        });

    }

}