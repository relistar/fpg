package com.executors.impl;

import com.dao.RecommendationsDAO;
import com.dao.impl.FileRecommendationsDAOImpl;
import com.domain.Arguments;
import com.domain.Constants;
import com.domain.ConverterType;
import com.executors.Runner;
import com.factories.MinerResultConverterFactory;
import com.miners.Miner;
import com.parsers.CLIParser;
import com.products.MinerResultOutput;
import com.utils.ExecutionUtils;
import com.utils.Logger;
import org.apache.commons.cli.ParseException;

import java.util.List;
import java.util.Objects;

public class BasicRunner implements Runner {

    private RecommendationsDAO recommendationsDAO = new FileRecommendationsDAOImpl();

    @Override
    public void run(String[] args) {

        Arguments arguments = parseArguments(args);

        List<List<String>> transactions = recommendationsDAO.getTransactions(arguments);

        Miner miner = mine(arguments, transactions);

        MinerResultConverterFactory factory = new MinerResultConverterFactory();

        MinerResultOutput excludedOutput = factory.create(ConverterType.EXCLUDED_TRANSACTIONS, miner.getExcludedTransactions());
        excludedOutput.setData(miner.getExcludedTransactions());
        recommendationsDAO.save(excludedOutput);

        MinerResultOutput itemSetsOutput = factory.create(ConverterType.ITEM_SETS, miner.getItemSets());
        recommendationsDAO.save(itemSetsOutput);

        MinerResultOutput excludedItemSetsOutput = factory.create(ConverterType.EXCLUDED_ITEM_SETS, miner.getExcludedItemSets());
        recommendationsDAO.save(excludedItemSetsOutput);

        MinerResultOutput recommendationsOutput = factory.create(ConverterType.RECOMMENDATIONS, miner.getRecommendations());
        recommendationsDAO.save(recommendationsOutput);

        Logger.log(Constants.MSG_KEY_DONE);
    }

    private Miner mine(Arguments arguments, List<List<String>> transactions) {
        Miner miner = Miner.newBuilder()
                .setAlgorithm(Objects.requireNonNull(arguments).getAlgorithm())
                .setTransactions(transactions)
                .setMinSupportLevel(arguments.getMinSupportLevel())
                .setMinSetSize(arguments.getMinSetSize())
                .setMaxTransactionSize(arguments.getMaxTransactionSize())
                .build();

        miner.mine();

        return miner;
    }

    @Override
    public Arguments parseArguments(String[] args) {
        Arguments arguments = null;

        try {
            arguments = new CLIParser().parse(args);
        } catch (ParseException parseException) {
            ExecutionUtils.stopExecution(Constants.MSG_KEY_NOT_ALL_REQUIRED_OPTIONS_PRESENTED, null);
        }

        return arguments;
    }
}
