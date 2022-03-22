package com.example.bcpexchange.api.controllers;

import com.example.bcpexchange.context.transaction.application.CreateTransaction;
import com.example.bcpexchange.context.transaction.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {
    private final CreateTransaction createTransaction;

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction data, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return ResponseEntity.ok().body(this.createTransaction.run(data, principal.getName()));
    }
}
