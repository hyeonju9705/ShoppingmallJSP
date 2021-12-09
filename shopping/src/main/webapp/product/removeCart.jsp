<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page
	import="java.sql.*, java.util.ArrayList, com.conan.vo.Products, javax.sql.DataSource, javax.naming.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%//삭제 페이지
//한 행이 아예 사라지는것(삭제버튼 클릭시 상품 종류 하나가 아예 사라져버림). 삭제할때마다 수량-1 해주는 기능은 없음
String proId=request.getParameter("proId");

ArrayList<Products> products = (ArrayList<Products>)session.getAttribute("cartList"); //세션중에 cartList가져와서 products에 넣음
//cartList session은 addCart.java에서 세션에 카트리스트가 없을 때에 session을 cartList 라는 이름으로 생성해줬음

Products orderdItem = new Products(); 
for (int i=0; i< products.size(); i++){//arrayList의 크기만큼 for문 돌려
	orderdItem=products.get(i); //session의 카트리스트에 담겨있는걸 다 넣어 넣어
	if(orderdItem.getProId().equals(proId)){ // 그러다가 삭제하려는 상품 아이디랑 똑같은거 발견하면
		products.remove(i); //그거 지워버림
	}
}
RequestDispatcher rd= request.getRequestDispatcher("cart.jsp");
rd.forward(request, response);
//cartList 중 해당 제품만 제거한 다음 cart.jsp로 리다이렉트
%>
</body>
</html>