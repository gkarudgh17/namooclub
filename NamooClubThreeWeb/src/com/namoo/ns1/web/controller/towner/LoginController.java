package com.namoo.ns1.web.controller.towner;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.session.SessionManager;

@WebServlet("/towner/login.do")
public class LoginController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String email = req.getParameter("inputEmail");
		String password = req.getParameter("inputPassword");
		
		PageTranfer pageTransfer = PageTranfer.getInstance(req, resp);
		SessionManager sessionManager = SessionManager.getInstance(req);
		if (sessionManager.login(email, password)) {
			//
			pageTransfer.redirectTo("/community/main.do");
		} else {
			//
			req.setAttribute("email", email);
			req.setAttribute("password", password);
			
			String message = "로그인에 실패하였습니다. 회원정보를 확인하세요";
			String linkURL = "/towner/login.xhtml";
			pageTransfer.error(message, linkURL); 
		}
	}
}
