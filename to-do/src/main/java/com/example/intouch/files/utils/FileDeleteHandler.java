package com.example.intouch.files.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.example.intouch.constans.UploadPath;
import com.example.intouch.exceptions.UploadException;
import com.example.intouch.model.beans.Task;
import com.example.intouch.model.beans.User;

public class FileDeleteHandler extends AbstractFileHandler {

	public void deleteFile(User user, Task task) throws IOException {
		int idUser = user.getId();
		int idTask = task.getId();
		String uploadPath = UploadPath.getPathUpload();
		String fileName = task.getFile().getName();
		String fileCatalog = getCurrentFileCatalog(uploadPath, idUser, idTask);

		Files.delete(Paths.get(fileCatalog + fileName));
		Files.delete(Paths.get(fileCatalog));
	}

	@Override
	public void upload() throws IOException, UploadException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getUploadedIdTask() throws UploadException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getUploadedFileName() throws UploadException {
		throw new UnsupportedOperationException();
	}
}
