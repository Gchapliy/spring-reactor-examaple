package com.example.spring_reactor_examaple.repository;

import com.example.spring_reactor_examaple.document.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ItemReactiveRepository extends ReactiveMongoRepository<Item, String> {
}
