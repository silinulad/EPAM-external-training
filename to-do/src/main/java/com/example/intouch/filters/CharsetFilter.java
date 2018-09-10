package com.example.intouch.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/*" })
public class CharsetFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final String CHARSET = "UTF-8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
