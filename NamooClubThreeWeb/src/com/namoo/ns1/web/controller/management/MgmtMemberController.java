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
import com.namoo.ns1.web.controller.management.pres.PresManagedMember;
import com.namoo.ns1.web.session.LoginRequired;
import com.namoo.ns1.web.session.SessionManager;
import com.namoo.ns1.web.util.StringUtil;

import dom.entity.Club;
import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.MembershipState;

@LoginRequired
@WebServlet("/management/member.do")
public class MgmtMemberController extends MgmtDefaultController {
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
			List<CommunityMember> members = communityService.findAllCommunityMember(communityId);
			String loginEmail = SessionManager.getInstance(req).getLoginEmail();
			List<PresManagedMember> presMembers = new ArrayList<PresManagedMember>();
			for (CommunityMember member : members) {
				presMembers.add(convert(communityId, member, loginEmail, communityService, clubService));
			}
			
			req.setAttribute("communityId", communityId);
			req.setAttribute("members", presMembers);
		}
		
		// 타입별 처리
		if (!StringUtil.isEmpty(type)) {
			String email = req.getParameter("email");
			if ("accept".equals(type)) {
				communityService.updateCommunityMemberState(communityId, email, MembershipState.Active);
			} else if ("reject".equals(type)) {
				communityService.updateCommunityMemberState(communityId, email, MembershipState.Rejected);
			} else if ("withdraw".equals(type)) {
				communityService.updateCommunityMemberState(communityId, email, MembershipState.Closed);
			}
			
			PageTranfer pageTranfer = PageTranfer.getInstance(req, resp);
			pageTranfer.redirectTo("/management/member.do?communityId=" + communityId);
			return;
		}
		
		// foward page
		PageTranfer.getInstance(req, resp).forwardTo("/WEB-INF/views/management/member.jsp");
	}

	private PresManagedMember convert(String communityId, CommunityMember member, String loginEmail, CommunityService communityService, ClubService clubService) {
		// 
		PresManagedMember presMember = new PresManagedMember();
		
		presMember.setEmail(member.getEmail());
		presMember.setName(member.getName());
		presMember.setState(member.getState());
		
		List<Club> clubs = clubService.findJoinedClubsByEmail(communityId, member.getEmail());
		int countOfClubs = (clubs != null) ? clubs.size() : 0;
		presMember.setCountOfClubs(countOfClubs);

		Community community = communityService.findCommunity(communityId);
		if (community.getManager().getEmail().equals(loginEmail)) {
			presMember.setAuthorized(true);
		}
		return presMember;
	}
}
