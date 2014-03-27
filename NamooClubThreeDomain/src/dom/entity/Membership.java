package dom.entity;

import java.sql.Date;

import com.namoo.ns1.common.Identifiable;

public class Membership implements Identifiable {
	//
	private static final long serialVersionUID = -925379253472838928L;
	
	private SocialPerson rolePerson;
	private MembershipState state;
	private Date startDate;
	private Date endDate;

	//--------------------------------------------------------------------------
	public Membership(SocialPerson rolePerson) {
		//
		this.rolePerson = rolePerson;
		state = MembershipState.Requested;
		startDate = new Date(new java.util.Date().getTime());
		endDate = Date.valueOf("9999-12-31");
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
	
	public Date getBirthDate() {
		//
		return rolePerson.getBirthDate();
	}
	
	public Gender getGender() {
		//
		return rolePerson.getGender();
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	//--------------------------------------------------------------------------

	@Override
	public String getOId() {
		// 
		return rolePerson.getOId();
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder();
		builder.append("name:" + getName());
		builder.append(",email:" + getEmail());
		builder.append(",state:" + state);
		builder.append(",startDate:" + startDate);
		builder.append(",endDate:" + endDate);
		
		return builder.toString();
	}
}
