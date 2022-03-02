package com.test.generator.demo.writers;

import com.test.generator.demo.v2.model.PayoutModel;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Component
public class PayoutWriter {

    public void writePayouts(List<PayoutModel> payouts) {
        try (var br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("payouts-data.txt")))) {

            for (PayoutModel payout : payouts) {
                var format = String.format("INSERT INTO payout_to_merchant " +
                        "(payout_id, institution_id, sme_id, account_id, account_number, currency, " +
                        "amount, execution_date,agreement_id," +
                        "msc, msc_transaction_id,type,payment_transaction_id,status, created_on,last_changed_on) VALUES (");

                var sb = new StringBuilder();
                sb.append("'" + payout.getPayoutId() + "',");
                sb.append("'" + payout.getInstitutionId() + "',");
                sb.append("'" + payout.getSmeId() + "',");
                sb.append("'55',");
                sb.append("'1',");
                sb.append("'" + payout.getCurrency() + "',");
                sb.append("'" + payout.getAmount() + "',");
                sb.append("'" + payout.getExecutionDate() + "',");
                sb.append("'" + payout.getAgreementId() + "',");
                sb.append("'" + payout.getMsc() + "',");
                sb.append("'" + payout.getMscTransactionId() + "',");
                sb.append("'" + payout.getType() + "',");
                sb.append("'" + payout.getPaymentTransactionId() + "',");
                sb.append("'" + payout.getStatus() + "',");
                sb.append("'" + payout.getCreatedOn() + "',");
                sb.append("'" + payout.getLastChangedOn() + "');");

                br.write(format + sb);
                br.newLine();
            }
            br.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
