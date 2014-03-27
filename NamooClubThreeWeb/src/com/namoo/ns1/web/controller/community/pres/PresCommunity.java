package com.namoo.ns1.web.controller.community.pres;

import java.util.Date;

import dom.entity.Community;
import dom.entity.MembershipState;

public class PresCommunity {
	//
	private Community community;
	private MembershipState membershipState;
	
	private int countOfClubs;
	private int countOfMembers;
	
	private boolean owned;
	private boolean joined;
	
	//--------------------------------------------------------------------------
	// constructors
	
	public PresCommunity(Community community) {
		//
		this.community = community;
	}

	//--------------------------------------------------------------------------
	// methods
	
	public String getId() {
		//
		return community.getId();
	}
	
	public MembershipState getMembershipState() {
		return membershipState;
	}

	public void setMembershipState(MembershipState membershipState) {
		this.membershipState = membershipState;
	}

	public String getName() {
		//
		return community.getName();
	}
	
	public String getDescription() {
		//
		return community.getDescription();
	}
	
	public Date getOpenDate() {
		//
		return community.getOpenDate();
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public int getCountOfMembers() {
		return countOfMembers;
	}

	public void setCountOfMembers(int countOfMembers) {
		this.countOfMembers = countOfMembers;
	}

	public int getCountOfClubs() {
		return countOfClubs;
	}

	public void setCountOfClubs(int countOfClubs) {
		this.countOfClubs = countOfClubs;
	}

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public boolean isJoined() {
		return joined;
	}

	public void setJoined(boolean joined) {
		this.joined = joined;
	}
}
