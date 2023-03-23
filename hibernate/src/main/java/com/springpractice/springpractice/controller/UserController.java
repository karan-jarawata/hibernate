package com.springpractice.springpractice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springpractice.springpractice.Dao.UserDao;

import com.springpractice.springpractice.entity.User;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private UserDao userDao;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userDao.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody User user) {
        userDao.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created with id: ");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        userDao.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("Employee updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable User user) {
        userDao.deleteUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }

}