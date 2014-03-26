package com.namoo.ns1.web.controller.towner;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.session.LoginRequired;

@WebServlet("/towner/withdrawal/confirm.do")
@LoginRequired
public class TownerWithdrawalPageController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String message = "정말로 나무클럽의 회원에서 탈퇴하시겠습니까?";
		String linkURL = "/towner/withdrawal.do"; 
		
		PageTranfer.getInstance(req, resp).confirm(message, linkURL);
	}
}
