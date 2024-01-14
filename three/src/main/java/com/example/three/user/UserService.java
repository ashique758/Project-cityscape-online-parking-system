package com.example.three.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User userByEmailpassing(String email){
        Optional<User> optionalUserbyEmail = userRepository.findUserByEmail(email);
        User userbyemailpassing = null;
        if (optionalUserbyEmail.isPresent()) {
            userbyemailpassing = optionalUserbyEmail.get();
        }

        return userbyemailpassing;

    }
    public User userByEmail(User user) {
        Optional<User> optionalUserbyEmail = userRepository.findUserByEmail(user.getEmail());
        User userbyemail = null;
        if (optionalUserbyEmail.isPresent()) {
            userbyemail = optionalUserbyEmail.get();
        }

        return userbyemail;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user){

        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if(userByEmail.isPresent()){
            throw new IllegalStateException("User Email already Exist");
        }

        System.out.println("printing User details:  "+ user);

        userRepository.save(user);
    }

    public boolean isUserExist(User user){

        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()) {
            return true;
        }
        else{
            return false;
        }

    }

    //Update service methods
    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }


    //deleteservie

     void deleteUserbyId(Long id){
        userRepository.deleteById(id);
     }



     //is admin user

    public boolean isAdmin(User user) {
        String userRole = user.getRole(); // Assuming there's a getter for role in User class
        // Check if the user's role is "admin" (case-insensitive)
        return userRole != null && userRole.equalsIgnoreCase("admin");
    }

}
