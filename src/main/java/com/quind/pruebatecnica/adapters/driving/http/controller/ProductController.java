package com.quind.pruebatecnica.adapters.driving.http.controller;


import com.quind.pruebatecnica.adapters.driving.http.dto.request.product.ProductRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.handlers.IProductHandler;
import com.quind.pruebatecnica.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
