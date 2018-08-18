package com.example.demo.bookstore.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.security.PasswordResetToken;
import com.example.demo.domain.security.Role;
import com.example.demo.domain.security.UserRole;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by prakashdas on 16/08/18.
 */

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;


    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("classActiveLogin",true);
        return "myAccount";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(Locale locale, @RequestParam("token") String token, Model model)
    {
        model.addAttribute("classActiveForgetPassword",true);
        return "myAccount";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String newUserPost(
        HttpServletRequest request, @ModelAttribute("email") String userEmail, @ModelAttribute("username") String username, Model model) throws Exception{
        model.addAttribute("classActiveNewAccount",true);
        model.addAttribute("email",userEmail);
        model.addAttribute("username",username);

        if(userService.findByUsername(username)!=null){
            model.addAttribute("usernameExists",true);
            return "myAccount";
        }

        if(userService.findByEmail(userEmail)!=null){
            model.addAttribute("email",true);
            return "myAccount";
        }
        User user = new User();
        user.setEmail(userEmail);
        user.setUsername(userEmail);

        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user,role));
        userService.createUser(user,userRoles);
    }

    @RequestMapping("/newUser")
    public String newUser(Locale local, @RequestParam("token") String token, Model model)
    {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);
        if(passToken==null){
            String message = "Invalid token";
            model.addAttribute("message",message);
            return "redirect:/badRequest";
        }
        User user = passToken.getUser();
        String username = user.getUsername();
        UserDetails userDetails = userSecurityService.loadUserByUsername(username);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        model.addAttribute("classActiveNewUser",true);
        return "myAccount";
    }
}
