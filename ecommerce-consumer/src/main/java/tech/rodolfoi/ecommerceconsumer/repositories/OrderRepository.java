package tech.rodolfoi.ecommerceconsumer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import tech.rodolfoi.ecommerceconsumer.entities.Order;

public interface OrderRepository extends MongoRepository<Order, String> { }
