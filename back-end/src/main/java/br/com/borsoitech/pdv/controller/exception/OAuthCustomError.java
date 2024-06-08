package br.com.borsoitech.pdv.controller.exception;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthCustomError implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private String error;
	
	@JsonProperty("error_description")
	private String erroDescription;
	
	public OAuthCustomError() {
	}

	public OAuthCustomError(String error, String erroDescription) {
		this.error = error;
		this.erroDescription = erroDescription;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErroDescription() {
		return erroDescription;
	}

	public void setErroDescription(String erroDescription) {
		this.erroDescription = erroDescription;
	}
}
