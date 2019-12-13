package testCase;

/**
 * @Description:
 * @auther phantom
 * @create 2019-12-07 下午6:56
 */
public class TestCasefactory {

    public static void product(String object){
        switch (object){
            case "ACMS":
                new GenerateTestCase4ACMS().generateTestCase();
                break;
            case "CUBS":
                new GenerateTestCase4CUBS().generateTestCase();
                break;
            case "ERS":
                new GenerateTestCase4ERS().generateTestCase();
                break;
            case "MOS":
                new GenerateTestCase4MOS().generateTestCase();
                break;
            default:
                new GenerateTestCase4ACMS().generateTestCase();
                new GenerateTestCase4CUBS().generateTestCase();
                new GenerateTestCase4ERS().generateTestCase();
                new GenerateTestCase4MOS().generateTestCase();
                break;
        }
    }

    public static void main(String[] args) {
        TestCasefactory.product("all");
    }
}
