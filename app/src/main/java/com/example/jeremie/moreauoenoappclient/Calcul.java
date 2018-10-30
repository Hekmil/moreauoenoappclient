package com.example.jeremie.moreauoenoappclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Calcul extends Fragment {

    public Calcul() {

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calcul, container, false);

        //Instancier vos composants graphique ici (faîtes vos findViewById)
        return view; }

}
