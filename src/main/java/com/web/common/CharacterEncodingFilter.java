package com.web.common;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"*.do"},
initParams = @WebInitParam(name = "boardEncoding", value="UTF-8"))
public class CharacterEncodingFilter extends HttpFilter implements Filter{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse respose, FilterChain chain)
			throws IOException, ServletException {
		
		ServletContext context = request.getServletContext();
		String encoding = context.getInitParameter("boardEncoding");
		request.setCharacterEncoding(encoding);
		
		chain.doFilter(request, respose);
	}

	
	
	

	
}
