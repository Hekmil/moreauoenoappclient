package com.moreau_oenologie.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MessageActivity extends AppCompatActivity {
	private User user;
	private FirebaseUser firebaseUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
		Intent intent = getIntent();
		if (intent!= null) {
			user = intent.getParcelableExtra("user");
		}
	}
}
