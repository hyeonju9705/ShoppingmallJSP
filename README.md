# ShoppingmallJSP
JSP로 쇼핑몰 제작하기
<br><br>
개발환경 : Java SE 11, My SQL, MySQL Workbench

# 프로젝트 계기
Jsp와 servlet을 이해하고 spring으로 넘어가기 위해서 제작해봄

# 기간 / 인원 수
2021.12.07-11 (약 30시간) / 1명

# 배운점
1. connection 을 하는 여러가지 방법에 대해 알게됨
2. java에서 db조작 시 주의해야할 부분에 대해 알게됨 ex) like 함수 : like ?  setString(1,"%"+변수이름+"%")
3. ResultSet 의 next() 개념에 대해 확실히 숙지하게 됨 ex) 1개면 if(rs.next()), 여러개면 where(rs.next())
4. close()의 필요성 (무한로딩 가능성 제거) 및 close의 코드 속 위치(executeQuery()문이 끝나고 close()) 등에 대해 고민할 수 있었음 
5. executeQuery() 와 executeUpdate()의 차이에 대해 다시 한 번 알게됨
6. arrayList 를 사용한 java beans 개념에 대해 알게됨
7. beans 개념을 익히고 dto의 개념을 익혔고, servlet을 배우면서 메소드를 정의하는 dao와 servlet의 기능을 한군데에서 하는 편리한 controller의 기능도 알게됨
8. 여러 페이지를 이동하기 때문에 parameter로 이동하는 get방식의 데이터의 이동에 대해 알게되었음
9. actionDo 및 UTF-8의 남용시 ??? 출력 에 대해 알게되었음
10. sendRedirect와 RequestDespatcher forward방식의 차이에 대해서도 알게됨
11. 또한 dao에서 싱글톤을 사용하며 dao.getInstance()의 편리함에 대해서도 숙지함
12. el태그의 편리성 및 setAttribute로 el태그 사용, core의 사용법에 대해서도 이해함
