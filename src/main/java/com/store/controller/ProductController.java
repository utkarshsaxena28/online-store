package com.store.controller;

import com.store.OnlineStoreApplication;
import com.store.entity.Product;
import com.store.http.Request;
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
            resp.setMessage("Sorry no items present in the shop");
            return  new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        }
        resp.setMessage("here is the list of all the products");
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
        Response resp1 = proService.getProductByCategory(catgry,userId);
        String message = resp1.getMessage();
        List<Product> list2 = resp1.getList();
        if (resp1.getMessage().equalsIgnoreCase("pass")) {
            if (list2.isEmpty()) {
                resp.setMessage(String.format("Sorry product under %s Category is out of stock  ", catgry));
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            } else {
                resp.setList(list2);
                resp.setMessage(String.format("here is the list of all %s ", catgry));
                return new ResponseEntity<>(resp,HttpStatus.OK);
            }
        }
        else {
            resp.setMessage(message);
            return new ResponseEntity<>(resp,HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> add(@RequestBody Request req , @RequestParam("userId") Integer userId) {
        Product prt = req.getProduct();
        Response resp = new Response();
        List<Product> list3 = new ArrayList<>();
        try {
            list3.add(proService.addProduct(prt,userId));
            resp.setList(list3);
            int id = prt.getProductId();
            logger.info("Adding New Product having id equal to {}..........", id);
            resp.setMessage(String.format("Your product having id = %s is successfully added", id));
            return new ResponseEntity<>(resp, HttpStatus.CREATED);
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/products")                                                          //CHECKED
    public ResponseEntity<Response> updateUser(@RequestBody Request req , @RequestParam("proId") Integer id) {
        Response resp = new Response();
        Product pdt = req.getProduct();
        List<Product> list4 = new ArrayList<>();
        try {
            list4.add(proService.updateProduct(pdt,id));
            resp.setList(list4);
            logger.info("Updating Product having id equal to {} is successfully updated", id);
            resp.setMessage(String.format("Your product having id = %s is successfully updated", id));
            return new ResponseEntity<>(resp,HttpStatus.OK);
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/products")                                          //CHECKED
    public ResponseEntity<Response> updatePartially(@RequestBody Request req , @RequestParam("proId") Integer proId) {
        Response resp = new Response();
        Product pdt = req.getProduct();
        List<Product> list5 = new ArrayList<>();
        try {
            list5.add(proService.partiallyUpdateProduct(pdt,proId));
            resp.setList(list5);
            logger.info("Updating Product having id equal to {}............", proId);
            resp.setMessage(String.format("Your product having id = %s is successfully updated", proId));
            return new ResponseEntity<>(resp,HttpStatus.OK);
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/products")                                         // CHECKED
    public ResponseEntity<String> delete(@RequestParam("proId") Integer proId, @RequestParam("userId") Integer userId) {
        try {
            Response resp = proService.delete(proId, userId);
            logger.info("Product having id equal to {} is deleted.............", proId);
            return new ResponseEntity<>(resp.getMessage(),HttpStatus.OK);
        } catch(Exception e) {
            logger.error("Ops! some error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
