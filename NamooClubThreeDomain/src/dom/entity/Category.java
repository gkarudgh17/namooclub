package dom.entity;

import com.namoo.ns1.common.Identifiable;

public class Category implements Identifiable {
	//
	private static final long serialVersionUID = 1012957645608004986L;

	private String id;
	private String name;
	
	//--------------------------------------------------------------------------
	// constructor
	
	public Category(String id, String name) {
		//
		this.id = id;
		this.name = name;
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//--------------------------------------------------------------------------
	// override
	
	@Override
	public String getOId() {
		// 
		return id;
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder();
		builder.append("id:" + id);
		builder.append(",name:" + name);
		
		return builder.toString();
	}
	
}
