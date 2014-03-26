package com.namoo.ns1.web.controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageTranfer {
	//
	private static final String INFO_PAGE = "/WEB-INF/views/common/info_msg.jsp";
	private static final String ERROR_PAGE = "/WEB-INF/views/common/error_msg.jsp";
	private static final String CONFIRM_PAGE = "/WEB-INF/views/common/confirm_msg.jsp";
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	private PageTranfer(HttpServletRequest req, HttpServletResponse resp) {
		//
		this.req = req;
		this.resp = resp;
	}
	
	public static PageTranfer getInstance(HttpServletRequest req, HttpServletResponse resp) {
		//
		return new PageTranfer(req, resp);
	}
	
	public void forwardTo(String uri) throws ServletException, IOException {
		//
		req.getRequestDispatcher(uri).forward(req, resp);
	}
	
	public void information(String message, String linkURL) throws ServletException, IOException {
		//
		req.setAttribute("message", message);
		req.setAttribute("linkURL", req.getContextPath() + linkURL);
		
		req.getRequestDispatcher(INFO_PAGE).forward(req, resp);
	}
	
	public void error(String message, String linkURL) throws ServletException, IOException {
		//
		req.setAttribute("message", message);
		req.setAttribute("linkURL", req.getContextPath() + linkURL);

		req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
	}
	
	public void confirm(String message, String linkURL) throws ServletException, IOException {
		//
		req.setAttribute("message", message);
		req.setAttribute("linkURL", req.getContextPath() + linkURL);

		req.getRequestDispatcher(CONFIRM_PAGE).forward(req, resp);
	}

	public void redirectTo(String uri) throws ServletException, IOException {
		//
		resp.sendRedirect(req.getContextPath() + uri);
	}
}
