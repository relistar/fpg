package com.products.impl;

import com.domain.Constants;
import com.github.chen0040.fpm.data.ItemSet;
import com.products.MinerResultOutput;

import java.util.List;
import java.util.stream.Collectors;

public class ExcludedItemSetsOutput implements MinerResultOutput {

    private String content;

    @Override
    @SuppressWarnings("unchecked")
    public void setData(Object object) {
        List<ItemSet> itemSets = (List<ItemSet>) object;

        content = itemSets.stream()
                .map(ItemSet::toString)
                .collect(Collectors.joining(Constants.SYMBOL_NEW_LINE));
    }

    @Override
    public String getFileName() {
        return Constants.OUTPUT_FILE_EXCLUDED_SETS;
    }

    @Override
    public String getStartMessage() {
        return Constants.MSG_KEY_WRITING_EXCLUDED_SET_FILE_START;
    }

    @Override
    public String getSuccessMessage() {
        return Constants.MSG_KEY_WRITING_EXCLUDED_SET_FILE_SUCCESS;
    }

    @Override
    public String getContent() {
        return content;
    }
}
