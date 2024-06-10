package life.wewu.web.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import life.wewu.web.domain.user.User;
import life.wewu.web.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kotlinx.serialization.Required;


@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String showLoginPage() {
        return "/user/loginView"; // login.jsp �������� ��ȯ
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, HttpSession session) {
        System.out.println("/user/login : POST");

        try {
            // �α��� ���� ȣ��
            User dbUser = userService.login(user);

            if (dbUser != null) {
                session.setAttribute("user", dbUser);
                System.out.println("�α��� ����: " + dbUser.getUserId());
                return "redirect:/index.jsp";
            } else {
                System.out.println("�α��� ����: ��й�ȣ ����ġ �Ǵ� ����� ����");
                return "redirect:/user/loginView.jsp?error=login_failed";
            }
        } catch (IllegalArgumentException e) {
            // ��ȿ���� ���� ����� ���� ���� ó��
            System.out.println("��ȿ���� ���� ����� ����: " + e.getMessage());
            return "redirect:/user/loginView.jsp?error=invalid_user";
        } catch (Exception e) {
            // �Ϲ� ���� ó��
            System.out.println("�α��� �� ���� �߻�: " + e.getMessage());
            return "redirect:/user/loginView.jsp?error=unexpected_error";
        }
    }

    
	@RequestMapping( value="/logout", method=RequestMethod.GET )
	public String logout(HttpSession session ) throws Exception{
		
		System.out.println("/user/logout : POST");
		
		session.invalidate();
		
		return "redirect:/user/loginView.jsp";
	}
	
	@RequestMapping(value = "/verification", method =RequestMethod.GET)
	public String verification() throws Exception {
		
		System.out.println("/user/verification : GET");
		
		return "forward:/user/verification.jsp";
	}
	
	@RequestMapping(value = "/findPwd", method =RequestMethod.GET)
	public String findPwd() throws Exception {
		
		System.out.println("/user/findPwd : GET");
		
		return "forward:/user/findPwd.jsp";
	}
	
	@PostMapping("/user/updatePwd")
    public String updatePwd(@RequestParam("userId") String userId,
                            @RequestParam(value = "newPassword" , required = false) String newPassword,
                            @RequestParam(value = "confirmPassword" , required = false) String confirmPassword,
                            Model model) {
			
        try {
            User user = userService.getUser(userId);
            if (user != null) {
                user.setUserPwd(newPassword);
                userService.updatePwd(user);
                return "redirect:/user/updatePwdView.jsp";  // ���� �������� �����̷�Ʈ
            }
            else {
                model.addAttribute("error", "����ڸ� ã�� �� �����ϴ�.");
                return "redirect:/user/updatePwd?userId=" + userId;
            }
        } catch (Exception e) {
            model.addAttribute("error", "��й�ȣ ���� �� ������ �߻��߽��ϴ�.");
            return "redirect:/user/updatePwd?userId=" + userId;
        }
    }
	
	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception{
	
		System.out.println("/user/addUser : GET");
		
		return "redirect:/user/addUserView.jsp";
	}
	
	@RequestMapping( value="addUser", method=RequestMethod.POST )
	public String addUser( @ModelAttribute("user") User user ) throws Exception {

		System.out.println("/user/addUser : POST");
		//Business Logic
		userService.addUser(user);
		
		return "redirect:/user/loginView.jsp";
	}
	
}

