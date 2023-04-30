package org.acme.product;

import org.acme.product.dto.request.ProductCreateDto;
import utils.ValidatorUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductMapper productMapper;

    @Inject
    private ValidatorUtils validator;


    public List<Product> findAll() {
        return productRepository.findAll().list();
    }

    public void create(ProductCreateDto productDto) {
        validator.validators(productDto);

        Product product = productMapper.fromDto(productDto);

        productRepository.persist(product);
    }

}
