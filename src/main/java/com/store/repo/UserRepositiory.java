package com.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.store.entity.User;

@Service
public interface UserRepositiory extends JpaRepository<User, Integer> {
    
	public User findById(int id);
    
	public User findByName(String name);
}
