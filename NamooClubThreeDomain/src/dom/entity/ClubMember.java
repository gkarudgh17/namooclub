package dom.entity;

import com.namoo.ns1.common.Identifiable;

public class ClubMember extends Membership implements Identifiable {
	//
	private static final long serialVersionUID = -8771609525182833682L;
	
	private String clubId;
	
	//--------------------------------------------------------------------------
	
	public ClubMember(String clubId, SocialPerson rolePerson, MembershipState state) {
		//
		super(rolePerson);
		this.clubId = clubId;
		
		setState(state);
	}

	public String getClubId() {
		return clubId;
	}
}
