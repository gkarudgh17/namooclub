package com.namoo.ns1.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class SimpleFilterAdapter implements Filter {

	@Override
	public void destroy() {
		// 
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 
		boolean result = doFilter(req, resp);
		if (result == true) {
			chain.doFilter(req, resp);
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		//
		
	}
	
	/**
	 * 필터를 수행하고 후속필터 동작여부를 리턴한다.
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract boolean doFilter(ServletRequest req, ServletResponse resp) throws IOException, ServletException;

}
