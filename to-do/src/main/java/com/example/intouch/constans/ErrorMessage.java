package com.example.intouch.constans;

public class ErrorMessage {

	//login and sign up errors
	public static final String ERROR_LOGIN_OR_PASSWORD = "Login or password are absent";
	public static final String ERROR_SEVERAL_VALUES = "One or more field values are absent";
	public static final String ERROR_LOGIN_OR_PASSWORD_INCORRECT = "Incorrect login or password";
	public static final String ERROR_EMPTY_LOGIN = "Login is empty";
	public static final String ERROR_EMPTY_EMAIL = "Email is empty";
	public static final String ERROR_EMPTY_PASSWORD = "Password is empty";
	public static final String ERROR_PASSWORDS_DO_NOT_MATCH = "The passwords do not match";
	
	//database errors
	public static final String ERROR_DB_CONNECT = "Unable to connect to the database";
	public static final String ERROR_QUERY = "Unknown error the query database";
	public static final String ERROR_LOGIN = "This login has already existed. Please select an another login";
	
	//task errors
	public static final String ERROR_NO_SELECT_FLAG = "No selected flags";
	public static final String ERROR_DATE_ABSENT = "Date field is a absent";
	public static final String ERROR_DATE_EMPTY = "Date is empty";
	public static final String ERROR_CONTENT_ABSENT = "Content is absent";
	public static final String ERROR_CONTENT_EMPTY = "Content is empty";
    public static final String ERROR_CONTENT_LONG = "The content is too long. The maximum length of 1000 characters. Please, upload a file, if you want to add more informahtion about the task";
    
	public static final String NO_SELECTED_FILE = "No selected file for this task";
	public static final String NO_SELECTED_TASK = "No selected task to download";
	public static final String ERROR_FILE_DELETE = "Error file delete";
	public static final String ERROR_TASK_REQUEST = "Task request error";
	
	//init paths error
	public static final String ERROR_INIT_PATH = "The class is not initialized";
}
