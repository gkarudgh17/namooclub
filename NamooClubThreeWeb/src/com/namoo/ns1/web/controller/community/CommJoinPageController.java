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

import dom.entity.Community;

@WebServlet("/community/join/confirm.do")
@LoginRequired
public class CommJoinPageController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String communityId = req.getParameter("communityId");
		
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		Community community = communityService.findCommunity(communityId);
		
		req.setAttribute("community", community);
		//
		String message = community.getName() + " 회원으로 가입하시겠습니까?";
		String linkURL = "/community/join/process.do?communityId=" + communityId; 
		
		PageTranfer.getInstance(req, resp).confirm(message, linkURL);
	}
}
