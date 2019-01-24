package com.moreau_oenologie.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
	private User user;
	private FirebaseUser firebaseUser;
	private EditText input;
	private FirebaseListAdapter<Message> messageAdapter;
	private ListView listView;
	private DatabaseReference database;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
		Intent intent = getIntent();
		if (intent != null) {
			user = intent.getParcelableExtra("user");
		}
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(this);
		input = (EditText)findViewById(R.id.input);
		listView = (ListView)findViewById(R.id.list_of_messages);
		database = FirebaseDatabase.getInstance().getReference().child("messages");
		Query query = FirebaseDatabase.getInstance().getReference().child("messages");
		FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()
				.setQuery(query, Message.class)
				.setLayout(R.layout.message)
				.build();

		messageAdapter = new FirebaseListAdapter<Message>(options) {
			@Override
			protected void populateView(@NonNull View v, @NonNull Message model, int position) {
				TextView messageText = (TextView)findViewById(R.id.message_text);
				TextView messargeUser = (TextView)findViewById(R.id.message_user);
				TextView messageTime = (TextView)findViewById(R.id.message_time);
				messageText.setText(model.messageText);
				((TextView) messargeUser).setText(model.messageUser);
				String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date(model.messageTime));
				messageTime.setText(date);
			}
		};
		listView.setAdapter(messageAdapter);
	}

	public void onClick(View v) {
		int viewId = v.getId();
		if (viewId == R.id.fab) {
			Message message = new Message(input.getText().toString(), user.firstName + " " + user.lastName);
			database.push().setValue(message);
			input.setText("");
		}
	}
}
