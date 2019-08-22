package com.domain;

public class Arguments {
    private String fileName = Constants.DEFAULT_VALUE_FILE_NAME;
    private String algorithm = Constants.DEFAULT_VALUE_ALGORITHM;
    private int minSupportLevel = Constants.DEFAULT_VALUE_MIN_SUPPORT;
    private int minSetSize = Constants.DEFAULT_VALUE_MIN_RESULT_SET_SIZE;
    private int maxTransactionSize = Constants.DEFAULT_VALUE_MAX_TRANSACTION_SIZE;

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getMinSupportLevel() {
        return this.minSupportLevel;
    }

    public void setMinSupportLevel(int minSupportLevel) {
        this.minSupportLevel = minSupportLevel;
    }

    public int getMinSetSize() {
        return this.minSetSize;
    }

    public void setMinSetSize(int minSetSize) {
        this.minSetSize = minSetSize;
    }

    public int getMaxTransactionSize() {
        return maxTransactionSize;
    }

    public void setMaxTransactionSize(int maxTransactionSize) {
        this.maxTransactionSize = maxTransactionSize;
    }
}
