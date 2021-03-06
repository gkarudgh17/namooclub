package com.namoo.ns1.web.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ContextPathFilter extends AbstractFilter {

	@Override
	protected boolean doFilter(ServletRequest req, ServletResponse resp) throws IOException, ServletException {
		//
		// JSP에서 사용하기 편하도록 ContextPath를 ctx로 세팅한다. 
		req.setAttribute("ctx", req.getServletContext().getContextPath());
		return true;
	}
}
