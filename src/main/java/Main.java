import com.github.chen0040.fpm.AssocRuleMiner;
import com.github.chen0040.fpm.apriori.Apriori;
import com.github.chen0040.fpm.data.ItemSet;
import com.github.chen0040.fpm.data.ItemSets;
import com.github.chen0040.fpm.data.MetaData;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static String INPUT_FILES_PATH_PREFIX = "src/main/";
    private static String OUTPUT_FILES_PATH_PREFIX = "src/main/output/";
    private static String INPUT_FILE = INPUT_FILES_PATH_PREFIX + "transactions.txt";
    private static String OUTPUT_FILE_TYPE_POSTFIX = ".txt";
    private static String DATE_PATTERN = "HH-mm-ss_dd-MM-yyyy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);


    public static void main(String[] args) throws IOException {

        /*FileWriter fileWriter = new FileWriter("src/main/transactions.txt");

        Map<String, TreeSet<String>> map = new HashMap<String, TreeSet<String>>();

        Scanner scanner = new Scanner(fileReader);
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

        fileReader.close();
        fileWriter.close();*/

        FileReader fileReader = new FileReader(INPUT_FILE);
        Scanner sc = new Scanner(fileReader);


        List<List<String>> database = new ArrayList<>();

        int i = 0;

        while (sc.hasNextLine() && i < 1000) {
            String[] split = sc.nextLine().split(",");
            database.add(Arrays.asList(split));
            i++;
        }

        File file = new File(OUTPUT_FILES_PATH_PREFIX + generateFileName());

        // if file does not exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());

        AssocRuleMiner method = new Apriori();
        method.setMinSupportLevel(5);

        MetaData metaData = new MetaData(database);

        ItemSets frequentItemSets = method.minePatterns(database, metaData.getUniqueItems());
        List<ItemSet> sets = frequentItemSets.getSets();

        for (ItemSet set : sets) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            List<String> items = set.getItems();
            for (int index = 0; index < items.size(); index++) {
                stringBuilder.append(items.get(index));
                if (index != items.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("}");

            fileWriter.write(stringBuilder.toString() + "\n");
        }

        fileReader.close();
        fileWriter.close();

        System.out.println(generateFileName());
    }

    private static String generateFileName() {
        return simpleDateFormat.format(new Date()).toString() + OUTPUT_FILE_TYPE_POSTFIX;
    }
}
