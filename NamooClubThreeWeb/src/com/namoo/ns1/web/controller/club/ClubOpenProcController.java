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

@WebServlet("/club/open/process.do")
@LoginRequired
public class ClubOpenProcController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();

		// 요청파라미터
		String communityId = req.getParameter("communityId");
		String category = req.getParameter("category");
		String clubName = req.getParameter("clubName");
		String description = req.getParameter("description");
		String adminEmail = SessionManager.getInstance(req).getLoginEmail();

		clubService.registClub(communityId, category, clubName, description, adminEmail);
		
		PageTranfer pageTranfer = PageTranfer.getInstance(req, resp);
		String message = "클럽 생성이 완료되었습니다. 관리자 승인 후 활성화됩니다.";
		String linkURL = "/club/main.do?communityId=" + communityId;

		pageTranfer.information(message, linkURL);
	}
}
