package com.example.intouch.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.model.beans.User;

@WebFilter(urlPatterns = { "/distribution-controller", "/add-task-controller", "/action-controller",
		"/displace-controller", "/upload-controller", "/download-controller", "/delete-controller" })
public class UserFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(true);
		User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);

		if (currentUser == null) {
			session.invalidate();
			httpResponse.sendRedirect(httpRequest.getContextPath() + PathJSP.MAIN_PAGE);
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
