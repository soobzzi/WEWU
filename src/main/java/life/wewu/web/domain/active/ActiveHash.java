package life.wewu.web.domain.active;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActiveHash {

	//�ʵ�
	private int hashNo;//�ؽ��±� ��ȣ
	private int activeNo;//��� Ȱ���� �ؽ��±����� Ȱ�� ��ȣ
	private String hashName;//�ؽ��±� �̸�
	
}
