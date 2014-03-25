package com.namoo.ns1.web.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class ContextPathFilter extends SimpleFilterAdapter {

	@Override
	protected boolean doFilter(ServletRequest req, ServletResponse resp) throws IOException, ServletException {
		//
		req.setAttribute("ctx", req.getServletContext().getContextPath());
		return true;
	}
}
