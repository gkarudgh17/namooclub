package com.namoo.ns1.web.controller.towner;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.session.LoginRequired;
import com.namoo.ns1.web.session.SessionManager;

@WebServlet("/towner/withdrawal.do")
@LoginRequired
public class TownerWithdrawalController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String email = SessionManager.getInstance(req).getLoginEmail();
		TownerService townerService = NamooClubServiceFactory.getInstance().getTownerService();
		townerService.removeTowner(email);
		
		// logout
		SessionManager.getInstance(req).logout();
		
		String message = "나무클럽 회원에서 탈퇴되었습니다.";
		String linkURL = "/towner/login.xhtml"; 
		PageTranfer.getInstance(req, resp).information(message, linkURL);
	}
}
