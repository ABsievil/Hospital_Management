package hcmut.hospitalmanagement.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "username", required = false, defaultValue = "") String username, 
                        @RequestParam(name = "password", required = false, defaultValue = "") String password, Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "hello";
    }
    @RestController
    class LoginController {  //demo

        @PostMapping("/login")
        public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
            Map<String, Object> response = new HashMap<>();

            // Thực hiện kiểm tra đăng nhập ở đây
            String username = credentials.get("username");
            String password = credentials.get("password");

            if ("your_username".equals(username) && "your_password".equals(password)) {
                response.put("success", true);
            } else {
                response.put("success", false);
            }

            return response;
        }
    }
}
