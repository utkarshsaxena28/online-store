package com.store.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.OnlineStoreApplication;
import com.store.entity.User;
import com.store.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

	static Logger logger = LogManager.getLogger(OnlineStoreApplication.class);

    @Autowired
    private UserService usrService;

    @GetMapping("/users")                                                //CHECKED
    public ResponseEntity<List<User>> list() {

        List<User> list = usrService.listAll();

        if (list.size()<=0) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("/usrs")                                           //CHECKED
    public ResponseEntity<User> getUser(@RequestParam("userId") Integer id) {
        try {
            logger.info("getting employee by id number {}..............", id);
            User ur = usrService.getUserById(id);
            return ResponseEntity.of(Optional.of(ur));
        }catch (Exception e) {
            logger.error("Ops! some error occurred", e);
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")                                                //CHECKED
    public ResponseEntity<User> add(@Valid @RequestBody User uzr) {
        User ue = null;
        try {
            ue = usrService.addUser(uzr);
            int id = uzr.getUserId();
            logger.info("Adding New User having id equale to {}..........", id);
            return new ResponseEntity<>(ue, HttpStatus.CREATED);
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/users")                                                    //CHECKED
    public ResponseEntity<User> updateUser(@Valid @RequestBody User us, @RequestParam("userId") Integer id) {

        try {
            User ue = usrService.updateUser(us,id);
            logger.info("Updating User having id equale to {}............", id);
            return ResponseEntity.ok().body(ue);
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/users")                                          //CHECKED
    public ResponseEntity<User> updatePartially(@Valid @RequestBody User ue, @RequestParam("userId") Integer id) {
        try {
            User uer = usrService.partiallyUpdateUser(ue,id);
            logger.info("Updating User having id equale to {}............", id);
            return ResponseEntity.of(Optional.of(uer));
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/users")                                         // CHECKED
    public ResponseEntity<?> delete(@RequestParam("userId") Integer userId, @RequestParam("adminId") Integer adminId ) {
        try {
            usrService.delete(userId,adminId);
            logger.info("User having id equal to {} is deleted.............", userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
