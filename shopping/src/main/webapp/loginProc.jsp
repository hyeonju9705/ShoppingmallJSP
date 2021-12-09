<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

String url="jdbc:mysql://localhost:3306/scottDB?useSSL=false";
String dbId="scott",dbPwd="tiger";
Connection conn=null;
String sql=null;
PreparedStatement pstmt = null;
ResultSet rs= null;
try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn=DriverManager.getConnection(url,dbId,dbPwd);
	/* out.println("데이터베이스 연결 성공"); */
	
	if (request.getMethod().equals("POST")) {
		String userId=request.getParameter("userId"); //id get방식으로 받아옴
		String userPwd=request.getParameter("userPwd");
		
		/* 유효성 처리 */
		if (userId.isEmpty() || userPwd.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("loginFrm.jsp");
			rd.forward(request, response);
		}
		/* 로그인 처리 */
		sql= "select userPwd from Members2 where userid = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,userId);
		rs=pstmt.executeQuery(); 
		if(rs.next()){//회원인 경우 = 데이터베이스에 아이디가 잇음
			if(rs.getString(1).equals(userPwd)){
				out.print("로그인성공");
				session.setAttribute("userId",userId); //세션 생성
				response.sendRedirect("memberInfo.jsp"); //redirect
			}else if(rs.getString(1).equals(userId)){
				out.print("<script>alert('비밀번호 불일치');history.back();</script>");
			}
		}else{ //데이터베이스에 아이디가 없는경우
			out.print("<script>alert('아이디 불일치');history.back();</script>");
			//왜안돼 ㅡㅡ 퍼센트 나오는이유좀
		}
		
	}

} catch(ClassNotFoundException e){
	e.printStackTrace();
}catch(SQLException e){
	e.printStackTrace();
}
finally{
	conn.close();
}
%>
%>
</body>
</html>