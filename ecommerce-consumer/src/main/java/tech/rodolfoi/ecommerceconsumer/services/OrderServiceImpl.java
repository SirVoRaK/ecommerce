package tech.rodolfoi.ecommerceconsumer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.rodolfoi.ecommerceconsumer.entities.Order;
import tech.rodolfoi.ecommerceconsumer.repositories.OrderRepository;
import tech.rodolfoi.ecommerceconsumer.services.interfaces.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

	@Override
	public void save(Order order) {
        this.orderRepository.save(order);
	}

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }
}
