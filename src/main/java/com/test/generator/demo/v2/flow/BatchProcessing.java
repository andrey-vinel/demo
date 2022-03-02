package com.test.generator.demo.v2.flow;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import com.test.generator.demo.v2.model.KeyVo;
import com.test.generator.demo.v2.model.TransactionModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class BatchProcessing {

    private Faker faker = new Faker();
    Random random = new Random();

    public Set<KeyVo> processWithBatch(List<List<List<TransactionModel>>> transactions) {
        Set<KeyVo> batchIds = new HashSet<>();
        for (List<List<TransactionModel>> byAgreement : transactions) {
            System.out.println("By agreement : " + transactions.size());
            for (List<TransactionModel> byTerminal : byAgreement) {
                System.out.println("By terminal : " + byTerminal.size());
                var lists = Lists.partition(byTerminal, byTerminal.size() / 48).subList(0, 20);

                for (List<TransactionModel> byBatchId : lists) {
                    var id = "ID" + faker.number().randomNumber(6, true);
                    var currency = randomCurrency();
                    long minDay = LocalDateTime.of(2022, 2, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
                    long maxDay = LocalDateTime.of(2022, 2, 5, 12, 59).toEpochSecond(ZoneOffset.UTC);
                    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
                    LocalDateTime randomDate = LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC);

                    batchIds.add(new KeyVo(id,
                            currency,
                            randomDate.toLocalDate(),
                            (long) byBatchId.size(),
                            byBatchId.get(0).getTerminalId(),
                            byBatchId.get(0).getAgreementId(),
                            byBatchId.stream().map(TransactionModel::getTransactionAmount).reduce(BigDecimal.ZERO, BigDecimal::add),
                            byBatchId.stream().map(TransactionModel::getMsc).reduce(BigDecimal.ZERO, BigDecimal::add)
                    ));
                    for (TransactionModel trx : byBatchId) {
                        trx.setBatchId(id);
                        trx.setBatchOpenDate(randomDate.toLocalDate());
                        trx.setProcessingDate(randomDate);
                        trx.setTransactionCurrency(currency);
                        trx.setTransactionDateTime(randomDate);
                    }
                }
            }
        }
        return batchIds;
    }


    private String randomCurrency() {
        int min = 1;
        int max = 3;

        int value = random.nextInt(max + min) + min;

        return switch (value) {
            case 1 -> "USD";
            case 2 -> "HUF";
            case 3 -> "EUR";
            default -> "USD";
        };
    }
}
