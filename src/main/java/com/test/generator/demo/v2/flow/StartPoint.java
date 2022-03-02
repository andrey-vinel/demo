package com.test.generator.demo.v2.flow;

import com.test.generator.demo.generators.BatchGenerator;
import com.test.generator.demo.generators.PayoutGenerator;
import com.test.generator.demo.v2.model.TransactionModel;
import com.test.generator.demo.writers.BatchWriter;
import com.test.generator.demo.writers.PayoutWriter;
import com.test.generator.demo.writers.TransactionsWriter;

import java.util.List;
import java.util.stream.Collectors;

public class StartPoint {


    public static void main(String[] args) {
        TransactionParser transactionParser = new TransactionParser();
        AgreementProcessor agreementProcessor = new AgreementProcessor();
        TerminalProcessor terminalProcessor = new TerminalProcessor();
        BatchProcessing batchProcessing = new BatchProcessing();
        TransactionsWriter transactionsWriter = new TransactionsWriter();
        BatchGenerator batchGenerator = new BatchGenerator();
        BatchWriter batchWriter = new BatchWriter();
        PayoutGenerator payoutGenerator = new PayoutGenerator();
        PayoutWriter payoutWriter = new PayoutWriter();

        var transactionModels = transactionParser.parseTransactions("PSP_transact2_20211001_103212_chunk_1.csv");
        var transactionListPerAgreement = agreementProcessor.processWithAgreement(transactionModels);
        var transactionListPerTerminal = terminalProcessor.processWithTerminal(transactionListPerAgreement);
        var strings = batchProcessing.processWithBatch(transactionListPerTerminal);
        var collect = transactionListPerTerminal.stream()
                .flatMap(List::stream)
                .flatMap(List::stream)
                .filter(trx -> !trx.getBatchId().equals("1"))
                .collect(Collectors.toList());
        var result = collect.stream().collect(Collectors.groupingBy(TransactionModel::getAgreementId, Collectors.groupingBy(TransactionModel::getTerminalId, Collectors.groupingBy(TransactionModel::getBatchId))));

        transactionsWriter.writeTransactions(collect);

        var batchModels = batchGenerator.generateBatches(strings);
        var payoutModels = payoutGenerator.generatePayouts(batchModels);

        batchWriter.writeBatches(batchModels);
        payoutWriter.writePayouts(payoutModels);
    }
}
