package distance;

import java.io.*;
import java.util.*;

/**
 * @Description:
 * @auther phantom
 * @create 2019-12-03 下午9:54
 */
public class ObtainDistance {

    protected List<String> objectTestFrames;

    /**
     * read content of file
     * @param path
     * @return
     */
    protected List<List<String>> getTestFramesFileContent(String path){
        List<List<String>> content = new ArrayList<>();
        File file = new File(path);

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String temp = "";
            while((temp = bufferedReader.readLine()) != null){
                List<String> tempList = new ArrayList<>();
                String[] tempTestFrames = temp.split(";");
                tempList.add(tempTestFrames[0]);
                tempList.add(tempTestFrames[1]);
                content.add(tempList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * get all test frames
     * @param content
     * @return
     */
    protected Set getAllTestFrames(List<List<String>> content){
        Set<String> testFrames = new HashSet();
        content.forEach(alist ->{
            alist.forEach(ele ->{
                testFrames.add(ele);
            });
        });
        return testFrames;
    }

    /**
     * delete brace of test frame
     * @param testFarmes
     * @return
     */
    protected Set delectBrace(Set<String> testFarmes){
        Set<String> newTestFarmes = new HashSet<>();
        for (String testFarme : testFarmes){
            testFarme = testFarme.substring(1, testFarme.length() - 1);
            newTestFarmes.add(testFarme);
        }
        return newTestFarmes;
    }

    /**
     * calculate the distance between the two test frames
     * @param oneFrame
     * @param twoFrame
     * @return
     */
    protected int calDistanceOfTwoTestFrames(String oneFrame, String twoFrame){
        String[] oneList = oneFrame.split(",");
        String[] twoList = twoFrame.split(",");
        int distance = 0;
        if (oneList.length != twoList.length){
            distance = (oneList.length > twoList.length)? (oneList.length - twoList.length) : (twoList.length - oneList.length);
            if (oneList.length > twoList.length){
                for (int i = 0; i < twoList.length; i++) {
                    if (!twoList[i].equals(oneList[i])){
                        distance += 1;
                    }
                }
            }else {
                for (int i = 0; i < oneList.length; i++) {
                    if (!twoList[i].equals(oneList[i])){
                        distance += 1;
                    }
                }
            }
        }else {
            for (int i = 0; i < oneList.length; i++) {
                if (!oneList[i].equals(twoList[i])){
                    distance += 1;
                }
            }
        }
        return distance;
    }

    public List<String> getObjectTestFrames() {
        return objectTestFrames;
    }

    public void setObjectTestFrames(List<String> objectTestFrames) {
        this.objectTestFrames = objectTestFrames;
    }
}
