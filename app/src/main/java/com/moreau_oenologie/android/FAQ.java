package com.moreau_oenologie.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FAQ extends Fragment {

    public FAQ() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        Button butVinification = view.findViewById(R.id.buttonVinification);
        Button butElevage = view.findViewById(R.id.buttonElevage);
        Button buttonPreparation =  view.findViewById(R.id.buttonPreparation);
        Button buttonBio = view.findViewById(R.id.buttonBio);
        Button buttonEffervescent = view.findViewById(R.id.buttonEffervescent);
        Button buttonLiquoreux =  view.findViewById(R.id.buttonLiquoreux);

        butVinification.setOnClickListener(view1 -> {
            FragmentTransaction f = getFragmentManager().beginTransaction();
            f.replace(R.id.fragment, new Vinification());
            f.commit();
        });

        butElevage.setOnClickListener(view1 -> {
            FragmentTransaction f = getFragmentManager().beginTransaction();
            f.replace(R.id.fragment, new Elevage());
            f.commit();
        });

        buttonPreparation.setOnClickListener(view1 -> {
            FragmentTransaction f = getFragmentManager().beginTransaction();
            f.replace(R.id.fragment, new Preparation());
            f.commit();
        });

        buttonBio.setOnClickListener(view1 -> {
            FragmentTransaction f = getFragmentManager().beginTransaction();
            f.replace(R.id.fragment, new Bio());
            f.commit();
        });

        buttonEffervescent.setOnClickListener(view1 -> {
            FragmentTransaction f = getFragmentManager().beginTransaction();
            f.replace(R.id.fragment, new Effervescent());
            f.commit();
        });

        buttonLiquoreux.setOnClickListener(view1 -> {
            FragmentTransaction f = getFragmentManager().beginTransaction();
            f.replace(R.id.fragment, new Liquoreux());
            f.commit();
        });


        return view;
    }

   /* public void loadFragment(final Fragment fragment){

        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.addToBackStack(fragment.getClass().getName());
        ft.commit();

    }*/
}
