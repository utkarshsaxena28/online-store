package com.store.service;

import com.store.entity.Product;
import com.store.entity.User;
import com.store.repo.ProductRepositiory;
import com.store.repo.UserRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepositiory proRepo;

    @Autowired
    private UserRepositiory userRepo;

    // Getting list of all Product present in database
    public List<Product> listAll(int Uid) {
        List<Product> list = null;
        String status = userRepo.findById(Uid).getStatus();
        String roll = userRepo.findById(Uid).getRol();
        if (Objects.equals(status, "enable") || Objects.equals(roll, "admin")) {
            list = proRepo.findAll();
            return list;
        }
        return list;
    }


    // get single product by id
    public Product getProductById(int id) {
        Product prodct = null;
        try {
            prodct = proRepo.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodct;
    }

    // Getting list of all Product By Category
    public List<Product> getProductByCategory(String catgry, int Uid) {
        List<Product> list = null;
        String status = userRepo.findById(Uid).getStatus();
        String roll = userRepo.findById(Uid).getRol();
        if (Objects.equals(status, "enable") || Objects.equals(roll, "admin")) {
            try {
                list = proRepo.findByCategory(catgry);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        return list;
    }

    // Adding the product
    public Product addProduct(Product pro, int Uid) {
        String roll = userRepo.findById(Uid).getRol();
        if (Objects.equals(roll, "admin")) {
            Product result = proRepo.save(pro);
            return result;
        }
        return null;
    }

    // Update the Product
    public Product updateProduct(Product pro, int Pid) {
        pro.setProductId(Pid);
        Product result = proRepo.save(pro);
        return result;
    }

    // Partially Update the Product
    public Product partiallyUpdateProduct(Product pro, int Pid) {
        pro.setProductId(Pid);
        Product result = proRepo.save(pro);
        return result;
    }

    // Deleting the User
    public void delete(Integer id, int Uid) {
        String roll = userRepo.findById(Uid).getRol();
        if (Objects.equals(roll, "admin")) {
            proRepo.deleteById(id);
        }
    }
}
