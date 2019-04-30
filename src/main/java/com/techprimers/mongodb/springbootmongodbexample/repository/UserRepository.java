package com.techprimers.mongodb.springbootmongodbexample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techprimers.mongodb.springbootmongodbexample.document.Users;

public interface UserRepository extends MongoRepository<Users, Integer> {
}
