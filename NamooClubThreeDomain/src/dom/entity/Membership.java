package dom.entity;

import java.sql.Date;

import com.namoo.ns1.common.Identifiable;

public class Membership implements Identifiable {
	//
	private static final long serialVersionUID = -925379253472838928L;
	
	private SocialPerson rolePerson;
	private MembershipState state;
	private Date openDate;
	private Date closeDate;

	//--------------------------------------------------------------------------
	public Membership(SocialPerson rolePerson) {
		//
		this.rolePerson = rolePerson;
		state = MembershipState.Requested;
		openDate = new Date(new java.util.Date().getTime());
		closeDate = Date.valueOf("9999-12-31");
	}
	
	//--------------------------------------------------------------------------
	
	public String getName() {
		//
		return rolePerson.getName();
	}

	public String getEmail() {
		// 
		return rolePerson.getEmail();
	}
	
	//--------------------------------------------------------------------------
	
	public SocialPerson getRolePerson() {
		return rolePerson;
	}

	public void setRolePerson(SocialPerson rolePerson) {
		this.rolePerson = rolePerson;
	}

	public MembershipState getState() {
		return state;
	}

	public void setState(MembershipState state) {
		this.state = state;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Override
	public String getOId() {
		// 
		return rolePerson.getOId();
	}

}
