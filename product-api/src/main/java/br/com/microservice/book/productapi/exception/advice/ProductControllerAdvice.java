package br.com.microservice.book.productapi.exception.advice;

import br.com.microservice.book.shoppingclient.DTO.ErrorDTO;
import br.com.microservice.book.shoppingclient.exceptions.CategoryNotFoundException;
import br.com.microservice.book.shoppingclient.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "br.com.microservice.book.productapi.controller")
public class ProductControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleUserNotFound(ProductNotFoundException productNotFoundException){

        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage("Product not found");
        errorDto.setTimestamp(new Date());
        return errorDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDTO handleCategoryNotFound(CategoryNotFoundException categoryNotFoundException){

        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage("Category not found");
        errorDto.setTimestamp(new Date());
        return errorDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO processValidationError(MethodArgumentNotValidException ex){

        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder("Invalid value in the fields:");

        for (FieldError fieldError : fieldErrors) {
            stringBuilder.append(" ");
            stringBuilder.append(fieldError.getField());
        }

        errorDto.setMessage(stringBuilder.toString());
        errorDto.setTimestamp(new Date());
        return errorDto;
    }

}
