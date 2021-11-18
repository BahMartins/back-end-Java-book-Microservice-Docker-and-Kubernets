package br.com.microservice.book.shoppingclient.DTO;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoryDTO {

    @NotNull
    private Long id;
    private String name;

}
