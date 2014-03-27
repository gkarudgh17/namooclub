package com.namoo.ns1.service.logic;
import java.util.ArrayList;
import java.util.List;

import com.namoo.ns1.data.EntityManager;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.shared.exception.NamooExceptionFactory;
import com.namoo.ns1.service.util.IdGenerator;

import dom.entity.Community;
import dom.entity.CommunityMember;
import dom.entity.MembershipState;
import dom.entity.SocialPerson;

public class CommunityServiceLogic implements CommunityService {

	private EntityManager em;
	
	public CommunityServiceLogic() {
		//
		em = EntityManager.getInstance();
	}
	
	@Override
	public String registCommunity(String communityName, String description, String adminName, String email, String password) {
		//
		if (em.find(Community.class, communityName) != null) {
			throw NamooExceptionFactory.createRuntime("이미 존재하는 커뮤니티입니다.");
		}
		
		if (em.find(SocialPerson.class, email) != null) {
			throw NamooExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}

		SocialPerson admin = createPerson(adminName, email, password);
		String id = IdGenerator.generate(Community.class);
				
		Community community = new Community(id, communityName, description, admin);
		em.store(community);
		
		return id;
	}

	@Override
	public String registCommunity(String communityName, String description, String email) {
		//
		if (em.find(Community.class, communityName) != null) {
			throw NamooExceptionFactory.createRuntime("이미 존재하는 커뮤니티입니다.");
		}
		
		SocialPerson towner = em.find(SocialPerson.class, email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		
		String id = IdGenerator.generate(Community.class);
		Community community = new Community(id, communityName, description, towner);
		
		em.store(community);
		
		return id;
	}

	private SocialPerson createPerson(String name, String email, String password) {
		// 
		SocialPerson person = new SocialPerson(name, email, password);
		em.store(person);
		
		return person;
	}

	@Override
	public Community findCommunity(String communityId){
		//
		return em.find(Community.class, communityId);
	}

	@Override
	public void joinAsMember(String communityId, String name, String email, String password){
		//
		Community community = em.find(Community.class, communityId);
		
		if (community == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
	
		if (em.find(SocialPerson.class, email) != null) {
			throw NamooExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}
		
		SocialPerson towner = createPerson(name, email, password);
		community.addMember(towner);
		
		em.store(community);
	}

	@Override
	public void joinAsMember(String communityId, String email) {
		// 
		Community community = em.find(Community.class, communityId);
		
		if (community == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		SocialPerson towner = em.find(SocialPerson.class, email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		
		// 이미 존재하는 회원인 경우 탈퇴처리 후 재가입
		if (community.findMember(email) != null) {
			community.removeMember(email);
		}
		community.addMember(towner);
		em.store(community);
	}

	@Override
	public CommunityMember findCommunityMember(String communityId, String email) {
		// 
		Community community = em.find(Community.class, communityId);
		
		if (community == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		for (CommunityMember member : community.getMembers()) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		
		return null;
	}

	@Override
	public List<CommunityMember> findAllCommunityMember(String communityId) {
		// 
		Community community = em.find(Community.class, communityId);
		
		if (community == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		return community.getMembers();
	}

	@Override
	public int countMembers(String communityId){
		//
		Community community = em.find(Community.class, communityId);
		if (community != null) {
			return community.getMembers().size();
		}
		
		return 0;
	}

	@Override
	public void removeCommunity(String communityId) {
		// 
		em.remove(Community.class, communityId);
	}

	@Override
	public List<Community> findAllCommunities() {
		// 
		return em.findAll(Community.class);
	}

	@Override
	public List<Community> findJoinedCommunities(String email) {
		// 
		List<Community> commnities = em.findAll(Community.class);
		if (commnities == null) return null;
		
		List<Community> joined = new ArrayList<>();
		for (Community community : commnities) {
			CommunityMember member = community.findMember(email);
			if (member != null && member.getState() != MembershipState.Closed) {
				joined.add(community);
			}
		}
		return joined;
	}
	
	@Override
	public List<Community> findUnjoinedCommunities(String email) {
		// 
		List<Community> commnities = em.findAll(Community.class);
		if (commnities == null) return null;
		
		List<Community> unjoined = new ArrayList<>();
		for (Community community : commnities) {
			CommunityMember member = community.findMember(email);
			if (member == null || member.getState() == MembershipState.Closed) {
				unjoined.add(community);
			}
		}
		return unjoined;
	}

	@Override
	public List<Community> findManagedCommnities(String email) {
		// 
		List<Community> commnities = em.findAll(Community.class);
		if (commnities == null) return null;
		
		List<Community> manages = new ArrayList<>();
		for (Community community : commnities) {
			if (community.getManager().getEmail().equals(email)) {
				manages.add(community);
			}
		}
		return manages;
	}

	@Override
	public void withdrawCommunity(String communityId, String email) {
		//
		Community community = em.find(Community.class, communityId);
		if (community == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		if (community.getManager().getEmail().equals(email)) {
			throw NamooExceptionFactory.createRuntime("관리자는 탈퇴할 수 없습니다.");
		}
		
		community.removeMember(email);
		em.store(community);
	}

	@Override
	public void modifyCommunity(Community community) {
		// 
		em.store(community);
	}

	@Override
	public void updateCommunityMemberState(String communityId, String email, MembershipState state) {
		// 
		Community community = em.find(Community.class, communityId);
		CommunityMember member = community.findMember(email);
		member.setState(state);
		
		em.store(community);
	}
}