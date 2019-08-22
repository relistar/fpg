package com.converters;

import com.domain.Constants;
import com.github.chen0040.fpm.data.ItemSet;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MiningResultConverter {
    public static String convertRecommendations(Map<String, Set<String>> recommendations) {
        return recommendations.keySet().stream()
                .map(key -> key + Constants.RECOMMENDATION_KEY_VALUE_DELIMITER + recommendations.get(key))
                .collect(Collectors.joining(Constants.SYMBOL_NEW_LINE));
    }

    public static String convertItemSets(List<ItemSet> itemSets) {
        return itemSets.stream()
                .map(ItemSet::toString)
                .collect(Collectors.joining(Constants.SYMBOL_NEW_LINE));
    }

    public static String convertTransactions(List<List<String>> transactions) {
        return transactions.stream().map(
                set -> StringUtils.join(set, Constants.SYMBOL_WHITESPACE)
        ).collect(Collectors.joining(Constants.SYMBOL_NEW_LINE));
    }
}
