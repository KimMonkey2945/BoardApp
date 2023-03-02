package com.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.board.BoardDAO;
import com.biz.board.BoardVO;
import com.biz.user.UserDAO;
import com.biz.user.UserVO;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		if (path.equals("/login.do")) {
			
			System.out.println("로그인 처리 ");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			
			UserDAO dao = new UserDAO();
			
			UserVO user = dao.getUser(vo);
			
			HttpSession session = request.getSession();
			
			if(user != null && user.getPassword().equals(password)) {
				session.setAttribute("user", user);
			
				RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/loginView.do");
				dispatcher.forward(request, response);
			}
			
			
		} else if (path.equals("/insertUser.do")) {
			
			System.out.println("회원가입 처리");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String role = request.getParameter("role");
			
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			vo.setName(name);
			vo.setRole(role);
			
			UserDAO dao = new UserDAO();
			dao.insertUser(vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
			
		} else if (path.equals("/logout.do")) {
			
			System.out.println("로그아웃 처리");
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect("/");
			
		} else if (path.equals("/insertBoard.do")) {
			
			System.out.println("글 등록 처리");
			
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO dao = new BoardDAO();
			dao.insertBoard(vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if (path.equals("/updateBoard.do")) {
			
			System.out.println("글 수정 처리");
			
			String title = request.getParameter("title");
			String seq = request.getParameter("seq");
			String content = request.getParameter("content");
			
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setSeq(Integer.parseInt(seq));
			vo.setContent(content);
			
			BoardDAO dao = new BoardDAO();
			dao.updateBoard(vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if (path.equals("/deleteBoard.do")) {
			
			System.out.println("글 삭제 처리");
			
			String seq = request.getParameter("seq");
			
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO dao = new BoardDAO();
			dao.deleteBoard(vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/getBoardList.do");
			dispatcher.forward(request, response);
			
		} else if (path.equals("/getBoard.do")) {
			
			System.out.println("글 상세 조회 처리");
			
			String seq = request.getParameter("seq");
			
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO dao = new BoardDAO();
			
			BoardVO board = dao.getBoard(vo);
			
			request.setAttribute("board", board);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/getBoard.jsp");
			dispatcher.forward(request, response);
			
			
		} else if (path.equals("/getBoardList.do")) {
			System.out.println("글 목록 검색 처리");
			
			String searchCondition = request.getParameter("searchCondition");
			String searchKeyword = request.getParameter("searchKeyword");
			
			if(searchCondition == null) searchCondition = "TITLE";
			if(searchKeyword == null) searchKeyword = "";
			
			HttpSession session = request.getSession();
			session.setAttribute("condition", searchCondition);
			session.setAttribute("keyword", searchKeyword);
			
			BoardVO vo = new BoardVO();
			vo.setSearchCondition(searchCondition);
			vo.setSearchKeyword(searchKeyword);
			
			BoardDAO dao = new BoardDAO();
			List<BoardVO> boardList = dao.getBoardList(vo);
			
			request.setAttribute("boardList", boardList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/getBoardList.jsp");
			dispatcher.forward(request, response);
			
		} else if(path.equals("/loginView.do")) {
			System.out.println("로그인 화면으로 이동");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/login.jsp");
			dispatcher.forward(request, response);
		}  else if(path.equals("/insertBoardView.do")) {
			System.out.println("글 등록 화면으로 이동");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/insertBoard.jsp");
			dispatcher.forward(request, response);
		} else if(path.equals("/insertUserView.do")) {
			System.out.println("회원가입 화면으로 이동");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/insertUser.jsp");
			dispatcher.forward(request, response);
		}
	}

}
