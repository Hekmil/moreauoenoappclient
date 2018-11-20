package com.example.jeremie.moreauoenoappclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class Sulfitage extends AppCompatActivity {
    private EditText textVolumeCuve;
    private EditText textSO2Initial;
    private EditText textSO2Souhaite;
    private EditText textResultat;
    private Spinner spinnerVolumeCuve;
    private Spinner spinnerConcentration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sulfitage);
        spinerVolumeCuve = findViewById(R.id.spinnerVolumeCube);
        spinnerConcentration = findViewById(R.id.spinnerConcentration);
        textVolumeCuve = findViewById(R.id.textVolumeCube);
        textSO2Initial = findViewById(R.id.textSO2Initial);
        textSO2Souhaite = findViewById(R.id.textSO2Souhaite);
        textResultat = findViewById(R.id.textResultat);

    }

    private double qtAdd(double final, double initial) {
        return final -initial;
    }
}