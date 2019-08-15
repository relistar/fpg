import domain.Arguments;
import domain.Constants;
import executors.Runner;
import executors.impl.BasicRunner;
import org.apache.commons.cli.ParseException;
import parsers.CLIParser;
import utils.ExecutionUtils;

public class Main {
    public static void main(String[] args) {
        CLIParser parser = new CLIParser();
        Runner runner = new BasicRunner();
        Arguments arguments;

        try {
            arguments = parser.parse(args);
            runner.run(arguments);
        } catch (ParseException parseException) {
            ExecutionUtils.stopExecution(Constants.MSG_KEY_NOT_ALL_REQUIRED_OPTIONS_PRESENTED, null);
        }
    }
}
