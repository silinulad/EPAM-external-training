package com.example.intouch.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.Controllers;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.ifaces.IUserDAO;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.factories.UserFactory;

@WebServlet(name = "LoginController", urlPatterns = { "/login-controller" })
public class LoginController extends AbstractBaseController {

	private static final long serialVersionUID = -3748371706611735312L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = (String) request.getParameter(Constants.ENTRY_LOGIN);
		String password = (String) request.getParameter(Constants.ENTRY_PASSWORD);

		String inputInfo = inputString(login, password);
		if (inputInfo != null) {
			forwardErrorTo(PathJSP.LOGIN_PAGE, inputInfo, request, response);
			return;
		}
		IUserDAO userDAO = UserFactory.getClassFromFactory();
		try {
			User user = userDAO.getUser(login.trim(), password);

			HttpSession session = request.getSession();
			session.setAttribute(Constants.CURRENT_USER, user);

			forwardTo(Controllers.DISTRIB_CONTROLLER, request, response);
		} catch (DAOException e) {
			forwardErrorTo(PathJSP.LOGIN_PAGE, e.getMessage(), request, response);
		}
	}

	private String inputString(String login, String password) {
		if (login == null || password == null) {
			return ErrorMessage.ERROR_LOGIN_OR_PASSWORD;
		}
		login = login.trim();
		if (Constants.EMPTY_STRING.equals(login)) {
			return ErrorMessage.ERROR_EMPTY_LOGIN;
		}

		if (Constants.EMPTY_STRING.equals(password)) {
			return ErrorMessage.ERROR_EMPTY_PASSWORD;
		}
		return null;
	}
}
