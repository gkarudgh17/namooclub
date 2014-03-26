package com.namoo.ns1.web.controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.web.session.SessionManager;
import com.namoo.ns1.web.session.LoginRequired;

public abstract class DefaultController extends HttpServlet {
	//
	private static final long serialVersionUID = 2999352839231443343L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		// 로그인 필수인 경우
		if (this.getClass().getAnnotation(LoginRequired.class) != null) {
			//
			if (!SessionManager.getInstance(req).isLogin()) {
				//
				String message = "해당 기능을 사용하기 위해서 로그인이 필요합니다.";
				String linkURL = "/towner/login.xhtml";
				PageTranfer.getInstance(req, resp).error(message, linkURL);
				return;
			}
		}
		
		process(req, resp);
	}

	protected abstract void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
