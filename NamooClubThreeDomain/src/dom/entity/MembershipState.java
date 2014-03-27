package dom.entity;

public enum MembershipState {
	//
	Requested("가입신청중"),
	Rejected("가입거절"),
	Active("정상"),
	RequestWithdrawal("탈퇴신청중"),
	Closed("탈퇴");
	
	private String name;
	
	private MembershipState(String name) {
		//
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
