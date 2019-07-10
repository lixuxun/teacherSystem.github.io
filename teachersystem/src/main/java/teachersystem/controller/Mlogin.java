package teachersystem.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class Mlogin {
    @PostMapping("/teacher/login")//可以在config注册
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, HttpSession session, HttpServletRequest request) {
        if (!username.isEmpty() && password.equals("123456")) {
            session.setAttribute("loginUser", username);
            return "redirect:/menu.html";
        } else {
            request.setAttribute("msg", "用户密码错误");
            //  return "/index.html"; //使用post, Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported]
            // return  "redirect:/index.html";
            //  return  "forward:/index.html";
            //使用GET可以
            return "login";
        }
    }
}
