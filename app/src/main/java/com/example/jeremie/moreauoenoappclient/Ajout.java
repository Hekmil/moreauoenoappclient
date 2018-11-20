package com.example.jeremie.moreauoenoappclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Ajout extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;

    private TextView textView1;
    private TextView textView2;

    boolean updateText = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        editText3 = findViewById(R.id.edittext3);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);

        textView1 = findViewById(R.id.textview1);
        textView2 = findViewById(R.id.textview2);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.concentrationsm2_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.masses_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.concentrationsv2_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.volumes_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);

        // Volume
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateSolide(s.toString(), editText2.getText().toString(), textView1, spinner1, spinner2);
            }
        });

        //Solide
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateSolide(editText1.getText().toString(), s.toString(), textView1, spinner1, spinner2);
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSolide(editText1.getText().toString(), editText2.getText().toString(), textView1, spinner1, spinner2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSolide(editText1.getText().toString(), editText2.getText().toString(), textView1, spinner1, spinner2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Liquide
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateLiquide(editText1.getText().toString(), s.toString(), textView2, spinner3, spinner4);
            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateLiquide(editText1.getText().toString(), editText3.getText().toString(), textView2, spinner3, spinner4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateLiquide(editText1.getText().toString(), editText3.getText().toString(), textView2, spinner3, spinner4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //Récupération des valeurs des champs Solide
    public void updateSolide(String volume, String solide,  TextView viewTextResult, Spinner spinnerDepart, Spinner spinnerArrivee) {
        if (updateText)
            return;
        int unitDepart = spinnerDepart.getSelectedItemPosition();
        int unitArrivee = spinnerArrivee.getSelectedItemPosition();
        double volumeVal = 0;
        double solideVal = 0;
        try {
            volumeVal = Double.parseDouble(volume);
            solideVal = Double.parseDouble(solide);
        } catch (Exception e) {
            return;
        }
        double valeurConvertie = conversionSolide(unitDepart, unitArrivee, volumeVal, solideVal);
        valeurConvertie = Math.round(valeurConvertie * 100000) / (double) 100000;

        updateText = true;
        viewTextResult.setText(valeurConvertie + "");
        updateText = false;
    }

    public double conversionSolide(int unitCm, int unitM, double volumeVal, double solideVal) {
        String[] arrayCm = getResources().getStringArray(R.array.concentrationCm_taux_array);
        String[] arrayM = getResources().getStringArray(R.array.masses_taux_array);
        double tauxCm = Double.parseDouble(arrayCm[unitCm]);
        double tauxM = Double.parseDouble(arrayM[unitM]);
        double valeurFinale = volumeVal * solideVal * tauxCm * (1 / tauxM);
        return valeurFinale;
    }

    //Récupération des valeurs des champs Liquide
    public void updateLiquide(String volume, String liquide,  TextView viewTextResult, Spinner spinnerDepart, Spinner spinnerArrivee) {
        if (updateText)
            return;
        int unitDepart = spinnerDepart.getSelectedItemPosition();
        int unitArrivee = spinnerArrivee.getSelectedItemPosition();
        double volumeVal;
        double liquideVal;
        try {
            volumeVal = Double.parseDouble(volume);
            liquideVal = Double.parseDouble(liquide);
        } catch (Exception e) {
            return;
        }
        double valeurConvertie = conversionLiquide(unitDepart, unitArrivee, volumeVal, liquideVal);
        valeurConvertie = Math.round(valeurConvertie * 100000) / (double) 100000;

        updateText = true;
        viewTextResult.setText(valeurConvertie + "");
        updateText = false;
    }

    public double conversionLiquide(int unitCm, int unitM, double volumeVal, double liquideVal) {
        String[] arrayCm = getResources().getStringArray(R.array.concentrationCv_taux_array);
        String[] arrayM = getResources().getStringArray(R.array.volumes_taux_array);
        double tauxCm = Double.parseDouble(arrayCm[unitCm]);
        double tauxM = Double.parseDouble(arrayM[unitM]);
        double valeurFinale = volumeVal * liquideVal * tauxCm * (1 / tauxM);
        return valeurFinale;
    }

}
