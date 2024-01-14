package com.example.three.booking;

import com.example.three.user.User;
import com.example.three.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.three.user.UserController.LOGGED_IN_USER;

@Controller
@RequestMapping
public class MapController {


    private final UserService userService;
    private final BookingService bookingService;

    public MapController(BookingService bookingService,UserService userService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }
////////////////////////////////////////////////////////////////
    private boolean isLoggedIn(HttpSession session) {
        if(session.getAttribute(LOGGED_IN_USER) != null){
            return true;
        }
        return false;
    }
    @GetMapping("/book")
    public String showMapPage(Model model, HttpSession session, booking booking) {
        boolean isLoggedIn = isLoggedIn(session);
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "map"; // map.html Thymeleaf template
    }

/////
@PostMapping("/book")
public String book(@ModelAttribute("booking") booking booking, Model model, HttpSession session, Principal principal) {
    boolean isLoggedIn = isLoggedIn(session);
    model.addAttribute("isLoggedIn", isLoggedIn);
    if (isLoggedIn) {
        User loggedInUser = (User) session.getAttribute(LOGGED_IN_USER);
        String userEmail = loggedInUser.getEmail(); // Retrieve the logged-in user's email
        booking.setUserEmail(userEmail); // Set the user's email in the booking object
       //


        //
        bookingService.addNewBooking(booking);
        model.addAttribute("message", "Hello, please do the payment.");
        model.addAttribute("bookingid", booking.getBookingid());
        // Process booking or perform necessary actions here
        return "slotbooked";}
    else {
        // Handle case where the user is not logged in
        // For example, redirect to the login page
        return "redirect:/login"; // Adjust this URL based on your login endpoint
    }
}



    @GetMapping("/payment")
    public String paymentPage(Model model, HttpSession session, booking booking) {

        return "payment"; // map.html Thymeleaf template
    }


    @GetMapping("/mybookings")
    public String showUserBookings(Principal principal, Model model, HttpSession session) {
        boolean isLoggedIn = isLoggedIn(session);
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (!isLoggedIn) {
            // Handle case where the user is not logged in
            // For example, redirect to the login page
            return "redirect:/login"; // Adjust this URL based on your login endpoint
        } else {
            User loggedInUser = (User) session.getAttribute(LOGGED_IN_USER);
            String userEmail = loggedInUser.getEmail();
            List<booking> userBookings = bookingService.getBookingsByUserEmail(userEmail);

            // Add bookings to the model
            model.addAttribute("bookings", userBookings);

            return "userBookings"; // This should be the name of your Thymeleaf template
        }
    }



    @GetMapping("/booking/{bookingid}")
    public String deleteBookingById(@PathVariable int bookingid){
        bookingService.deletebookingbyId((long) bookingid);
        return "redirect:/mybookings";
    }


    @GetMapping("/allbooking")
    public String adminPanelbooking(HttpSession session, Model model) {
        if (session.getAttribute(LOGGED_IN_USER) != null) {
            boolean isLoggedIn = isLoggedIn(session);
            model.addAttribute("isLoggedIn", isLoggedIn);
            User loggedInUser = (User) session.getAttribute(LOGGED_IN_USER);
            model.addAttribute("isLoggedIn", isLoggedIn);
            model.addAttribute("bookings",bookingService.getBookings());
            model.addAttribute("userName", loggedInUser.getName());
            // Add any other necessary attributes for the admin panel
            return "allbooking";
        } else {
            return "redirect:/login"; // Redirect to login if no user is logged in
        }
    }


    //delete apis

    @GetMapping("/booking/{bookingid}/{useremail}")
    public String deleteBookingByIdAndUserEmail(@PathVariable Long bookingid, @PathVariable String useremail) {
        bookingService.deleteBookingByIdAndEmail(bookingid, useremail);
        return "redirect:/allbooking";
    }


//Lot provider admin portal
    @GetMapping("/lotprovideradmin")
    public String lotprovideradmin(Model model){
        model.addAttribute("Message","Hello Lot Provider");
        return "lotprovideradmin";
    }

}

