package life.wewu.web.service.active.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import life.wewu.web.common.Search;
import life.wewu.web.domain.active.Active;
import life.wewu.web.service.active.ActiveService;

@SpringBootTest
public class ActiveServiceTest {

	//�ʵ�
	@Autowired
	@Qualifier("activeServiceImpl")
	private ActiveService activeService;
	
	//�޼ҵ�
	
	//��� �׽�Ʈ
	//@Test
	public void testAddActive() {
		
		System.out.println("addActive");
		
		Date date = new Date(new java.util.Date().getDate());
		
		Active active = Active.builder()
				.groupNo(1)
				.activeName("������ ������ �ݱ�")
				.activeStartDate(date)
				.activeEndDate(date)
				.activeX("37.5679981")
				.activeY("126.9812957")
				.activeStartTime("13:00:00")
				.activeEndTime("15:00:00")
				.activeInfo("������ ������ ���� ��������!")
				.activeLocal("����")
				.stateFlag("N")
				.activeUrl("https://kr.object.ncloudstorage.com/wewu-project-test/hun_balnom.jpg")
				.activeShortUrl("https://me2.do/Fu6RPnkN")
				.build();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("active", active);
		map.put("hash", "������,�÷α�,������ �ݱ�");
		
		activeService.addActive(map);
		
	}
	
	//��ȸ �׽�Ʈ
	//@Test
	public void testGetActive() {
		
		System.out.println("getActive");
		
		Active active = activeService.getActive(3);
		
		System.out.println("getActive Done");
		
		System.out.println(active);
		
	}
	
	//������Ʈ �׽�Ʈ
	//@Test
	public void testUpdateActive() {
		
		System.out.println("updateActive");
		
		System.out.println("������Ʈ ��");
		
		Active active = activeService.getActive(3);
		
		System.out.println(active);
		
		active.setActiveName("������ �÷α�");
		active.setActiveStartTime("10:00:00");
		active.setActiveEndTime("12:00:00");
		active.setActiveInfo("�÷α� �սô�");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("active", active);
		//map.put("hash", "������,�÷α�,������ �ݱ�");
		map.put("hash", "������, �÷α�");
		
		activeService.updateActive(map);
		
		System.out.println();
		System.out.println("update �Ϸ�");
		active = activeService.getActive(3);
		System.out.println(active);
		
	}
	
	//���� �׽�Ʈ
	//@Test
	public void testDeleteActive() {
		
		System.out.println("deleteActive");
		
		System.out.println("delete ��");
		
		Active active = activeService.getActive(3);
		
		System.out.println();
		
		activeService.deleteActive(active);
		
		System.out.println("delete ��");
		
		active = activeService.getActive(3);
		
		System.out.println(active);
		
	}
	
	//��� ��ȸ �׽�Ʈ
	@Test
	public void testGetActiveList() {
		
		System.out.println("getActiveList");
		
		Search search = new Search();
		
		search.setCurrentPage(1);
		//search.setCurrentPage(2);
		
		//search.setSearchKeyword("����");
		
		List<Active> list = activeService.getActiveList(search);
		
		for(Active record : list) {
			
			System.out.println("record : "+record);
			
		}
		
	}
	
}














