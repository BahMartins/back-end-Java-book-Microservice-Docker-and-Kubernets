package br.com.microservice.book.productapi.service.impl;

import br.com.microservice.book.productapi.converter.DTOconverter;
import br.com.microservice.book.productapi.model.Product;
import br.com.microservice.book.productapi.repository.CategoryRepository;
import br.com.microservice.book.productapi.repository.ProductRepository;
import br.com.microservice.book.productapi.service.ProductService;
import br.com.microservice.book.shoppingclient.DTO.ProductDTO;
import br.com.microservice.book.shoppingclient.exceptions.CategoryNotFoundException;
import br.com.microservice.book.shoppingclient.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null){
            return DTOconverter.convert(product);
        }
        throw new ProductNotFoundException();
    }

    @Override
    public ProductDTO save(ProductDTO productDto) {
        boolean existCategory = categoryRepository.existsById(productDto.getCategoryDto().getId());

        if (existCategory){
            Product product = productRepository.save(Product.convert(productDto));
            return DTOconverter.convert(product);
        }
        throw new CategoryNotFoundException();
    }

    @Override
    public ProductDTO delete(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(value -> productRepository.delete(value));
        throw new ProductNotFoundException();
    }
}
