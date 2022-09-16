package com.store.service;

import com.store.entity.Product;
import com.store.entity.Sales;
import com.store.entity.User;
import com.store.repo.ProductRepositiory;
import com.store.repo.SalesRepositiory;
import com.store.repo.UserRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private ProductService proService;
    @Autowired
    private ProductRepositiory proRepo;

    @Autowired
    private UserRepositiory userRepo;

    @Autowired
    private SalesRepositiory salesRepo;

    // Getting the sales data
    public List<Sales> listAll() {
        List<Sales> list= salesRepo.findAll();
        return list;
    }

    public Sales buyProduct(String proName, int Uid, int quantity) {
        Product pro = proRepo.findByName(proName);
        double price = pro.getOriginalPrice();
        double discount = pro.getDiscount();
        double sellingPrice = (((100-discount)*price)/100)*quantity;
        Sales sale = new Sales();
        sale.setProductSold(proName);
        sale.setQuantity(quantity);
        sale.setSellingPrice(sellingPrice);
        sale.setSoldTo(Uid);
        Sales result = salesRepo.save(sale);
        pro.setQuantity(pro.getQuantity()-quantity);
        int id = pro.getProductId();
        proService.partiallyUpdateProduct(pro,id);
        return  result;
    }
}
