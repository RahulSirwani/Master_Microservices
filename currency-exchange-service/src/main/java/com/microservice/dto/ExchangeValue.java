package com.microservice.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExchangeValue
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "VALUE_FROM")
	private String from;
	
	@Column(name = "VALUE_TO")
	private String to;
	
	
	private BigDecimal conversionMultiple;
	
	@Column(name = "VALUE_PORT")
	private int port;
	
	public ExchangeValue() 
	{
		
	}

	public ExchangeValue(int id, String from, String to, BigDecimal ConversionMultiple) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = ConversionMultiple;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	
	
}
