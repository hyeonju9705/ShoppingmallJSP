package com.conan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//cmd 추출
		String uri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd= uri.substring(ctxPath.length());
		if(cmd.equals("/board/writeProc.do")) {
			//db에 게시글 저장 후 listProc.do로 이동
			System.out.println("write");
			System.out.println("=========================");
			response.sendRedirect(request.getContextPath()+"/board/writeFrm.jsp");
		}else if(cmd.equals("/board/listProc.do")) {
			//게시글 목록 디비에서 불러와서 list.jsp에 출력하기
			System.out.println("list");
			System.out.println("=========================");
			response.sendRedirect(request.getContextPath()+"/board/list.jsp");
		}else if(cmd.equals("/board/viewProc.do")) {
			//패러미터 추출해서 게시글 번호에 해당하는 글 조회수 증가하기, 불러와서 view.jsp에 출력하기
			System.out.println("view");
			System.out.println("=========================");
			response.sendRedirect(request.getContextPath()+"/board/view.jsp");
		}
		
	}

}
