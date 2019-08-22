package com.executors;

import com.dao.RecommendationsDAO;
import com.dao.impl.FileRecommendationsDAOImpl;
import com.domain.Arguments;
import com.domain.Constants;
import com.miners.Miner;
import com.parsers.CLIParser;
import com.utils.ExecutionUtils;
import com.utils.Logger;
import org.apache.commons.cli.ParseException;

import java.util.List;
import java.util.Objects;

public class BasicRunner {

    public void run(String[] args) {
        Arguments arguments = null;

        try {
            arguments = new CLIParser().parse(args);
        } catch (ParseException parseException) {
            ExecutionUtils.stopExecution(Constants.MSG_KEY_NOT_ALL_REQUIRED_OPTIONS_PRESENTED, null);
        }

        RecommendationsDAO recommendationsDAO = new FileRecommendationsDAOImpl();

        List<List<String>> transactions = recommendationsDAO.getTransactions(arguments);

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        Miner miner = Miner.newBuilder()
                .setAlgorithm(Objects.requireNonNull(arguments).getAlgorithm())
                .setTransactions(transactions)
                .setMinSupportLevel(arguments.getMinSupportLevel())
                .setMinSetSize(arguments.getMinSetSize())
                .setMaxTransactionSize(arguments.getMaxTransactionSize())
                .build();

        miner.mine();

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        recommendationsDAO.saveExcludedTransactions(miner.getExcludedTransactions());

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        recommendationsDAO.saveItemSets(miner.getItemSets());

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        recommendationsDAO.saveExcludedItemSets(miner.getExcludedItemSets());

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        recommendationsDAO.saveRecommendations(miner.getRecommendations());

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        Logger.log(Constants.MSG_KEY_DONE);
    }
}
