package com.quind.pruebatecnica.adapters.driving.http.controller;


import com.quind.pruebatecnica.adapters.driving.http.dto.request.product.ProductRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.response.ProductResponseDto;
import com.quind.pruebatecnica.adapters.driving.http.handlers.IProductHandler;
import com.quind.pruebatecnica.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequestMapping("api/product")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final IProductHandler productHandler;

    @Operation(summary = "Add a new product",
            responses = {
                    @ApiResponse(responseCode = "201", description = "product created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "422", description = "product already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
    })
    @PostMapping
    public ResponseEntity<Map<String,String>> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        productHandler.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PRODUCT_CREATED_MESSAGE));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@NotNull @PathVariable Long productId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productHandler.getProduct(productId));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Map<String,String>> activateProduct(@PathVariable Long id){
        productHandler.activateProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PRODUCT_ACTIVATED_MESSAGE));
    }
    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Map<String,String>> inactivateProduct(@PathVariable Long id){
        productHandler.inactivateProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PRODUCT_INACTIVATED_MESSAGE));
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Map<String,String>> cancelProduct(@PathVariable Long id){
        productHandler.cancelProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PRODUCT_CANCELED_MESSAGE));
    }

}
