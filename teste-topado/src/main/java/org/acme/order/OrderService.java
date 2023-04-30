package org.acme.order;

import org.acme.order.dto.request.OrderCreateDto;
import org.acme.order.dto.request.OrderUpdateDto;
import utils.ValidatorUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;


@ApplicationScoped
public class OrderService {
    @Inject
    private OrderRepository orderRepository;

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

    public Order update(OrderUpdateDto orderDto, Long id) {
        this.validator.validators(orderDto);

        Order order = this.orderRepository.findById(id);
        if (order == null) throw new WebApplicationException("Order not Found", Response.Status.NOT_FOUND);

        this.orderMapper.updateFromDto(orderDto, order);

        this.orderRepository.persist(order);
        return order;
    }


}
