package com.store.controller;

import com.store.OnlineStoreApplication;
import com.store.entity.Product;
import com.store.http.Response;
import com.store.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {

    static Logger logger = LogManager.getLogger(OnlineStoreApplication.class);

    @Autowired
    private ProductService proService;

    @GetMapping("/prdcts")                                                         //CHECKED
    public ResponseEntity<Response> list(@RequestParam("userId") Integer userId) {

        Response resp = new Response();
        List<Product> list = proService.listAll(userId);
        resp.setList(list);

        if (list.size()<=0) {
            resp.setMessage(String.format("Sorry no items present in the shop"));
            return  ResponseEntity.status( HttpStatus.NOT_FOUND).build();
        }
        resp.setMessage(String.format("here is the list of all the products"));
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @GetMapping("/pros")                                                            //CHECKED
    public ResponseEntity<Response> getProduct(@RequestParam("proId") Integer proId) {
        Response resp = new Response();
        List<Product> list1 = new ArrayList<>();
        list1.add(proService.getProductById(proId));
        resp.setList(list1);
        if (list1 == null) {
            resp.setMessage(String.format("Product having id = %s is not present", proId));
            return  new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        }
        resp.setMessage(String.format("here is the Product having id = %s", proId));
        return new ResponseEntity<>(resp,HttpStatus.OK);

    }

    @GetMapping("/products")                                                        //CHECKED
    public ResponseEntity<Response> productByCategory(@RequestParam("Category") String catgry,  @RequestParam("userId") Integer userId) {
        Response resp = new Response();
        logger.info("getting Product by Category {}..............", catgry);
        List<Product> list2 = proService.getProductByCategory(catgry,userId);
        resp.setList(list2);
        if (list2.isEmpty()) {
            resp.setMessage(String.format("Sorry product under %s Category is out of stock  ", catgry));
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        } else {
            resp.setMessage(String.format("here is the list of all %s ", catgry));
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
    }

    @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> add(@RequestBody Product prt, @RequestParam("userId") Integer userId) {
        Product pt = null;
        try {
            pt = proService.addProduct(prt,userId);
            int id = pt.getProductId();
            logger.info("Adding New Product having id equal to {}..........", id);
            return new ResponseEntity<>(pt, HttpStatus.CREATED);
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/products")                                                          //CHECKED
    public ResponseEntity<Product> updateUser(@RequestBody Product pdt, @RequestParam("proId") Integer id) {

        try {
            Product ue = proService.updateProduct(pdt,id);
            logger.info("Updating Product having id equal to {}............", id);
            return ResponseEntity.ok().body(ue);
        } catch(Exception e) {
            logger.error("Ops!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/products")                                          //CHECKED
    public ResponseEntity<Product> updatePartially(@RequestBody Product pdt, @RequestParam("proId") Integer id) {

        try {
            Product ue = proService.partiallyUpdateProduct(pdt,id);
            logger.info("Updating Product having id equal to {}............", id);
            return ResponseEntity.ok().body(ue);
        } catch(Exception e) {
            logger.error("Ops!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/products")                                         // CHECKED
    public ResponseEntity<?> delete(@RequestParam("proId") Integer proId, @RequestParam("userId") Integer userId) {
        try {
            proService.delete(proId, userId);
            logger.info("Product having id equal to {} is deleted.............", proId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            logger.error("Ops!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
