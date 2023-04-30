package org.acme.order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;


@ApplicationScoped
public class OrderService {
    @Inject
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.listAll();
    }


}
