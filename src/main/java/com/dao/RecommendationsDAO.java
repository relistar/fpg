package com.dao;

import com.domain.Arguments;
import com.products.MinerResultOutput;

import java.util.List;

public interface RecommendationsDAO {
    List<List<String>> getTransactions(Arguments arguments);

    void save(String fileName, String content);

    void save(MinerResultOutput output);
}
