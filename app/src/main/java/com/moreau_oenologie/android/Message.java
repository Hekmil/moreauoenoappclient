package com.moreau_oenologie.android;

import java.util.Date;

public class Message {
	public String messageText;
	public String messageUser;
	public Long messageTime;
	public Message(String messageText, String messageUser) {
		this.messageText = messageText;
		this.messageUser = messageUser;
		this.messageTime = new Date().getTime();
	}
	public Message() {
	}

}
