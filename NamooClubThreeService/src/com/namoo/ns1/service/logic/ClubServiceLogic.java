package com.namoo.ns1.service.logic;

import java.util.ArrayList;
import java.util.List;

import com.namoo.ns1.data.EntityManager;
import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.shared.exception.NamooExceptionFactory;
import com.namoo.ns1.service.util.IdGenerator;

import dom.entity.Category;
import dom.entity.Club;
import dom.entity.ClubState;
import dom.entity.Community;
import dom.entity.SocialPerson;

public class ClubServiceLogic implements ClubService {

	private EntityManager em;
	
	public ClubServiceLogic() {
		//
		em = EntityManager.getInstance();
	}
	
	@Override
	public void registClub(String communityId, String categoryId, String clubName, String description, String email) {
		// 
		Community community = em.find(Community.class, communityId);
		if (community.findMember(email) == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티 회원만 클럽가입이 가능합니다.");
		}

		// TODO: 동일한 카테고리를 가진 클럽이 이미 존재하는 경우 예외발생
		
		Category category = community.findClubCategoryById(categoryId);
		SocialPerson person = em.find(SocialPerson.class, email);
		
		String id = IdGenerator.generate(Club.class);
		Club club = new Club(id, clubName, description, category, person);
		
		// 커뮤니티에 클럽추가
		community.addClub(club);
		
		em.store(community);
		em.store(club);
	}

	@Override
	public void joinAsClub(String communityId, String clubId, String email) {
		// 
		Community community = em.find(Community.class, communityId);
		if (community.findMember(email) == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티 회원만 클럽가입이 가능합니다.");
		}
		
		Club club = em.find(Club.class, clubId);
		SocialPerson person = em.find(SocialPerson.class, email);
		
		club.addMember(person);
		em.store(club);
	}

	@Override
	public void withdrawClub(String communityId, String clubId, String email) {
		// 
		Club club = em.find(Club.class, clubId);
		club.removeMember(email);
		
		em.store(club);
	}

	@Override
	public void dropClub(String communityId, String clubId) {
		// 
		Community community = em.find(Community.class, communityId);
		Club club = em.find(Club.class, clubId);
		
		community.removeClub(clubId);
		em.store(community);
		em.remove(club);
	}

	@Override
	public List<Club> findAllClubs(String communityId) {
		// 
		return em.find(Community.class, communityId).getClubs();
	}

	@Override
	public Club findClub(String clubId) {
		// 
		return em.find(Club.class, clubId);
	}

	@Override
	public int countMembers(String clubId) {
		// 
		Club club = em.find(Club.class, clubId);
		return club.getMembers().size();
	}

	@Override
	public int countClubs(String communityId) {
		// 
		Community community = em.find(Community.class, communityId);
		return (community != null) ? community.getClubs().size() : 0;
	}

	@Override
	public List<Club> findJoinedClubsByEmail(String communityId, String email) {
		// 
		Community community = em.find(Community.class, communityId);
		List<Club> allClubs = community.getClubs();
		
		List<Club> joinedClubs = new ArrayList<Club>();
		if (allClubs != null) {
			for (Club club : allClubs) {
				club = em.find(Club.class, club.getId());
				if (club.findMember(email) != null) {
					joinedClubs.add(club);
				}
			}
		}
		return joinedClubs;
	}

	@Override
	public void updateClubState(String communityId, String clubId, ClubState state) {
		// 
		Club club = em.find(Club.class, clubId);
		club.setState(state);
		
		em.store(club);
	}
	
}
