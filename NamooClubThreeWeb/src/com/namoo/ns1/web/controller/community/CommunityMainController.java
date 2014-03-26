package com.namoo.ns1.web.controller.community;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.session.SessionManager;
import com.namoo.ns1.web.session.LoginRequired;

import dom.entity.Community;

@WebServlet("/community/main.do")
@LoginRequired
public class CommunityMainController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		
		String email = SessionManager.getInstance(req).getLoginEmail();
		List<Community> belongList = communityService.findBelongCommunities(email);
		List<Community> notBelongList = communityService.findNotBelongCommunities(email);
		
		req.setAttribute("belongList", belongList);
		req.setAttribute("notBelongList", notBelongList);
		//
		PageTranfer.getInstance(req, resp).forwardTo("/WEB-INF/views/community/main.jsp");
	}
}
