package testCase;

import com.alibaba.fastjson.JSONObject;
import constant.Constant;
import distance.ObtainDistance;
import distance.ObtainDistance4Object;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.io.File.separator;

/**
 * @Description:
 * @auther phantom
 * @create 2019-12-04 下午9:14
 */
public class GenerateTestCase4ACMS implements GenerateTestCase{
    Map<String, Map<String, String>> testCases;

    public GenerateTestCase4ACMS() {
        testCases = new LinkedHashMap<>();
    }

    @Override
    public void generateTestCase() {
        ObtainDistance obtainDistance = new ObtainDistance4Object(Constant.ACMS);
        // get all test frames
        List<String> testFrames = obtainDistance.getObjectTestFrames();

        // write test frames into file
        Utl.writeTestFrames(testFrames, Constant.ACMS);

        // generate a test case for each test frame
        for (int i = 0; i < testFrames.size(); i++) {
            Map<String, String> testcase = new LinkedHashMap<>();
            String[] choices = testFrames.get(i).split(",");
            for (int j = 0; j < choices.length; j++) {
                if (choices[j].equals("I-1a") || choices[j].equals("I-1b") ||
                        choices[j].equals("I-1c") || choices[j].equals("I-1d")){
                    testcase.put("airClass", getAirClass(choices[j]));
                }else if (choices[j].equals("I-2a") || choices[j].equals("I-2b")){
                    testcase.put("area", getArea(choices[j]));
                }else if (choices[j].equals("I-3a") || choices[j].equals("I-3b")){
                    testcase.put("isStudent", getIsStudent(choices[j]));
                }else if (choices[j].equals("I-4a") || choices[j].equals("I-4b")){
                    testcase.put("luggage", getLuggage(testcase.get("airClass"), testcase.get("isStudent"),
                            testcase.get("area"), choices[j]));
                }else {
                    testcase.put("economicfee", getEconomicFee(choices[j]));
                }
            }
            testCases.put(String.valueOf(i), testcase);
        }
        writeTestCasesIntoJson();
    }

    @Override
    public void writeTestCasesIntoJson() {
        String content = JSONObject.toJSONString(testCases);
        File file = new File(Constant.testCasePath + separator + "ACMS.json");
        if (file.exists()){
            return;
        }

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
            printWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getAirClass(String index){
        if (index.equals("I-1a")){
            return "0";
        }else if (index.equals("I-1b")){
            return "1";
        }else if (index.equals("I-1c")){
            return "2";
        }else{
            return "3";
        }
    }

    private String getArea(String index){
        if (index.equals("I-1a")){
            return "0";
        }else {
            return "1"; //international
        }
    }

    private String getIsStudent(String index){
        if (index.equals("I-3a")){
            return "true";
        }else {
            return "false";
        }
    }

    private String getLuggage(String airClass, String isStudent, String area, String index){
        Random random = new Random();
        if (index.equals("0")){ // <= benchmark
            if (airClass.equals("0")){
                return String.valueOf(random.nextInt(41));
            }else if (airClass.equals("1")){
                return String.valueOf(random.nextInt(31));
            }else if (airClass.equals("2")){
                if (area.equals("1") || isStudent.equals("true")){
                    return String.valueOf(random.nextInt(31));
                }else {
                    return String.valueOf(random.nextInt(21));
                }
            }else {
                return "0";
            }
        }else { // > benchmark
            if (airClass.equals("0")){
                return String.valueOf(random.nextInt(41) + 41);
            }else if (airClass.equals("1")){
                return String.valueOf(random.nextInt(31) + 31);
            }else if (airClass.equals("2")){
                if (area.equals("1") || isStudent.equals("true")){
                    return String.valueOf(random.nextInt(31) + 31);
                }else {
                    return String.valueOf(random.nextInt(21) + 21);
                }
            }else {
                return "0";
            }
        }
    }

    private String getEconomicFee(String index){
        if (index.equals("I-5a")){
            return "0";
        }else {
            return String.valueOf(new Random().nextDouble() * 3000);
        }
    }

    public static void main(String[] args) {
        GenerateTestCase4ACMS generate = new GenerateTestCase4ACMS();
        generate.generateTestCase();
    }

}
