package com.store.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.store.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.entity.User;
import com.store.repo.UserRepositiory;

@Service
@Transactional
public class UserService {

	@Autowired
    private UserRepositiory userRepo;

    // Getting list of all users present in database
    public List<User> listAll() {
        List<User> list=  (List<User>) userRepo.findAll();
        return list;
    }

    // get single user by id
    public User getUserById(int id) {
        User user = null;
        try {
            user = userRepo.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Adding the user or Posting the User
    public User addUser(User usr) {
        if (usr.getCode() == null) {
            System.out.println("____________________Hello m inside if block____________________");
            usr.setRol("normal");
        } else if (usr.getCode().equalsIgnoreCase("qwerty")) {
            System.out.println("____________________Hello m inside else block____________________");
            usr.setRol("admin");
            usr.setStatus(null);
        }
        User result = userRepo.save(usr);
        return result;
    }

    // Update the User
    public User updateUser(User usr, int Uid) {
        usr.setUserId(Uid);
        User result = userRepo.save(usr);
        return result;
    }

    // Partially Update the User
    public User partiallyUpdateUser(User usr, int Uid) {
        usr.setUserId(Uid);
        User result = userRepo.save(usr);
        return result;
    }

    // Deleting the User
    public Response delete(int Uid, int Aid) {
        Response resp = new Response();
        try{
            String roll1 = userRepo.findById(Aid).getRol();
            String roll2 = userRepo.findById(Uid).getRol();
            if (Objects.equals(roll1, "admin") && (Objects.equals(roll2, "normal"))) {
                userRepo.deleteById(Uid);
                resp.setMessage(String.format("Administrator having id equal to %s has deleted the user having user_id = %s", Aid, Uid));
            } else if (Objects.equals(roll1, "admin") && Objects.equals(roll2, "admin")) {
                resp.setMessage("You can not delete another Admin");
            } else {
                resp.setMessage("You are not authorized");
            }
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            resp.setMessage(String.format("User having user_id = %s does not exist", Uid));
            return resp;
        }
    }
}
