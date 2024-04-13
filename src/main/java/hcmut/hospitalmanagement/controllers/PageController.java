package hcmut.hospitalmanagement.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import hcmut.hospitalmanagement.models.Employee;
import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.models.Role;
import hcmut.hospitalmanagement.repositories.EmployeeRepository;
import hcmut.hospitalmanagement.repositories.PatientRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    EmployeeRepository employeeRepository;

    Employee addEmployeeToModel(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            String username = principal.getUsername();
            Employee employee = employeeRepository.findByUsername(username);
            model.addAttribute("employee", employee);
            return employee;
        }
        return null;
    }

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
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/index";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            String username = principal.getUsername();
            Employee employee = employeeRepository.findByUsername(username);
            String message = "Hi " + employee.getInformation().getFirstName() //
                    + ", You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }

    @RequestMapping("/home")
    public String homePage(Model model){
        Employee emp = addEmployeeToModel(model);
        if(emp.getRole() == Role.ADMIN) return "homeAdmin"; 
        return "homePage";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @Controller
    @RequestMapping("/add")
    public class addUser{

        @GetMapping("/patient")
        public String renderPatientPage(){
            return "addPatient";
        }

        @GetMapping("/employee")
        public String renderEmployeePage(){
            return "addEmployee";
        }

        @PostMapping("/patient")
        public void postPatient(){

        }

        @PostMapping("/employee")
        public void postEmployee(){
            
        }
    }

    @Controller
    @RequestMapping("/profile")
    public class ProfileController{

        @RequestMapping("")
        public String profile(Model model){
            Employee emp = addEmployeeToModel(model);
            String nameuser = emp.getInformation().getLastName() + " " + emp.getInformation().getFirstName();
            model.addAttribute("nameuser", nameuser);
            return "profile";
        }

        @RequestMapping("/calendar")
        public String calendar(Model model){
            addEmployeeToModel(model);
            return "calendar";
        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public String editInfor(){
            return "editInfor";
        }

        @RequestMapping(value = "/edit", method =  RequestMethod.POST)
        public void postInfor(){
            
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

    @Controller
    @RequestMapping("/user/infor")
    public class userInfor{
        @RequestMapping(value = "", method = RequestMethod.GET)
        public String renderPage(Model model){
            addEmployeeToModel(model);
            return "employeeInfor";
        }

        @RequestMapping(value = "", method = RequestMethod.POST)
        public void getInfor(){
            
        }
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
