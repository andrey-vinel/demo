package com.test.generator.demo.v2.model.enums;

public enum TransactionType {
    PURCHASE(5), REFUND(6), PURCHASE_REVERSAL(25), REFUND_REVERSAL(26);

    private final int code;

    TransactionType(int code) {
        this.code = code;
    }

    public static TransactionType fromCode(int code) {
        for (TransactionType type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Transaction Type with code '%s' not found", code));
    }

    public int getCode() {
        return code;
    }
}
