package com.example.intouch.files.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.constans.UploadPath;
import com.example.intouch.exceptions.UploadException;
import com.example.intouch.model.beans.User;

public class FileUploadHandlerNew extends AbstractFileHandler {

	private static final String CONTEN_DISPOSITON = "content-disposition";
	private static final String SPLITTER = ";";
	private static final String FILE_NAME_TOKEN = "filename";

	private final User user;
	private final HttpServletRequest request;

	public FileUploadHandlerNew(User user, HttpServletRequest request) {
		this.user = user;
		this.request = request;
	}

	@Override
	public void upload() throws IOException, UploadException, ServletException {
		String tasksId = request.getParameter(Constants.ID_TASK_UPLOAD);
		idTask = Integer.parseInt(tasksId);
		for (Part part : request.getParts()) {
			if (part.getContentType() != null) {
				// save file Part to disk
				fileName = getFileName(part);
				if (fileName != null && !fileName.isEmpty()) {
					String fileCatalog = getCurrentFileCatalog(UploadPath.getPathUpload(), user.getId(), idTask);
					createDirectory(fileCatalog);
					part.write(fileCatalog + File.separator + fileName);
					return;
				} else if (fileName.isEmpty() && tasksId.equals(part.getName())) {
					throw new UploadException(ErrorMessage.NO_SELECTED_TASK);
				}
			}
		}
	}

	private void createDirectory(String pathDir) throws IOException {
		Path path = Paths.get(pathDir);
		Files.createDirectories(path);
	}

	private String getFileName(Part part) {
		String fileName = "";
		String contentDisp = part.getHeader(CONTEN_DISPOSITON);
		String[] tokens = contentDisp.split(SPLITTER);
		for (String token : tokens) {
			if (token.trim().startsWith(FILE_NAME_TOKEN) && String.valueOf(idTask).equals(part.getName())) {
				// to get a file name after "=" and without quotes
				fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return fileName;
	}
}
