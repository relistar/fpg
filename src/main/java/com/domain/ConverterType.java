package com.domain;

import com.products.MinerResultOutput;
import com.products.impl.ExcludedItemSetsOutput;
import com.products.impl.ExcludedTransactionsOutput;
import com.products.impl.ItemSetsOutput;
import com.products.impl.RecommendationsOutput;

public enum ConverterType {

    EXCLUDED_TRANSACTIONS {
        @Override
        public MinerResultOutput getInstance(Object object) {
            MinerResultOutput output = new ExcludedTransactionsOutput();
            output.setData(object);

            return output;
        }
    },

    ITEM_SETS {
        @Override
        public MinerResultOutput getInstance(Object object) {
            MinerResultOutput output = new ItemSetsOutput();
            output.setData(object);

            return output;
        }
    },

    EXCLUDED_ITEM_SETS {
        @Override
        public MinerResultOutput getInstance(Object object) {
            MinerResultOutput output = new ExcludedItemSetsOutput();
            output.setData(object);

            return output;
        }
    },

    RECOMMENDATIONS {
        @Override
        public MinerResultOutput getInstance(Object object) {
            MinerResultOutput output = new RecommendationsOutput();
            output.setData(object);

            return output;
        }
    };

    public abstract MinerResultOutput getInstance(Object object);
}
