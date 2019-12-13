package testCase;

import constant.Constant;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.File;

/**
 * @Description: write all test frames to file for all objects
 * @auther phantom
 * @create 2019-12-06 &#x4e0b;&#x5348;8:57
 */
public class Utl {


    public static void writeTestFrames(List<String> testFrames, String object){
        StringBuffer stringBuffer = new StringBuffer(512);
        for (String testFrame : testFrames){
            stringBuffer.append(testFrame + "\n");
        }
        File file = new File(Constant.testCasePath + File.separator + "TestFrames4" + object);
        if (file.exists()){
            return;
        }
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
            printWriter.write(stringBuffer.toString());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
