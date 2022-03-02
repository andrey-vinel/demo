package com.test.generator.demo.generators;

import com.github.javafaker.Faker;
import com.test.generator.demo.v2.constants.Constants;
import com.test.generator.demo.v2.model.BatchModel;
import com.test.generator.demo.v2.model.KeyVo;
import com.test.generator.demo.v2.model.enums.SettlementStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BatchGenerator {

    private Faker faker = new Faker();

    List<SettlementStatus> STATUSES = List.of(
            SettlementStatus.PAID, SettlementStatus.PENDING, SettlementStatus.SUBMITTED
    );

    public List<BatchModel> generateBatches(Set<KeyVo> agreements) {
        var list = new ArrayList<BatchModel>();
        agreements.forEach(keyVo -> list.add(BatchModel.builder()
                .batchId(keyVo.id)
                .batchDate(keyVo.date)
                .institutionId(GeneratorProperties.INSTITUTION_ID)
                .smeId(Constants.SME_ID)
                .merchantId("67452")
                .terminalId(keyVo.terminalId)
                .agreementId(keyVo.agreementId)
                .shipmentNumber(faker.address().fullAddress())
                .currency(keyVo.currency)
                .numberOfTransactions(keyVo.count)
                .totalAmountOfTransactions(keyVo.totalAmount)
                .totalAmountOfMsc(keyVo.totalAmountMsc)
                .settlementStatus(STATUSES.get(getNumberBetween(0, STATUSES.size())))
                .statusTimestamp(LocalDateTime.now())
                .createdOn(LocalDateTime.now())
                .build()));

        return list;
    }

    private Integer getNumberBetween(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

}
