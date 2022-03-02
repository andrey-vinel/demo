package com.test.generator.demo.v2.model;

import com.test.generator.demo.v2.model.enums.PayoutStatus;
import com.test.generator.demo.v2.model.enums.PayoutType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayoutModel {

    private String payoutId;

    private Long institutionId;

    private String smeId;

    private Long accountId;

    private Long agreementId;

    private String accountNumber;

    private String currency;

    private BigDecimal amount;

    private LocalDate executionDate;

    private Long paymentTransactionId;

    private PayoutStatus status;

    private PayoutType type;

    private BigDecimal msc;

    private String mscTransactionId;

    private LocalDateTime statusChangeTimestamp;

    private String statusComment;

    private LocalDateTime createdOn = LocalDateTime.now();

    private LocalDateTime lastChangedOn = LocalDateTime.now();
}
