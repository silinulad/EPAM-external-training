package com.example.intouch.model.beans;

import com.example.intouch.constans.Constants;

public class User {
	
	private int id;
    private String login;
    private String password;
    private String email;

	public User() {
        id = Constants.ID_EMPTY;
    }

    public User(String login, String password, String email) {
    	this();
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(int id, String login, String password, String email) {
		this(login, password, email);
    	this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
