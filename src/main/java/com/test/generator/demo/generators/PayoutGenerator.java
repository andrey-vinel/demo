package com.test.generator.demo.generators;

import com.github.javafaker.Faker;
import com.test.generator.demo.v2.constants.Constants;
import com.test.generator.demo.v2.model.BatchModel;
import com.test.generator.demo.v2.model.PayoutModel;
import com.test.generator.demo.v2.model.enums.PayoutStatus;
import com.test.generator.demo.v2.model.enums.PayoutType;
import com.test.generator.demo.v2.model.enums.SettlementStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PayoutGenerator {

    private Faker faker = new Faker();

    public List<PayoutModel> generatePayouts(List<BatchModel> batches) {
        var paidBatches
                = batches.stream().filter(batch -> batch.getSettlementStatus().equals(SettlementStatus.PAID)).collect(Collectors.toList());

        var result = new ArrayList<PayoutModel>();

        for (BatchModel batch : paidBatches) {

            var payout = PayoutModel.builder()
                    .payoutId("ID" + faker.number().randomNumber(6, true))
                    .institutionId(GeneratorProperties.INSTITUTION_ID)
                    .smeId(Constants.SME_ID)
                    .accountId(null)
                    .agreementId(batch.getAgreementId())
                    .accountNumber(null)
                    .currency(batch.getCurrency())
                    .amount(batch.getTotalAmountOfTransactions())
                    .executionDate(LocalDate.now())
                    .paymentTransactionId(faker.number().randomNumber(6, true))
                    .status(PayoutStatus.PAID)
                    .type(PayoutType.PAYOUT)
                    .msc(batch.getTotalAmountOfMsc())
                    .mscTransactionId("t" + faker.number().randomNumber(6, true))
                    .statusChangeTimestamp(LocalDateTime.now())
                    .statusComment("test")
                    .createdOn(LocalDateTime.now())
                    .lastChangedOn(LocalDateTime.now())
                    .build();

            batch.setPayoutReference(payout.getPayoutId());
            batch.setMscTransactionId(payout.getMscTransactionId());

            result.add(payout);
        }

        return result;
    }
}
