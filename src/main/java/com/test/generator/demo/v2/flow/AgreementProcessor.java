package com.test.generator.demo.v2.flow;

import com.test.generator.demo.v2.model.TransactionModel;
import org.apache.commons.lang3.SerializationUtils;

import java.util.List;

public class AgreementProcessor {

    List<Long> agreementIds = List.of(6723L, 9177L, 5684L);

    public List<List<TransactionModel>> processWithAgreement(List<TransactionModel> transactions) {
        var firstListTransactions = transactions.subList(0, 2978).stream().map(SerializationUtils::clone).toList();
        firstListTransactions.forEach(trx -> trx.setAgreementId(agreementIds.get(0)));
        var secondListTransactions = transactions.subList(2978, 6140).stream().map(SerializationUtils::clone).toList();
        secondListTransactions.forEach(trx -> trx.setAgreementId(agreementIds.get(1)));
        var thirdListTransactions = transactions.subList(6140, 8902).stream().map(SerializationUtils::clone).toList();
        thirdListTransactions.forEach(trx -> trx.setAgreementId(agreementIds.get(2)));


        return List.of(firstListTransactions, secondListTransactions, thirdListTransactions);
    }
}
