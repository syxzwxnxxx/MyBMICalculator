package sg.edu.c346rp.mybmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etWeight;
    EditText etHeight;
    Button btnCal;
    Button btnReset;

    @Override
    protected void onPause() {
        super.onPause();

        String strWeight= etWeight.getText().toString();
        String strHeight = etHeight.getText().toString();
        String date = tvDate.getText().toString();
        String BMI = tvBMI.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("weight", strWeight);
        prefEdit.putString("height", strHeight);
        prefEdit.putString("date", date);
        prefEdit.putString("BMI", BMI);
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        String strWeight = prefs.getString("weight", "");
//        String strHeight = prefs.getString("height", "");
//        etWeight.setText(strWeight);
//        etHeight.setText(strHeight);
        tvDate.setText("Last Calculated Date: ");
        tvBMI.setText("Last Calculated BMI: 0.0");
    }

    TextView tvDate;
    TextView tvBMI;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = (EditText)findViewById(R.id.editTextWeight);
        etHeight = (EditText)findViewById(R.id.editTextHeight);
        btnCal = (Button)findViewById(R.id.buttonCalculate);
        btnReset = (Button)findViewById(R.id.buttonReset);
        tvBMI = (TextView)findViewById(R.id.textViewBMI);
        tvDate = (TextView)findViewById(R.id.textViewDate);

        Calendar now = Calendar.getInstance(); //Create a Calendar object with current date/time
        final String datetime = now.get(Calendar.DAY_OF_MONTH)+ "/"+
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY)+":"+
                now.get(Calendar.MINUTE);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etWeight.setText("");
                etHeight.setText("");
                tvBMI.setText("Last Calculated BMI: ");
                tvDate.setText("Last Calculated Date: ");

            }
        });

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight  =  etWeight.getText().toString();
                double weightx = Double.parseDouble(weight);

                String height = etHeight.getText().toString();
                double heightx = Double.parseDouble(height);

                bmi = weightx / (heightx * heightx);

                tvBMI.setText("Last Calculated BMI: " + bmi);
                tvDate.setText("Last Calculated Date: " + datetime);


            }
        });
    }


}
