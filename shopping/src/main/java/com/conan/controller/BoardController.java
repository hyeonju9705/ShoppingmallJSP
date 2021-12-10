package com.conan.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conan.dao.BoardDAO;
import com.conan.vo.BoardVO;

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
		request.setCharacterEncoding("UTF-8");
		//cmd 추출
		String uri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd= uri.substring(ctxPath.length());
		if(cmd.equals("/board/writeProc.do")) {
			//db에 게시글 저장 후 listProc.do로 이동
			System.out.println("write : "+cmd);
			System.out.println("=========================");
			String userId=request.getParameter("userId");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			BoardDAO dao = BoardDAO.getInstance();
			BoardVO vo = new BoardVO(userId,title,content);
			dao.insertBoard(vo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/listProc.do");
			rd.forward(request, response);
			/* response.sendRedirect(request.getContextPath()+"/board/listProc.do"); */
			
		}else if(cmd.equals("/board/listProc.do")) {
			BoardDAO dao = BoardDAO.getInstance();
			ArrayList<BoardVO> voList;
			String text=request.getParameter("text");
			
			if(text==null && request.getParameter("items")==null) {
			voList = dao.selectBoardAll();
			}else {
				voList=dao.selectSubject(text);
			}
			System.out.println("size" +voList.size());
			request.setAttribute("voList", voList);
			
			request.setAttribute("cnt", dao.getListCount());
			//게시글 목록 디비에서 불러와서 list.jsp에 출력하기
			System.out.println("list" +cmd);
			System.out.println("=========================");
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
			rd.forward(request, response);
			
		}else if(cmd.equals("/board/viewProc.do")) {
			//패러미터 추출해서 게시글 번호에 해당하는 글 조회수 증가하기, 불러와서 view.jsp에 출력하기
			System.out.println("view" +cmd);
			System.out.println("=========================");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			BoardDAO dao = BoardDAO.getInstance();
			
			dao.increaseHit(boardNo);
			
			request.setAttribute("bd", dao.selectBoard(boardNo));
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/view.jsp");
			rd.forward(request, response);
			
		}else if(cmd.equals("/board/upordelProc.do")) {
			String btn[] = request.getParameterValues("threebtn");
			int boardNo= Integer.parseInt(request.getParameter("boardNo"));
			
			BoardDAO dao = BoardDAO.getInstance();
			request.setAttribute("bd", dao.selectBoard(boardNo));
			
			for(int i=0; i<btn.length; i++) {
				if(btn[i].equals("수정")){
					RequestDispatcher rd= request.getRequestDispatcher("/board/updateFrm.jsp");
					rd.forward(request, response);
				}else if(btn[i].equals("삭제")) {
					dao.deleteBoard(boardNo);
					
					RequestDispatcher rd= request.getRequestDispatcher("/board/listProc.do");
					rd.forward(request, response);
				}
			}
			
		}else if(cmd.equals("/board/updateProc.do")) {
			int boardNo= Integer.parseInt(request.getParameter("boardNo"));
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			BoardDAO dao = BoardDAO.getInstance();
			BoardVO vo = new BoardVO(title,content,boardNo);
			dao.updateBoard(vo); 
			request.setAttribute("bd", dao.selectBoard(boardNo));
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/view.jsp?boardNo="+boardNo);
			rd.forward(request, response);
			
		}else if(cmd.equals("/board/selectProc.do")) {
			String select[] = request.getParameterValues("items");
			BoardDAO dao = BoardDAO.getInstance();
			String text= request.getParameter("text");
			
			for(int i=0; i<select.length; i++) {
				if (select[i].equals("subject")) {
					//검색
					ArrayList<BoardVO> vo =dao.selectSubject(text);
					
					request.setAttribute("bd", vo);
					System.out.println("text 출력되니?  "+dao.selectSubject(text));
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/listProc.do");
					rd.forward(request, response);
				}
			}
		}
		
		
	}

}
