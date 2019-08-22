package com.dao.impl;

import com.dao.RecommendationsDAO;
import com.domain.Arguments;
import com.domain.Constants;
import com.products.MinerResultOutput;
import com.utils.ExecutionUtils;
import com.utils.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class FileRecommendationsDAOImpl implements RecommendationsDAO {
    private String outputFolderPath;

    private static List<List<String>> splitLines(List<String> lines) {
        return lines.stream().map(line -> Arrays.asList(line.split(Constants.SYMBOL_WHITESPACE))).collect(Collectors.toList());
    }

    private static List<String> readFile(String fileName) {
        Logger.log(Constants.MSG_KEY_READING_FILE_START_PREFIX, Constants.SYMBOL_WHITESPACE, fileName, Constants.MSG_KEY_ELLIPSIS);

        File file = new File(fileName);
        List<String> lines = new ArrayList<>();

        try {
            lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            ExecutionUtils.stopExecution(Constants.MSG_KEY_READING_FILE_ERROR, e);
        }

        Logger.log(Constants.MSG_KEY_READING_FILE_SUCCESS);

        return lines;
    }

    @Override
    public void save(String fileName, String content) {
        if (outputFolderPath == null) {
            outputFolderPath = generateNewFolderPath();
        }

        File file = new File(Constants.OUTPUT_DIRECTORY + outputFolderPath + fileName);

        try {
            FileUtils.write(file, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            ExecutionUtils.stopExecution(Constants.MSG_KEY_WRITING_FILE_ERROR, e);
        }
    }

    private static String generateNewFolderPath() {
        return Constants.SIMPLE_DATE_FORMAT.format(new Date()) + Constants.PATH_DELIMITER;
    }

    @Override
    public List<List<String>> getTransactions(Arguments arguments) {
        List<String> lines = readFile(arguments.getFileName());

        return splitLines(Objects.requireNonNull(lines));
    }

    @Override
    public void save(MinerResultOutput output) {
        Logger.log(output.getStartMessage());

        save(output.getFileName(), output.getContent());

        Logger.log(output.getSuccessMessage());
    }
}
