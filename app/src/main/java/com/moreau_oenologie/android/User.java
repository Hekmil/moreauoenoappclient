package com.moreau_oenologie.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User implements Parcelable {
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

	protected User(Parcel in) {
		firstName = in.readString();
		lastName = in.readString();
		email = in.readString();
		domain = in.readString();
		isOeno = in.readByte() != 0;
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};

	public void writeNewUser(String UID) {
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("/Users/" + UID).setValue(this);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(email);
		dest.writeString(domain);
		dest.writeByte((byte) (isOeno ? 1 : 0));
	}
}