package testCase;

import com.alibaba.fastjson.JSONObject;
import constant.Constant;
import distance.ObtainDistance;
import distance.ObtainDistance4Object;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static java.io.File.separator;

/**
 * @Description:
 * @auther phantom
 * @create 2019-12-06 下午9:22
 */
public class GenerateTestCase4CUBS implements GenerateTestCase {

    Map<String, Map<String, String>> testCases = new IdentityHashMap<>();
    @Override
    public void generateTestCase() {
        ObtainDistance obtainDistance = new ObtainDistance4Object(Constant.CUBS);
        // get all test frames
        List<String> testFrames = obtainDistance.getObjectTestFrames();

        // write test frames into file
        Utl.writeTestFrames(testFrames, Constant.CUBS);

        // generate a test case for each test frame
        for (int i = 0; i < testFrames.size(); i++) {
            Map<String, String> testcase = new LinkedHashMap<>();
            String[] choices = testFrames.get(i).split(",");
            for (int j = 0; j < choices.length; j++) {
                if (choices[j].equals("I-1a") || choices[j].equals("I-1b")){
                    testcase.put("planType", getPlanType(choices[j]));
                }else if (choices[j].equals("I-2a") || choices[j].equals("I-2b")
                         || choices[j].equals("I-2c") || choices.equals("I-2d") ||
                         choices.equals("I-2e") || choices.equals("I-2f")){
                    testcase.put("planFee", getPlanFee(testcase.get("planType"), choices[j]));
                }else if (choices[j].equals("I-3a") || choices[j].equals("I-3b")){
                    testcase.put("talkTime", getTalkTime(testcase.get("planType"), testcase.get("planFee"), choices[j]));
                }else if (choices[j].equals("I-4a") || choices[j].equals("I-4b")){
                    testcase.put("flow", getFlow(testcase.get("planType"), testcase.get("planFee"), choices[j]));
                }
            }
            testCases.put(String.valueOf(i), testcase);
        }
        writeTestCasesIntoJson();
    }

    @Override
    public void writeTestCasesIntoJson() {
        String content = JSONObject.toJSONString(testCases);
        File file = new File(Constant.testCasePath + separator + "CUBS.json");
        if (file.exists()){
            return;
        }

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
            printWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPlanType(String index){
        if (index.equals("I-1a")){
            return "A";
        }else {
            return "B";
        }
    }

    private String getPlanFee(String type, String index){
        if (type.equals("A")){
            if (index.equals("I-2a")){
                return "46";
            }else if (index.equals("I-2b")){
                return "96";
            }else if (index.equals("I-2c")){
                return "126";
            }else if (index.equals("I-2d")){
                return "186";
            }else if (index.equals("I-2e")){
                return "286";
            }else if (index.equals("I-2f")) {
                return "886";
            }else {
                System.out.println("error");
                return "null";
            }
        }else {
            if (index.equals("I-2a")){
                return "46";
            }else if (index.equals("I-2b")){
                return "96";
            }else if (index.equals("I-2c")){
                return "126";
            }else if (index.equals("I-2d")){
                return "186";
            }else {
                System.out.println("error");
                return "null";
            }
        }
    }


    private String getTalkTime(String type, String plan, String index){
        Random random = new Random();
        if (type.equals("A")){
            switch (plan){
                case "46":
                    if (index.equals("I-3a")){
                        return String.valueOf(random.nextInt(50));
                    }else {
                        return String.valueOf(random.nextInt(20) + 50);
                    }
                case "96":
                    if (index.equals("I-3a")){
                        return String.valueOf(random.nextInt(96));
                    }else {
                        return String.valueOf(random.nextInt(20) + 96);
                    }
                case "286":
                    if (index.equals("I-3a")){
                        return String.valueOf(random.nextInt(286));
                    }else {
                        return String.valueOf(random.nextInt(20) + 286);
                    }
                case "886":
                    if (index.equals("I-3a")){
                        return String.valueOf(random.nextInt(3000));
                    }else {
                        return String.valueOf(random.nextInt(100) + 3000);
                    }
                default:
                    System.out.println("error");
                    return "null";
            }
        }else {
            switch (plan){
                case "46":
                    if (index.equals("I-3a")){
                        return String.valueOf(random.nextInt(120));
                    }else {
                        return String.valueOf(random.nextInt(20) + 120);
                    }
                case "96":
                    if (index.equals("I-3a")){
                        return String.valueOf(random.nextInt(450));
                    }else {
                        return String.valueOf(random.nextInt(50) + 450);
                    }
                case "126":
                    if (index.equals("I-3a")){
                        return String.valueOf(random.nextInt(126));
                    }else {
                        return String.valueOf(random.nextInt(50) + 126);
                    }
                case "186":
                    if (index.equals("I-3a")){
                        return String.valueOf(random.nextInt(186));
                    }else {
                        return String.valueOf(random.nextInt(50) + 186);
                    }
                default:
                    System.out.println("erroe");
                    return "null";
            }

        }
    }

    private String getFlow(String type, String plan, String index){
        Random random = new Random();
        if (type.equals("A")){
            switch (plan){
                case "46":
                    if (index.equals("I-4a")){
                        return String.valueOf(random.nextInt(150));
                    }else {
                        return String.valueOf(random.nextInt(20) + 150);
                    }
                case "96":
                    if (index.equals("I-4a")){
                        return String.valueOf(random.nextInt(240));
                    }else {
                        return String.valueOf(random.nextInt(20) + 240);
                    }
                case "286":
                    if (index.equals("I-4a")){
                        return String.valueOf(random.nextInt(900));
                    }else {
                        return String.valueOf(random.nextInt(20) + 900);
                    }
                case "886":
                    if (index.equals("I-4a")){
                        return String.valueOf(random.nextInt(3000));
                    }else {
                        return String.valueOf(random.nextInt(100) + 3000);
                    }
                default:
                    System.out.println("error");
                    return "null";
            }
        }else {
            switch (plan){
                case "46":
                    if (index.equals("I-4a")){
                        return String.valueOf(random.nextInt(40));
                    }else {
                        return String.valueOf(random.nextInt(20) + 40);
                    }
                case "96":
                    if (index.equals("I-4a")){
                        return String.valueOf(random.nextInt(80));
                    }else {
                        return String.valueOf(random.nextInt(20) + 80);
                    }
                case "126":
                    if (index.equals("I-4a")){
                        return String.valueOf(random.nextInt(100));
                    }else {
                        return String.valueOf(random.nextInt(50) + 100);
                    }
                case "186":
                    if (index.equals("I-4a")){
                        return String.valueOf(random.nextInt(150));
                    }else {
                        return String.valueOf(random.nextInt(50) + 150);
                    }
                default:
                    System.out.println("erroe");
                    return "null";
            }

        }
    }
}
