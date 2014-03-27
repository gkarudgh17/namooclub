package com.namoo.ns1.service.shared.exception;

public class NamooExceptionFactory {

	private NamooExceptionFactory() {
		//
	}
	
	public static NamooRuntimeException createRuntime(String msg) {
		//
		return new NamooRuntimeException(msg);
	}
}
