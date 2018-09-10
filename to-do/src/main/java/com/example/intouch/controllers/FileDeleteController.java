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
import com.example.intouch.files.utils.FileDeleteHandler;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.ifaces.IFileDAO;
import com.example.intouch.ifaces.ITaskDAO;
import com.example.intouch.model.beans.Task;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.factories.FileFactory;
import com.example.intouch.model.factories.TaskFactory;

@WebServlet(name = "FileDeleteController", urlPatterns = { "/delete-controller" })
public class FileDeleteController extends AbstractBaseController {

    private static final long serialVersionUID = -4235203883129578102L;

    @Override
    protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String rawIdTask = request.getParameter(Constants.ID_TASK_DELETE);
        int idTask = Integer.parseInt(rawIdTask);
        
        try {
        	HttpSession session = request.getSession();
			User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
        	
            ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
            Task currentTask = taskDAO.getTask(currentUser, idTask);

            IFileDAO fileDAO = FileFactory.getClassFromFactory();
            fileDAO.removeFile(currentTask.getFile().getId());

            FileDeleteHandler deleteHandler = new FileDeleteHandler();
            deleteHandler.deleteFile(currentUser, currentTask);

            redirectToIndex(request, response);
		} catch (DAOException | NumberFormatException e) {
			forwardErrorTo(PathJSP.MAIN_PAGE, e.getMessage(), request, response);
		}
    }
}
