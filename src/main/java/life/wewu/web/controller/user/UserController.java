package life.wewu.web.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import life.wewu.web.domain.user.User;
import life.wewu.web.service.user.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="login", method=RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception {
        System.out.println("/user/login : POST");
        // �α��� ���� ȣ��
        User dbUser = userService.login(user);
        
        if (dbUser != null) {
            session.setAttribute("user", dbUser);
            return "redirect:/index.jsp";
        }
        
        // �α��� ���� �� ó�� (��: �α��� �������� �����̷�Ʈ)
        return "redirect:/login.jsp";
    }
    
	@RequestMapping( value="logout", method=RequestMethod.GET )
	public String logout(HttpSession session ) throws Exception{
		
		System.out.println("/user/logout : POST");
		
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
}

