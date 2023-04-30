package org.acme.product;

import org.acme.product.dto.request.ProductCreateDto;
import org.acme.product.dto.request.ProductUpdateDto;
import utils.ValidatorUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
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

    public Product update(ProductUpdateDto productDto, Long id) {
        validator.validators(productDto);

        Product product = this.productRepository.findById(id);
        if (product == null) throw new WebApplicationException("Product not Found", Response.Status.NOT_FOUND);

        int unities = product.getUnity();
        productMapper.updateFromDto(productDto, product);

        if (product.getUnity() <= 0) product.setUnity(unities);

        productRepository.persist(product);
        return product;
    }

    public boolean delete(Long id) {
        return productRepository.deleteById(id);
    }

}
