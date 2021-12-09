package com.conan.vo;

public class BoardVO {
	private int boardNo=1; //1번부터 1씩 증가, 기본키
	private String userId; //작성자는 아이디로
	private String title; //게시글 제목
	private String content; //게시글 내용
	private String regDate; //등록일, 시스템 시간
	private int hit=0; //조회수
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}
	public BoardVO(int boardNo, String userId, String title, String content, String regDate, int hit) {
		this.boardNo=boardNo;
		this.userId=userId;
		this.title=title;
		this.content=content;
		this.regDate=regDate;
		this.hit=hit;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
}
