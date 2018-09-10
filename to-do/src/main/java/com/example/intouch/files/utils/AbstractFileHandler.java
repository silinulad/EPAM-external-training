package com.example.intouch.files.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import com.example.intouch.constans.Constants;
import com.example.intouch.exceptions.UploadException;

public abstract class AbstractFileHandler {

	protected String fileName;
	protected int idTask = Constants.ID_EMPTY;

	protected final String getCurrentFileCatalog(String uploadPath, int idUser, int idTask) {
		return uploadPath + File.separator + idUser + File.separator + idTask + File.separator;
	}

	public int getUploadedIdTask() throws UploadException {
		final String ERROR_TASK = "No task to upload the file";
		if (idTask == Constants.ID_EMPTY) {
			throw new UploadException(ERROR_TASK);
		}
		return idTask;
	}

	public String getUploadedFileName() throws UploadException {
		final String ERROR_FILE = "No uploaded file";
		if (fileName == null) {
			throw new UploadException(ERROR_FILE);
		}
		return fileName;
	}

	public abstract void upload() throws IOException, UploadException, ServletException;

}
