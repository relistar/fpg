package com.miners;

import com.domain.Constants;
import com.factories.AssocRuleMinerFactory;
import com.github.chen0040.fpm.AssocRuleMiner;
import com.github.chen0040.fpm.data.ItemSet;
import com.github.chen0040.fpm.data.ItemSets;
import com.github.chen0040.fpm.data.MetaData;
import com.utils.Logger;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;

public class Miner {

    private AssocRuleMiner assocRuleMiner;
    private List<List<String>> transactions;
    private List<List<String>> excludedTransactions;
    private int minSetSize;
    private int maxTransactionSize;
    private int minSupportLevel;
    private String algorithm;
    private List<ItemSet> itemSets;
    private List<ItemSet> excludedItemSets;
    private Map<String, Set<String>> recommendations;

    private Miner() {
    }

    private String getMinerName() {
        return this.assocRuleMiner.getClass().getSimpleName();
    }

    public List<ItemSet> getItemSets() {
        return itemSets;
    }

    private void setItemSets(List<ItemSet> itemSets) {
        this.itemSets = itemSets;
    }

    public List<ItemSet> getExcludedItemSets() {
        return excludedItemSets;
    }

    private void setExcludedItemSets(List<ItemSet> excludedItemSets) {
        this.excludedItemSets = excludedItemSets;
    }

    private void setTransactions(List<List<String>> transactions) {
        this.transactions = transactions;
    }

    public List<List<String>> getExcludedTransactions() {
        return excludedTransactions;
    }

    private void setExcludedTransactions(List<List<String>> excludedTransactions) {
        this.excludedTransactions = excludedTransactions;
    }

    public Map<String, Set<String>> getRecommendations() {
        return recommendations;
    }

    public void mine() {
        Logger.log(getMinerName(), Constants.SYMBOL_WHITESPACE, Constants.MSG_KEY_PATTERN_MINING_START_POSTFIX);

        filterTransactions();

        List<String> uniqueItems = new MetaData(transactions).getUniqueItems();
        ItemSets frequentItemSets = assocRuleMiner.minePatterns(transactions, uniqueItems);

        filterSets(frequentItemSets);
        extractRecommendations();

        Logger.log(Constants.MSG_KEY_PATTERN_MINING_SUCCESS);
    }

    private void extractRecommendations() {
        Map<String, Set<String>> recommendations = new HashMap<>();

        for (ItemSet set : this.itemSets) {
            List<String> items = set.getItems();
            for (String item : items) {
                recommendations.computeIfAbsent(item, k -> new TreeSet<>());

                Set<String> ins = new TreeSet<>(recommendations.get((item)));

                List<String> clone = ObjectUtils.clone(items);
                clone.remove(item);
                ins.addAll(clone);

                recommendations.put(item, ins);
            }
        }

        this.recommendations = recommendations;
    }

    private void filterSets(ItemSets sets) {
        List<ItemSet> itemSets = new ArrayList<>();
        List<ItemSet> excluded = new ArrayList<>();

        sets.stream().forEach(set -> {
            if (set.getItems().size() >= this.minSetSize) {
                itemSets.add(set);
            } else {
                excluded.add(set);
            }
        });

        setItemSets(itemSets);
        setExcludedItemSets(excluded);
    }

    private void filterTransactions() {
        List<List<String>> transactions = new ArrayList<>();
        List<List<String>> excluded = new ArrayList<>();

        this.transactions.forEach(row -> {
            if (row.size() < this.maxTransactionSize) {
                transactions.add(row);
            } else {
                excluded.add(row);
            }
        });

        setTransactions(transactions);
        setExcludedTransactions(excluded);
    }

    public static Builder newBuilder() {
        return new Miner().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setAlgorithm(String algorithm) {
            Miner.this.algorithm = algorithm;

            return this;
        }

        public Builder setTransactions(List<List<String>> database) {
            Miner.this.transactions = database;

            return this;
        }

        public Builder setMinSetSize(int minSetSize) {
            Miner.this.minSetSize = minSetSize;

            return this;
        }

        public Builder setMaxTransactionSize(int maxTransactionSize) {
            Miner.this.maxTransactionSize = maxTransactionSize;

            return this;
        }

        public Builder setMinSupportLevel(int minSupportLevel) {
            Miner.this.minSupportLevel = minSupportLevel;

            return this;
        }

        public Miner build() {
            AssocRuleMinerFactory factory = new AssocRuleMinerFactory();

            Miner.this.assocRuleMiner = factory.fabricate(algorithm);
            Miner.this.assocRuleMiner.setMinSupportLevel(Miner.this.minSupportLevel);
            return Miner.this;
        }
    }
}
