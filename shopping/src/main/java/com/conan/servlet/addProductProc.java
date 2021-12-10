package com.conan.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
/**
 * Servlet implementation class addProductProc
 */
@WebServlet("/product/addProductProc")
public class addProductProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addProductProc() {
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
		int size = 1024* 1024*10; //10m
		ServletContext application =request.getServletContext(); //밑 줄에서 application쓰려면 선언해야함
		String path= application.getRealPath("/upload"); //파일이 저장될 위치
		//실제로 저장되는 공간과 다름
		//C:\test\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\bitJSP의
		//upload 폴더에 저장되었음 대박,,
		
		try{//multi는 addProduct.jsp에서 enctype="multipart/form-data" 땜에 제일 먼저 된다. 그래서 항상
			//multi는 맨 윗줄에서 처리 해야함!
			MultipartRequest multi = new MultipartRequest(
			request,path,size,"UTF-8", new DefaultFileRenamePolicy()); //multi에서 fileName을 재가공 하는듯하다
			//파일 네임이 중복되면 뒤에 숫자를 붙여서 중복되지 않도록 해주는 방식
			Enumeration files = multi.getFileNames(); //files를 열거형으로 선언하고, 거기다가 fileName을 넣어줌
			//request.getParameter()로 받는게아니라 multi.getParameter로 받는 이유
			//MultipartRequest multi= new MultipartRequest(request,path,size)에서 
			//request에 대한 모든 요청을 다 multi에 넣어버리기땜에
			//request.getParameter가 아닌 multi.getParameter로 받아야함
			//중요!!!!!!!!!
			String name=(String)files.nextElement();
			String file = multi.getFilesystemName(name); //파일명
			String proId = multi.getParameter("proId");
			String proName = multi.getParameter("proName");
			int unitPrice = Integer.parseInt(multi.getParameter("unitPrice"));
			String description = multi.getParameter("description");
			String manufacturer = multi.getParameter("manufacturer");
			String category = multi.getParameter("category");
			String noOfStock = multi.getParameter("noOfStock");

			// 1. jndi 서버 객체 생성 jsp8번pdf jstl 9/11 잘 보기/...
			InitialContext ic;
			ic = new InitialContext();
			// 2. lookup()
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			// 3. getConnection()
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into PRODUCTS values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, proId);
			pstmt.setString(2, proName);
			pstmt.setInt(3, unitPrice);
			pstmt.setString(4, description);
			pstmt.setString(5, manufacturer);
			pstmt.setString(6, category);
			pstmt.setString(7, noOfStock);
			pstmt.setString(8, file);
			pstmt.executeUpdate(); // rs를 쓸 필요가 없음 insert,delete,이런거는 쓸필요가 없어서 이렇게 하면 진짜 insert 됨

			pstmt.close();
			conn.close();
			
			RequestDispatcher rd= request.getRequestDispatcher("/product/productList");
			rd.forward(request, response);
		}catch(Exception e){
			System.out.print("업로드 오류 발생");
					
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
