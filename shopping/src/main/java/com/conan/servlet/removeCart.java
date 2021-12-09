package com.conan.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conan.vo.Products;

/**
 * Servlet implementation class removeCart
 */
@WebServlet("/removeCart")
public class removeCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String proId=request.getParameter("proId");
		
		HttpSession session = request.getSession(); //servlet에서 session사용하려면
		//이거 사용해야함
		ArrayList<Products> products = (ArrayList<Products>)session.getAttribute("cartList");

		Products orderdItem = new Products(); 
		for (int i=0; i< products.size(); i++){//arrayList의 크기만큼 for문 돌려
			orderdItem=products.get(i); //session의 카트리스트에 담겨있는걸 다 넣어 넣어
			if(orderdItem.getProId().equals(proId)){ // 그러다가 삭제하려는 상품 아이디랑 똑같은거 발견하면
				products.remove(i); //그거 지워버림
			}
		}
		RequestDispatcher rd= request.getRequestDispatcher("cart.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
