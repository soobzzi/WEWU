package life.wewu.web.service.active.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import life.wewu.web.common.Search;
import life.wewu.web.domain.active.Active;
import life.wewu.web.domain.active.ActiveHash;
import life.wewu.web.repository.S3Repository;
import life.wewu.web.service.active.ActiveDao;
import life.wewu.web.service.active.ActiveService;

@Service("activeServiceImpl")
public class ActiveServiceImpl implements ActiveService {
	
	//�ʵ�
	@Autowired
	@Qualifier("activeDao")
	ActiveDao activeDao; //activeDao injection
	
	@Autowired
	S3Repository s3;

	//�޼ҵ�
	//Ȱ���� �ؽ��±� ��� ���
	@Override
	@Transactional
	public void addActive(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Active active = (Active)map.get("active");
		
		//System.out.println(active);
		
		//���� ���ε�
		MultipartFile file = (MultipartFile)map.get("file");
		
		active.setActiveUrl(s3.uplodaFile(file));
		
		activeDao.addActive(active);
		
		String[] hashList = map.get("hash").toString().split(",");
		
		for(int i = 0; i < hashList.length; i++) {
			ActiveHash activeHash = ActiveHash.builder()
					.activeNo(active.getActiveNo())
					.hashName(hashList[i])
					.build();
			
			activeDao.addActiveHash(activeHash);
		}
		
	}

	//Ȱ���� �ؽ��±� ��������
	@Override
	public Active getActive(int activeNo) {
		// TODO Auto-generated method stub
		
		//System.out.println(activeNo);
		
		Active active = activeDao.getActive(activeNo);
		
		if(active == null) {
			return null;
		}
		
		active.setHashList(activeDao.getActiveHashList(activeNo));
		
		return active;
	}

	@Override
	@Transactional
	public void updateActive(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		Active active = (Active)map.get("active");
		
		activeDao.updateActive(active);
		
		activeDao.deleteActiveHash(active.getActiveNo());
		
		String[] hashList = map.get("hash").toString().split(",");
		
		for(int i = 0; i < hashList.length; i++) {
			ActiveHash activeHash = ActiveHash.builder()
					.activeNo(active.getActiveNo())
					.hashName(hashList[i])
					.build();
			
			activeDao.addActiveHash(activeHash);
		}
		
	}

	@Override
	@Transactional
	public void deleteActive(Active active) {
		// TODO Auto-generated method stub
		active.setStateFlag("D");
		
		activeDao.updateActive(active);
		
	}

	@Override
	public List<Active> getActiveList(Search search) {
		// TODO Auto-generated method stub
		List<Active> activeList = activeDao.getActiveList(search);
		
		return activeList;
	}

	
	
}
