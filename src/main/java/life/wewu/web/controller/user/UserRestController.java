package life.wewu.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import life.wewu.web.domain.user.User;
import life.wewu.web.service.user.SmsService;
import life.wewu.web.service.user.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("smsService")
    private SmsService smsService;

    // ������ȣ �߼� �޼ҵ�
    @PostMapping("/send-verification-code")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String phoneNum) {
        userService.sendVerificationCode(phoneNum);
        return ResponseEntity.ok("������ȣ�� ���۵Ǿ����ϴ�.");
    }

    @PostMapping("/verify-code-userId")
    public String verifyCodeUserId(@RequestParam String phoneNum,
                             @RequestParam String code,
                             @RequestParam String userName,
                             Model model) throws Exception {
        System.out.println("verifyCodeUserId ȣ���");
        System.out.println("phoneNum:" + phoneNum);
        System.out.println("code:" + code);
        System.out.println("userName:" + userName);

        boolean isVerified = userService.verifyCode(phoneNum, code);
        System.out.println("isVerified:" + isVerified);
        if (isVerified) {
            // ���� ����: ���̵� �����ִ� �������� ���𷺼�
            User user = userService.findUserId(phoneNum, userName);
            if (user != null) {
                model.addAttribute("userId", user.getUserId());
                return "user/findUserId";
            } else {
                model.addAttribute("phoneNum", phoneNum);
                model.addAttribute("userName", userName);
                model.addAttribute("error", "����ڸ� ã�� �� �����ϴ�.");
                return "user/verification";
            }
        } else {
            // ���� ����
            model.addAttribute("phoneNum", phoneNum);
            model.addAttribute("userName", userName);
            model.addAttribute("error", "���� ����");
            return "user/verification";
        }
    }
    
    @PostMapping("/verify-code-userPwd")
    public String verifyCodeUserPwd(@RequestParam String phoneNum,
                             @RequestParam String code,
                             @RequestParam String userId,
                             Model model) throws Exception {
        System.out.println("verifyCodePwd ȣ���");
        System.out.println("phoneNum:" + phoneNum);
        System.out.println("code:" + code);
        System.out.println("userId:" + userId);

        boolean isVerified = userService.verifyCode(phoneNum, code);
        System.out.println("isVerified:" + isVerified);
        if (isVerified) {
            // ���� ����: ���̵� �����ִ� �������� ���𷺼�
            User user = userService.findUserPwd(phoneNum, userId);
            if (user != null) {
                model.addAttribute("userId", user.getUserId());
                return "user/updatePwd";
            } else {
                model.addAttribute("phoneNum", phoneNum);
                model.addAttribute("userId", userId);
                model.addAttribute("error", "����ڸ� ã�� �� �����ϴ�.");
                return "user/findPwd";
            }
        } else {
            // ���� ����
            model.addAttribute("phoneNum", phoneNum);
            model.addAttribute("userId", userId);
            model.addAttribute("error", "���� ����");
            return "user/findPwd";
        }
    }
    
    
    // �׽�Ʈ�� ������ȣ �߼� �޼ҵ�
    @PostMapping("/send-test-verification-code")
    public ResponseEntity<String> sendTestVerificationCode(@RequestParam String phoneNum) {
        userService.sendTestVerificationCode(phoneNum);
        return ResponseEntity.ok("�׽�Ʈ�� ������ȣ�� ���۵Ǿ����ϴ�.");
    }
    
}
