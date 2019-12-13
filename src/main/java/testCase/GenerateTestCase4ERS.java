package testCase;

import com.alibaba.fastjson.JSONObject;
import constant.Constant;
import distance.ObtainDistance;
import distance.ObtainDistance4Object;

import javax.management.relation.RelationNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static java.io.File.separator;

/**
 * @Description:
 * @auther phantom
 * @create 2019-12-06 下午10:13
 */
public class GenerateTestCase4ERS implements GenerateTestCase{
    Map<String, Map<String, String>> testCases = new IdentityHashMap<>();
    @Override
    public void generateTestCase() {
        ObtainDistance obtainDistance = new ObtainDistance4Object(Constant.ERS);
        // get all test frames
        List<String> testFrames = obtainDistance.getObjectTestFrames();

        // write test frames into file
        Utl.writeTestFrames(testFrames, Constant.ERS);

        // generate a test case for each test frame
        for (int i = 0; i < testFrames.size(); i++) {
            Map<String, String> testcase = new LinkedHashMap<>();
            String[] choices = testFrames.get(i).split(",");
            for (int j = 0; j < choices.length; j++) {
                if (choices[j].equals("I-1a") || choices[j].equals("I-1b") || choices[j].equals("I-1c")){
                    testcase.put("stafflevel", getStafflevel(choices[j]));
                }else if (choices[j].equals("I-2a") || choices[j].equals("I-2b")
                        || choices[j].equals("I-2c")){
                    testcase.put("actualmonthlymileage", getActualmonthlymileage(choices[j]));
                }else if (choices[j].equals("I-3a") || choices[j].equals("I-3b")
                         || choices[j].equals("I-3c") || choices[j].equals("I-3d")){
                    testcase.put("monthlysalesamount", getMonthlysalesamount(choices[j]));
                }else if (choices[j].equals("I-4a") || choices[j].equals("I-4b")){
                    testcase.put("airfareamount", getAirfareamount(choices[j]));
                }else if (choices.equals("I-5a") || choices.equals("I-5b")){
                    testcase.put("otherexpensesamount", getOtherexpensesamount(choices[j]));
                }
            }
            testCases.put(String.valueOf(i), testcase);
        }
        writeTestCasesIntoJson();
    }

    @Override
    public void writeTestCasesIntoJson() {
        String content = JSONObject.toJSONString(testCases);
        File file = new File(Constant.testCasePath + separator + "ERS.json");
        if (file.exists()){
            return;
        }

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
            printWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStafflevel(String index){
        if (index.equals("I-1a")){
            return "seniormanager";
        }else if (index.equals("I-1b")){
            return "manager";
        }else {
            return "supervisor";
        }
    }

    private String getActualmonthlymileage(String index){
        Random random = new Random();
        if (index.equals("I-2a")){
            return String.valueOf(random.nextInt(3000));
        }else if (index.equals("I-2b")){
            return String.valueOf(random.nextInt(1000) + 3000);
        }else {
            return String.valueOf(random.nextInt(1000) + 4000);
        }
    }

    private String getMonthlysalesamount(String index){
        Random random = new Random();
        if (index.equals("I-3a")){
            return String.valueOf(random.nextInt(50000));
        }else if (index.equals("I-3b")){
            return String.valueOf(random.nextInt(10000) + 50000);
        }else if (index.equals("I-3c")){
            return String.valueOf(random.nextInt(10000) + 80000);
        }else{
            return String.valueOf(random.nextInt(10000) + 100000);
        }
    }

    private String getAirfareamount(String index){
        Random random = new Random();
        if (index.equals("I-4a")){
            return String.valueOf(0);
        }else {
            return String.valueOf(random.nextInt(1000));
        }
    }

    private String getOtherexpensesamount(String index){
        Random random = new Random();
        if (index.equals("I-5a")){
            return String.valueOf(0);
        }else {
            return String.valueOf(random.nextInt(1000));
        }
    }


}
