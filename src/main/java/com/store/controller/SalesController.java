package com.store.controller;

import com.store.OnlineStoreApplication;
import com.store.entity.Product;
import com.store.entity.Sales;
import com.store.service.ProductService;
import com.store.service.SalesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/sale")
public class SalesController {

    @Autowired
    private SalesService salesService;

    static Logger logger = LogManager.getLogger(OnlineStoreApplication.class);

    @PostMapping("/slaes")
    public ResponseEntity<Sales> add(@RequestParam("proName") String proName,
                                     @RequestParam("userId") Integer userId,
                                     @RequestParam("quantity") Integer quantity ) {
        Sales sl = null;
        try {
            sl = salesService.buyProduct(proName,userId,quantity);
            return new ResponseEntity<>(sl, HttpStatus.CREATED);
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
