package com.conan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.conan.vo.BoardVO;

public class BoardDAO {
	InitialContext ic;
	Connection conn = null;
	PreparedStatement pstmt =null;
	// singleton
	private static BoardDAO dao = new BoardDAO(); // static타입이라는 것을 기억해.
	// dao 이름으로 이제부터 어디서든지 MemberDAO를 쓸수 있게 하겟다는거야

	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
		return dao; // 자기자신을 불러오는 메소드
	}

	public Connection getConnection() throws SQLException {// DB 연결 객체 반환
		// 1.JNDL 서버 객체 생성 > 가장 최신의 connection 하는방법..****
		
		try {
			ic = new InitialContext();
			// 2.Lookup()
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			// 3.getConnection()
			conn = ds.getConnection();

		} catch (NamingException e) {
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

	public ArrayList<BoardVO> selectBoardAll() throws SQLException {// 게시글 전체목록 반환
		ArrayList<BoardVO> bd = new ArrayList<BoardVO>(); // new 해줄 때는 무조건 앞에꺼랑 똑같이 () 해줘야됨
		// DTO 의 정보가 너무 여러개니까 arrayList에 넣어서 배열처럼 사용하기 위해 arrayList생성
		String sql = "select * from Board";
		conn = getConnection(); //connection은 pstmt.getConnection 절대 하면안대
		pstmt = conn.prepareStatement(sql); // conn 을 getConnection()으로 해서
		
		// 연결 가져옴
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			bd.add(new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getInt(6))); 
			// arrayList니까
			// add로
			// 넣어줘야함
		}
		
		close(conn, pstmt, rs);
		
		return bd;

	}

	public BoardVO selectBoard(Integer boardNo) throws SQLException {// 특정 게시글 정보 반환
		String sql = "select * from Board where boardNo=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			BoardVO bd = new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getInt(6));
			return bd;
		}
		return null;
	}

	public int insertBoard(BoardVO vo) throws SQLException { // 게시글 저장
		String sql = "insert into Board(userId,title,content,hit) values(?,?,?,?)";
		PreparedStatement pstmt = getConnection().prepareStatement(sql); // db에
		pstmt.setString(1, vo.getUserId());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		pstmt.setInt(4, vo.getHit());

		return pstmt.executeUpdate();
	}

	public int updateBoard(BoardVO vo) throws SQLException { 
		//게시글 번호에 해당하는 게시글 수정
		String sql = "update Board set title=?, set content=? where boardNo=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setInt(2, vo.getBoardNo());

		return pstmt.executeUpdate();
	} 

	public int deleteBoard(Integer boardNo) throws SQLException {
		//게시글 번호에 해당하는 게시글 삭제
		String sql = "delete from Board where boardNo=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setInt(1, boardNo);

		return pstmt.executeUpdate(); // 행 수 출력해주는 거 = int형 반환해야될때 할거없음 이거! }

	}
	public int getListCount() throws SQLException {
		//게시글 개수 반환
		String sql="select count(*) from Board";
		PreparedStatement pstmt =getConnection().prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();//rs.next를 안하면 출력이안되지
		return rs.getInt(1);  //count로 센 거 출력
	}
	public int increaseHit(Integer boardNo) { //인자가 한개면 인자 하나로만 setting 가능
		//조회수 증가
		String sql="update Board set hit=hit+1 where boardNo=?";
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1,boardNo);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardNo;
	}
}
