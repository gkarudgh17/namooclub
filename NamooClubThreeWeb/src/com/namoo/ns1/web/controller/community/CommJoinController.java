package com.namoo.ns1.web.controller.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.session.LoginRequired;
import com.namoo.ns1.web.session.SessionManager;

@WebServlet("/community/join.do")
@LoginRequired
public class CommJoinController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String communityId = req.getParameter("communityId");
		String email = SessionManager.getInstance(req).getLoginEmail();
		
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		communityService.joinAsMember(communityId, email);
		
		String message = "커뮤니티 멤버로 가입되었습니다.";
		String linkURL = "/community/main.do"; 
		PageTranfer.getInstance(req, resp).information(message, linkURL);
	}
}
