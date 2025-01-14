package com.example.ProductRecord.service;


import com.example.ProductRecord.entity.UserEntity;
import com.example.ProductRecord.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    //post


    public String saveEntry(UserEntity user) {

        productRepo.save(user);
        return "User Fetch Data Sucessfully";

    }


    //getall

    public List<UserEntity> getAll() {

        return productRepo.findAll();

    }


    //getbyid

    public Optional<UserEntity> getById(String id) {

        return productRepo.findById(id);


    }


    //delete

    public String deleteById(String id) {

        productRepo.deleteById(id);
        return "Delete record Sucessfully";
    }

    //Put

    @Transactional
    public String updateEntry(String id, UserEntity updatedUser){
        Optional<UserEntity> existingProduct = productRepo.findById(id);
        if(existingProduct.isPresent()){
            UserEntity user = existingProduct.get();
            user.setName(updatedUser.getName());
            user.setDescription(updatedUser.getDescription());
            user.setPrice(updatedUser.getPrice());

            productRepo.save(user);
            return "Product Updated Successfully";
        }
        else {
            throw  new RuntimeException("User Not Found");
        }
    }
}
