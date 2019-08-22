package com.factories;

import com.domain.Constants;
import com.github.chen0040.fpm.AssocRuleMiner;
import com.github.chen0040.fpm.apriori.Apriori;
import com.github.chen0040.fpm.fpg.FPGrowth;

public class AssocRuleMinerFactory {
    public AssocRuleMiner fabricate(String algorithm) {
        if (Constants.ALGORITHM_APRIORI.equals(algorithm)) {
            return new Apriori();
        }
        return new FPGrowth();
    }
}
