package br.com.microservice.book.shoppingapi.exception.advice;

import br.com.microservice.book.shoppingclient.DTO.ErrorDTO;
import br.com.microservice.book.shoppingclient.exceptions.ProductNotFoundException;
import br.com.microservice.book.shoppingclient.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice(basePackages = "br.com.microservice.book.shoppingapi.controller")
public class ShoppingControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleProductNotFound(ProductNotFoundException productNotFoundException){
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage("Product not found");
        errorDto.setTimestamp(new Date());
        return errorDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFound(UserNotFoundException userNotFoundException){
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage("User not found");
        errorDto.setTimestamp(new Date());
        return errorDto;
    }
}
