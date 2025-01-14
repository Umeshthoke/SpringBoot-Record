package com.example.ProductRecord.controller;


import com.example.ProductRecord.entity.UserEntity;
import com.example.ProductRecord.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productRecord")
public class ProductController {


    @Autowired
    private ProductService productService;


    //get

    @GetMapping

    public List<UserEntity> getAll() {

        return productService.getAll();
    }


    //getById

    @GetMapping("/{id}")


    public ResponseEntity<UserEntity> getById(@PathVariable String id) {

        Optional<UserEntity> user = productService.getById(id);
        if (user.isPresent()) {

            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //post

    @PostMapping

    public ResponseEntity<?> saveEntry(@RequestBody UserEntity user) {
        try {
            productService.saveEntry(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("Created");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable String id, @RequestBody UserEntity updatedUser){
        try{
            String message = productService.updateEntry(id,updatedUser);
            return ResponseEntity.ok(message);
        }catch (RuntimeException runtimeException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(runtimeException.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    //deleteById
    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteById(@PathVariable String id) {

        productService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
