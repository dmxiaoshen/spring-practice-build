package com.xyz.service.demo.service;

import com.xyz.service.demo.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(Book book){
        mongoTemplate.save(book);
    }

    public Book get(String id){
        return mongoTemplate.findById(id,Book.class);
    }
}
