package com.test.generator.demo.v2.flow;

import com.test.generator.demo.generators.GeneratorProperties;
import com.test.generator.demo.v2.constants.Constants;
import com.test.generator.demo.v2.model.TransactionModel;
import com.test.generator.demo.v2.model.enums.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TransactionParser {

    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<TransactionModel> parseTransactions(String fileName) {
        log.info("Start parsing transactions from csv, file name: '{}'", fileName);
        var transactions = new ArrayList<TransactionModel>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getFileFromResourceAsStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                var attributes = line.split(CSV_SEPARATOR);

                var transaction = createTransaction(attributes);
                transactions.add(transaction);
            }
        } catch (IOException | URISyntaxException ex) {
            log.error("Error importing data. Cannot read '{}' file", fileName, ex);
        }
        log.info("Transactions successfully parsed, number of parsed transactions: '{}'", transactions.size());

        return transactions
                .stream()
                .filter(trx -> !trx.getTerminalId().isEmpty())
                .toList();
    }

    private TransactionModel createTransaction(String[] attributes) {
        return TransactionModel.builder()
                .institutionId(Constants.INSTITUTION_ID)
                .paymentId(Long.parseLong(attributes[0]))
                .card(attributes[1])
                .merchantName(attributes[2])
                .merchantId(attributes[3])
                .terminalId(attributes[4])
                .cardType(CardType.valueOf(attributes[5].toUpperCase()))
                .acquirerRefNumber(attributes[6])
                .batchId(attributes[7])
                .batchOpenDate(LocalDate.parse(attributes[8]))
                .transactionDateTime(LocalDateTime.parse(attributes[9], FORMATTER))
                .transactionType(TransactionType.fromCode(Integer.parseInt(attributes[10])))
                .transactionAmount(BigDecimal.valueOf(Double.parseDouble(attributes[11])))
                .transactionCurrency(attributes[12])
                .msc(BigDecimal.valueOf(Double.parseDouble(attributes[13])))
                .transactionRetrievalRefNumber(attributes[14])
                .approvalCode(attributes[15])
                .processingDate(LocalDateTime.parse(attributes[16], FORMATTER))
                .merchantIbanCode(attributes[17])
                .processingCode(ProcessingCode.fromCode(Integer.parseInt(attributes[18])))
                .issuerCountry(attributes[19])
                .processingRegion(attributes[20])
                .mcc(attributes[21])
                .merchantCountry(attributes[22])
                .region(attributes[23])
                .cardProductType(CardProductType.valueOf(attributes[24].toUpperCase()))
                .userDefineField1(attributes[25])
                .userDefineField2(attributes[26])
                .userDefineField3(attributes[27])
                .merchantLegalName(attributes[28])
                .cardProductClass(CardProductClass.valueOf(attributes[29].toUpperCase()))
                .smeId(Constants.SME_ID)
                .build();
    }

    private InputStream getFileFromResourceAsStream(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File for data import not found! " + fileName);
        }
        return inputStream;
    }

}
