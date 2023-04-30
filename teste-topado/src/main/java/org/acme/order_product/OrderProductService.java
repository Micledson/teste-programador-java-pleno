package org.acme.order_product;


import org.acme.order.Order;
import org.acme.order.OrderRepository;
import org.acme.order_product.dto.request.OrderProductCreateDto;
import org.acme.product.Product;
import org.acme.product.ProductRepository;
import utils.ValidatorUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderProductService {
    @Inject
    ValidatorUtils validator;
    @Inject
    OrderRepository orderRepository;

    @Inject
    ProductRepository productRepository;


    public Order find(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new WebApplicationException("Order Not Found", 422);
        }

        return orderRepository.find("SELECT o FROM Order o JOIN o.products p WHERE o.id = ?1", id).firstResult();
    }

    public List<Product> create(List<OrderProductCreateDto> ordersProductDto, Long id) {
        validator.validators(ordersProductDto);

        this.orderExists(id);
        this.productExists(ordersProductDto);
        try {
            Order order = orderRepository.findById(id);

            ArrayList<Product> products = new ArrayList<>();

            ordersProductDto.forEach(ord -> {
                products.addAll(productRepository.find("id in ?1", ord.getId()).list());
            });
            order.setProducts(products);
            order.setTotalValue(calculateTotalValue(ordersProductDto, products));
            orderRepository.persist(order);
            return products;
        } catch (Exception error) {
            throw error;
        }

    }

    public Exception orderExists(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new WebApplicationException("Order Not Found", Response.Status.NOT_FOUND);
        }
        return null;
    }

    public Exception productExists(List<OrderProductCreateDto> orderProductCreateDtos) {
        orderProductCreateDtos.forEach(product -> {
            if (productRepository.findById(product.getId()) == null) {
                throw new WebApplicationException("Product Not Found", Response.Status.NOT_FOUND);
            }
        });
        return null;
    }

    public Double calculateTotalValue(List<OrderProductCreateDto> orders, List<Product> products) {
        Double totalValue = 0.0;
        for (OrderProductCreateDto order : orders) {
            Product prod = products.stream().filter(product -> product.getId().equals(order.getId())).findAny().orElse(null);
            if (prod != null) {
                totalValue += (prod.getPrice() * order.getQuantity());
            }
        }

        return totalValue;
    }
}