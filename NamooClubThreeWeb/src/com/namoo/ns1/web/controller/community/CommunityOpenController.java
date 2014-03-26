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
import com.namoo.ns1.web.session.SessionManager;

@WebServlet("/community/open.do")
public class CommunityOpenController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();

		// 요청파라미터
		String communityName = req.getParameter("communityName");
		String description = req.getParameter("description");
		String adminEmail = SessionManager.getInstance(req).getLoginEmail();

		communityService.registCommunity(communityName, description, adminEmail);
		
		PageTranfer pageTranfer = PageTranfer.getInstance(req, resp);
		String message = "커뮤티니 생성이 완료되었습니다.";
		String linkURL = "/community/main.do";

		pageTranfer.information(message, linkURL);
	}
}
