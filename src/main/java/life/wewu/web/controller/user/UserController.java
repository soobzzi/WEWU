package life.wewu.web.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import life.wewu.web.common.Page;
import life.wewu.web.common.Search;
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
	
	@RequestMapping(value = "/findUserId", method =RequestMethod.GET)
	public String verification() throws Exception {
		
		System.out.println("/user/findUserId : GET");
		
		return "forward:/user/findUserIdView.jsp";
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
                return "redirect:/user/updatePwd.jsp";  // ���� �������� �����̷�Ʈ
            }
            else {
                model.addAttribute("error", "����ڸ� ã�� �� �����ϴ�.");
                return "redirect:/user/updatePwdView?userId=" + userId;
            }
        } catch (Exception e) {
            model.addAttribute("error", "��й�ȣ ���� �� ������ �߻��߽��ϴ�.");
            return "redirect:/user/updatePwdView?userId=" + userId;
        }
    }
	
	@RequestMapping( value="/addUser", method=RequestMethod.GET )
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
	
	//��������ȸ�������� �̵�
	 @GetMapping("/myInfo")
	    public String myInfo(HttpSession session, Model model) throws Exception {
		 
	        System.out.println("/user/myInfo : GET");

	        User sessionUser = (User) session.getAttribute("user");
	        if (sessionUser == null) {
	            return "redirect:/user/loginView.jsp"; // ���ǿ� ���� ������ ������ �α��� �������� ���𷺼�
	        }
	        
	        String userId = sessionUser.getUserId();
	        User user = userService.getUser(userId);
	        model.addAttribute("user", user);

	        return "forward:/user/myInfo.jsp"; // �� �̸� ��ȯ
	    }
	 
	 //listUser���� ���������� ����
		@RequestMapping( value="/getUser", method=RequestMethod.GET )
		public String getUser( @RequestParam("userId") String userId , Model model ) throws Exception {
			
			System.out.println("/user/myInfo : GET");
			//Business Logic
			User user = userService.getUser(userId);
			// Model �� View ����
			model.addAttribute("user", user);
			
			return "forward:/user/myInfo.jsp";
		}
	//������ ��ȸ���������� ����Է��ϸ� ������������������ �̵�
	 @PostMapping("/checkPassword")
	    public String checkPassword(@RequestParam("userId") String userId, 
					                                @RequestParam(value="password") String password, 
					                                Model model) throws Exception {
		 
		 System.out.println("/user/checkPassword : POST");
		 
	        User user = userService.getUser(userId);
	        if (user != null && user.getUserPwd().equals(password)) {
	        	 model.addAttribute("user", user);
	        	 //return "forward:/user/myInfo.jsp";
	            return "forward:/user/myInfoView.jsp?userId=" + userId;
	        } else {
	            model.addAttribute("error", "��ȿ���� ���� ��й�ȣ �Դϴ�. �ٽ� �Է��ϼ���.");
	            return "forward:/user/myInfo.jsp";
	        }
	    }
	//myInfoView�� �̵�
	@RequestMapping( value="updateUser", method=RequestMethod.GET )
	public String updateUser( @RequestParam("userId") String userId , Model model ) throws Exception{

		System.out.println("/user/updateUser : GET");
		//Business Logic
		User user = userService.getUser(userId);
		// Model �� View ����
		model.addAttribute("user", user);
		
		return "forward:/user/myInfoView.jsp";
	}

	@RequestMapping( value="updateUser", method=RequestMethod.POST )
	public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session) throws Exception{

		System.out.println("/user/updateUser : POST");
		//Business Logic
		userService.updateUser(user);
		
		String sessionId=((User)session.getAttribute("user")).getUserId();
		if(sessionId.equals(user.getUserId())){
			session.setAttribute("user", user);
		}
		
		return "redirect:/user/myInfo?userId="+user.getUserId();
	}
	
	@RequestMapping(value = "userQuit", method = RequestMethod.POST)
	public void userQuit(@ModelAttribute("user") User user, HttpServletResponse response) throws Exception {
	    System.out.println("/user/userQuit : POST");

	    userService.deleteUser(user.getUserId());

	    // ����ڰ� Ż�� �� �����̷�Ʈ
	    response.sendRedirect("/user/userQuit.jsp");
	}

	@RequestMapping( value="listUser" )
	public String listUser( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/user/listUser : GET / POST");
		int pageSize =3;
		int pageUnit=5;
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		Map<String , Object> map=userService.getUserList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/user/listUser.jsp";
	}
	
	
    @PostMapping("/updateAdmin")
    public String updateUser(@ModelAttribute User user) throws Exception {
       
		System.out.println("/user/updateAdmin : POST");

    	userService.updateUser(user);
    	
        return "redirect:/user/myInfo?userId=" + user.getUserId();
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") String userId) throws Exception {
        
		System.out.println("/user/deleteUser : POST");
    	
    	userService.deleteUser(userId);
        
        return "redirect:/user/listUser";
    }
}

