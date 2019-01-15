package com.moreau_oenologie.android;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.text.TextUtils;

public class Login extends AppCompatActivity implements View.OnClickListener {
	private static final String TAG = "EmailPassword";
	private TextView mStatusTextView;
	private TextView mDetailTextView;
	private EditText mEmailField;
	private EditText mPasswordField;
	private FirebaseAuth mAuth;
	public ProgressDialog mProgressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mStatusTextView = (TextView) findViewById(R.id.status);
		mDetailTextView = (TextView) findViewById(R.id.detail);
		mEmailField = (EditText) findViewById(R.id.fieldEmail);
		mPasswordField = (EditText) findViewById(R.id.fieldPassword);
		findViewById(R.id.emailSignInButton).setOnClickListener(this);
		findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
		findViewById(R.id.signOutButton).setOnClickListener(this);
		findViewById(R.id.verifyEmailButton).setOnClickListener(this);
		mAuth = FirebaseAuth.getInstance();
	}

	@Override
	public void onStart() {
		super.onStart();
		FirebaseUser currentUser = mAuth.getCurrentUser();
		updateUI(currentUser);
	}

	private void createAccount(String email, String password) {
			Log.d(TAG, "createAccount" + email);
			if (!validateForm()) {
				return;
			}
		showProgressDialog();
		mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {
					Log.d(TAG, "user created");
					FirebaseUser user = mAuth.getCurrentUser();
					updateUI(user);
				} else {
					Log.d(TAG, "failure", task.getException());
					Toast.makeText(Login.this, "AuthenticationFailed", Toast.LENGTH_SHORT).show();
					updateUI(null);
				}
				hideProgressDialog();
			}
		});
	}

	public void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("loading");
			mProgressDialog.setIndeterminate(true);
		}
		mProgressDialog.show();
	}

	public void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	private boolean validateForm() {
		boolean valid = true;
		String email = mEmailField.getText().toString();
		if (TextUtils.isEmpty(email)) {
			mEmailField.setError("Required.");
			valid = false;
		} else {
			mEmailField.setError(null);
		}

		String password = mPasswordField.getText().toString();
		if (TextUtils.isEmpty(password)) {
			mPasswordField.setError("Required.");
			valid = false;
		} else {
			mPasswordField.setError(null);
		}

		return valid;
	}

	private void updateUI(FirebaseUser user) {
		hideProgressDialog();
		if (user != null) {
			mStatusTextView.setText("email: " + user.getEmail() + " " + user.isEmailVerified());
			mDetailTextView.setText("user with " + user.getUid());

			findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
			findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
			findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);
			findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
		} else {
			mStatusTextView.setText("sign out");
			mDetailTextView.setText(null);
			findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
			findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
			findViewById(R.id.signedInButtons).setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.emailCreateAccountButton) {
			createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
		} else if (i == R.id.emailSignInButton) {
			signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
		} else if (i == R.id.signOutButton) {
			signOut();
		} else if (i == R.id.verifyEmailButton) {
			sendEmailVerification();
		}
	}

	private void signIn(String email, String password) {
		Log.d(TAG, "signIn:" + email);
		if (!validateForm()) {
			return;
		}

		showProgressDialog();

		// [START sign_in_with_email]
		mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {
					// Sign in success, update UI with the signed-in user's information
					Log.d(TAG, "signInWithEmail:success");
					FirebaseUser user = mAuth.getCurrentUser();
					updateUI(user);
				} else {
					// If sign in fails, display a message to the user.
					Log.w(TAG, "signInWithEmail:failure", task.getException());
					Toast.makeText(Login.this, "Authentication failed.",
							Toast.LENGTH_SHORT).show();
					updateUI(null);
				}

				// [START_EXCLUDE]
				if (!task.isSuccessful()) {
					mStatusTextView.setText("Authentication failed");
				}
				hideProgressDialog();
				// [END_EXCLUDE]
			}
		});
		// [END sign_in_with_email]
	}

	private void signOut() {
		mAuth.signOut();
		updateUI(null);
	}

	private void sendEmailVerification() {
		// Disable button
		findViewById(R.id.verifyEmailButton).setEnabled(false);

		// Send verification email
		// [START send_email_verification]
		final FirebaseUser user = mAuth.getCurrentUser();
		user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
			@Override
			public void onComplete(@NonNull Task<Void> task) {
				// [START_EXCLUDE]
				// Re-enable button
				findViewById(R.id.verifyEmailButton).setEnabled(true);

				if (task.isSuccessful()) {
					Toast.makeText(Login.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
				} else {
					Log.e(TAG, "sendEmailVerification", task.getException());
					Toast.makeText(Login.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
				}
				// [END_EXCLUDE]
			}
		});
		// [END send_email_verification]
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