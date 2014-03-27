package dom.entity;

import com.namoo.ns1.common.Identifiable;

public class ClubManager extends Membership implements Identifiable {
	//
	private static final long serialVersionUID = 7096706829023569656L;

	private String clubId;
	private boolean representative; // 대표관리자 여부 
	
	//--------------------------------------------------------------------------
	// constructor
	
	public ClubManager(String clubId, SocialPerson rolePerson, boolean representative) {
		//
		super(rolePerson);
		this.clubId = clubId;
		this.representative = representative;
		setState(MembershipState.Active);
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public boolean isRepresentative() {
		return representative;
	}
	
	public void setRepresentative(boolean representative) {
		this.representative = representative;
	}

	public String getClubId() {
		return clubId;
	}
	
	//--------------------------------------------------------------------------
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(",clubId:" + clubId);
		builder.append(",representative:" + representative);
		return builder.toString();
	}
}
