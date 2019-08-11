package factories;

import com.github.chen0040.fpm.AssocRuleMiner;
import com.github.chen0040.fpm.apriori.Apriori;
import com.github.chen0040.fpm.fpg.FPGrowth;

public class AssocRuleMinerFactory {
    public AssocRuleMiner fabricate(String algorithm) {
        if ("apriori".equals(algorithm)) {
            return new Apriori();
        }
        return new FPGrowth();
    }
}
