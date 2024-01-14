package com.example.three.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@Controller
@RequestMapping
//        (path="api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public static final String LOGGED_IN_USER = "loggedInUser";

    ////////////////////////////////////////////

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

//    /////////////////////////////////////////////////////////////////////////////////////////////////



    @GetMapping("/home")
    public String homePage(Model model,HttpSession session,User user) {
        boolean isLoggedIn = isLoggedIn(session);
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            User loggedInUser = (User) session.getAttribute(LOGGED_IN_USER);
            model.addAttribute("userEmail", loggedInUser.getEmail());
            model.addAttribute("userName", loggedInUser.getName());
            model.addAttribute("isAdmin",userService.isAdmin(loggedInUser));
        }
        else
        {
            model.addAttribute("userEmail", "Non Registered User");
        }

        return "home";
    }

    //LOGIN APIS
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginError", false);
        User user =new User();
        model.addAttribute("user",user);
        model.addAttribute("isAdmin", userService.isAdmin(user));
        return "login";
    }



    @PostMapping("/login")
    public String userlogin(@ModelAttribute("user") User user, Model model, HttpSession session) {
        if (userService.isUserExist(user)) {
            User completeuser = userService.userByEmail(user);
            //verify pass
            if (completeuser.getPassword().equals(user.getPassword())) {
                session.setAttribute(LOGGED_IN_USER, completeuser);

                // User exists, redirect to a success page or do necessary actions
                if (userService.isAdmin(user)) {
                    return "redirect:/showusers";
                }
                return "redirect:/home";
            } else {
                // Password doesn't match
                model.addAttribute("loginError", true);
                return "login";
            }
        }
            else{
                // User doesn't exist or error in login
                model.addAttribute("loginError", true);// Set login error attribute
                return "login";
            }

        }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(LOGGED_IN_USER);
        return "redirect:/home";
    }

    @GetMapping("/getusers")
    public String getUsersPage(Model model) {
        model.addAttribute("users",userService.getUsers());

        return "showusers";

    }

    @GetMapping("/adminpanel")
    public String adminPanel(HttpSession session, Model model) {
        if (session.getAttribute(LOGGED_IN_USER) != null) {
            boolean isLoggedIn = isLoggedIn(session);
            model.addAttribute("isLoggedIn", isLoggedIn);
            User loggedInUser = (User) session.getAttribute(LOGGED_IN_USER);
            model.addAttribute("isLoggedIn", isLoggedIn);
            model.addAttribute("users",userService.getUsers());
            model.addAttribute("userName", loggedInUser.getName());
            // Add any other necessary attributes for the admin panel
            return "showusers";
        } else {
            return "redirect:/login"; // Redirect to login if no user is logged in
        }
    }

    // Check if user is logged in
    private boolean isLoggedIn(HttpSession session) {
        if(session.getAttribute(LOGGED_IN_USER) != null){
            return true;
        }
        return false;
    }



    //REGISTER APIS
    @GetMapping("/register")
    public String registerUserPage(Model model) {
        model.addAttribute("registerError", false);
        User user =new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, Model model){
        if (!userService.isUserExist(user)) {
            userService.addNewUser(user);
            return "redirect:/login";
        }
        model.addAttribute("registerError", true);
        return "register";
    }

    @GetMapping("/about")
    public String getaboutpage(Model model) {
        model.addAttribute("users",userService.getUsers());

        return "about";

    }

    @GetMapping("/contactus")
    public String getcontactuspage(Model model) {
        model.addAttribute("users",userService.getUsers());

        return "contactus";

    }

    //UPDATE APIS
    @GetMapping("/user/edit/{id}")//url on update button on users list page
    public String editUser(@PathVariable Long id,Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "edituser";
    }

    @PostMapping("/user/{id}")//the url on the form action on edituser html
    public String updateUser(@PathVariable Long id,Model model,@ModelAttribute("user") User user){
    //get user from db by id

        User existinguser = userService.getUserById(id);
        existinguser.setEmail(user.getEmail());
        existinguser.setName(user.getName());
        existinguser.setRole(user.getRole());
        existinguser.setPassword(user.getPassword());


        userService.updateUser(existinguser);
        return "redirect:/getusers";

    }
    //delete apis
    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserbyId(id);
        return "redirect:/getusers";
    }

    //edit profile APIS



    @GetMapping("/editprofile")
    public String editProfilePage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute(LOGGED_IN_USER);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("user", loggedInUser); // Add the user object to the model
        return "editprofile";
    }

    @PostMapping("/update")
    public String updateUserProfile(@ModelAttribute("user") User updatedUser, HttpSession session) {
        User loggedInUser = (User) session.getAttribute(LOGGED_IN_USER);

        // Update user details
        loggedInUser.setName(updatedUser.getName());
        loggedInUser.setEmail(updatedUser.getEmail());

        // Handle updating password if changed
        if (!updatedUser.getPassword().isEmpty()) {
            loggedInUser.setPassword(updatedUser.getPassword());
        }
        userService.updateUser(loggedInUser);
        // Call userService.updateUser(loggedInUser) to save changes to the database

        return "redirect:/home"; // Redirect to the home page after updating profile
    }

}