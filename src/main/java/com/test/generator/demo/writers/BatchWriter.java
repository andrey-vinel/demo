package com.test.generator.demo.writers;

import com.test.generator.demo.v2.model.BatchModel;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Component
public class BatchWriter {

    public void writeBatches(List<BatchModel> batches) {
        try (var br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("batches-data.txt")))) {

            for (BatchModel batch : batches) {
                var format = String.format("INSERT INTO merchant_terminal_batch " +
                        "(batch_id, batch_date, institution_id, sme_id, merchant_id, terminal_id, agreement_id, " +
                        "currency, number_of_transactions,total_amount_of_transactions," +
                        "total_amount_of_msc, settlement_status, created_on,msc_transaction_id,payout_reference) VALUES (");

                var sb = new StringBuilder();
                sb.append("'" + batch.getBatchId() + "',");
                sb.append("'" + batch.getBatchDate() + "',");
                sb.append("'" + batch.getInstitutionId() + "',");
                sb.append("'" + batch.getSmeId() + "',");
                sb.append("'" + batch.getMerchantId() + "',");
                sb.append("'" + batch.getTerminalId() + "',");
                sb.append("'" + batch.getAgreementId() + "',");
                sb.append("'" + batch.getCurrency() + "',");
                sb.append("'" + batch.getNumberOfTransactions() + "',");
                sb.append("'" + batch.getTotalAmountOfTransactions() + "',");
                sb.append("'" + batch.getTotalAmountOfMsc() + "',");
                sb.append("'" + batch.getSettlementStatus() + "',");
                sb.append("'" + batch.getCreatedOn() + "',");
                sb.append("" + (batch.getMscTransactionId() == null ? null : "'" + batch.getMscTransactionId() + "'") + ",");
                sb.append("" + (batch.getPayoutReference() == null ? null : "'" + batch.getPayoutReference() + "'") + ");");

                br.write(format + sb);
                br.newLine();
            }
            br.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
