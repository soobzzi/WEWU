//24.06.03 �ۼ�
package life.wewu.web.domain.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String userId;				//����� ���̵�
    private String email;				//����� �̸���
    private String phoneNum;		//����� ��ȭ��ȣ
    private String residentNum;	//����� �ֹι�ȣ
    private String nickname;			//����� �г��� (unique)
    private String userPwd;			//����� ��й�ȣ
    private String addr;				//����� �ּ�
    private String getAddr;			//����� ���ּ�
    private String gender;			//����� ����
    private Integer currentPoint;	//����� ��������Ʈ
    private String role;					// 1.admin, 2.user, 3.leader 4.delete
    private String userName;		//����� �̸�
}

