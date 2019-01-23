package com.moreau_oenologie.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.text.TextUtils;

public class Login extends AppCompatActivity implements View.OnClickListener {
	private EditText logInEmailField;
	private EditText logInPasswordField;
	private FirebaseAuth mAuth;
	public ProgressDialog mProgressDialog;
	private User user;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		logInEmailField = (EditText) findViewById(R.id.logInFieldEmail);
		logInPasswordField = (EditText) findViewById(R.id.logInFieldPassword);
			findViewById(R.id.logInCreateAccountButton).setOnClickListener(this);
		findViewById(R.id.logInButton).setOnClickListener(this);
		mAuth = FirebaseAuth.getInstance();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	public void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage(getString(R.string.loading));
			mProgressDialog.setIndeterminate(true);
		}
		mProgressDialog.show();
	}

	public void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	private boolean validateForm(String email, String password) {
		boolean valid = true;
		if (TextUtils.isEmpty(email)) {
			logInEmailField.setError(getString(R.string.required));
			valid = false;
		} else {
			logInEmailField.setError(null);
		}
		if (TextUtils.isEmpty(password)) {
			logInPasswordField.setError(getString(R.string.required));
			valid = false;
		} else {
			logInPasswordField.setError(null);
		}

		return valid;
	}

	@Override
	public void onClick(View v) {
		int viewID = v.getId();
		if (viewID == R.id.logInCreateAccountButton) {
			Intent[] intentArray = new Intent[1];
			intentArray[0] = new Intent(Login.this, SignUp.class);
			startActivities(intentArray);
		} else if (viewID == R.id.logInButton) {
			signIn(logInEmailField.getText().toString(), logInPasswordField.getText().toString());
		}
	}

	private void signIn(String email, String password) {
		if (!validateForm(email, password)) {
			return;
		}

		showProgressDialog();

		// [START sign_in_with_email]
		mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {
					String UID = mAuth.getCurrentUser().getUid();
					getUserFromDb(UID);
					Intent messageActivityIntent = new Intent(Login.this, MessageActivity.class);
					messageActivityIntent.putExtra("user", user);
					startActivity(messageActivityIntent);
				} else {
					// If sign in fails, display a message to the user.
					Toast.makeText(Login.this, "Authentication failed.",
							Toast.LENGTH_SHORT).show();
				}
				hideProgressDialog();
				// [END_EXCLUDE]
			}
		});
		// [END sign_in_with_email]
	}
	private void getUserFromDb(String UID) {
		DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("/Users/" + UID);
		ValueEventListener userListener = new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				user = dataSnapshot.getValue(User.class);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Toast.makeText(Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
			}
		};
		database.addListenerForSingleValueEvent(userListener);
	}



	public void hideKeyboard(View view) {
		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	@Override
	public  void onStop() {
		super.onStop();
		hideProgressDialog();
	}


}