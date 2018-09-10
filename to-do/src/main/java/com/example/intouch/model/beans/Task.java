package com.example.intouch.model.beans;

import java.io.Serializable;
import java.util.Date;

import com.example.intouch.constans.Constants;

public class Task implements Serializable {

	private static final long serialVersionUID = 8980382488464139098L;

	private int id;
	private String content;
	private Date date;
	private boolean fixed;
	private boolean recycle;
	private User user;
	private File file;

	public Task() {
		id = Constants.ID_EMPTY;
	}

	public Task(int id, String content, Date date) {
		this.id = id;
		this.content = content;
		this.date = date;
	}

	public Task(int id, String content, Date date, boolean fixed, boolean recycle, User user) {
		this(id, content, date);
		this.fixed = fixed;
		this.recycle = recycle;
		this.user = user;
	}

	public Task(int id, String content, Date date, boolean fixed, boolean recycle, User user, File file) {
		this(id, content, date, fixed, recycle, user);
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isRecycle() {
		return recycle;
	}

	public void setRecycle(boolean recycle) {
		this.recycle = recycle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
