package com.example.jeremie.moreauoenoappclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
        spinnerVolumeCuve = findViewById(R.id.spinnerVolumeCuve);
        spinnerConcentration = findViewById(R.id.spinnerConcentration);
        textVolumeCuve = findViewById(R.id.textVolumeCuve);
        textSO2Initial = findViewById(R.id.textSO2Initial);
        textSO2Souhaite = findViewById(R.id.textSO2Souhaite);
        textResultat = findViewById(R.id.textResultat);
        ArrayAdapter<CharSequence> adapterSpinnerVolumeCuve = ArrayAdapter.createFromResource(this, R.array.arrayVolumeCuve, android.R.layout.simple_spinner_item);

    }

}