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

    @RequestMapping("/home")
    public String homePage(){
        return "homePage";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @Controller
    @RequestMapping("/profile")
    public class ProfileController{

        @RequestMapping("")
        public String profile(){
            return "profile";
        }

        @RequestMapping("/calendar")
            public String calendar(){
                return "calendar";
            }
    }
    
    @Controller
    @RequestMapping("/storage")
    public class StorageController{

        @RequestMapping("/equipment")
        public String equipment(){
            return "equipment";
        }    

        @RequestMapping("/medicine")
        public String medicine(){
            return "medicine";  
        }   
    }

    @RequestMapping("/report")
    public String report(){
        return "report";
    }


    @RequestMapping("/doctorlist")
    public String doctorList(){
        return "doctorList";
    }

    @RequestMapping("/patientlist")
    public String patientList(){
        return "patientList";
    }

    @RequestMapping("/patientlist/patientinfor")
    public String patientInformation(){
        return "patientInformation";
    }

    @RequestMapping("/patientlist/patientinfor/treatmenthistory")
    public String treatmentHistory(){
        return "treatmentHistory";
    }

    @RequestMapping("/patientlist/patientinfor/treatmenthistory/prescription")
    public String prescription(){
        return "prescription";
    }

    @RequestMapping("/help")
    public String helpAndSupport(){
        return "helpAndSupport";
    }

    @RequestMapping("/setting")
    public String setting(){
        return "setting";
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
