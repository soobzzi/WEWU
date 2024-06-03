package life.wewu.web.domain.active;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Active {
	
	//�ʵ�
	private int activeNo;//active�� ��ȣ
	private int groupNo;//��� �׷��� Ȱ������ group ��ȣ
	private String activeName;//Ȱ�� �̸�
	private String leaderNick;//�׷����� �г���
	private Date activeStartDate;//Ȱ�� ������
	private Date activeEndDate;//Ȱ�� ������
	private String activeX;//����
	private String activeY;//�浵
	private String activeStartTime;//Ȱ�� ���� �ð�
	private String activeEndTime;//Ȱ�� ���� �ð�
	private String activeInfo;//Ȱ�� �ڸ�Ʈ
	private String activeLocal;//Ȱ�� ����
	private Date activeRegDate;//Ȱ���� ��ϵ� ��¥
	private String stateFlag;//Ȱ�� ���� -> Y:Ȱ���� N:��� E:���� D:������
	private String activeUrl;//��Ŀ ������ ������ url
	private String activeShortUrl;//url�� short ����
	private List<ActiveHash> hashList; //�ؽ� �±� ���� ����
	
}
