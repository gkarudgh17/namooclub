package com.namoo.ns1.web.controller.management.pres;

import dom.entity.MembershipState;

public class PresManagedMember {
	//
	private String email;
	private String name;
	private MembershipState state;
	private int countOfClubs;
	private boolean authorized;
	
	//--------------------------------------------------------------------------
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public MembershipState getState() {
		return state;
	}

	public void setState(MembershipState state) {
		this.state = state;
	}

	public int getCountOfClubs() {
		return countOfClubs;
	}

	public void setCountOfClubs(int countOfClubs) {
		this.countOfClubs = countOfClubs;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
}
