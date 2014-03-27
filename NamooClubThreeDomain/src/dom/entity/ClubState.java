package dom.entity;

public enum ClubState {
	//
	Requested("개설신청중"),
	Rejected("거절"),
	Opened("개설"),
	RequestClose("폐쇄신청중"),
	Closed("폐쇄");
	
	private String name;
	
	private ClubState(String name) {
		//
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
