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
 * @create 2019-12-06 下午10:43
 */
public class GenerateTestCase4MOS implements GenerateTestCase{
    Map<String, Map<String, String>> testCases = new IdentityHashMap<>();
    @Override
    public void generateTestCase() {
        ObtainDistance obtainDistance = new ObtainDistance4Object(Constant.MOS);
        // get all test frames
        List<String> testFrames = obtainDistance.getObjectTestFrames();

        // write test frames into file
        Utl.writeTestFrames(testFrames, Constant.MOS);

        // generate a test case for each test frame
        for (int i = 0; i < testFrames.size(); i++) {
            Map<String, String> testcase = new LinkedHashMap<>();
            String[] choices = testFrames.get(i).split(",");
            for (int j = 0; j < choices.length; j++) {
                if (choices[j].equals("I-1a") || choices[j].equals("I-1b") || choices[j].equals("I-1c") ||
                        choices[j].equals("I-1d") || choices[j].equals("I-1e")){
                    testcase.put("aircraftmodel", getAircraftmodel(choices[j]));
                }else if (choices[j].equals("I-2a") || choices[j].equals("I-2b")){
                    testcase.put("changeinthenumberofcrewmembers", getChangeinthenumberofcrewmembers(choices[j]));
                }else if (choices[j].equals("I-3a") || choices[j].equals("I-3b") || choices[j].equals("I-3c")){
                    testcase.put("newnumberofcrewmembers", getNewnumberofcrewmembers(testcase.get("aircraftmodel"), choices[j]));
                }else if (choices[j].equals("I-4a") || choices[j].equals("I-4b")){
                    testcase.put("changeinthenumberofpilots", getChangeinthenumberofpilots(choices[j]));
                }else if (choices[j].equals("I-5a") || choices[j].equals("I-5b") || choices[j].equals("I-5c")){
                    testcase.put("newnumberofpilots", getNewnumberofpilots(testcase.get("aircraftmodel"), choices[j]));
                }else if (choices[j].equals("I-6a") || choices[j].equals("I-6b")){
                    testcase.put("numberofchildpassengers", getNumberofchildpassengers(choices[j]));
                }else if (choices[j].equals("I-7a") || choices[j].equals("I-7b")){
                    testcase.put("numberofrequestedbundlesofflowers", getNumberofrequestedbundlesofflowers(choices[j]));
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


    private String getAircraftmodel(String index){
        if (index.equals("I-1a")){
            return "747200";
        }else if (index.equals("I-1b")){
            return "747300";
        }else if (index.equals("I-1c")){
            return "747400";
        }else if (index.equals("I-1d")){
            return "000200";
        }else {
            return "000300";
        }
    }

    private String getChangeinthenumberofcrewmembers(String index){
        if (index.equals("I-2a")){
            return "y";
        }else {
            return "n";
        }
    }

    private String getNewnumberofcrewmembers(String aircraftmodel, String index){
        Random random = new Random();
        if (index.equals("I-3a")){
            switch (aircraftmodel){
                case "747200":
                    return String.valueOf(random.nextInt(5) + 11);
                case "747300":
                    return String.valueOf(random.nextInt(5) + 12);
                case "747400":
                    return String.valueOf(random.nextInt(5) + 15);
                case "000200":
                    return String.valueOf(random.nextInt(5) + 14);
                case "000300":
                    return String.valueOf(random.nextInt(5) + 15);
                default:
                    System.out.println("error");
                    return "null";
            }
        }else if (index.equals("I-3b")){
            switch (aircraftmodel){
                case "747200":
                    return "10";
                case "747300":
                    return "12";
                case "747400":
                    return "14";
                case "000200":
                    return "13";
                case "000300":
                    return "14";
                default:
                    System.out.println("error");
                    return "null";
            }
        }else {
            switch (aircraftmodel){
                case "747200":
                    return String.valueOf(random.nextInt(10));
                case "747300":
                    return String.valueOf(random.nextInt(12));
                case "747400":
                    return String.valueOf(random.nextInt(14));
                case "000200":
                    return String.valueOf(random.nextInt(13));
                case "000300":
                    return String.valueOf(random.nextInt(14));
                default:
                    System.out.println("error");
                    return "null";
            }
        }
    }

    private String getChangeinthenumberofpilots(String index){
        if (index.equals("I-4a")){
            return "y";
        }else {
            return "n";
        }
    }

    private String getNewnumberofpilots(String aircraftmodel, String index){
        Random random = new Random();
        if (index.equals("I-3a")){
            switch (aircraftmodel){
                case "747200":
                    return String.valueOf(random.nextInt(3) + 3);
                case "747300":
                    return String.valueOf(random.nextInt(3) + 4);
                case "747400":
                    return String.valueOf(random.nextInt(3) + 4);
                case "000200":
                    return String.valueOf(random.nextInt(3) + 3);
                case "000300":
                    return String.valueOf(random.nextInt(3) + 4);
                default:
                    System.out.println("error");
                    return "null";
            }
        }else if (index.equals("I-3b")){
            switch (aircraftmodel){
                case "747200":
                    return "2";
                case "747300":
                    return "3";
                case "747400":
                    return "3";
                case "000200":
                    return "2";
                case "000300":
                    return "3";
                default:
                    System.out.println("error");
                    return "null";
            }
        }else {
            switch (aircraftmodel){
                case "747200":
                    return String.valueOf(random.nextInt(2));
                case "747300":
                    return String.valueOf(random.nextInt(3));
                case "747400":
                    return String.valueOf(random.nextInt(3));
                case "000200":
                    return String.valueOf(random.nextInt(2));
                case "000300":
                    return String.valueOf(random.nextInt(3));
                default:
                    System.out.println("error");
                    return "null";
            }
        }
    }

    private String getNumberofchildpassengers(String index){
        Random random = new Random();
        if (index.equals("I-6a")){
            return String.valueOf(random.nextInt(10) + 1);
        }else {
            return "0";
        }
    }

    private String getNumberofrequestedbundlesofflowers(String index){
        Random random = new Random();
        if (index.equals("I-7a")){
            return String.valueOf(random.nextInt(10) + 1);
        }else {
            return "0";
        }
    }
}
