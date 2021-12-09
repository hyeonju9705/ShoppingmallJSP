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
import javax.sql.DataSource;

import com.conan.vo.Products;

/**
 * Servlet implementation class productList
 */
@WebServlet("/product/productList")
public class productList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1. jndi 서버 객체 생성 jsp8번pdf jstl 9/11 잘 보기/... servlet으로 바꾸려면 sevlet을 만든다음 doget에다가 이 <%를 다 넣어주면되고,
		//서블릿에서는 requestDispatcher 해서 forward(request, response)방식으로 jsp로 보내주고 , 
		//서블릿이랑 각각의 페이지를 연결하면 되는데 request.contextPath머시기로하면댐
		InitialContext ic;
		try {
			ic = new InitialContext();
			//2. lookup()
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			//3. getConnection()
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUCTS");
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Products> pl = new ArrayList<Products>(); //Products 자바 빈에서 가져와서 arrayList pl을 만듦
			while (rs.next()) {//db에 있는 1~7번째 컬럼을 다 읽어온다음에 Products 빈즈에다가 저장하구 그거를 arrayList pl에 추가함
				pl.add(new Products(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
			request.setAttribute("list", pl); //c:set var="list" value=pl이랑 같은거
			
			RequestDispatcher rd= request.getRequestDispatcher("productList.jsp");
			rd.forward(request, response);
			
			rs.close();
			pstmt.close();
			conn.close();
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
