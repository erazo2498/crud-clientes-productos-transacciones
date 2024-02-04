package com.quind.pruebatecnica.configuration;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.CustomerAlreadyExistsException;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.CustomerAlreadyExistsWithIDException;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.CustomerNotFoundException;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.ProductAlreadyExistsException;
import com.quind.pruebatecnica.domain.exceptions.AgeNoValidException;
import com.quind.pruebatecnica.domain.exceptions.InvalidValueException;
import com.quind.pruebatecnica.domain.exceptions.NegativeBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.quind.pruebatecnica.configuration.Constants.*;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError fieldError) {
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AgeNoValidException.class)
    public ResponseEntity<Map<String, String>> ageNoValidException(
            AgeNoValidException ageNoValidException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CUSTOMER_IS_MINOR_MESSAGE));
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCustomerAlreadyExistsException(
            CustomerAlreadyExistsException customerAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CUSTOMER_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(CustomerAlreadyExistsWithIDException.class)
    public ResponseEntity<Map<String, String>> handleCustomerAlreadyExistsWithIDException(
            CustomerAlreadyExistsWithIDException customerAlreadyExistsWithIDException) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CUSTOMER_ALREADY_EXISTS_WITH_ID_MESSAGE));
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerNotFoundException(
            CustomerNotFoundException customerNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CUSTOMER_NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExistsException(
            ProductAlreadyExistsException productAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PRODUCT_ALREADY_EXISTS_WITH_ID_MESSAGE));
    }
    @ExceptionHandler(NegativeBalanceException.class)
    public ResponseEntity<Map<String, String>> handleNegativeBalanceException(
            NegativeBalanceException negativeBalanceException) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, BALANCE_NEGATIVE_MESSAGE));
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<Map<String, String>> invalidValueException(
            InvalidValueException customerNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, customerNotFoundException.getMessage()));
    }


}
