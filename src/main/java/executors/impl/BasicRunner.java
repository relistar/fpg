package executors.impl;

import dao.RecommendationsDAO;
import dao.impl.FileRecommendationsDAOImpl;
import domain.Arguments;
import domain.Constants;
import executors.Runner;
import miners.Miner;
import utils.Logger;

import java.util.List;

public class BasicRunner implements Runner {
    @Override
    public void run(Arguments arguments) {
        RecommendationsDAO recommendationsDAO = new FileRecommendationsDAOImpl();

        List<List<String>> transactions = recommendationsDAO.getTransactions(arguments);

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        Miner miner = Miner.newBuilder()
                .setAlgorithm(arguments.getAlgorithm())
                .setTransactions(transactions)
                .setMinSupportLevel(arguments.getMinSupportLevel())
                .setMinSetSize(arguments.getMinSetSize())
                .build();

        miner.mine();

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        recommendationsDAO.saveItemSets(miner.getItemSets());

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        recommendationsDAO.saveRecommendations(miner.getRecommendations());

        Logger.log(Constants.MSG_KEY_STEP_SEPARATOR);

        Logger.log(Constants.MSG_KEY_DONE);
    }
}
