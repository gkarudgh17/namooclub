package com.namoo.ns1.web.controller.club.pres;

import java.util.Date;

import dom.entity.Club;

public class PresClub {
	//
	private Club club;
	
	private int countOfMembers;
	
	private boolean owned;
	private boolean joined;
	
	//--------------------------------------------------------------------------
	// constructors
	
	public PresClub(Club club) {
		//
		this.club = club;
	}

	//--------------------------------------------------------------------------
	// methods
	
	public String getId() {
		//
		return club.getId();
	}
	
	public String getName() {
		//
		return club.getName();
	}
	
	public String getDescription() {
		//
		return club.getDescription();
	}
	
	public Date getOpenDate() {
		//
		return club.getOpenDate();
	}
	
	public String getCategoryName() {
		//
		return club.getCategory().getName();
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public int getCountOfMembers() {
		return countOfMembers;
	}

	public void setCountOfMembers(int countOfMembers) {
		this.countOfMembers = countOfMembers;
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
