package com.example.intouch.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.ifaces.AbstractBaseController;

@WebServlet(name = "LogoutController", urlPatterns = { "/logout-controller" })
public class LogoutController extends AbstractBaseController {

	private static final long serialVersionUID = -6083946295946892079L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		redirectToIndex(request, response);
	}
}
