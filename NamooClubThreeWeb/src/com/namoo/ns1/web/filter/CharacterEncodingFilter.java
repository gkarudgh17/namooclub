package com.namoo.ns1.web.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
	urlPatterns = {"/*"}, 
	initParams = {
		@WebInitParam(name = "encoding", value = CharacterEncodingFilter.ENCODING )
	}	
)
public class CharacterEncodingFilter extends SimpleFilterAdapter {

	protected static final String ENCODING = "UTF-8";

	@Override
	public boolean doFilter(ServletRequest req, ServletResponse resp) throws IOException, ServletException {
		// 
		req.setCharacterEncoding(ENCODING);
		resp.setContentType("text/html; charset=" + ENCODING);
		resp.setCharacterEncoding(ENCODING);
		
		return true;
	}
}
