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

public class Conversion extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Spinner spinner5;
    Spinner spinner6;
    Spinner spinner7;
    Spinner spinner8;

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    EditText editText8;

    boolean updateEditText1 = false;
    boolean updateEditText2 = false;
    boolean updateEditText3 = false;
    boolean updateEditText4 = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.spinner5);
        spinner6 = findViewById(R.id.spinner6);
        spinner7 = findViewById(R.id.spinner7);
        spinner8 = findViewById(R.id.spinner8);

        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        editText3 = findViewById(R.id.edittext3);
        editText4 = findViewById(R.id.edittext4);
        editText5 = findViewById(R.id.edittext5);
        editText6 = findViewById(R.id.edittext6);
        editText7 = findViewById(R.id.edittext7);
        editText8 = findViewById(R.id.edittext8);



        // initialisation des spinner
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.masses_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.volumes_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.concentrationsm_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.concentrationsv_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);







        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionmasses(editText1.getText().toString(), editText2, spinner1, spinner2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionmasses(editText1.getText().toString(), editText2, spinner1, spinner2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //initialisation des edittext
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateConversionmasses(s.toString(), editText2, spinner1, spinner2);
            }

        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateConversionmasses(s.toString(), editText1, spinner2, spinner1);

            }
        });











        spinner3.setAdapter(adapter2);
        spinner4.setAdapter(adapter2);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionvolumes(editText3.getText().toString(), editText4, spinner3, spinner4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionvolumes(editText3.getText().toString(), editText4, spinner3, spinner4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateConversionvolumes(s.toString(), editText4, spinner3, spinner4);
            }

        });

        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateConversionvolumes(s.toString(), editText3, spinner4, spinner3);

            }
        });










        spinner5.setAdapter(adapter3);
        spinner6.setAdapter(adapter3);

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionconcentrationm(editText5.getText().toString(), editText6, spinner5, spinner6);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionconcentrationm(editText5.getText().toString(), editText6, spinner5, spinner6);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateConversionconcentrationm(s.toString(), editText6, spinner5, spinner6);
            }

        });

        editText6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateConversionconcentrationm(s.toString(), editText5, spinner6, spinner5);

            }
        });






        spinner7.setAdapter(adapter4);
        spinner8.setAdapter(adapter4);

        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionconcentrationv(editText7.getText().toString(), editText8, spinner7, spinner8);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateConversionconcentrationv(editText7.getText().toString(), editText8, spinner7, spinner8);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editText7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateConversionconcentrationv(s.toString(), editText8, spinner7, spinner8);
            }

        });

        editText8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateConversionconcentrationv(s.toString(), editText7, spinner8, spinner7);

            }
        });






    }




























    public void updateConversionmasses(String masseDepart, EditText editTextArrivee, Spinner spinnerDepart, Spinner spinnerArrivee){
        if (updateEditText1)
            return;
        int masseDepartPos = spinnerDepart.getSelectedItemPosition();
        int masseArriveePos = spinnerArrivee.getSelectedItemPosition();
        double masseDepartD = 0;
        try {
            masseDepartD = Double.parseDouble(masseDepart);
        } catch (Exception e) {
            return;
        }
        double valeurConvertie = conversionmasses(masseDepartPos, masseArriveePos, masseDepartD);
        valeurConvertie = Math.round(valeurConvertie * 10000) / (double) 10000;

        updateEditText1 = true;
        editTextArrivee.setText(valeurConvertie + "");
        updateEditText1 = false;
    }




    public void updateConversionvolumes(String volumeDepart, EditText editTextArrivee, Spinner spinnerDepart, Spinner spinnerArrivee){
        if (updateEditText2)
            return;
        int volumeDepartPos = spinnerDepart.getSelectedItemPosition();
        int volumeArriveePos = spinnerArrivee.getSelectedItemPosition();
        double volumeDepartD = 0;
        try {
            volumeDepartD = Double.parseDouble(volumeDepart);
        } catch (Exception e) {
            return;
        }
        double valeurConvertie = conversionvolumes(volumeDepartPos, volumeArriveePos, volumeDepartD);
        valeurConvertie = Math.round(valeurConvertie * 10000) / (double) 10000;

        updateEditText2 = true;
        editTextArrivee.setText(valeurConvertie + "");
        updateEditText2 = false;
    }

    public void updateConversionconcentrationm(String concentrationmDepart, EditText editTextArrivee, Spinner spinnerDepart, Spinner spinnerArrivee){
        if (updateEditText3)
            return;
        int concentrationmDepartPos = spinnerDepart.getSelectedItemPosition();
        int concentrationmArriveePos = spinnerArrivee.getSelectedItemPosition();
        double concentrationmDepartD = 0;
        try {
            concentrationmDepartD = Double.parseDouble(concentrationmDepart);
        } catch (Exception e) {
            return;
        }
        double valeurConvertie = conversionconcentrationm(concentrationmDepartPos, concentrationmArriveePos, concentrationmDepartD);
        valeurConvertie = Math.round(valeurConvertie * 10000) / (double) 10000;

        updateEditText3 = true;
        editTextArrivee.setText(valeurConvertie + "");
        updateEditText3 = false;
    }

    public void updateConversionconcentrationv(String concentrationvDepart, EditText editTextArrivee, Spinner spinnerDepart, Spinner spinnerArrivee){
        if (updateEditText4)
            return;
        int concentrationvDepartPos = spinnerDepart.getSelectedItemPosition();
        int concentrationvArriveePos = spinnerArrivee.getSelectedItemPosition();
        double concentrationvDepartD = 0;
        try {
            concentrationvDepartD = Double.parseDouble(concentrationvDepart);
        } catch (Exception e) {
            return;
        }
        double valeurConvertie = conversionconcentrationv(concentrationvDepartPos, concentrationvArriveePos, concentrationvDepartD);
        valeurConvertie = Math.round(valeurConvertie * 10000) / (double) 10000;

        updateEditText4 = true;
        editTextArrivee.setText(valeurConvertie + "");
        updateEditText4 = false;
    }










    public double conversionmasses (int masseDepartPos, int masseArriveePos, double masseDepart){
            String[] tauxArray1 = getResources().getStringArray(R.array.masses_taux_array);
            Double taux1 = Double.parseDouble(tauxArray1[masseDepartPos]);
            Double taux2 = Double.parseDouble(tauxArray1[masseArriveePos]);
            double valeurGramme = masseDepart * taux1;
            double valeurArrivee = valeurGramme * (1/taux2);
            return valeurArrivee;
        }








    public double conversionvolumes (int volumeDepartPos, int volumeArriveePos, double volumeDepart){
        String[] tauxArray2 = getResources().getStringArray(R.array.volumes_taux_array);
        Double taux1 = Double.parseDouble(tauxArray2[volumeDepartPos]);
        Double taux2 = Double.parseDouble(tauxArray2[volumeArriveePos]);
        double valeurLitre = volumeDepart * taux1;
        double valeurArrivee = valeurLitre * (1/taux2);
        return valeurArrivee;
    }

    public double conversionconcentrationm (int concentrationmDepartPos, int concentrationmArriveePos, double concentrationmDepart){
        String[] tauxArray3 = getResources().getStringArray(R.array.concentrationm_taux_array);
        Double taux1 = Double.parseDouble(tauxArray3[concentrationmDepartPos]);
        Double taux2 = Double.parseDouble(tauxArray3[concentrationmArriveePos]);
        double valeurConcentration = concentrationmDepart * taux1;
        double valeurArrivee = valeurConcentration * (1/taux2);
        return valeurArrivee;
    }


    public double conversionconcentrationv (int concentrationvDepartPos, int concentrationvArriveePos, double concentrationvDepart){
        String[] tauxArray4 = getResources().getStringArray(R.array.concentrationv_taux_array);
        Double taux1 = Double.parseDouble(tauxArray4[concentrationvDepartPos]);
        Double taux2 = Double.parseDouble(tauxArray4[concentrationvArriveePos]);
        double valeurConcentration = concentrationvDepart * taux1;
        double valeurArrivee = valeurConcentration * (1/taux2);
        return valeurArrivee;
    }






}
