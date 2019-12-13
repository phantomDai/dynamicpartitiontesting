package constant;

import static java.io.File.separator;

/**
 * describe:
 *
 * @author phantom
 * @date 2019/04/19
 */
public class Constant {

    public static final String ACMS = "ACMS";

    public static final String CUBS = "CUBS";

    public static final String ERS = "ERS";

    public static final String MOS = "MOS";

    public static final String acmssource = "mutants.ACMS.json.sourceCode.AirlinesBaggageBillingService";

    public static final String cubssource = "mutants.CUBS.sourceCode.BillCalculation";

    public static final String parentDir = System.getProperty("user.dir") + separator + "src" + separator +
                                            "main" + separator + "java";

    public static final String testingresultdir = System.getProperty("user.dir") +
            separator + "results";

    public static final String testFramesPath = System.getProperty("user.dir") + separator + "src" + separator
            + "main" + separator + "java" + separator + "testframes";

    public static final String testCasePath = parentDir + separator + "testCase";

    /**the number of repeating times for testing*/
    public static final int repeatNumber = 30;

}
