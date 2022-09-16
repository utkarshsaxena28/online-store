package com.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.store.entity.Sales;

@Service
public interface SalesRepositiory extends JpaRepository<Sales, Integer>{

}
