package com.example.intouch.model.beans;

import com.example.intouch.constans.Constants;

public class File {

	private int id;
	private String name;

	public File() {
		id = Constants.ID_EMPTY;
	}

	public File(String name) {
		super();
		this.name = name;
	}

	public File(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
