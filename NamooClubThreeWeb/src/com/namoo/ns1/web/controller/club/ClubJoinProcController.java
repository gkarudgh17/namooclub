package com.namoo.ns1.web.controller.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.session.LoginRequired;
import com.namoo.ns1.web.session.SessionManager;

@WebServlet("/club/join/process.do")
@LoginRequired
public class ClubJoinProcController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String communityId = req.getParameter("communityId");
		String clubId = req.getParameter("clubId");
		String email = SessionManager.getInstance(req).getLoginEmail();
		
		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();
		clubService.joinAsClub(communityId, clubId, email);
		
		String message = "클럽 회원가입이 완료되었습니다.";
		String linkURL = "/club/main.do?communityId=" + communityId; 
		PageTranfer.getInstance(req, resp).information(message, linkURL);
	}
}
