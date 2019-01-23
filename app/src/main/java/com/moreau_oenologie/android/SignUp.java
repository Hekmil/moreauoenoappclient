package com.moreau_oenologie.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
	private FirebaseAuth mAuth;;
	private EditText signUpEmailField;
	private EditText signUpPasswordField;
	private EditText signUpFirstNameField;
	private EditText signUpLastNameField;
	private CheckBox signUpIsOenoCheckBox;
	private EditText signUpPasswordOenoField;
	private EditText signUpDomainField;
	private User user;
	public ProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		signUpEmailField = (EditText)findViewById(R.id.signUpFieldEmail);
		signUpPasswordField = (EditText) findViewById(R.id.signUpFieldPassword);
		signUpFirstNameField = (EditText)findViewById(R.id.signUpFieldFirstName);
		signUpLastNameField = (EditText)findViewById(R.id.signUpFieldLastName);
		signUpIsOenoCheckBox = (CheckBox) findViewById(R.id.signUpCheckBoxIsOeno);
		signUpPasswordOenoField = (EditText)findViewById(R.id.signUpFieldPasswordOeno);
		signUpDomainField = (EditText)findViewById(R.id.signUpFieldDomain);
		signUpPasswordOenoField.setVisibility(View.GONE);
		findViewById(R.id.signUpButton).setOnClickListener(this);
		signUpIsOenoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					signUpPasswordOenoField.setVisibility(View.VISIBLE);
				} else {
					signUpPasswordOenoField.setVisibility(View.GONE);
				}
			}
		});
		mAuth = FirebaseAuth.getInstance();
	}
	private void createAccount(String email, String password, String firstName, String lastName, String domain, boolean isChecked, String passwordOeno) {
		showProgressDialog();
		if (!validateForm(email, password, firstName, lastName, domain, isChecked, passwordOeno)) {
			hideProgressDialog();
			return;
		}
		mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {
					boolean isOeno = isChecked && passwordOeno.equals("moreau");
					user = new User(firstName, lastName, email, domain, isOeno);
					sendEmailVerification();
					String UID = mAuth.getCurrentUser().getUid();
					user.writeNewUser(UID);
					Intent messageActivityIntent = new Intent(SignUp.this, MessageActivity.class);
					messageActivityIntent.putExtra("user", user);
					startActivity(messageActivityIntent);
				} else {
					Toast.makeText(SignUp.this, getString(R.string.errorSignUp), Toast.LENGTH_SHORT).show();
				}
			}
		});
		hideProgressDialog();
		return;
	}

	private boolean validateForm(String email, String password, String firstName, String lastName, String domain, boolean isChecked, String passwordOeno) {
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
		if (TextUtils.isEmpty(firstName)) {
			signUpFirstNameField.setError(getString(R.string.required));
			valid = false;
		} else {
			signUpFirstNameField.setError(null);
		}
		if (TextUtils.isEmpty(lastName)) {
			signUpLastNameField.setError(getString(R.string.required));
			valid = false;
		} else {
			signUpLastNameField.setError(null);
		}
		if (TextUtils.isEmpty(domain)) {
			signUpDomainField.setError(getString(R.string.required));
			valid = false;
		} else {
			signUpDomainField.setError(null);
		}
		if (isChecked) {
			if (TextUtils.isEmpty(passwordOeno) || !passwordOeno.equals("moreau")) {
				signUpPasswordOenoField.setError(getString(R.string.required));
				valid = false;
			} else {
				signUpPasswordOenoField.setError(null);
			}
		}
		return valid;
	}
	public void onClick(View v) {
		int viewId = v.getId();
		if (viewId == R.id.signUpButton) {
			createAccount(signUpEmailField.getText().toString(), signUpPasswordField.getText().toString(), signUpFirstNameField.getText().toString(), signUpLastNameField.getText().toString(), signUpDomainField.getText().toString(), signUpIsOenoCheckBox.isChecked(), signUpPasswordOenoField.getText().toString());
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

	private void sendEmailVerification() {
		final FirebaseUser firebaseUser = mAuth.getCurrentUser();
		firebaseUser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
			@Override
			public void onComplete(@NonNull Task<Void> task) {
				if (task.isSuccessful()) {
					String verifiedString = getString(R.string.verifiedEmail) + " " + firebaseUser.getEmail();
					Toast.makeText(SignUp.this, verifiedString, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(SignUp.this, getString(R.string.errorVerrifiedEmail), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}