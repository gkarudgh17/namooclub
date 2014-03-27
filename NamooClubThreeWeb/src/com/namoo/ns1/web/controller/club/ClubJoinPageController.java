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

import dom.entity.Club;

@WebServlet("/club/join/confirm.do")
@LoginRequired
public class ClubJoinPageController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String communityId = req.getParameter("communityId");
		String clubId = req.getParameter("clubId");
		
		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();
		Club club = clubService.findClub(clubId);
		
		//
		String message = club.getName() + " 회원으로 가입하시겠습니까?";
		String linkURL = "/club/join/process.do?communityId=" + communityId + "&clubId=" + clubId; 
		PageTranfer.getInstance(req, resp).confirm(message, linkURL);
	}
}
