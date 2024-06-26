package br.com.borsoitech.pdv.model.type;

import java.io.Serial;
import java.io.Serializable;

public class LoginRequest implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
    private String username;
    private String password;
    private String grant_type;

    public LoginRequest(String username, String password, String grant_type) {
        this.username = username;
        this.password = password;
        this.grant_type = grant_type;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
}
