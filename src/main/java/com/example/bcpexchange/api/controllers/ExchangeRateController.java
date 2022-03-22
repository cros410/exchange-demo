package com.example.bcpexchange.api.controllers;

import com.example.bcpexchange.context.exchangeRate.application.ListExchangeRate;
import com.example.bcpexchange.context.exchangeRate.application.RegisterExchangeRate;
import com.example.bcpexchange.context.exchangeRate.application.UpdateExchangeRate;
import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final RegisterExchangeRate registerExchangeRate;
    private final ListExchangeRate listExchangeRate;
    private final UpdateExchangeRate updateExchangeRate;

    @GetMapping("/exchange-rate")
    public ResponseEntity<List<ExchangeRate>> listExchangeRate() {
        return ResponseEntity.ok().body(this.listExchangeRate.run());
    }

    @PostMapping("/exchange-rate")
    public ResponseEntity<ExchangeRate> listExchangeRate(@RequestBody ExchangeRate data, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return ResponseEntity.ok().body(this.registerExchangeRate.run(data, principal.getName()));
    }

    @PutMapping("/exchange-rate/{id}")
    public ResponseEntity<ExchangeRate> listExchangeRate(@RequestBody ExchangeRate data, @PathVariable Long id, HttpServletRequest request) {
        data.setId(id);
        Principal principal = request.getUserPrincipal();
        return ResponseEntity.ok().body(this.updateExchangeRate.run(data, principal.getName()));
    }
}
