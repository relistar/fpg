package parsers;

import configurators.OptionsConfigurator;
import domain.Arguments;
import domain.Constants;
import org.apache.commons.cli.*;

public class CLIParser {
    private OptionsConfigurator optionsConfigurator = new OptionsConfigurator();
    private Options options = optionsConfigurator.configure();
    private DefaultParser parser = new DefaultParser();

    public Arguments parse(String[] args) throws ParseException {
        CommandLine commandLine;

        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException parseException) {
            new HelpFormatter().printHelp("java -jar pm", "Options:", options, "\n" + parseException.getMessage(), true);
            throw parseException;
        }

        return extractArguments(commandLine);
    }

    private Arguments extractArguments(CommandLine commandLine) {
        Arguments arguments = new Arguments();

        if (commandLine.hasOption(Constants.SHORT_OPT_FILE)) {
            arguments.setFileName(commandLine.getOptionValue(Constants.SHORT_OPT_FILE));
        }

        if (commandLine.hasOption(Constants.SHORT_OPT_ALGORITHM)) {
            arguments.setAlgorithm(commandLine.getOptionValue(Constants.SHORT_OPT_ALGORITHM));
        }

        if (commandLine.hasOption(Constants.SHORT_OPT_SUPPORT)) {
            arguments.setMinSupportLevel(Integer.parseInt(commandLine.getOptionValue(Constants.SHORT_OPT_SUPPORT)));
        }

        if (commandLine.hasOption(Constants.SHORT_OPT_SET_SIZE)) {
            arguments.setMinSetSize(Integer.parseInt(commandLine.getOptionValue(Constants.SHORT_OPT_SET_SIZE)));
        }

        if (commandLine.hasOption(Constants.SHORT_OPT_MAX_TR_SIZE)) {
            arguments.setMaxTransactionSize(Integer.parseInt(commandLine.getOptionValue(Constants.SHORT_OPT_MAX_TR_SIZE)));
        }

        return arguments;
    }
}
