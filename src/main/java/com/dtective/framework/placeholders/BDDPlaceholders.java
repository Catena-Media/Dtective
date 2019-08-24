package com.dtective.framework.placeholders;

import com.dtective.framework.configuration.ParameterMap;
import com.dtective.framework.environment.TestEnvironmentManager;
import com.dtective.framework.test.TestDataCore;
import com.dtective.framework.utils.CharacterSetExamples;
import com.dtective.framework.utils.RandomString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class which contains the general steps available to the webdriver.
 *
 * @since 1.0
 */
public class BDDPlaceholders {

    /**
     * Placeholder identifier for randomization
     */
    private static final String RANDOMIZE = "@Randomize";
    /**
     * Placeholder identifier for fetching the last generated random
     */
    private static final String LASTRANDOM = "@LastRandom";
    /**
     * Placeholder identifier for the current browser type
     */
    private static final String CURRENTBROWSER = "@CurrentBrowserType";
    /**
     * Placeholder identifier for the current date
     */
    private static final String CURRENTDATE = "@CurrentDate";
    /**
     * Placeholder identifier for the current time
     */
    private static final String CURRENTTIME = "@CurrentTime";
    /**
     * Placeholder identifier for the current month
     */
    private static final String CURRENTMONTH = "@CurrentMonth";
    /**
     * Placeholder identifier for the current year
     */
    private static final String CURRENTYEAR = "@CurrentYear";
    private static Logger logger = LogManager.getLogger(BDDPlaceholders.class);
    /**
     * Date formatter for test placeholders
     */
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd");
    /**
     * Time formatter for test placeholders
     */
    private static SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a zzz");
    /**
     * Month formatter for test placeholders
     */
    private static SimpleDateFormat monthFormatter = new SimpleDateFormat("MMMM");
    /**
     * Year formatter for test placeholders
     */
    private static SimpleDateFormat yearFormatter = new SimpleDateFormat("YYYY");

    /**
     * Method to replace all occurences of static parameters like '@CurrentDate' or dynamic ones
     * from the *testEnvironment.json* file or ones stored in the DataStore
     *
     * @param output text that possibly includes placeholders
     * @return text that has all occurences of placeholders replaced
     */
    public static String replace(String output) {

        if (output.contains("@")) {

            if (output.contains(RANDOMIZE)) {
                while (output.contains(RANDOMIZE)) {
                    output = randomizer(output);
                }
            } else if (output.contains(LASTRANDOM)) {
                while (output.contains(LASTRANDOM)) {
                    output = output.replace(LASTRANDOM, RandomString.getLastRandomString());
                }
            }

            if (output.contains("@Current")) {
                while (output.contains(CURRENTBROWSER)) {
                    output = output.replace(CURRENTBROWSER, ParameterMap.getParamBrowserType());
                }
                while (output.contains(CURRENTDATE)) {
                    output = output.replace(CURRENTDATE, dateFormatter.format(Calendar.getInstance().getTime()));
                }
                while (output.contains(CURRENTTIME)) {
                    output = output.replace(CURRENTTIME, timeFormatter.format(Calendar.getInstance().getTime()));
                }
                while (output.contains(CURRENTMONTH)) {
                    output = output.replace(CURRENTMONTH, monthFormatter.format(Calendar.getInstance().getTime()));
                }
                while (output.contains(CURRENTYEAR)) {
                    output = output.replace(CURRENTYEAR, yearFormatter.format(Calendar.getInstance().getTime()));
                }
            }

        }

        if (output.contains("{") && output.contains("}")) {
            output = localParamParser(output);
        }

        return output;
    }

    /**
     * Method to generate a randomizer placeholder to random text.
     * <p>
     * Example commands
     *
     * @param text text including the @Randomize placeholder
     * @return text where the first occurance of the @Randomize placeholder is replaced
     * @Randomize(5) = 5 characters at random
     * @Randomize(500) = 500 characters at random
     * <p>
     * Character sets (default is ALL, UPPER-LOWER case + numbers)
     * @Randomize(5,charset:all)
     * @Randomize(5,charset:letters)
     * @Randomize(5,charset:letters-uppercase)
     * @Randomize(5,charset:letters-lowercase)
     * @Randomize(5,charset:numbers-letters-uppercase)
     * @Randomize(5,charset:numbers-letters-lowercase)
     * @Randomize(5,charset:numbers) Custom charset
     * @Randomize(5,charset:ABCD) Prefix - Suffix can be applied within the BDD file, will not be handled here
     * BDD = I create user "TestUser_@Randomize(5)_Demo"
     * <p>
     * Result = "TestUser_sadADF234_Demo"
     */
    private static String randomizer(String text) {

        String command = text.substring(text.indexOf(RANDOMIZE + "("));
        command = command.substring(0, command.indexOf(")") + 1);

        int length;
        String charset = RandomString.ALPHANUM;

        if (command.contains("charset:")) {
            String param = command.substring(command.indexOf("charset:"), command.length() - 1);

            switch (param) {
                case "charset:all":
                    break;
                case "charset:letters": {
                    charset = RandomString.UPPER + RandomString.LOWER;
                    break;
                }
                case "charset:numbers": {
                    charset = RandomString.DIGITS;
                    break;
                }
                case "charset:letters-uppercase": {
                    charset = RandomString.UPPER;
                    break;
                }
                case "charset:letters-lowercase": {
                    charset = RandomString.LOWER;
                    break;
                }
                case "charset:numbers-letters-lowercase": {
                    charset = RandomString.LOWER + RandomString.DIGITS;
                    break;
                }
                case "charset:numbers-letters-uppercase": {
                    charset = RandomString.UPPER + RandomString.DIGITS;
                    break;
                }
                case "charset:CHINESE": {
                    charset = CharacterSetExamples.CHINESE;
                    break;
                }
                case "charset:RUNIC": {
                    charset = CharacterSetExamples.RUNIC;
                    break;
                }
                case "charset:GREEK": {
                    charset = CharacterSetExamples.GREEK;
                    break;
                }
                case "charset:RUSSIAN": {
                    charset = CharacterSetExamples.RUSSIAN;
                    break;
                }
                case "charset:GEORGIAN": {
                    charset = CharacterSetExamples.GEORGIAN;
                    break;
                }
                case "charset:BRAILLES": {
                    charset = CharacterSetExamples.BRAILLES;
                    break;
                }
                case "charset:all-languages": {
                    charset = CharacterSetExamples.ALL_LANGUAGES;
                    break;
                }
                case "charset:ASCII": {
                    charset = "ASCII";
                    break;
                }
                default:
                    break;
            }
        }

        String lengthText;

        if (command.contains(",")) {
            lengthText = command.substring(command.indexOf("(") + 1, command.indexOf(","));
        } else {
            lengthText = command.substring(command.indexOf("(") + 1, command.indexOf(")"));
        }

        length = Integer.parseInt(lengthText);

        return text.replace(command, "" + new RandomString(length, charset).nextString());
    }

    /**
     * Method to parse string and replace placeholders with values from local data.
     * The placeholder need to be a param name between curly brackets
     *
     * @param value - The string to parse
     * @return - The parsed string with values instead of placeholders
     */
    public static String localParamParser(String value) {
        String toRemove = null;
        String paramName;
        String paramData;

        if (TestEnvironmentManager.getTestEnvironment() == null
                || TestEnvironmentManager.getTestEnvironment().length() == 0) {
            throw new Error("Unable to find or parse testEnvironment file");
        }

        while (value.contains("{") && value.contains("}") && !value.contains("\"")) {

            toRemove = value.substring(value.indexOf('{'), value.indexOf('}') + 1);
            logger.trace(String.format(Thread.currentThread().getName() + " - Found Placeholder : %s |", toRemove));

            paramName = value.substring(value.indexOf('{') + 1, value.indexOf('}'));

            if (TestDataCore.existsInConfigStore(paramName)) {
                paramData = (String) TestDataCore.getConfigStore(paramName);
            } else if (TestDataCore.existsInDataStore(paramName)) {
                paramData = (String) TestDataCore.getDataStore(paramName);
            } else if (TestDataCore.existsInGlobalStore(paramName)) {
                paramData = (String) TestDataCore.getGlobalStore(paramName);
            } else {
                throw new Error("Unable to find the definition of : " + toRemove);
            }

            logger.trace(String.format(Thread.currentThread().getName() + " - Replacing Placeholder : %s with %s |", toRemove, paramData));
            value = value.replace(toRemove, paramData);

        }

        return value;

    }


}
