package com.example.jeremie.moreauoenoappclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Sulfitage extends AppCompatActivity {

    private EditText editTextVolumeCuve;
    private EditText editTextSO2Initial;
    private EditText editTextSO2Final;

    private Spinner spinnerVolumeCuve;
    private Spinner spinnerConcentration;

    private TextView textViewResultat;

    private boolean updateText = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sulfitage);

        editTextVolumeCuve = findViewById(R.id.textVolumeCuve);
        editTextSO2Initial = findViewById(R.id.textSO2Initial);
        editTextSO2Final = findViewById(R.id.textSO2Final);

        spinnerVolumeCuve = findViewById(R.id.spinnerVolumeCuve);
        spinnerConcentration = findViewById(R.id.spinnerConcentration);

        textViewResultat = findViewById(R.id.textResultat);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.arrayVolumeCuve, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.arrayContrationSulfitage, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerVolumeCuve.setAdapter(adapter1);
        spinnerConcentration.setAdapter(adapter2);

        //Volume cuve
        editTextVolumeCuve.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateQuantite(
                        s.toString(),
                        editTextSO2Initial.getText().toString(),
                        editTextSO2Final.getText().toString(),
                        spinnerVolumeCuve,
                        spinnerConcentration,
                        textViewResultat
                );
            }
        });

        spinnerVolumeCuve.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuantite(
                        editTextVolumeCuve.getText().toString(),
                        editTextSO2Initial.getText().toString(),
                        editTextSO2Final.getText().toString(),
                        spinnerVolumeCuve,
                        spinnerConcentration,
                        textViewResultat
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //SO2 Initial
        editTextSO2Initial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateQuantite(
                        editTextVolumeCuve.getText().toString(),
                        s.toString(),
                        editTextSO2Final.getText().toString(),
                        spinnerVolumeCuve,
                        spinnerConcentration,
                        textViewResultat
                );
            }
        });

        //SO2 Final
        editTextSO2Final.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateQuantite(
                        editTextVolumeCuve.getText().toString(),
                        editTextSO2Initial.getText().toString(),
                        s.toString(),
                        spinnerVolumeCuve,
                        spinnerConcentration,
                        textViewResultat
                );
            }
        });

        //Concentration
        spinnerConcentration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuantite(
                        editTextVolumeCuve.getText().toString(),
                        editTextSO2Initial.getText().toString(),
                        editTextSO2Final.getText().toString(),
                        spinnerVolumeCuve,
                        spinnerConcentration,
                        textViewResultat
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Récupération des valeurs des champs
    public void updateQuantite(String volume, String so2Initial, String so2Final, Spinner spinnerVolume, Spinner spinnerConcentration, TextView viewTestResult) {
        if (updateText)
            return;
        int valSpinnerVolume = spinnerVolume.getSelectedItemPosition();
        int valSpinnerConcentration = spinnerConcentration.getSelectedItemPosition();
        double valVolume;
        double valSo2Initial;
        double valSo2Final;
        try {
            valVolume = Double.parseDouble(volume);
            valSo2Initial = Double.parseDouble(so2Initial);
            valSo2Final = Double.parseDouble(so2Final);
        } catch (Exception e) {
            return;
        }
        double valeurFinale = resultat(valSpinnerVolume, valSpinnerConcentration, valVolume, valSo2Initial, valSo2Final);
        valeurFinale = Math.round(valeurFinale * 1000) / (double) 1000;

        updateText = true;
        viewTestResult.setText(valeurFinale + "");
        updateText = false;
    }

    public double resultat(int spinnerVolume, int spinnerC, double volume, double s02Initial, double s02Final) {

        String[] tab = getResources().getStringArray(R.array.arrayTauxContrationSulfitage);
        double tauxV = Double.parseDouble(tab[spinnerC]);
        double m;
        if (spinnerVolume == 0) {
            m = 1;
        } else {
            m = 2.28;
        }
        return (s02Final - s02Initial) * 1.5/100 * m * volume * tauxV;
    }
}