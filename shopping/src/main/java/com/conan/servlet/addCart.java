package com.conan.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.conan.vo.Products;

/**
 * Servlet implementation class addCart
 */
@WebServlet("/product/addCart")
public class addCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//커넥션 풀을 이용하여 connection 얻어오는 과정
		// 1. jndi 서버 객체 생성 jsp8번pdf jstl 9/11 잘 보기/...
		InitialContext ic;
		try {
			ic = new InitialContext();
		
		// 2. lookup()
		DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
		// 3. getConnection()
		Connection conn = ds.getConnection();
		
		String proId=request.getParameter("proId"); //proId를 받음
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUCTS WHERE PROID=?");
		pstmt.setString(1,proId);
		ResultSet rs = pstmt.executeQuery();
		Products selectedItem = null; //import 해온 Products를 selectedItem으로 지정
		if(rs.next()){
			selectedItem = new Products(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),
					rs.getString(6),rs.getInt(7),rs.getString(8)); //proId에 맞는 모든 테이블에 있는 정보를 selectedItem에 넣음
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		HttpSession session = request.getSession(); //servlet에서 session사용하려면
		//이거 사용해야함
		ArrayList<Products> cartList = (ArrayList<Products>) session.getAttribute("cartList");//Products를 ArrayList로 만듦!
		//이때는 session을 가져올건데 cartList로 setting되어있는 session을 가져올 것임
		//그리고 ArrayList<Products> 로 되어잇으니까 형식도 ArrayList<Products>로 ()에 넣어서 맞춰줌 강제형변환임ㅋㅋ
		if(cartList == null){ //세션에 카트리스트가 없으면, 카트 리스트는 선택된 모든 아이템 저장하고, session도 생성함
			cartList=new ArrayList<Products>();
			session.setAttribute("cartList",cartList); // session 새로 생성
		}
		int cnt = 0;
		Products orderdItem = new Products(); //주문 상품
		for (int i=0; i< cartList.size(); i++){
			orderdItem=cartList.get(i); // 세션에 추가된 상품을 주문 상품에 추가
			if(orderdItem.getProId().equals(proId)){
				cnt++;//카트에 추가했다는 의미로 cnt++을 해준다
				int orderedQuantity = orderdItem.getQuantity()+1; //주문 수량 증가
				orderdItem.setQuantity(orderedQuantity);//증가한거 다시 세팅해줌
			}
		}
		if(cnt == 0){//카트에 추가된 적이 없으면
			selectedItem.setQuantity(1); //최초 주문 수량을 1로 설정
			cartList.add(selectedItem); //카트 리스트에 추가
		}
		//해당 상품의 상세 페이지로
		RequestDispatcher rd= request.getRequestDispatcher("/product/productDetail?proId="+proId);
				rd.forward(request, response);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
