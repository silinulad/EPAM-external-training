package com.example.intouch.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.files.utils.FileDownloadHandler;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.ifaces.ITaskDAO;
import com.example.intouch.model.beans.Task;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.factories.TaskFactory;

@WebServlet(name = "FileDownloadController", urlPatterns = { "/download-controller" })
public class FileDownloadController extends AbstractBaseController {

	private static final long serialVersionUID = 9078324825361923304L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);

			String rawIdTask = request.getParameter(Constants.ID_TASK_DOWNLOAD);
			int idTask = Integer.parseInt(rawIdTask);

			ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
			Task currentTask = taskDAO.getTask(currentUser, idTask);

			FileDownloadHandler downloadHandler = new FileDownloadHandler();
			downloadHandler.downloadFile(response, currentUser, currentTask);

		} catch (DAOException | NumberFormatException e) {
			forwardErrorTo(PathJSP.MAIN_PAGE, e.getMessage(), request, response);
		}
	}
}
