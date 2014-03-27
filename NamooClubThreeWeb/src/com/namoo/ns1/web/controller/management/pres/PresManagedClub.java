package com.namoo.ns1.web.controller.management.pres;

import dom.entity.ClubManager;
import dom.entity.ClubState;

public class PresManagedClub {
	//
	private String id;
	private String name;
	private String category;
	private ClubState state;
	private ClubManager manager;
	private boolean authorized; // 처리권한이 있는지 여부
	
	//--------------------------------------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ClubState getState() {
		return state;
	}

	public void setState(ClubState state) {
		this.state = state;
	}

	public ClubManager getManager() {
		return manager;
	}

	public void setManager(ClubManager manager) {
		this.manager = manager;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
}
