package dom.entity;

public enum Gender {
	//
	Male("M", "남자")
	,Female("F", "여자");
	
	private String code;
	private String name;
	
	private Gender(String code, String name) {
		//
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}

	public static Gender findBy(String genderCode) {
		// 
		for (Gender gender : values()) {
			if (gender.getCode().equals(genderCode)) {
				return gender;
			}
		}
		return null;
	}
}
