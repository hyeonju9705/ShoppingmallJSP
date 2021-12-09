package com.conan.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class listProc
 */
@WebServlet(name="/listProc", urlPatterns= {"/board/listProc.do"})
public class listProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listProc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//게시글 목록 디비에서 불러와서 list.jsp에 출력하기
		
		BoardDAO dao = BoardDAO.getInstance();
		/* 보통 dao로 설정해준다. getInstance()를 해서 MemberDAO의 static 메소드인
			getInstance()를 불러와서 자기 자신인 dao를 불러와주는것....
			근데 이게 singleton으로 정의되어있는데 singleton이 뭐냐면 new 없이 객체생성이 가능한것
			객체 생성을 여러개 하지말구 정해놓은 dao 딱 하나로만 하라는 뜻이여~~~~
			그래서 객체생성이 복잡하지 않게 딱 한개만 가능해서 좋음....*/
		ArrayList<BoardVO> voList;
		try {
			voList = dao.selectBoardAll();
			request.setAttribute("voList", voList);
			
			RequestDispatcher rd = request.getRequestDispatcher("board/list.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* dao.selectMemberAll() 을 memList에다가 넣어줌
		근데 왜 ArrayList<MemberDTO> memList냐?? 
				그 이유는 selectMemberAll()이 ArrayList<MemberDTO> 형식으로
				memberDAO.java에서 정의해놨기 때문이야!!! */
		/* out.print(memList.size()); */
		
		/* 그리고 request.setAttribute는 머냐면 memList라는 값을 "memList"로
		사용하기 위해서 setting 해주는것 
		이렇게 세팅 해주면 ${memList}로도 사용가능해 
		참고로 $는 memList의 값을 가져오겠다는 뜻인듯 함 */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
