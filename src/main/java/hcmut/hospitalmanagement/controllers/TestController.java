package hcmut.hospitalmanagement.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello World!";
    }

    @RestController
    class LoginController {

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
