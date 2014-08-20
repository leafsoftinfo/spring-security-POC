package com.acss.poc.core;

/**
 * Message bean for notification purposes.
 * @author gvargas.local
 *
 */
public class Message {

	private String infoMsg;
	private String isError;
	final static String MESSAGE_KEY = "message";
	public final static String TRUE = "true";
	public final static String FALSE = "false";
	public String exceptionMsg;
	
	public Message() {}
	
	public String getInfo() {
		return infoMsg;
	}


	public void setInfo(String info) {
		this.infoMsg = info;
	}


	public String getIsError() {
		return isError;
	}

	public void setIsError(String isError) {
		this.isError = isError;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	
}
