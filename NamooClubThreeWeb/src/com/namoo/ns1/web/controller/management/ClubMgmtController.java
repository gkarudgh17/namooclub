package com.namoo.ns1.web.controller.management;

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
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.controller.management.pres.PresManagedClub;
import com.namoo.ns1.web.session.LoginRequired;
import com.namoo.ns1.web.session.SessionManager;
import com.namoo.ns1.web.util.StringUtil;

import dom.entity.Club;
import dom.entity.ClubState;
import dom.entity.Community;

@LoginRequired
@WebServlet("/management/club/list.do")
public class ClubMgmtController extends MgmtDefaultController {
	//
	private static final long serialVersionUID = -1347719561637453445L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		super.process(req, resp);

		String communityId = req.getParameter("communityId");
		String type = req.getParameter("type");

		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();
		
		if (!StringUtil.isEmpty(communityId)) {
			List<Club> clubs = clubService.findAllClubs(communityId);

			// 관리자 권한이 있는지 여부 체크
			Community community = communityService.findCommunity(communityId);
			String loginEmail = SessionManager.getInstance(req).getLoginEmail();
			boolean authorized = false;
			if (community.getManager().getEmail().equals(loginEmail)) {
				authorized = true;
			}
			
			List<PresManagedClub> presClubs = new ArrayList<PresManagedClub>();
			for (Club club : clubs) {
				PresManagedClub presClub= convert(communityId, club);
				presClub.setAuthorized(authorized);
				presClubs.add(presClub);
			}
			
			req.setAttribute("communityId", communityId);
			req.setAttribute("clubs", presClubs);
		}
		
		// 타입별 처리
		if (!StringUtil.isEmpty(type)) {
			String clubId = req.getParameter("clubId");
			if ("accept".equals(type)) {
				clubService.updateClubState(communityId, clubId, ClubState.Opened);
			} else if ("reject".equals(type)) {
				clubService.updateClubState(communityId, clubId, ClubState.Rejected);
			} else if ("close".equals(type)) {
				clubService.updateClubState(communityId, clubId, ClubState.Closed);
			}
			
			PageTranfer pageTranfer = PageTranfer.getInstance(req, resp);
			pageTranfer.redirectTo("/management/club/list.do?communityId=" + communityId);
			return;
		}
		
		// forward page
		PageTranfer.getInstance(req, resp).forwardTo("/WEB-INF/views/management/club.jsp");
	}

	private PresManagedClub convert(String communityId, Club club) {
		//
		PresManagedClub presClub = new PresManagedClub();
		presClub.setId(club.getId());
		presClub.setName(club.getName());
		presClub.setCategory(club.getCategory().getName());
		presClub.setState(club.getState());
		presClub.setManager(club.getRepresentativeManager());
		
		return presClub;
	}
}
