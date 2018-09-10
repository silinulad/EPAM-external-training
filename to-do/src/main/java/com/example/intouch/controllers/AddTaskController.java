package com.example.intouch.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.enums.SectionType;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.exceptions.ValidationException;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.ifaces.ITaskDAO;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.factories.TaskFactory;

@WebServlet(name = "AddTaskController", urlPatterns = { "/add-task-controller" })
public class AddTaskController extends AbstractBaseController {

	private static final long serialVersionUID = -2688953365710569815L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
			SectionType sectionType = (SectionType) session.getAttribute(Constants.SECTION_TYPE);

			String date = request.getParameter(Constants.TASK_DATE);
			String content = getInputContent(request);

			ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
			taskDAO.addTask(content, sectionType.getDate(date), currentUser);

			redirectToIndex(request, response);
		} catch (ValidationException | DAOException ex) {
			forwardErrorTo(PathJSP.ADD_TASK_PAGE, ex.getMessage(), request, response);
		}
	}

	private String getInputContent(HttpServletRequest request)
			throws ValidationException, UnsupportedEncodingException {

		final String SOURCE_CHARSET = "ISO-8859-1";
		final String TARGET_CHARSET = "UTF-8";
		final int MAX_LENGTH_CONTENT = 1000;

		String taskContent = request.getParameter(Constants.TASK_CONTENT);
		if (taskContent == null) {
			throw new ValidationException(ErrorMessage.ERROR_CONTENT_ABSENT);
		}
		taskContent = taskContent.trim();
		if (Constants.EMPTY_STRING.equals(taskContent)) {
			throw new ValidationException(ErrorMessage.ERROR_CONTENT_EMPTY);
		}
		if (taskContent.length() > MAX_LENGTH_CONTENT) {
			throw new ValidationException(ErrorMessage.ERROR_CONTENT_LONG);
		}
		return new String(taskContent.getBytes(SOURCE_CHARSET), TARGET_CHARSET);
	}
}
