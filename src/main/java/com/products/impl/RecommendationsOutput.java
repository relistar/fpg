package com.products.impl;

import com.domain.Constants;
import com.products.MinerResultOutput;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RecommendationsOutput implements MinerResultOutput {

    private String content;

    @Override
    @SuppressWarnings("unchecked")
    public void setData(Object object) {
        Map<String, Set<String>> recommendations = (Map<String, Set<String>>) object;

        content = recommendations.keySet().stream()
                .map(key -> key + Constants.RECOMMENDATION_KEY_VALUE_DELIMITER + recommendations.get(key))
                .collect(Collectors.joining(Constants.SYMBOL_NEW_LINE));
    }

    @Override
    public String getFileName() {
        return Constants.OUTPUT_FILE_MAP;
    }

    @Override
    public String getStartMessage() {
        return Constants.MSG_KEY_WRITING_MAP_FILE_START;
    }

    @Override
    public String getSuccessMessage() {
        return Constants.MSG_KEY_WRITING_MAP_FILE_SUCCESS;
    }

    @Override
    public String getContent() {
        return content;
    }
}
