package com.namoo.ns1.service.facade;

import java.util.List;

import dom.entity.Club;
import dom.entity.ClubState;

public interface ClubService {
	//
	void registClub(String communityId, String categoryId, String clubName, String description, String email);
	void joinAsClub(String communityId, String clubId, String email);
	void withdrawClub(String communityId, String clubId, String email);
	void dropClub(String communityId, String clubId);
	List<Club> findAllClubs(String communityId);
	int countMembers(String clubId);
	Club findClub(String clubId);
	int countClubs(String communityId);
	List<Club> findJoinedClubsByEmail(String communityId, String email);
	void updateClubState(String communityId, String clubId, ClubState state);
}
