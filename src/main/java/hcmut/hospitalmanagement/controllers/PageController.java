package hcmut.hospitalmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.repositories.PatientRepository;

@Controller
public class PageController {
    @RequestMapping(value = {"/", "index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @Controller
    public class LoginController {

        @GetMapping("/login")
        public String login() {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/index";
    }

    // @Controller
    // public class LoginController {

    //     @Autowired
    //     private PatientRepository patientRepository;

    //     @GetMapping("/login")
    //     public String showLoginForm() {
    //         return "login";
    //     }

    //     @PostMapping("/login")
    //     public String login(String username, String password) {
    //         Patient patient = patientRepository.findByUsernameAndPassword(username, password);

    //         if (patient != null) {
    //             // Xác thực thành công, chuyển hướng đến trang chủ
    //             return "hello";
    //         } else {
    //             // Xác thực thất bại, hiển thị thông báo lỗi
    //             return "login false!";
    //         }
    //     }

    // }
    @RequestMapping("/homePage")
    public String homePage(){
        return "homePage";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "username", required = false, defaultValue = "") String username, 
                        @RequestParam(name = "password", required = false, defaultValue = "") String password, Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "hello";
    }

    @RequestMapping("/helloUser")
    public String helloUser(@RequestParam(name = "username", required = false, defaultValue = "") String username, 
                        @RequestParam(name = "password", required = false, defaultValue = "") String password, Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "helloUser";
    }

    @RequestMapping("/helloAdmin")
    public String helloAdmin(@RequestParam(name = "username", required = false, defaultValue = "") String username, 
                        @RequestParam(name = "password", required = false, defaultValue = "") String password, Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "helloAdmin";
    }
}
