package com.example.intouch.model.impls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ConstantsSQL;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.ifaces.IFileDAO;
import com.example.intouch.model.beans.File;
import com.example.intouch.model.db.ConnectionDB;

public class DBFileImpl implements IFileDAO {

	private final ConnectionDB connect = new ConnectionDB();

	@Override
	public File setFile(String fileName, int idTask) throws DAOException {

		final int INDEX_GENERATED_KEY = 1;
		final int INDEX_FILENAME = 1;

		final int INDEX_ID_FILE = 1;
		final int INDEX_ID_TASK = 2;

		Connection connection = null;
		PreparedStatement psInsertFile = null;
		ResultSet generatedKeys = null;
		PreparedStatement psUpdateTask = null;
		try {
			int fileId = Constants.ID_EMPTY;
			connection = connect.getConnection();

			psInsertFile = connection.prepareStatement(ConstantsSQL.PS_INSERT_FILE, Statement.RETURN_GENERATED_KEYS);
			psInsertFile.setString(INDEX_FILENAME, fileName);
			psInsertFile.executeUpdate();
			generatedKeys = psInsertFile.getGeneratedKeys();

			if (generatedKeys.next()) {
				fileId = generatedKeys.getInt(INDEX_GENERATED_KEY);
			}

			psUpdateTask = connection.prepareStatement(ConstantsSQL.PS_UPDATE_FILE_TASK);
			if (fileName == null) {
				psUpdateTask.setObject(INDEX_ID_FILE, null);
			} else {
				psUpdateTask.setInt(INDEX_ID_FILE, fileId);
			}
			psUpdateTask.setInt(INDEX_ID_TASK, idTask);
			psUpdateTask.executeUpdate();

			return new File(fileId, fileName);
		
		} catch (ClassNotFoundException | IOException ex) {
			throw new DAOException(ErrorMessage.ERROR_DB_CONNECT, ex);
		} catch (SQLException ex) {
			throw new DAOException(ErrorMessage.ERROR_QUERY, ex);
		} finally {
			connect.closeResources(psUpdateTask, generatedKeys, psInsertFile, connection);
		}
	}

	@Override
	public void removeFile(int idFile) throws DAOException {
		final int INDEX_ID = 1;
		Connection connection = null;
		PreparedStatement psDeleteFile = null;
		try {
			connection = connect.getConnection();
			psDeleteFile = connection.prepareStatement(ConstantsSQL.PS_DELETE_FILE);
			psDeleteFile.setInt(INDEX_ID, idFile);
			psDeleteFile.executeUpdate();

		} catch (ClassNotFoundException | IOException ex) {
			throw new DAOException(ErrorMessage.ERROR_DB_CONNECT, ex);
		} catch (SQLException ex) {
			throw new DAOException(ErrorMessage.ERROR_QUERY, ex);
		} finally {
			connect.closeResources(psDeleteFile, connection);
		}
	}
}
