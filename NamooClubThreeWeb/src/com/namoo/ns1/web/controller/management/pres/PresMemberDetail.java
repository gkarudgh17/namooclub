package com.namoo.ns1.web.controller.management.pres;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.CommunityMember;
import dom.entity.Gender;
import dom.entity.MembershipState;

public class PresMemberDetail {
	//
	private String email;
	private String name;
	private Date birthDate;
	private Gender gender;
	private Date joinDate;
	private MembershipState state;
	
	private List<PresJoinClub> clubs;

	//--------------------------------------------------------------------------
	
	public PresMemberDetail(CommunityMember member) {
		//
		this.clubs = new ArrayList<PresJoinClub>();
		this.email = member.getEmail();
		this.name = member.getName();
		this.birthDate = member.getBirthDate();
		this.gender = member.getGender();
		this.joinDate = member.getStartDate();
		this.state = member.getState();
	}
	
	//--------------------------------------------------------------------------

	public void addClub(Club club) {
		//
		PresJoinClub joinClub = new PresJoinClub();
		joinClub.setName(club.getName());
		
		ClubMember member = club.findMember(email);
		System.out.println(member);
		joinClub.setJoinDate(member.getStartDate());
		joinClub.setState(member.getState());
		
		System.out.println(">>>>" + email);
		System.out.println(">>>>" + club.findManager(email));
		if (club.findManager(email) != null) {
			joinClub.setOwned(true);
		}
		this.clubs.add(joinClub);
	}
	
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public List<PresJoinClub> getClubs() {
		return clubs;
	}

	public void setClubs(List<PresJoinClub> clubs) {
		this.clubs = clubs;
	}

	public MembershipState getState() {
		return state;
	}

	public void setState(MembershipState state) {
		this.state = state;
	}

	//--------------------------------------------------------------------------
	public static class PresJoinClub {
		//
		private String name;
		private boolean owned;
		private Date joinDate;
		private MembershipState state;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isOwned() {
			return owned;
		}
		public void setOwned(boolean owned) {
			this.owned = owned;
		}
		public Date getJoinDate() {
			return joinDate;
		}
		public void setJoinDate(Date joinDate) {
			this.joinDate = joinDate;
		}
		public MembershipState getState() {
			return state;
		}
		public void setState(MembershipState state) {
			this.state = state;
		}
	}
}
