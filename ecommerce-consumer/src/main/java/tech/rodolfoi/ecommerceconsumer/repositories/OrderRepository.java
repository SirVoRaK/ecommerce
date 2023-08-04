package tech.rodolfoi.ecommerceconsumer.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import tech.rodolfoi.ecommerceconsumer.entities.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findById(String id);
}
