package com.moreau_oenologie.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
	private FirebaseAuth mAuth;
	private EditText signUpEmailField;
	private EditText signUpPasswordField;
	public ProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		signUpEmailField = (EditText)findViewById(R.id.signUpFieldEmail);
		signUpPasswordField = (EditText)findViewById(R.id.signUpfieldPassword);
		Intent loginIntent = getIntent();
		mAuth = FirebaseAuth.getInstance();
	}
	private void createAccount(String email, String password) {
		if (!validateForm(email, password)) {
			return;
		}
		showProgressDialog();
		mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {
					sendEmailVerification();
					hideProgressDialog();
				} else {
					Toast.makeText(SignUp.this, getString(R.string.errorSignUp), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	private void sendEmailVerification() {
		final FirebaseUser user = mAuth.getCurrentUser();
		user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
			@Override
			public void onComplete(@NonNull Task<Void> task) {
				if (task.isSuccessful()) {
						String verifiedString = getString(R.string.verifiedEmail) + " " + user.getEmail();
					Toast.makeText(SignUp.this, verifiedString, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(SignUp.this, getString(R.string.errorVerrifiedEmail), Toast.LENGTH_SHORT).show();
				}
			}
		});
		return;
	}
	private boolean validateForm(String email, String password) {
		boolean valid = true;
		if (TextUtils.isEmpty(email)) {
			signUpEmailField.setError(getString(R.string.required));
			valid = false;
		} else {
			signUpEmailField.setError(null);
		}
		if (TextUtils.isEmpty(password)) {
			signUpPasswordField.setError(getString(R.string.required));
			valid = false;
		} else {
			signUpPasswordField.setError(null);
		}
		return valid;
	}
			public void onClick(View v) {
		int viewId = v.getId();
		if (viewId == R.id.signUpButton) {
			createAccount(signUpEmailField.getText().toString(), signUpPasswordField.getText().toString());
		}
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
	public void hideKeyboard(View view) {
		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}


}