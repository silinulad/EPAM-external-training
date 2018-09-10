package com.example.intouch.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.exceptions.ValidationException;
import com.example.intouch.ifaces.AbstractBaseController;
import com.example.intouch.ifaces.IUserDAO;
import com.example.intouch.model.beans.User;
import com.example.intouch.model.factories.UserFactory;

@WebServlet(name = "RegisterController", urlPatterns = { "/register-controller" })
public class RegisterController extends AbstractBaseController {

	private static final long serialVersionUID = -7610657925011371859L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter(Constants.REGISTER_LOGIN);
		String password = request.getParameter(Constants.REGISTER_PASSWORD);
		String retypePassword = request.getParameter(Constants.REGISTER_RETYPE_PASSWORD);
		String email = request.getParameter(Constants.REGISTER_EMAIL);

		try {
			checkInput(login, password, retypePassword, email);

			IUserDAO userDAO = UserFactory.getClassFromFactory();
			User user = userDAO.setUser(login, password, email);

			HttpSession session = request.getSession();
			session.setAttribute(Constants.CURRENT_USER, user);

			redirectToIndex(request, response);
		} catch (ValidationException | DAOException ex) {
			forwardErrorTo(PathJSP.REGISTER_PAGE, ex.getMessage(), request, response);
		}
	}

	private static void checkInput(String login, String password, String retypePassword, String email)
			throws ValidationException {

		if (login == null || password == null || retypePassword == null || email == null) {
			throw new ValidationException(ErrorMessage.ERROR_SEVERAL_VALUES);
		}

		login = login.trim();
		if (Constants.EMPTY_STRING.equals(login)) {
			throw new ValidationException(ErrorMessage.ERROR_EMPTY_LOGIN);
		}

		if (Constants.EMPTY_STRING.equals(password)) {
			throw new ValidationException(ErrorMessage.ERROR_EMPTY_PASSWORD);
		}

		if (!password.equals(retypePassword)) {
			throw new ValidationException(ErrorMessage.ERROR_PASSWORDS_DO_NOT_MATCH);
		}

		email = email.trim();
		if (Constants.EMPTY_STRING.equals(email)) {
			throw new ValidationException(ErrorMessage.ERROR_EMPTY_EMAIL);
		}
	}
}
