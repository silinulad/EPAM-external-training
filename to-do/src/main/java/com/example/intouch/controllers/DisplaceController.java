package com.example.intouch.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.controllers.utils.ControllerHelper;
import com.example.intouch.enums.ActionType;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.ifaces.ITaskDAO;
import com.example.intouch.model.beans.Task;
import com.example.intouch.model.factories.TaskFactory;

@WebServlet(name = "DisplaceController", urlPatterns = { "/displace-controller" })
public class DisplaceController extends AbstractBaseController {

	private static final long serialVersionUID = 8288485730031375449L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			@SuppressWarnings("unchecked")
			List<Task> selectedTasks = (List<Task>) request.getAttribute(Constants.SELECTED_TASKS);
			ActionType action = ControllerHelper.getActionType(request);

			ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
			taskDAO.displaceTasks(selectedTasks, action);

			forwardTo(PathJSP.INDEX_PAGE, request, response);
		} catch (DAOException ex) {
			forwardErrorTo(PathJSP.MAIN_PAGE, ex.getMessage(), request, response);
		}
	}
}