package com.namoo.ns1.web.controller.club;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.club.pres.PresClub;
import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.session.LoginRequired;
import com.namoo.ns1.web.session.SessionManager;

import dom.entity.Club;
import dom.entity.Community;

@WebServlet("/club/main.do")
@LoginRequired
public class ClubMainController extends DefaultController {
	//
	private static final long serialVersionUID = 2826093055496197874L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();
		
		String communityId = req.getParameter("communityId");
		String email = SessionManager.getInstance(req).getLoginEmail();
		
		List<Club> clubs = clubService.findAllClubs(communityId);
		List<PresClub> presClubs = new ArrayList<PresClub>();
		
		if (clubs != null) {
			for (Club club : clubs) {
				presClubs.add(convert(club, email, clubService));
			}
		}

		Community community = communityService.findCommunity(communityId);
		
		req.setAttribute("community", community);
		req.setAttribute("clubs", presClubs);
		
		// forward page
		PageTranfer.getInstance(req, resp).forwardTo("/WEB-INF/views/club/main.jsp");
	}

	private PresClub convert(Club club, String email, ClubService clubService) {
		// 
		club = clubService.findClub(club.getId());
		PresClub presClub = new PresClub(club);
		
		// 회원수
		int countOfMembers = clubService.countMembers(club.getId());
		presClub.setCountOfMembers(countOfMembers);
		
		// 관리자 여부
		if (club.findManager(email) != null) {
			presClub.setOwned(true);
		}
		
		// 멤버여부
		if (club.findMember(email) != null) {
			presClub.setJoined(true);
		}
		
		return presClub;
	}
}
