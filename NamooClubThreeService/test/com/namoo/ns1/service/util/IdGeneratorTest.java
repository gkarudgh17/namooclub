package com.namoo.ns1.service.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.namoo.ns1.common.Identifiable;

public class IdGeneratorTest {

	@Test
	public void testGenerate() {
		//
		String id = IdGenerator.generate(SomeEntity.class);
		String id2 = IdGenerator.generate(SomeEntity.class);
		
		int numOfId = Integer.parseInt(id);
		int numOfId2 = Integer.parseInt(id2);
		
		assertEquals(numOfId2, (numOfId + 1));
	}
	
	private static class SomeEntity implements Identifiable {
		//
		private static final long serialVersionUID = 1L;
		private String id;
		@Override
		public String getOId() {
			// 
			return id;
		}
	}
}
