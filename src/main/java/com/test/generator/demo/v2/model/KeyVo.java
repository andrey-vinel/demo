package com.test.generator.demo.v2.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class KeyVo {
    public String id;
    public String currency;
    public LocalDate date;
    public Long count;
    public String terminalId;
    public Long agreementId;
    public BigDecimal totalAmount;
    public BigDecimal totalAmountMsc;

    public KeyVo(String id, String currency, LocalDate date, Long count, String terminalId, Long agreementId, BigDecimal totalAmount, BigDecimal totalAmountMsc) {
        this.id = id;
        this.currency = currency;
        this.date = date;
        this.count = count;
        this.terminalId = terminalId;
        this.agreementId = agreementId;
        this.totalAmount = totalAmount;
        this.totalAmountMsc = totalAmountMsc;
    }
}
