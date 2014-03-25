package com.namoo.ns1.web.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class OriginRequestURLFilter extends SimpleFilterAdapter {

	@Override
	public boolean doFilter(ServletRequest req, ServletResponse resp) throws IOException, ServletException {
		// 
		if (req instanceof HttpServletRequest) {
			req.setAttribute("origin", ((HttpServletRequest) req).getRequestURL());
			req.setAttribute("referer", ((HttpServletRequest) req).getHeader("referer"));
		}
		return true;
	}
}
