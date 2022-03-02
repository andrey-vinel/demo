package com.test.generator.demo.v2.model.enums;

public enum ProcessingCode {
    REFUND(20),
    CREDIT(26),
    QUASI_CASH(11);

    private final int code;

    ProcessingCode(int code) {
        this.code = code;
    }

    public static ProcessingCode fromCode(int code) {
        for (ProcessingCode type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Processing code with code '%s' not found", code));
    }

    public int getCode() {
        return code;
    }
}
