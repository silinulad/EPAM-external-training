package com.example.intouch.ifaces;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.JDBCPath;
import com.example.intouch.constans.PathJSP;
import com.example.intouch.constans.UploadPath;
import com.example.intouch.files.factory.FileUploadFactory;

public abstract class AbstractBaseController extends HttpServlet {

	private static final long serialVersionUID = 3942712702650530748L;
	private static boolean isInitWayUpload;
	private static boolean isInitUploadPath;
	private static boolean isInitJDBC;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		final String PROJECT_ROOT = "";
		String realPath = getServletContext().getRealPath(PROJECT_ROOT);
		
		if (!isInitWayUpload) {
			String wayUpload = config.getInitParameter(Constants.WAY_FILE_UPLOAD);
			FileUploadFactory.setWayUpload(wayUpload);
			isInitWayUpload = true;
		}

		if (!isInitUploadPath) {
			UploadPath.initialize(realPath);
			isInitUploadPath = true;
		}

		if (!isInitJDBC) {
			JDBCPath.initialize(realPath);
			isInitJDBC = true;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performTask(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performTask(request, response);
	}

	abstract protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	protected void forwardErrorTo(String url, String message, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(Constants.ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void forwardTo(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		return;
	}

	protected void redirectToIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + PathJSP.INDEX_PAGE);
	}

}
