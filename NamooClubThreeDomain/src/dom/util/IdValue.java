package dom.util;

import com.namoo.ns1.common.Identifiable;

public class IdValue implements Identifiable {
	//
	private static final long serialVersionUID = -1612798546221737455L;

	private String name;
	private int value;
	
	public IdValue(String name) {
		// 
		this.name = name;
	}

	public int getNextValue() {
		return ++value;
	}

	@Override
	public String getOId() {
		// 
		return name;
	}
}
