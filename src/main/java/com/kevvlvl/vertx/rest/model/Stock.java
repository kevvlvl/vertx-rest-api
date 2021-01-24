package com.kevvlvl.vertx.rest.model;

import java.math.BigDecimal;

public class Stock {

    private String symbol;
    private BigDecimal amount;

    public Stock(String symbol, BigDecimal amount) {
        this.symbol = symbol;
        this.amount = amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
