package org.acme.order;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
}
