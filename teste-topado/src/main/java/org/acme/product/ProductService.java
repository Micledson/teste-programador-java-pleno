package org.acme.product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    private ProductRepository productRepository;


    public List<Product> findAll() {
        return productRepository.findAll().list();
    }


}
