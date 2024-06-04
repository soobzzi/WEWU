package life.wewu.web.service.active;

import java.util.List;
import java.util.Map;

import io.micrometer.core.instrument.search.Search;
import life.wewu.web.domain.active.Active;
import life.wewu.web.domain.active.ActiveHash;

public interface ActiveService {

	public void addActive(Map<String, Object> map); //Ȱ�� ���
	
	public Active getActive(int activeNo); //Ȱ�� �� ��ȸ
	
	public void updateActive(Map<String, Object> map); //Ȱ�� ����
	
	public void deleteActive(Active active); //Ȱ�� ����
	
	public List<Active> getActiveList(Search search); //Ȱ�� ���
	
}
