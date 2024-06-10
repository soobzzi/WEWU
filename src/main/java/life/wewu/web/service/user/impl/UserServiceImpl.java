//24.06.03 �ۼ�

package life.wewu.web.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import life.wewu.web.common.Search;
import life.wewu.web.domain.user.User;
import life.wewu.web.service.user.SmsService;
import life.wewu.web.service.user.UserDao;
import life.wewu.web.service.user.UserService;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//==> ȸ������ ���� ����
@Service("userServiceImpl")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	//Field
	
    @Qualifier("userDao")
	private final UserDao userDao;
	
	@Qualifier("smsService")
	private final SmsService smsService;
    
	private Map<String, String> verificationCodes = new HashMap<>();
	
	///Method
    public void addUser(User user) throws Exception{
        userDao.addUser(user);
    }

    public void updateUser(User user) throws Exception{
        userDao.updateUser(user);
    }

    public void deleteUser(String userId) throws Exception{
        userDao.deleteUser(userId);
    }

    public List<User> getUserList(Search search) throws Exception {
        return userDao.getUserList(search);
    }

    public User getUser(String userId) throws Exception{
        return userDao.getUser(userId);
    }

    // �α��� ���� �߰�
    public User login(User user) throws Exception {
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("��ȿ���� ���� ����� �����Դϴ�.");
        }
        System.out.println("�α��� �õ� ����� ID: " + user.getUserId());
        User dbUser = userDao.getUser(user.getUserId());
        if (dbUser != null) {
            // Check if the user's role is 4
        	System.out.println("DB ����� ID: " + dbUser.getUserId());
            System.out.println("DB ����� Role: " + dbUser.getRole());
            if ("4".equals(dbUser.getRole())) {
                throw new Exception("����ó�� �Ǿ����ϴ�.");
            }

            // Check if the password matches
            if (user.getUserPwd() != null && user.getUserPwd().equals(dbUser.getUserPwd())) {
            	System.out.println("��й�ȣ ��ġ");
            	return dbUser;
            }else {
                System.out.println("��й�ȣ ����ġ");
            }
        }else {
            System.out.println("����ڸ� ã�� �� ����");
        }
        return null;
    }

    public void updateAdmin(User user) throws Exception{
        userDao.updateAdmin(user);
    }

    //����� ���̵� �ߺ�üũ
	public boolean checkUserId(String userId) throws Exception {
	    return userDao.checkUserId(userId);
	}
    
  //����� �г��� �ߺ�üũ
    public boolean checkNickName(String nickName) throws Exception {
    	return userDao.checkNickName(nickName);
    }
    
    //����� ���̵� ã��
    public User findUserId(String phoneNum, String userName) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("phoneNum", phoneNum);
    	map.put("userName", userName);
    	
        User user = userDao.findUserId(map);
        if (user != null) {
            System.out.println("User found:" + user.getUserId());
        } else {
            System.out.println("User not found with phoneNum:" + phoneNum + ", userName:" + userName);
        }
        return user;
    }
    
    //������ȣ ��ȭ��ȣ ����
    public String sendVerificationCode(String phoneNum) {
        String verificationCode = smsService.sendVerificationCode(phoneNum);
        verificationCodes.put(phoneNum, verificationCode);
        return verificationCode;
    }
    
    //������ȣ, ��ȭ��ȣ + �˸��� ������ȣ�� ����
    public boolean verifyCode(String phoneNum, String code) {
        String correctCode = verificationCodes.get(phoneNum);
        System.out.println("verifyCode - phoneNum:" + phoneNum + ", inputCode:" + code + ", correctCode:" + correctCode);
        return correctCode != null && correctCode.equals(code);
    }
    
    //���� �۽Ž� �ڵ� �����Ǵµ� verificationCodes�� ������ȣ �����Ѱ� �������� �޼ҵ�
    public Map<String, String> getVerificationCodes() {
        return verificationCodes;
    }
    
    //������ȣ testCodeȭ
    public String sendTestVerificationCode(String phoneNum) {
        String verificationCode = smsService.generateTestVerificationCode();
        verificationCodes.put(phoneNum, verificationCode);
        return verificationCode;
    }
    
    //����� ��й�ȣ ã��
	public User findUserPwd(String phoneNum, String userId) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("phoneNum", phoneNum);
    	map.put("userId", userId);
    	
        User user = userDao.findUserPwd(map);
        if (user != null) {
            System.out.println("User found:" + user.getUserId());
        } else {
            System.out.println("User not found with phoneNum:" + phoneNum + ", userId:" + userId);
        }
        return user;
    }
	
	//����� ��й�ȣ ����
	public void updatePwd(User user) throws Exception {
        userDao.updatePwd(user);
	}
}

