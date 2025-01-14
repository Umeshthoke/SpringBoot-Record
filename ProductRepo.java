package com.example.ProductRecord.repo;

import com.example.ProductRecord.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<UserEntity,String> {
}
