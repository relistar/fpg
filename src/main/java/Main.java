import com.github.chen0040.fpm.AssocRuleMiner;
import com.github.chen0040.fpm.data.ItemSets;
import com.github.chen0040.fpm.data.MetaData;
import com.github.chen0040.fpm.fpg.FPGrowth;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        /*FileWriter fileWriter = new FileWriter("src/main/transactions.txt");

        Map<String, TreeSet<String>> map = new HashMap<String, TreeSet<String>>();

        Scanner scanner = new Scanner(fr);
        int i = 0;

        while (scanner.hasNextLine() *//*&& i < 100*//*) {
            String line = scanner.nextLine();
            String[] split = line.split(";");

            String key = split[0];
            String value = split[1];

            TreeSet<String> set;

            set = map.get(key);

            if (set == null) {
                set = new TreeSet<>();
            }

            set.add(value);

            map.put(key, set);


            i++;
        }

        for (String key : map.keySet()) {
            String join = String.join(",", map.get(key));
            System.out.println(join);
            fileWriter.write(join + "\n");
        }

        fr.close();
        fileWriter.close();*/

        FileReader fr = new FileReader("src/main/transactions.txt");
        Scanner sc = new Scanner(fr);


        List<List<String>> database = new ArrayList<>();

        int i = 0;

        while (sc.hasNextLine() && i < 1000) {
            String[] split = sc.nextLine().split(",");
            database.add(Arrays.asList(split));
            i++;
        }


        AssocRuleMiner method = new FPGrowth();
        method.setMinSupportLevel(3);

        MetaData metaData = new MetaData(database);

// obtain all frequent item sets with support level not below 2
        /*ItemSets frequent_item_sets = method.minePatterns(database, metaData.getUniqueItems());*/
        ItemSets max_frequent_item_sets = method.findMaxPatterns(database, metaData.getUniqueItems());
        max_frequent_item_sets.stream().forEach(itemSet -> System.out.println("item-set: " + itemSet));


        fr.close();
    }
}
