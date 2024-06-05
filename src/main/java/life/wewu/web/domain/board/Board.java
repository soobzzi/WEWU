/*
 *	�ۼ���: ������
 *	�ۼ���: 2024-06-04 
 */
package life.wewu.web.domain.board;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
	
	//�ʵ�
	private int boardNo; 			//PK �Խ��� �ĺ���ȣ
	private int boardType; 			//FK �Խ��� ����
	private int memberNo; 			//FK ���� ��ȣ
	private String nickName;		//FK ����� �г���
	private String title; 			//�Խ��� ����
//	private List<BoardFile> fileNo ;//�Խ��� ÷�� ���ϸ�//
	private int userGroupNo;
	private String contents;		//�Խ��� ����
	private Date regDate;			//��� ��¥
	private BoardFile thumnail;		//����� �̹��� �̸�//
	private int bookmarkCnt;		//���ã�� ��
	private int views;				//��ȸ��
	private int commentCnt;			//��� ��
	private List<Comment> commentNo;//��� //
	
}
