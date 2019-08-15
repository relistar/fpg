package dao;

import com.github.chen0040.fpm.data.ItemSet;
import domain.Arguments;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RecommendationsDAO {
    List<List<String>> getTransactions(Arguments arguments);

    void saveRecommendations(Map<String, Set<String>> recommendations);

    void saveItemSets(List<ItemSet> itemSets);
}
