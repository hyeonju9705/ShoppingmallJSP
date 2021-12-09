package com.conan.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class productDetail
 */

@WebServlet(name="productDetail",urlPatterns={"/product/productDetail"}) 
// urlPatterns로 jsp에서 부를수있고, 보통 urlPatterns를 .do를붙임
public class productDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public productDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 1. jndi 서버 객체 생성 jsp8번pdf jstl 9/11 잘 보기/...
		InitialContext ic;
		try {
			String proId = request.getParameter("proId"); // productList.jsp 에서 parameter로 보낸것을 받아옴 그걸 proId에 저장
			ic = new InitialContext();
			// 2. lookup()
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			// 3. getConnection()
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUCTS where proId=?");
			pstmt.setString(1, proId);
			ResultSet rs = pstmt.executeQuery();

			Products pl = new Products(); // Products 자바 빈에서 가져와서 arrayList pl을 만듦
			if (rs.next()) {// db에 있는 1~7번째 컬럼을 다 읽어온다음에 Products 빈즈에다가 저장하구 그거를 arrayList pl에 추가함
				pl = new Products(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}
			request.setAttribute("product", pl); // 위에서 열심히 다 저장해놓은 pl을 ${product.~~~}로 사용하고 싶기땜에 set attribute 해줌
			//그리고 이 product는 requestDispatcher 에 의해 productDetail.jsp에서도
			// product를 사용할 수 잇게됨
			RequestDispatcher rd = request.getRequestDispatcher("productDetail.jsp");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
