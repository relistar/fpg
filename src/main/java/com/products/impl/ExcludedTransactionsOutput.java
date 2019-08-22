package com.products.impl;

import com.domain.Constants;
import com.products.MinerResultOutput;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ExcludedTransactionsOutput implements MinerResultOutput {

    private String content;

    @Override
    @SuppressWarnings("unchecked")
    public void setData(Object object) {
        List<List<String>> transactions = (List<List<String>>) object;

        content = transactions.stream().map(
                set -> StringUtils.join(set, Constants.SYMBOL_WHITESPACE)
        ).collect(Collectors.joining(Constants.SYMBOL_NEW_LINE));
    }

    @Override
    public String getFileName() {
        return Constants.OUTPUT_FILE_EXCLUDED_TRANSACTIONS;
    }

    @Override
    public String getStartMessage() {
        return Constants.MSG_KEY_WRITING_EXCLUDED_TRANSACTIONS_FILE_START;
    }

    @Override
    public String getSuccessMessage() {
        return Constants.MSG_KEY_WRITING_EXCLUDED_TRANSACTIONS_FILE_SUCCESS;
    }

    @Override
    public String getContent() {
        return content;
    }
}
