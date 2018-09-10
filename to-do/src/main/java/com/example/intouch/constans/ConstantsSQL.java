package com.example.intouch.constans;

public class ConstantsSQL {

	// queries for login and register
	public final static String SELECT_USER = "SELECT id, login, password, email FROM users WHERE login=? AND password=?";
	public final static String SELECT_LOGIN = "SELECT login FROM users WHERE login=?";
	public final static String INSERT_USER = "INSERT INTO users (login, password, email) VALUES(?,?,?)";

	// queries for tasks
	public final static String HEAD_QUERY_BY_DAY = "SELECT tasks.id, content, date, fixed, recycle, files.id, filename FROM tasks LEFT JOIN files ON tasks.fileId = files.id WHERE userId=?";
	public final static String SELECT_ALL_TASKS = HEAD_QUERY_BY_DAY;
	public final static String SELECT_TODAY_TASK = " AND date <= DATE(NOW()) AND recycle = 0 AND fixed = 0 Order by date";
	public final static String SELECT_TOMORROW_TASK = " AND date = DATE(NOW() + INTERVAL 1 DAY) AND recycle = 0 AND fixed = 0 Order by date";
	public final static String SELECT_SOMEDAY_TASK = " AND date > DATE(NOW()) AND date > DATE(NOW() + INTERVAL 1 DAY) AND recycle = 0 AND fixed = 0 Order by date";
	public final static String SELECT_FIXED_TASK = " AND recycle = 0 AND fixed = 1 Order by date";
	public final static String SELECT_RECYCLE_TASK = " AND recycle = 1 Order by date";

	public final static String PS_INSERT_TASK = "INSERT INTO tasks (content, date, userId) VALUES(?,?,?)";
	public final static String PS_FIX_TASK = "UPDATE tasks SET fixed = 1 WHERE id = ?";
	public final static String PS_RECYCLED_TASK = "UPDATE tasks SET recycle = 1 WHERE id = ?";
	public final static String PS_RESTORE_TASK = "UPDATE tasks SET fixed = 0, recycle = 0 WHERE id = ?";
	public final static String PS_REMOVE_TASK = "DELETE tasks, files FROM tasks LEFT JOIN files ON tasks.fileId = files.id WHERE tasks.id = ?";

	public final static String HEAD_QUERY_TASK = "SELECT tasks.id, content, date, fixed, recycle, files.id, filename FROM tasks LEFT JOIN files ON tasks.fileId = files.id ";
	public final static String PS_SELECT_TASK = HEAD_QUERY_TASK + "WHERE userId = ? AND tasks.id = ?";
	public final static String PS_SELECT_TASKS = HEAD_QUERY_TASK + "WHERE userId = ?";

	// queries for files
	public final static String PS_UPDATE_FILE_TASK = "UPDATE tasks SET fileId = ? WHERE id = ?";
	public final static String PS_INSERT_FILE = "INSERT INTO files (filename) VALUES(?)";
	public final static String PS_DELETE_FILE = "DELETE files FROM files WHERE id = ?";

}
