//24.06.03 �ۼ�

package life.wewu.web.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import life.wewu.web.domain.user.User;
import life.wewu.web.service.user.UserDao;
import life.wewu.web.service.user.UserService;

import java.util.List;

//==> ȸ������ ���� ����
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	//Field
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;
    
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	///Constructor
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}
	
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

    public List<User> getUserList() throws Exception{
        return userDao.getUserList();
    }

    public User getUser(String userId) throws Exception{
        return userDao.getUser(userId);
    }

    // �α��� ���� �߰�
    public User login(User user) throws Exception {
        User dbUser = userDao.getUser(user.getUserId());
        if (dbUser != null) {
            // Check if the user's role is 4
            if (dbUser.getRole() == ("4")) {
                throw new Exception("����ó�� �Ǿ����ϴ�.");
            }

            // Check if the password matches
            if (user.getUserPwd().equals(dbUser.getUserPwd())) {
                return dbUser;
            }
        }
        return null;
    }


    public void updateAdmin(User user) throws Exception{
        userDao.updateAdmin(user);
    }

    public String findUserId(String phonNum) throws Exception{
        return userDao.findUserId(phonNum);
    }

    public String findUserPwd(String userId) throws Exception{
        return userDao.findUserPwd(userId);
    }

    //����� ���̵� �ߺ�üũ
    public boolean checkUserId(String userId) throws Exception {
        return !userDao.checkUserId(userId);
    }
    
  //����� �г��� �ߺ�üũ
    public boolean checkNickName(String nickName) throws Exception {
    	return !userDao.checkNickName(nickName);
    }
    
    public User authUser(String userId, String password) throws Exception{
        return userDao.authUser(userId, password);
    }
}

