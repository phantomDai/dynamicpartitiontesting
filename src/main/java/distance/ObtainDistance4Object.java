package distance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import constant.Constant;

import static java.io.File.separator;

/**
 * @Description: calculate the distances between the test frames
 * this class provides two interfaces:(1) calculating the distances map;
 *                                    (2) getting the test frames
 * @auther phantom
 * @create 2019-12-03 下午9:41
 */
public class ObtainDistance4Object extends ObtainDistance{

    public ObtainDistance4Object(String object){
        getAllTestFrames(object);
    }


    /**
     * preparation for calculating the distances between the test frames
     * @return all test frames
     */
    private List<String> getAllTestFrames(String object){
        String testFramesPath = Constant.testFramesPath + separator + object;

        // the content of file
        List<List<String>> content = getTestFramesFileContent(testFramesPath);

        // get test frames
        Set<String> testFarmes = getAllTestFrames(content);

        // delete brace
        testFarmes = delectBrace(testFarmes);

        setObjectTestFrames(new ArrayList<String>(testFarmes));

        return new ArrayList<String>(testFarmes);
    }


    public int[][] getDistanceMap(String object){
        List<String> testframes = getAllTestFrames(object);
        int[][] distanceMap = new int[testframes.size()][testframes.size()];
        for (int i = 0; i < testframes.size(); i++) {
            for (int j = 0; j < testframes.size(); j++) {
                distanceMap[i][j] = calDistanceOfTwoTestFrames(testframes.get(i), testframes.get(j));
            }
        }
        return distanceMap;
    }

}
