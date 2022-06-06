package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.ResponseObject;
import com.example.demo.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Products")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    List<Product> getAllProduct() {
        return repository.findAll();
    }
//Get detail  product

    @GetMapping("/{id}")
        //return data,message,status
    ResponseEntity<ResponseObject> getProduct(@PathVariable("id") Long id) {
        Optional<Product> foudProduct = repository.findById(id);
        return foudProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query product successfully", foudProduct)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "cannot find product with id =" + id, ""));


    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        List<Product> foudProduct = repository.findByproductName(newProduct.getproductName().trim());
        return foudProduct.size() > 0 ? ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Product name already taken", "")) :

                ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "insert product successfully", repository.save(newProduct)));


    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        Product updatedProduct = repository.findById(id)
                .map(
                        product -> {
                            product.setproductName(newProduct.getproductName());
                            product.setYear(newProduct.getYear());
                            product.setPrice(newProduct.getPrice());
                            product.setUrl(newProduct.getUrl());
                            return repository.save(product);
                        }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "update Product successfully", updatedProduct));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "ok", "delete product successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(new ResponseObject
                            ("failed", "cannot find product to delete ", ""));
        }
    }
}


