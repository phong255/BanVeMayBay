package com.kttt.webbanve.controllers.client;

import com.kttt.webbanve.models.Customer;
import com.kttt.webbanve.services.CustomerServiceImpl;
import com.kttt.webbanve.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kttt.webbanve.models.User;

import javax.validation.Valid;

@Controller
public class AccountController {
    @Autowired
    UserServiceImpl us;
    @Autowired
    CustomerServiceImpl cu;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/account")
    public String goToLogin(Model model){
        model.addAttribute("pageTitle","Login");
        return "login";
    }
    @GetMapping("/account/signup")
    public String goToSignUp(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Sign up");
        return "signUp";
    }
    @PostMapping("/account/signup/save")
    public String signup(User user,RedirectAttributes ra,HttpServletRequest request){
        User u_exist = us.getAccount(user.getUsername());
        if(u_exist == null){
            Customer cus = new Customer();
            String email = request.getParameter("email");
            user.setPassword(bCryptPasswordEncoder.encode(request.getParameter("password")));
            if(request.getParameter("password").equals(request.getParameter("refill_password"))){
                cus.setUser(user);
                cus.setEmail(email);
                cu.createCustomer(cus);
                us.addAccount(user);
                ra.addFlashAttribute("messageSuccess","Sign up success !");
            }
            else
                ra.addFlashAttribute("messageError","The password refilled is incorrect !");
        }
        else{
            ra.addFlashAttribute("messageError","This username has been used !");
        }
        return "redirect:/account/signup";
    }
    @GetMapping("/account/logout")
    public String logout(Model model,HttpServletRequest request){
        if(request.getSession().getAttribute("customer") != null)
            request.getSession().removeAttribute("customer");
        model.addAttribute("pageTitle","Homepage");
        return "index";
    }

    @PostMapping("/account/login")
    public String login(Model model, HttpServletRequest request, RedirectAttributes ra){
        Customer u;
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            u = cu.getCustomer(username);
            if(u != null){
                if(bCryptPasswordEncoder.matches(password,u.getUser().getPassword())){
                    model.addAttribute("pageTitle","Homepage");
                    model.addAttribute("messageLogin","Login success!");
                    request.getSession().setAttribute("customer",u);
                    model.addAttribute("customer",u);
                    return "index";
                }
                else{
                    ra.addFlashAttribute("message","The password is incorrect !");
                    return "redirect:/account";
                }
            }
            else {
                ra.addFlashAttribute("message","This account does not exist !");
                return "redirect:/account";
            }

        }
        catch(Exception e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/account";
        }
    }

    @GetMapping("/account/information/{customerID}")
    public String infoPage(@PathVariable("customerID") Integer cid,Model model){
        model.addAttribute("pageTitle","Personal information");
        Customer customer = cu.getByID(cid);
        model.addAttribute("customer",customer);
        return "personalInformation";
    }

    @PostMapping("/account/updateInfo")
    public String updateInfo(@Valid @ModelAttribute("customer") Customer customer, RedirectAttributes ra, BindingResult bindingResult){
            Integer cid =  customer.getCustomerID();
        try{
            if(bindingResult.hasErrors()){
                ra.addFlashAttribute("message","Updated information Fail!");
            }
            else{
                cu.updateCustomer(customer,cid);
                ra.addFlashAttribute("message","Updated successfully!");
            }
            return "redirect:/account/information/"+cid;

        }
        catch (Exception e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/account/information/"+cid;
        }
    }
}
