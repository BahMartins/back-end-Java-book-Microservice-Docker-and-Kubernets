package br.com.microservice.book.productapi.model;

import br.com.microservice.book.shoppingclient.DTO.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private BigDecimal price;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String productIdentifier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Getter @Setter
    private Category category;

    public static Product convert(ProductDTO productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setProductIdentifier(productDto.getProductIdentifier());

        if(productDto.getCategoryDto() != null){
            product.setCategory(Category.convert(productDto.getCategoryDto()));
        }

        return product;
    }

}
