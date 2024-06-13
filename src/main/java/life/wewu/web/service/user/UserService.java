//24.06.03 �ۼ�

package life.wewu.web.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import life.wewu.web.common.Search;
import life.wewu.web.domain.user.User;


public interface UserService {
	
	//ȸ������
	public void addUser(User user) throws Exception;
    
	//����������
	public void updateUser(User user) throws Exception;
    
	//������ ����� ����
    public void deleteUser(String userId) throws Exception;
    
	//�����ڰ� ���� ��� Ȯ��
	public Map<String , Object> getUserList(Search search) throws Exception;
	
	//�����ڰ� ���� �󼼺���
	public User getUser(String userId) throws Exception;
    
	//�α���
	public User login(User user) throws Exception;
    
	//�����ڰ� ����� �󼼺���� ����
	public void updateAdmin(User user) throws Exception;
    
	//ȸ�����Խ� ����� ���̵� �ߺ�üũ
	public boolean checkUserId(String userId) throws Exception;
    
	//ȸ�����Խ� ����� �г��� �ߺ�üũ
	public boolean checkNickName(String nickname) throws Exception;
    
	
	/////////////////////////////////////ã�� ����///////////////////////////////////
	
//	ResponseEntity<String> sendSms(String to);
	
	public String sendVerificationCode(String phoneNum);
	
	public boolean verifyCode(String phoneNum, String code);
	
	public User findUserId(String phoneNum, String userName) throws Exception;
	
	public Map<String, String> getVerificationCodes();
	
    public String sendTestVerificationCode(String phoneNum);
    
    public User findUserPwd(String phoneNum, String userId) throws Exception;

	public void updatePwd(User user) throws Exception;
}
