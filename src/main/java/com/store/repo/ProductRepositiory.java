package com.store.repo;

import com.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.store.entity.Product;

import java.util.List;

@Service
public interface ProductRepositiory extends JpaRepository<Product, Integer>{

    public Product findById(int id);

    public Product findByName(String name);

    public List<Product> findByCategory(String category);
}
