package com.example.demo.dto;

import java.util.Date;

public class ExceptionResponce 
{
	private Date timestamp;
	private String message;
	private String details;
	
	public ExceptionResponce(Date timestamp , String message , String details) 
	{
		this.timestamp=timestamp;
		this.message=message;
		this.details=details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	@Override
	public String toString() {
		return "ExceptionResponce [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
	}
}