package org.acme.order;

import org.acme.client.ClientRepository;
import org.acme.order.dto.request.OrderCreateDto;
import utils.ValidatorUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;


@ApplicationScoped
public class OrderService {
    @Inject
    private OrderRepository orderRepository;

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private OrderMapper orderMapper;

    @Inject
    private ValidatorUtils validator;

    public List<Order> findAll() {
        return orderRepository.listAll();
    }

    public Order create(OrderCreateDto orderDto) {
        this.validator.validators(orderDto);

        Order order = this.orderMapper.fromDto(orderDto);


        this.orderRepository.persist(order);
        return order;
    }
}
