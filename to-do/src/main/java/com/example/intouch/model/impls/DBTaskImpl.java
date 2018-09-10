package com.example.intouch.model.impls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.intouch.constans.ConstantsSQL;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.enums.ActionType;
import com.example.intouch.enums.SectionType;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.ifaces.ITaskDAO;
import com.example.intouch.model.beans.File;
import com.example.intouch.model.beans.Task;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.db.ConnectionDB;

public class DBTaskImpl implements ITaskDAO {

	private final ConnectionDB connect = new ConnectionDB();

	public List<Task> getTasks(User user, SectionType section) throws DAOException {

		final int EMPTY_ID_FILE = 0;

		final int INDEX_ID = 1;
		final int INDEX_CONTENT = 2;
		final int INDEX_DATE = 3;
		final int INDEX_FIXED = 4;
		final int INDEX_RECYCLE = 5;
		final int INDEX_FILE_ID = 6;
		final int INDEX_FILENAME = 7;

		List<Task> tasks = new ArrayList<>();
		Connection connection = null;
		PreparedStatement psSelectTasks = null;
		ResultSet resultSet = null;

		try {
			connection = connect.getConnection();

			psSelectTasks = connection.prepareStatement(ConstantsSQL.HEAD_QUERY_BY_DAY + section.getSelectTaskQuery());
			psSelectTasks.setInt(INDEX_ID, user.getId());
			resultSet = psSelectTasks.executeQuery();

			while (resultSet.next()) {
				int currentId = resultSet.getInt(INDEX_ID);
				String currentContent = resultSet.getString(INDEX_CONTENT);
				Date currentDate = resultSet.getDate(INDEX_DATE);
				boolean currentFixed = resultSet.getBoolean(INDEX_FIXED);
				boolean currentRecycle = resultSet.getBoolean(INDEX_RECYCLE);
				int idFile = resultSet.getInt(INDEX_FILE_ID);
				File currentFile = null;
				if (idFile != EMPTY_ID_FILE) {
					String filename = resultSet.getString(INDEX_FILENAME);
					currentFile = new File(idFile, filename);
				}

				tasks.add(new Task(currentId, currentContent, currentDate, currentFixed, currentRecycle, user,
						currentFile));
			}
			return tasks;

		} catch (ClassNotFoundException | IOException ex) {
			throw new DAOException(ErrorMessage.ERROR_DB_CONNECT, ex);
		} catch (SQLException ex) {
			throw new DAOException(ErrorMessage.ERROR_QUERY, ex);
		} finally {
			connect.closeResources(resultSet, psSelectTasks, connection);
		}
	}

	@Override
	public void addTask(String content, Date date, User user) throws DAOException {

		final int INDEX_CONTENT = 1;
		final int INDEX_DATE = 2;
		final int INDEX_ID_USER = 3;

		Connection connection = null;
		PreparedStatement psAddTask = null;
		try {
			connection = connect.getConnection();
			psAddTask = connection.prepareStatement(ConstantsSQL.PS_INSERT_TASK);
			psAddTask.setString(INDEX_CONTENT, content);
			psAddTask.setDate(INDEX_DATE, new java.sql.Date(date.getTime()));
			psAddTask.setInt(INDEX_ID_USER, user.getId());
			psAddTask.executeUpdate();
		} catch (ClassNotFoundException | IOException ex) {
			throw new DAOException(ErrorMessage.ERROR_DB_CONNECT, ex);
		} catch (SQLException ex) {
			throw new DAOException(ErrorMessage.ERROR_QUERY, ex);
		} finally {
			connect.closeResources(psAddTask, connection);
		}
	}

	@Override
	public void displaceTasks(List<Task> selectedTasks, ActionType action) throws DAOException {
		final int INDEX_ID = 1;

		Connection connection = null;
		PreparedStatement psDisplaceTask = null;
		try {
			connection = connect.getConnection();
			psDisplaceTask = connection.prepareStatement(action.getActionQuery());
			for (Task currentTask : selectedTasks) {
				psDisplaceTask.setInt(INDEX_ID, currentTask.getId());
				psDisplaceTask.executeUpdate();
			}
		} catch (ClassNotFoundException | IOException ex) {
			throw new DAOException(ErrorMessage.ERROR_DB_CONNECT, ex);
		} catch (SQLException ex) {
			throw new DAOException(ErrorMessage.ERROR_QUERY, ex);
		} finally {
			connect.closeResources(psDisplaceTask, connection);
		}
	}

	@Override
	public Task getTask(User user, int id) throws DAOException {	
		final int EMPTY_ID_FILE = 0;
		final int REQUEST_INDEX_USER_ID = 1;
		final int REQUEST_INDEX_TASK_ID = 2;

		final int RESPONSE_INDEX_ID = 1;
		final int RESPONSE_INDEX_CONTENT = 2;
		final int RESPONSE_INDEX_DATE = 3;
		final int RESPONSE_INDEX_FIXED = 4;
		final int RESPONSE_INDEX_RECYCLE = 5;
		final int RESPONSE_INDEX_FILE_ID = 6;
		final int RESPONSE_INDEX_FILENAME = 7;

		Connection connection = null;
		PreparedStatement psSelectTasks = null;
		ResultSet resultSet = null;
		try {
			connection = connect.getConnection();
			psSelectTasks = connection.prepareStatement(ConstantsSQL.PS_SELECT_TASK);
			psSelectTasks.setInt(REQUEST_INDEX_USER_ID, user.getId());
			psSelectTasks.setInt(REQUEST_INDEX_TASK_ID, id);
			resultSet = psSelectTasks.executeQuery();
			if (resultSet.next()) {
				Task resultTask = new Task();
				resultTask.setId(resultSet.getInt(RESPONSE_INDEX_ID));
				resultTask.setContent(resultSet.getString(RESPONSE_INDEX_CONTENT));
				resultTask.setDate(resultSet.getDate(RESPONSE_INDEX_DATE));
				resultTask.setFixed(resultSet.getBoolean(RESPONSE_INDEX_FIXED));
				resultTask.setRecycle(resultSet.getBoolean(RESPONSE_INDEX_RECYCLE));
				int idFile = resultSet.getInt(RESPONSE_INDEX_FILE_ID);
				if (idFile != EMPTY_ID_FILE) {
					String filename = resultSet.getString(RESPONSE_INDEX_FILENAME);
					File file = new File(idFile, filename);
					resultTask.setFile(file);
				}
				return resultTask;
			} else {
				throw new DAOException(ErrorMessage.ERROR_TASK_REQUEST);
			}
		} catch (ClassNotFoundException | IOException ex) {
			throw new DAOException(ErrorMessage.ERROR_DB_CONNECT, ex);
		} catch (SQLException ex) {
			throw new DAOException(ErrorMessage.ERROR_QUERY, ex);
		} finally {
			connect.closeResources(resultSet, psSelectTasks, connection);
		}
	}

}
