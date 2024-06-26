package hcmut.hospitalmanagement.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    PatientRepository patientRepository;

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

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public String handleException() {
            return "403Page";
        }
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
        public String renderPatientPage(Model model){
            addEmployeeToModel(model);
            return "addPatient";
        }

        @GetMapping("/employee")
        public String renderEmployeePage(Model model){
            addEmployeeToModel(model);
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
        public String editInfor(Model model){
            addEmployeeToModel(model);
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
        public String equipment(Model model){
            addEmployeeToModel(model);
            return "equipment";
        }    

        @RequestMapping("/medicine")
        public String medicine(Model model){
            addEmployeeToModel(model);
            return "medicine";  
        }   
    }

    @RequestMapping("/report")
    public String report(Model model){
        addEmployeeToModel(model);
        return "report";
    }


    @RequestMapping("/doctorlist")
    public String doctorList(Model model){
        addEmployeeToModel(model);
        return "doctorList";
    }

    @RequestMapping("/nurselist")
    public String nurseList(Model model){
        addEmployeeToModel(model);
        return "nurseList";
    }

    @RequestMapping("/stafflist")
    public String staffList(Model model){
        addEmployeeToModel(model);
        return "staffList";
    }

    @Controller
    @RequestMapping("/user/infor")
    public class userInfor{
        @RequestMapping(value = "/{userID}", method = RequestMethod.GET)
        public String renderPage(Model model, @PathVariable Long userID){
            addEmployeeToModel(model);
            Employee emp = employeeRepository.findById(userID).orElse(null);
            String nameuser = emp.getInformation().getLastName() + " " + emp.getInformation().getFirstName();
            model.addAttribute("nameuser", nameuser);
            model.addAttribute("emp", emp);
            return "employeeInfor";
        }

        @RequestMapping(value = "", method = RequestMethod.POST)
        public void getInfor(){
            
        }
    }

    @RequestMapping("/patientlist")
    public String patientList(Model model){
        addEmployeeToModel(model);
        return "patientList";
    }

    @Controller
    @RequestMapping("/patientlist/patientinfor")
    public class patientInfor{

        @RequestMapping("/{patientID}")
        public String patientInformation(Model model, @PathVariable Long patientID){
            Patient patient = patientRepository.findById(patientID).orElse(null);
            String namepatient = patient.getInformation().getLastName() + " " + patient.getInformation().getFirstName();
            model.addAttribute("namepatient", namepatient);
            model.addAttribute("patient", patient);
            addEmployeeToModel(model);
            return "patientInformation";
        }
    
        @RequestMapping("/treatmenthistory")
        public String treatmentHistory(Model model){
            addEmployeeToModel(model);
            return "treatmentHistory";
        }
    
        @RequestMapping("/treatmenthistory/prescription")
        public String prescription(Model model){
            addEmployeeToModel(model);
            return "prescription";
        }
    }

    @RequestMapping("/help")
    public String helpAndSupport(Model model){
        addEmployeeToModel(model);
        return "helpAndSupport";
    }

    @RequestMapping("/setting")
    public String setting(Model model){
        Employee emp = addEmployeeToModel(model);
            String nameuser = emp.getInformation().getLastName() + " " + emp.getInformation().getFirstName();
            model.addAttribute("nameuser", nameuser);
        return "setting";
    }
}
