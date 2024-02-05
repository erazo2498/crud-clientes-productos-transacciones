package com.quind.pruebatecnica.adapters.driving.http.controller;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.CustomerRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.RequestUpdateCustomerDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.response.CustomerResponseDto;
import com.quind.pruebatecnica.adapters.driving.http.handlers.ICustomerHandler;
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

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerHandler customerHandler;
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@NotNull @PathVariable Long customerId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerHandler.getCustomerById(customerId));
    }

    @Operation(summary = "Add a new customer",
            responses = {
                    @ApiResponse(responseCode = "201", description = "customer created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "422", description = "customer already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
    })
    @PostMapping
    public ResponseEntity<Map<String,String>> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        customerHandler.createCustomer(customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.CUSTOMER_CREATED_MESSAGE));
    }

    @Operation(summary = "Update a customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "customer updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "customer not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "422", description = "customer already exists with ID",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))
            })
    @PutMapping
    public ResponseEntity<Map<String,String>> updateCustomer(@Valid @RequestBody RequestUpdateCustomerDto customerRequestDto){
        customerHandler.updateCustomer(customerRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.CUSTOMER_UPDATED_MESSAGE));
    }
    @Operation(summary = "deleted a customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "customer deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "customer not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateCustomer(@PathVariable Long id){
        customerHandler.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.CUSTOMER_DELETED_MESSAGE));
    }
}
