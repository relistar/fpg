import com.github.chen0040.fpm.data.ItemSet;
import domain.Arguments;
import domain.Constants;
import miners.Miner;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import parsers.CLIParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        CLIParser parser = new CLIParser();
        Arguments arguments = null;

        try {
            arguments = parser.parse(args);
        } catch (ParseException parseException) {
            stopExecution("Not all required options are presented!!!", null);
        }

        printStepSeparator();

        printLine("Reading " + Objects.requireNonNull(arguments).getFileName() + "...");

        List<String> lines = readFile(arguments.getFileName());

        printLine("Reading file success!");

        printStepSeparator();

        List<List<String>> database = splitLines(lines);

        Miner miner = Miner.newBuilder()
                .setAlgorithm(arguments.getAlgorithm())
                .setDatabase(database)
                .setMinSupportLevel(arguments.getMinSupportLevel())
                .setMinSetSize(arguments.getMinSetSize())
                .build();

        printLine(miner.getMinerName() + " pattern mining...");

        List<ItemSet> miningResult = miner.mine();

        printLine("Pattern mining success!");

        printStepSeparator();

        String newFileContent = getNewFileContent(miningResult);

        printLine("Writing to file...");

        writeToFile(newFileContent);

        printLine("Writing to file success!");

        printLine("");
        printLine("Done.");

    }

    private static List<List<String>> splitLines(List<String> lines) {
        return lines.stream().map(line -> Arrays.asList(line.split(" "))).collect(Collectors.toList());
    }

    private static void writeToFile(String content) {
        String outputFileName = generateFileName();
        File newFile = new File(Constants.OUTPUT_DIRECTORY + outputFileName);

        try {
            FileUtils.write(newFile, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            stopExecution("Writing to file error!!!", e);
        }
    }

    private static String getNewFileContent(List<ItemSet> sets) {
        StringBuilder stringBuilder = new StringBuilder();

        for (ItemSet set : sets) {
            String join = StringUtils.join(set.getItems(), " ") + "\n";
            stringBuilder.append(join);
        }

        return stringBuilder.toString();
    }

    private static void printStepSeparator() {
        System.out.println("------------------------------------------------------------");
    }

    private static List<String> readFile(String fileName) {
        File file = new File(fileName);

        List<String> lines = new LinkedList<>();

        try {
            lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            stopExecution("Reading file error!!!", e);
        }

        return lines;
    }

    private static void stopExecution(String msg, Exception e) {
        printLine(msg);

        if (e != null)
            e.printStackTrace();

        System.exit(1);
    }

    private static void printLine(String msg) {
        if (msg == null) {
            return;
        }
        System.out.println("- " + msg);
    }

    private static String generateFileName() {
        return Constants.simpleDateFormat.format(new Date()) + Constants.OUTPUT_FILE_FORMAT_POSTFIX;
    }
}
