/*
 *	�ۼ���: ������
 *	�ۼ���: 2024-06-04 
 */
package life.wewu.web.domain.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bookmark {
	
	//�ʵ�
	private int bookmarkNo;			//pk �ϸ�ũ ��ȣ		//
	private int boardNo;
	private String nickName;
	
}
