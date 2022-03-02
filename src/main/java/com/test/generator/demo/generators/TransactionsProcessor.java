/*
package com.test.generator.demo.generators;

import com.flodplain.acquiringtrxmaster.domain.entity.elasticsearch.AcquiringTransactionEntity;
import com.flodplain.agreementsupervisor.domain.entity.AgreementEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.test.generator.demo.generators.AgreementGenerator.TYPES;
import static com.test.generator.demo.generators.GeneratorProperties.*;

@Component
public class TransactionsProcessor {

    private Faker faker = new Faker();

    public List<AcquiringTransactionEntity> processTransactions(List<AcquiringTransactionEntity> allTransactions,
                                                                List<AgreementEntity> allAgreements
    ) {
        int start = 0;
        var agreements = allAgreements.stream().filter(agr -> TYPES.contains(agr.getType())).collect(Collectors.toList());
        var result = new ArrayList<AcquiringTransactionEntity>();

        for (AgreementEntity entity : agreements) {
            var transactionsCountPerAgreement = getTransactionsCountPerAgreement();
            var acquiringTransactionEntities = allTransactions.subList(start, transactionsCountPerAgreement + start);
            start += transactionsCountPerAgreement;
            result.addAll(processTransactionsSetAgreementIds(acquiringTransactionEntities, entity.getId()));
        }
        return result;
    }

    private List<AcquiringTransactionEntity> processTransactionsSetAgreementIds(List<AcquiringTransactionEntity> transactions, Long agreementId) {
        transactions.forEach(trx -> trx.setAgreementId(agreementId));
        var result = new ArrayList<AcquiringTransactionEntity>();

        int start = 0;
        var ids = generateIds(TERMINALS_COUNT_PER_AGREEMENT, "TR");
        for (int i = 0; i < TERMINALS_COUNT_PER_AGREEMENT; i++) {
            var count = transactions.size() / TERMINALS_COUNT_PER_AGREEMENT;
            result.addAll(processTransactionsSetTerminalIds(transactions.subList(start, count + start), ids.get(i)));
            start += count;
        }

        return result;
    }

    private List<AcquiringTransactionEntity> processTransactionsSetTerminalIds(List<AcquiringTransactionEntity> transactions, String id) {
        transactions.forEach(trx -> trx.setTerminalId(id));
        var result = new ArrayList<AcquiringTransactionEntity>();

        int start = 0;
        var ids = generateIds(BATCHES_COUNT_PER_TERMINAL, "ID");
        for (int i = 0; i < BATCHES_COUNT_PER_TERMINAL; i++) {
            var count = faker.number().numberBetween(MIN_TRX_COUNT_PER_BATCH, MAX_TRX_COUNT_PER_BATCH);
            result.addAll(processTransactionsSetBatchIds(transactions.subList(start, count + start), ids.get(i)));
            start += count;
        }

        return result;
    }

    private List<AcquiringTransactionEntity> processTransactionsSetBatchIds(List<AcquiringTransactionEntity> transactions, String id) {
        transactions.forEach(trx -> trx.setBatchId(id));
        return transactions;
    }

    private List<String> generateIds(Integer count, String prefix) {
        var ids = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            ids.add(prefix + faker.number().randomNumber(6, true));
        }
        return ids;
    }

    private Integer getTransactionsCountPerAgreement() {
        return TERMINALS_COUNT_PER_AGREEMENT * BATCHES_COUNT_PER_TERMINAL * MAX_TRX_COUNT_PER_BATCH;
    }

}
*/
