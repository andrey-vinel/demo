package com.test.generator.demo.v2.flow;

import com.google.common.collect.Lists;
import com.test.generator.demo.v2.model.TransactionModel;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

public class TerminalProcessor {

    List<String> terminalIds = List.of(
            "TR641252",
            "TR722311",
            "TR851056",
            "TR106326",
            "TR963310",
            "TR345222",
            "TR722311",
            "TR931633",
            "TR723773",
            "TR689234"
    );

    public List<List<List<TransactionModel>>> processWithTerminal(List<List<TransactionModel>> transactions) {
        List<List<List<TransactionModel>>> result = new ArrayList<>();
        int count = 0;
        for (List<TransactionModel> trxList : transactions) {
            var partition = Lists.partition(trxList, trxList.size() / 3).subList(0, 3);
            List<List<TransactionModel>> partiotionResult = new ArrayList<>();

            for (List<TransactionModel> trxPartition : partition) {
                int finalCount = count;
                var transactionModels = trxPartition.stream().map(trx -> {
                    var newTrx = SerializationUtils.clone(trx);
                    newTrx.setTerminalId(terminalIds.get(finalCount));
                    return newTrx;
                }).toList();

                count++;
                partiotionResult.add(transactionModels);
            }
            result.add(partiotionResult);
        }

        return result;
    }
}
