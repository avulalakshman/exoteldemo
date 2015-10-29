package com.ccube.pod.domain;

public class CallDetails {
		
			private User user;
			private Receiver receiver;
			private String status;
			
			public User getUser() {
				return user;
			}
			public void setUser(User user) {
				this.user = user;
			}
			public Receiver getReceiver() {
				return receiver;
			}
			public void setReceiver(Receiver receiver) {
				this.receiver = receiver;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			@Override
			public String toString() {
				return "CallDetails [user= -----> " + user + ", receiver=" + receiver + ", status=" + status + "]";
			}
			
			
			
}
