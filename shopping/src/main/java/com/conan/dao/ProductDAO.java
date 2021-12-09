package com.conan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.conan.vo.Products;

public class ProductDAO {
	// singleton
		private static ProductDAO dao = new ProductDAO(); //static타입이라는 것을 기억해.
		//dao 이름으로 이제부터 어디서든지 MemberDAO를 쓸수 있게 하겟다는거야
		private ProductDAO() {
		}

		public static ProductDAO getInstance() {
			return dao; //자기자신을 불러오는 메소드
		}

		public Connection getConnection() throws SQLException {// DB 연결 객체 반환
			// 1.JNDL 서버 객체 생성 > 가장 최신의 connection 하는방법..****
			InitialContext ic;
			Connection conn = null;
			try {
				ic = new InitialContext();
				// 2.Lookup()
				DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
				// 3.getConnection()
				conn = ds.getConnection();

			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}

		public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
			conn.close();
			pstmt.close();
			rs.close();
		} // 연결 닫기

		public void close(Connection conn, PreparedStatement pstmt) throws SQLException {
			conn.close();
			pstmt.close();
		} // 연결 닫기

		public ArrayList<Products> selectMemberAll() throws SQLException {
			ArrayList<Products> mem = new ArrayList<Products>(); //new 해줄 때는 무조건 앞에꺼랑 똑같이 () 해줘야됨
			//DTO 의 정보가 너무 여러개니까 arrayList에 넣어서 배열처럼 사용하기 위해 arrayList생성
			String sql = "select * from Members2";
			PreparedStatement pstmt = getConnection().prepareStatement(sql); //conn 을 getConnection()으로 해서
			//연결 가져옴
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				mem.add(new Products(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8))); //arrayList니까 add로 넣어줘야함
			}
			return mem;

		} // 모든 멤버 조회

		public Products selectMember(String userId) throws SQLException {
			String sql = "select * from Members2 where userid=?";
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Products mem = new Products(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
				return mem;
			}
			return null;
		} // 특정 멤버 조회

		/*
		 * public int insertMember(Products dto) throws SQLException {
		 * 
		 * String sql =
		 * "insert into Members(userId,userPwd,userName,phoneNo,address,email) values(?,?,?,?,?,?)"
		 * ; PreparedStatement pstmt = getConnection().prepareStatement(sql); // db에
		 * insert pstmt.setString(1, dto.getUserId()); pstmt.setString(2,
		 * dto.getUserPwd()); pstmt.setString(3, dto.getUserName()); pstmt.setString(4,
		 * dto.getPhoneNo()); pstmt.setString(5, dto.getAddress()); pstmt.setString(6,
		 * dto.getEmail());
		 * 
		 * return pstmt.executeUpdate(); } // 회원 정보 삽입
		 * 
		 * public int updateMember(MemberDTO dto) throws SQLException { String sql =
		 * "update Member2 set userId=? where userId=?"; PreparedStatement pstmt =
		 * getConnection().prepareStatement(sql); pstmt.setString(1, dto.getUserId());
		 * 
		 * return pstmt.executeUpdate(); } // 모든 멤버 수정
		 * 
		 * public int deleteMember(String userId) throws SQLException { String sql =
		 * "delete from Member2 where userId=?"; PreparedStatement pstmt =
		 * getConnection().prepareStatement(sql); pstmt.setString(1, userId);
		 * 
		 * return pstmt.executeUpdate(); //행 수 출력해주는 거 = int형 반환해야될때 할거없음 이거! }
		 */ // 특정 멤버 삭제
}
