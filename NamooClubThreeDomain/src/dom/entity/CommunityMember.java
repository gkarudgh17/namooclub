package dom.entity;

import com.namoo.ns1.common.Identifiable;

public class CommunityMember extends Membership implements Identifiable {
	//
	private static final long serialVersionUID = -876480131774545678L;
	
	private String communityId;

	//--------------------------------------------------------------------------
	// constructor
	
	public CommunityMember(String communityId, SocialPerson rolePerson){
		//
		super(rolePerson);
		this.communityId = communityId;
		
		setState(MembershipState.Requested);
	}
	
	//--------------------------------------------------------------------------

	public String getCommunityId() {
		return communityId;
	}
}