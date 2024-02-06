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
    @Operation(summary = "get product by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "returned product",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "product not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productHandler.getProduct(productId));
    }

    @Operation(summary = "activate product by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "returned product",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "product not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "422", description = "product is already activated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/activate/{id}")
    public ResponseEntity<Map<String,String>> activateProduct(@PathVariable Long id){
        productHandler.activateProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PRODUCT_ACTIVATED_MESSAGE));
    }
    @Operation(summary = "inactivate product by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "returned product",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "product not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "422", description = "product is already inactivated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Map<String,String>> inactivateProduct(@PathVariable Long id){
        productHandler.inactivateProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PRODUCT_INACTIVATED_MESSAGE));
    }

    @Operation(summary = "cancel product by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "returned product",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "product not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "422", description = "product is already canceled or balance different to zero",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Map<String,String>> cancelProduct(@PathVariable Long id){
        productHandler.cancelProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PRODUCT_CANCELED_MESSAGE));
    }

}
