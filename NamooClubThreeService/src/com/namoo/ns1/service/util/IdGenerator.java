package com.namoo.ns1.service.util;

import com.namoo.ns1.data.EntityManager;

import dom.util.IdValue;

public class IdGenerator {
	//
	public static <T> String generate(Class<T> clazz) {
		//
		EntityManager em = EntityManager.getInstance();
		
		String idName = clazz.getName();
		IdValue idValue = em.find(IdValue.class, idName);
		
		if (idValue == null) {
			idValue = new IdValue(idName);
		}
		
		int nextId = idValue.getNextValue();
		em.store(idValue);
		
		return Integer.toString(nextId);
	}
}
