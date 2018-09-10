package com.example.intouch.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.exceptions.UploadException;
import com.example.intouch.files.factory.FileUploadFactory;
import com.example.intouch.files.utils.AbstractFileHandler;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.ifaces.IFileDAO;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.factories.FileFactory;


@WebServlet(name = "FileUploadController", urlPatterns = { "/upload-controller" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 30, // 30 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 100) // 100 MB

public class FileUploadController extends AbstractBaseController {

	private static final long serialVersionUID = -428008052743661407L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);

			AbstractFileHandler fileUploader = FileUploadFactory.getClassFromFactory(currentUser, request);
			fileUploader.upload();

			int idTask = fileUploader.getUploadedIdTask();
			String fileName = fileUploader.getUploadedFileName();

			IFileDAO fileDAO = FileFactory.getClassFromFactory();
			fileDAO.setFile(fileName, idTask);

			redirectToIndex(request, response);
		} catch (UploadException | DAOException e) {
			forwardErrorTo(PathJSP.MAIN_PAGE, e.getMessage(), request, response);
		}
	}
}
