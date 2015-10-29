package com.ccube.pod.service.exception;

public class ReceiverException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4638256761793849495L;
	private String message;
	public static final String INVALID_COUNSELOR = "Invalid Receiver";
	public static final String RECORD_NOT_FOUND = "Record not found with the id :%s";
	public static final String NO_RECORDS_FOUND = "No records found (count : %s)";

	public ReceiverException() {
		message = "Excpetion in Receiver service ";

	}

	/**
	 * @param message
	 */
	public ReceiverException(String message) {
		this.message = message;
	}

	public ReceiverException(String message, Object value) {
		this.message = String.format(message, value);
	}

	@Override
	public String toString() {
		return message;
	}

}
