package com.quind.pruebatecnica.adapters.driving.http.controller;


import com.quind.pruebatecnica.adapters.driving.http.dto.request.product.ProductRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction.TransactionRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction.TransferTransactionRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.handlers.IProductHandler;
import com.quind.pruebatecnica.adapters.driving.http.handlers.ITransactionHandler;
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

@RequestMapping("api/transaction")
@RequiredArgsConstructor
@RestController
public class TransactionController {
    private final ITransactionHandler transactionHandler;

    @Operation(summary = "Create a new transaction",
            responses = {
                    @ApiResponse(responseCode = "201", description = "transaction created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))
                    )
    })
    @PostMapping
    public ResponseEntity<Map<String,String>> createTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto){
        transactionHandler.createTransaction(transactionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.TRANSACTION_CREATED_MESSAGE));
    }

    @Operation(summary = "Create a new transfer",
            responses = {
                    @ApiResponse(responseCode = "201", description = "transaction created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))
                    )
            })
    @PostMapping("/transfer")
    public ResponseEntity<Map<String,String>> transferTransaction(@Valid @RequestBody TransferTransactionRequestDto transferTransactionRequestDto){
        transactionHandler.createTransferTransaction(transferTransactionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.TRANSACTION_CREATED_MESSAGE));
    }
}
