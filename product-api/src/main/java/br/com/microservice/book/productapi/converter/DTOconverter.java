package br.com.microservice.book.productapi.converter;

import br.com.microservice.book.productapi.model.Category;
import br.com.microservice.book.productapi.model.Product;
import br.com.microservice.book.shoppingclient.DTO.CategoryDTO;
import br.com.microservice.book.shoppingclient.DTO.ProductDTO;

public class DTOconverter {
	
	public static CategoryDTO convert(Category category) {
		CategoryDTO categoryDto = new CategoryDTO();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		return categoryDto;
	}
	
	public static ProductDTO convert(Product product) {
		ProductDTO productDto = new ProductDTO();
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		if(product.getCategory() != null) {
			productDto.setCategoryDto(DTOconverter.convert(product.getCategory()));
		}
		return productDto;
	}
}
