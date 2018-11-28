package com.example.jeremie.moreauoenoappclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.firebase.ui.auth.AuthUI;

import com.firebase.ui.auth.AuthUI;

public class SignInActivity extends AppCompatActivity {

    private  signInProviders =
            listOf(AuthUI.IdpConfig.EmailBuilder()
                .setAllowNewAccount(true)
                .setRequireName(true)
                .build())

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


    }
}
