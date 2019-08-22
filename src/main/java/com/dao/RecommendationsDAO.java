package com.dao;

import com.domain.Arguments;
import com.github.chen0040.fpm.data.ItemSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RecommendationsDAO {
    List<List<String>> getTransactions(Arguments arguments);

    void saveRecommendations(Map<String, Set<String>> recommendations);

    void saveItemSets(List<ItemSet> itemSets);

    void saveExcludedItemSets(List<ItemSet> itemSets);

    void writeToFile(String fileName, String content);

    void saveExcludedTransactions(List<List<String>> transactions);
}
