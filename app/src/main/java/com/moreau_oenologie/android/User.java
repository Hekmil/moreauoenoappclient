package com.moreau_oenologie.android;

import android.provider.Contacts;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
	public String firstName;
	public String lastName;
	public String email;
	public String domain;
	public boolean isOeno;
	public User() {
	}
	public User(String firstName, String lastName, String email, String domain, boolean isOeno) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.domain = domain;
		this.isOeno = isOeno;
	}
	public void writeNewUser(String UID) {
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("/Users/" + UID).setValue(this);
			}


}