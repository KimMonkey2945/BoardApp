package com.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/getBoardList.do", "/getBoard.do", "/deleteBoard.do" })
public class AuthenticationFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse respose, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") == null) {
			respose.sendRedirect("/");
		}else {
			chain.doFilter(request, respose);			
		}

	}

}
