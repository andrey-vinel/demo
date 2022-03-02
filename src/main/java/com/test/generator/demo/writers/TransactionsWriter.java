package com.test.generator.demo.writers;

import com.test.generator.demo.v2.model.TransactionModel;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class TransactionsWriter {

    private static final String CSV_SEPARATOR = ",";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void writeTransactions(List<TransactionModel> transactions) {
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("transactions-data.csv"), StandardCharsets.UTF_8));) {
            br.write("payment_id,card,merchant_name,merchant_id,terminal_id,card_type_name,acq_ref_nr,tr_batch_id,tr_batch_open_date,tr_date_time,tr_type,tr_amount,tr_ccy,msc,tr_ret_ref_nr,tr_approval_id,tr_processing_date,merchant_iban_code,proc_code,issuer_country,proc_region,mcc,merchant_country,tran_region,card_product_type,user_define_field1,user_define_field2,user_define_field3,merchant_legal_name,card_product_class,agreement_id");
            br.newLine();

            for (TransactionModel transaction : transactions) {
                StringBuilder sb = new StringBuilder();

                sb.append(transaction.getPaymentId().toString());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getCard());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getMerchantName());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getMerchantId());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getTerminalId());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getCardType().toString());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getAcquirerRefNumber());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getBatchId());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getBatchOpenDate().toString());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getTransactionDateTime().format(formatter));
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getTransactionType().getCode());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getTransactionAmount().toString());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getTransactionCurrency());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getMsc().toString());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getTransactionRetrievalRefNumber());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getApprovalCode());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getProcessingDate().format(formatter));
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getMerchantIbanCode());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getProcessingCode().getCode());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getIssuerCountry());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getProcessingRegion());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getMcc());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getMerchantCountry());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getRegion());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getCardProductType().toString());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getUserDefineField1());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getUserDefineField2());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getUserDefineField3());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getMerchantLegalName());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getCardProductClass().toString());
                sb.append(CSV_SEPARATOR);

                sb.append(transaction.getAgreementId().toString());

                br.write(sb.toString());
                br.newLine();
            }

            br.flush();
        } catch (FileNotFoundException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
