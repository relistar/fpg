package configurators;

import domain.Constants;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsConfigurator {
    public Options configure() {
        Options options = new Options();

        Option fileNameOption = new Option(Constants.SHORT_OPT_FILE, Constants.LONG_OPT_FILE, true, Constants.OPT_DESC_FILE);
        Option algorithmOption = new Option(Constants.SHORT_OPT_ALGORITHM, Constants.LONG_OPT_ALGORITHM, true, Constants.OPT_DESC_ALGORITHM);
        Option minSupportOption = new Option(Constants.SHORT_OPT_SUPPORT, Constants.LONG_OPT_SUPPORT, true, Constants.OPT_DESC_SUPPORT);
        Option minResultSetSizeOption = new Option(Constants.SHORT_OPT_SET_SIZE, Constants.LONG_OPT_SET_SIZE, true, Constants.OPT_DESC_SET_SIZE);

        fileNameOption.setRequired(true);

        algorithmOption.setRequired(false);

        minSupportOption.setRequired(false);
        minSupportOption.setType(Integer.TYPE);

        minResultSetSizeOption.setRequired(false);
        minResultSetSizeOption.setType(Integer.TYPE);

        options.addOption(fileNameOption);
        options.addOption(algorithmOption);
        options.addOption(minSupportOption);
        options.addOption(minResultSetSizeOption);

        return options;
    }
}
