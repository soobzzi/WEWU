package life.wewu.web.service.active;

import java.util.List;
import java.util.Map;

import life.wewu.web.domain.active.Active;
import life.wewu.web.domain.active.ActiveHash;

public interface ActiveService {

	public void addActive(Map<String, Object> map); //Ȱ�� ���
	
	public Active getActive(int activeNo); //Ȱ�� �� ��ȸ
	
	public void updateActive(Active active); //Ȱ�� ����
	
	public void deleteActive(String stateFlag); //Ȱ�� ����
	
	public List<Active> getActiveList(); //Ȱ�� ���
	
}
