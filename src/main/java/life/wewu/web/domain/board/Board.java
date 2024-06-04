/*
 *	작성자: 최지원
 *	작성일: 2024-06-04 
 */
package life.wewu.web.domain.board;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
	
	//필드
	private int boardNo; 			//PK 게시판 식별번호
	private int boardType; 			//FK 게시판 종류
	private int memberNo; 			//FK 모임 번호
	private String nickName;		//FK 사용자 닉네임
	private String title; 			//게시판 제목
	private List<BoardFile> fileNo ;//게시판 첨부 파일명
	private String contents;		//게시판 내용
	private Date regDate;			//등록 날짜
	private BoardFile thumnail;		//썸네일 이미지 이름
	private int bookmarkCnt;		//즐겨찾기 수
	private boolean bookmarkFlag;	//즐겨찾기 여부
	private int views;				//조회수
	private int commentCnt;			//댓글 수
	private List<Comment> commentNo;//댓글
	
}
