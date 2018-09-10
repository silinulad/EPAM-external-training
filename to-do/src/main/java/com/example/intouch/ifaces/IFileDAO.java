package com.example.intouch.ifaces;

import com.example.intouch.exceptions.DAOException;
import com.example.intouch.model.beans.File;

public interface IFileDAO {

	File setFile(String fileName, int idTask) throws DAOException;
	void removeFile(int idFile) throws DAOException;
}
