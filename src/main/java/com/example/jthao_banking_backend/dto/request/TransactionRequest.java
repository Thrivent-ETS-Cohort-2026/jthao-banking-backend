package com.example.jthao_banking_backend.dto.request;
import java.math.BigDecimal;

public class TransactionRequest {
    private BigDecimal amount;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
