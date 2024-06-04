//24.06.03 �ۼ�

package life.wewu.web.service.user;

import java.util.List;

import life.wewu.web.domain.user.User;


public interface UserService {
	
	//ȸ������
	public void addUser(User user) throws Exception;
    
	//����������
	public void updateUser(User user) throws Exception;
    
	//������ ����� ����
	public  void deleteUser(String userId) throws Exception;
    
	//�����ڰ� ���� ��� Ȯ��
	public List<User> getUserList() throws Exception;
    
	//�����ڰ� ���� �󼼺���
	public User getUser(String userId) throws Exception;
    
	//�α���
	public User login(User user) throws Exception;
    
	//�����ڰ� ����� �󼼺���� ����
	public void updateAdmin(User user) throws Exception;
    
	//����� ���̵� ã��
	public String findUserId(String phoneNum) throws Exception;
    
	//����� ��й�ȣ ã��
	public String findUserPwd(String userId) throws Exception;
    
	//ȸ�����Խ� ����� ���̵� �ߺ�üũ
	public boolean checkUserId(String userId) throws Exception;
    
	//ȸ�����Խ� ����� �г��� �ߺ�üũ
	public boolean checkNickName(String nickname) throws Exception;
    
	//sms����
	public User authUser(String userId, String password) throws Exception;

}
