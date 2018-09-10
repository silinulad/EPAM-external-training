package com.example.intouch.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.controllers.utils.ControllerHelper;
import com.example.intouch.enums.ActionType;
import com.example.intouch.exceptions.ValidationException;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.model.beans.Task;

@WebServlet(name = "ActionController", urlPatterns = { "/action-controller" })
public class ActionController extends AbstractBaseController {

	private static final long serialVersionUID = -2688953365710569815L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ActionType action = ControllerHelper.getActionType(request);

			String[] selectedFlags = request.getParameterValues(Constants.SELECTED_ITEMS);
			HttpSession session = request.getSession();

			@SuppressWarnings("unchecked")
			List<Task> sourceTasks = (List<Task>) session.getAttribute(Constants.TASK_LIST);
			List<Task> selectedTasks = getSelectedTasks(selectedFlags, sourceTasks);

			request.setAttribute(Constants.SELECTED_TASKS, selectedTasks);
			
			forwardTo(action.getController(), request, response);
		} catch (ValidationException ex) {
			forwardErrorTo(PathJSP.MAIN_PAGE, ex.getMessage(), request, response);
		}
	}
	
	private List<Task> getSelectedTasks(String[] selectedFlags, List<Task> tasks)
			throws ValidationException {

		if (selectedFlags == null) {
			throw new ValidationException(ErrorMessage.ERROR_NO_SELECT_FLAG);
		}

		List<Task> selectedList = new ArrayList<Task>();

		for (String selectedItem : selectedFlags) {
			int idTask = Integer.parseInt(selectedItem);
			for (Task currentTask : tasks) {
				if (currentTask.getId() == idTask) {
					selectedList.add(currentTask);
					break;
				}
			}
		}
		return selectedList;
	}

}
