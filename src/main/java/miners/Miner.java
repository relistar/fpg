package miners;

import com.github.chen0040.fpm.AssocRuleMiner;
import com.github.chen0040.fpm.data.ItemSet;
import com.github.chen0040.fpm.data.ItemSets;
import com.github.chen0040.fpm.data.MetaData;
import factories.AssocRuleMinerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Miner {

    private AssocRuleMiner assocRuleMiner;
    private List<List<String>> database;
    private int minSetSize;
    private int minSupportLevel;
    private String algorithm;

    private Miner() {
    }

    public List<ItemSet> mine() {
        List<String> uniqueItems = new MetaData(database).getUniqueItems();
        ItemSets frequentItemSets = assocRuleMiner.minePatterns(database, uniqueItems);

        return filterSets(frequentItemSets, minSetSize);
    }

    private List<ItemSet> filterSets(ItemSets sets, int minSetSize) {
        return sets.stream().filter(set -> set.getItems().size() >= minSetSize).collect(Collectors.toList());
    }

    public String getMinerName() {
        return this.assocRuleMiner.getClass().getSimpleName();
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

        public Builder setDatabase(List<List<String>> database) {
            Miner.this.database = database;

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
