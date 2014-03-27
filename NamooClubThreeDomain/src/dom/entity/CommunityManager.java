package dom.entity;

import com.namoo.ns1.common.Identifiable;

public class CommunityManager extends Membership implements Identifiable {

	private static final long serialVersionUID = -1543226812538269293L;
	
	private String communityId;

	//--------------------------------------------------------------------------
	// constructor
	
	public CommunityManager(String communityId, SocialPerson rolePerson){
		//
		super(rolePerson);
		this.communityId = communityId;

		// 커뮤니티 관리자는 생성즉시 가입상태로 세팅
		setState(MembershipState.Active);
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getCommunityId() {
		return communityId;
	}
}