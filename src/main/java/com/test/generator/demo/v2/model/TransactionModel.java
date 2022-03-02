package com.test.generator.demo.v2.model;

import com.test.generator.demo.v2.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionModel implements Serializable {

    private Long paymentId;

    private Long institutionId;

    private String smeId;

    private String card;

    private Long agreementId;

    private String merchantName;

    private String merchantId;

    private String terminalId;

    private CardType cardType;

    private String acquirerRefNumber;

    private String batchId;

    private LocalDate batchOpenDate;

    private LocalDateTime transactionDateTime;

    private TransactionType transactionType;

    private BigDecimal transactionAmount;

    private String transactionCurrency;

    private BigDecimal msc;

    private String mscTransactionId;

    private BigDecimal exchangeRate;

    private String transactionRetrievalRefNumber;

    private String approvalCode;

    private LocalDateTime processingDate;

    private ProcessingCode processingCode;

    private String merchantIbanCode;

    private String issuerCountry;

    private String processingRegion;

    private String mcc;

    private String merchantCountry;

    private String region;

    private CardProductType cardProductType;

    private String userDefineField1;

    private String userDefineField2;

    private String userDefineField3;

    private String merchantLegalName;

    private CardProductClass cardProductClass;
}
