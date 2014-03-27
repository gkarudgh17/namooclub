package com.namoo.ns1.web.controller.management;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.common.DefaultController;

import dom.entity.Community;

public class MgmtDefaultController extends DefaultController {
	//
	private static final long serialVersionUID = 4820939516410343103L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		
		// 전체 커뮤니티 목록조회
		List<Community> communities = communityService.findAllCommunities();
		req.setAttribute("communities", communities);
	}
}
