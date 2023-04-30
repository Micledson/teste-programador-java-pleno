package org.acme.order_demand;


import org.acme.order.Order;
import org.acme.order.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

@ApplicationScoped
public class OrderProductService {

    @Inject
    OrderRepository orderRepository;


    public Order find(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new WebApplicationException("Order Not Found", 422);
        }

        return orderRepository.find("SELECT o FROM Order o JOIN o.products p WHERE o.id = ?1", id).firstResult();
    }

}