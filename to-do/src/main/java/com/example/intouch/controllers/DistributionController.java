package com.example.intouch.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.enums.SectionType;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.ifaces.ITaskDAO;
import com.example.intouch.model.beans.Task;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.factories.TaskFactory;

@WebServlet(name = "DistributionController", 
			urlPatterns = { "/distribution-controller" }, 
			initParams = {
					@WebInitParam(name = "wayFileUpload", value = "new")// or change value to "old"
})
public class DistributionController extends AbstractBaseController {

	private static final long serialVersionUID = 7798632458964885538L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
			SectionType currentType = (SectionType) session.getAttribute(Constants.SECTION_TYPE);

			SectionType newType = getSectionType(request, currentType);
			session.setAttribute(Constants.SECTION_TYPE, newType);

			ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
			List<Task> tasks = taskDAO.getTasks(currentUser, newType);

			session.setAttribute(Constants.TASK_LIST, tasks);

			forwardTo(PathJSP.MAIN_PAGE, request, response);
		} catch (DAOException e) {
			forwardErrorTo(PathJSP.MAIN_PAGE, e.getMessage(), request, response);
		}
	}

	private SectionType getSectionType(HttpServletRequest request, SectionType oldType) {
		for (SectionType currentAction : SectionType.values()) {
			if (request.getParameter(currentAction.toString().toLowerCase()) != null) {
				return currentAction;
			}
		}
		return (oldType == null) ? SectionType.TODAY : oldType;
	}

}