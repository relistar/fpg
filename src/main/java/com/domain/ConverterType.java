package com.domain;

import com.products.MinerResultOutput;
import com.products.impl.ExcludedItemSetsOutput;
import com.products.impl.ExcludedTransactionsOutput;
import com.products.impl.ItemSetsOutput;
import com.products.impl.RecommendationsOutput;

public enum ConverterType {

    EXCLUDED_TRANSACTIONS {
        @Override
        public MinerResultOutput getInstance() {
            return new ExcludedTransactionsOutput();
        }
    },

    ITEM_SETS {
        @Override
        public MinerResultOutput getInstance() {
            return new ItemSetsOutput();
        }
    },

    EXCLUDED_ITEM_SETS {
        @Override
        public MinerResultOutput getInstance() {
            return new ExcludedItemSetsOutput();
        }
    },

    RECOMMENDATIONS {
        @Override
        public MinerResultOutput getInstance() {
            return new RecommendationsOutput();
        }
    };

    public abstract MinerResultOutput getInstance();
}
