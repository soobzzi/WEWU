//24.06.03 �ۼ�

package life.wewu.web.service.user;

import java.util.List;

import life.wewu.web.domain.user.User;


public interface UserService {
	
	//ȸ������
	public void addUser(User user);
    
	//����������
	public void updateUser(User user);
    
	//������ ����� ����
	public  void deleteUser(String userId);
    
	//�����ڰ� ���� ��� Ȯ��
	public List<User> getUserList();
    
	//�����ڰ� ���� �󼼺���
	public User getUser(String userId);
    
	//sms����
	public User authUser(String userId, String password);
    
	//�α���
	public User login(User user);
    
	//�����ڰ� ����� �󼼺���� ����
	public void updateAdmin(User user);
    
	//����� ���̵� ã��
	public String findUserId(String email);
    
	//����� ��й�ȣ ã��
	public String findUserPwd(String userId);
    
	//����� Ż���ϱ�
	public void quitUser(String userId);
    
	//ȸ�����Խ� ����� ���̵� �ߺ�üũ
	public boolean checkUserId(String userId);
    
	//ȸ�����Խ� ����� �г��� �ߺ�üũ
	public boolean checkNickName(String nickname);
    
	//ȸ�����Խ� ����� ���̵� ���Ŀ� �´��� üũ
	public boolean checkErrorUserId(String userId);
    
	//ȸ�����Խ� ����� �г��� ���Ŀ� �´��� üũ
	public boolean checkErrorNickName(String nickname);
    
	//ȸ�����Խ� ����� ��й�ȣ�� ����,����, Ư����ȣ�� ���ԵǾ��ִ��� üũ
	public boolean checkSecUserPwd(String userId, String securityPwd);
    
	//ȸ�����Խ� ����ڰ� ��й�ȣĭ 2���� �Ȱ��� �������� üũ
	public boolean checkSameUserPwd(String userId, String password);

}
