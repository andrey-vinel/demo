package com.test.generator.demo.v2.model;

import com.test.generator.demo.v2.model.enums.SettlementStatus;
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
public class BatchModel {

    private String batchId;

    private LocalDate batchDate;

    private Long institutionId;

    private String smeId;

    private String merchantId;

    private String terminalId;

    private Long agreementId;

    private String shipmentNumber;

    private LocalDateTime shipmentDate;

    private String currency;

    private Long numberOfTransactions;

    private BigDecimal totalAmountOfTransactions;

    private BigDecimal totalAmountOfMsc;

    private String mscTransactionId;

    private Long settlementNumberOfTransactions;

    private BigDecimal settlementTotalAmountOfTransactions;

    private Long settlementNumberOfSuspendedTransactions;

    private Long settlementTotalAmountOfSuspendedTransactions;

    private SettlementStatus settlementStatus;

    private String payoutReference;

    private LocalDateTime statusTimestamp;

    private LocalDateTime createdOn = LocalDateTime.now();
}
