package com.example.intouch.files.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.example.intouch.constans.UploadPath;
import com.example.intouch.exceptions.UploadException;
import com.example.intouch.model.beans.Task;
import com.example.intouch.model.beans.User;

public class FileDownloadHandler extends AbstractFileHandler {

	public void downloadFile(HttpServletResponse response, User user, Task task) throws IOException {

		ServletOutputStream outputStream = null;
		InputStream inputStream = null;
		try {

			final int EXPIRED_TERM = 0;
			final String CONTENT_TYPE = "Content-type: application/octet-stream\n";
			final String EXPIRES_HEADER = "Expires";
			final String SOURCE_CHARSET = "Cp1251";
			final String TARGET_CHARSET = "Cp1252";
			final String SPACE_REGEXP = "[\u00a0\\s]";
			final String UNDERLINE = "_";
			final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
			final String ATTACHMENT = "attachment; filename=\"";
			final String QUOTE = "\"";

			int idUser = user.getId();
			int idTask = task.getId();
			String uploadPath = UploadPath.getPathUpload();
			String fileName = task.getFile().getName();
			String fileCatalog = getCurrentFileCatalog(uploadPath, idUser, idTask);

			File file = new File(fileCatalog + fileName);
			outputStream = response.getOutputStream();

			// set response headers
			response.setContentType(CONTENT_TYPE);
			response.setDateHeader(EXPIRES_HEADER, EXPIRED_TERM);

			// change character set for Cyrillic and replace 'spaces and nbsp' to '_'
			String normalizeFileName = new String(fileName.getBytes(SOURCE_CHARSET), TARGET_CHARSET);
			normalizeFileName = normalizeFileName.replaceAll(SPACE_REGEXP, UNDERLINE);

			response.addHeader(CONTENT_DISPOSITION_HEADER, ATTACHMENT + normalizeFileName + QUOTE);
			response.setContentLength((int) file.length());
			inputStream = new BufferedInputStream(new FileInputStream(file));
			int readBytes;
			while ((readBytes = inputStream.read()) != -1) {
				outputStream.write(readBytes);
			}
		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					throw e;
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
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
