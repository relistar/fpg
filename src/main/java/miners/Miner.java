package miners;

import com.github.chen0040.fpm.AssocRuleMiner;
import com.github.chen0040.fpm.data.ItemSet;
import com.github.chen0040.fpm.data.ItemSets;
import com.github.chen0040.fpm.data.MetaData;
import domain.Constants;
import factories.AssocRuleMinerFactory;
import org.apache.commons.lang3.ObjectUtils;
import utils.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Miner {
    private AssocRuleMiner assocRuleMiner;
    private List<List<String>> transactions;
    private int minSetSize;
    private int minSupportLevel;
    private String algorithm;
    private List<ItemSet> itemSets;
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

    public Map<String, Set<String>> getRecommendations() {
        return recommendations;
    }

    public void mine() {
        Logger.log(getMinerName(), Constants.SYMBOL_WHITESPACE, Constants.MSG_KEY_PATTERN_MINING_START_POSTFIX);

        List<String> uniqueItems = new MetaData(transactions).getUniqueItems();
        ItemSets frequentItemSets = assocRuleMiner.minePatterns(transactions, uniqueItems);
        List<ItemSet> itemSets = filterSets(frequentItemSets);

        setItemSets(itemSets);
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

    private List<ItemSet> filterSets(ItemSets sets) {
        return sets.stream().filter(set -> set.getItems().size() >= this.minSetSize).collect(Collectors.toList());
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
