package com.namoo.ns1.web.controller.management;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.common.PageTranfer;
import com.namoo.ns1.web.controller.management.pres.PresMemberDetail;
import com.namoo.ns1.web.session.LoginRequired;

import dom.entity.Club;
import dom.entity.CommunityMember;

@LoginRequired
@WebServlet("/management/member/detail.do")
public class MemberDetailController extends MgmtDefaultController {
	//
	private static final long serialVersionUID = -1347719561637453445L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		super.process(req, resp);

		String communityId = req.getParameter("communityId");
		String email = req.getParameter("email");
		
		req.setAttribute("communityId", communityId);
		
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();

		CommunityMember communityMember = communityService.findCommunityMember(communityId, email);
		PresMemberDetail member = new PresMemberDetail(communityMember);
		
		List<Club> clubs = clubService.findJoinedClubsByEmail(communityId, email);
		if (clubs != null) {
			for (Club club : clubs) {
				member.addClub(club);
			}
		}
		req.setAttribute("member", member);
		
		// forward page
		PageTranfer.getInstance(req, resp).forwardTo("/WEB-INF/views/management/member_detail.jsp");
	}
}
