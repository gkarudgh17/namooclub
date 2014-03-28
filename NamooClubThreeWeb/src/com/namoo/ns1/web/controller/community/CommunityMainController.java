package com.namoo.ns1.web.controller.community;

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
import com.namoo.ns1.web.controller.common.DefaultController;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.controller.community.pres.PresCommunity;
import com.namoo.ns1.web.session.SessionManager;
import com.namoo.ns1.web.util.StringUtil;

import dom.entity.Community;
import dom.entity.CommunityMember;

@WebServlet("/community/main.do")
public class CommunityMainController extends DefaultController {
	//
	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();
		
		String email = SessionManager.getInstance(req).getLoginEmail();
		List<Community> joined = null;
		List<Community> unjoined = null;
		
		if (!StringUtil.isEmpty(email)) {
			joined = communityService.findJoinedCommunities(email);
			unjoined = communityService.findUnjoinedCommunities(email);
		} else {
			unjoined = communityService.findAllCommunities();
		}
		
		List<PresCommunity> joinedList = new ArrayList<PresCommunity>();
		if (joined != null) {
			for (Community community : joined) {
				joinedList.add(convert(community, email, communityService, clubService));
			}
		}
		req.setAttribute("joinedList", joinedList);
		
		List<PresCommunity> unjoinedList = new ArrayList<PresCommunity>();
		if (unjoined != null) {
			for (Community community : unjoined) {
				unjoinedList.add(convert(community, email, communityService, clubService));
			}
		}
		req.setAttribute("unjoinedList", unjoinedList);
		//
		PageTranfer.getInstance(req, resp).forwardTo("/WEB-INF/views/community/main.jsp");
	}

	private PresCommunity convert(Community community, String email, CommunityService communityService, ClubService clubService) {
		// 
		community = communityService.findCommunity(community.getId());
		PresCommunity presCommunity = new PresCommunity(community);
		
		// 클럽수
		int countOfClubs = clubService.countClubs(community.getId());
		presCommunity.setCountOfClubs(countOfClubs);
		
		// 회원수
		int countOfMembers = communityService.countMembers(community.getId());
		presCommunity.setCountOfMembers(countOfMembers);
		
		// 로그인된 경우만
		if (!StringUtil.isEmpty(email)) {
			// 관리자 여부
			if (community.getManager().getEmail().equals(email)) {
				presCommunity.setOwned(true);
			}
			
			// 멤버여부 및 상태 세팅
			CommunityMember member = community.findMember(email);
			if (member != null) {
				presCommunity.setJoined(true);
				presCommunity.setMembershipState(member.getState());
			}
		}
		return presCommunity;
	}
}
