package com.usecase.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usecase.dto.User;

@FeignClient(value = "user-service-app")
public interface UserService {
	
	@GetMapping("/api/users")
    public List<User> getAllUsers();
    
    @GetMapping("/api/users/{user_id}")
    public User getUserById(@PathVariable Long user_id);

    @PostMapping("/api/users")
    public String addUser(@RequestBody User user);
    
    @DeleteMapping("/api/users/{user_id}")
    public String deleteUser(@PathVariable Long user_id);
    
    @PutMapping("/api/users/{user_id}")
    public String updateUser(@PathVariable Long user_id, @RequestBody User user);
}
