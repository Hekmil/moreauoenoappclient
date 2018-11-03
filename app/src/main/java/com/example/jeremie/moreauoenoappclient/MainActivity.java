package com.example.jeremie.moreauoenoappclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void pageajout(View view){
        startActivity(new Intent(this, Ajout.class));
    }

    public void pageaconversion(View view){
        startActivity(new Intent(this, Conversion.class));
    }

    public void pagesulfitage(View view){
        startActivity(new Intent(this, Sulfitage.class));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        FragmentManager fragmentManager = getSupportFragmentManager();





        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_faq:
                    FAQ faq = new FAQ();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment, faq)
                            .commit();
                    return true;
                case R.id.navigation_calcul:
                    Calcul calcul = new Calcul();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment, calcul)
                            .commit();
                    return true;
                case R.id.navigation_chat:
                    Chat chat = new Chat();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment, chat)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
