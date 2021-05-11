package blanco.restautotest;

import blanco.restautotest.task.BlancoRestAutotestProcessImpl;
import blanco.restautotest.task.valueobject.BlancoRestAutotestProcessInput;
import blanco.restautotest.valueobject.BlancoRestAutotestTestCaseData;
import org.junit.Test;

import java.io.IOException;

public class GenerateAutotestTest {
    @Test
    public void testGenerateAutotest() {
        BlancoRestAutotestProcessInput input = new BlancoRestAutotestProcessInput();
        input.setMetadir("meta/test3");
        input.setEncoding("UTF-8");
        input.setSheetType("php");
        input.setTmpdir("tmpTest");
        input.setSearchTmpdir("tmp/api,tmp/telegrams");
        input.setTargetdir("sample/blanco");
        input.setTargetStyle("maven");
        input.setVerbose(true);
        input.setOutputJson(true);
        input.setLineSeparator("LF");
        input.setJsonDataDir("src/test/resources/json");

        BlancoRestAutotestProcessImpl imple = new BlancoRestAutotestProcessImpl();
        try {
            imple.execute(input);
//            System.out.println("testCaseDataList = " + BlancoRestAutotestUtil.testCaseDataList);
            for (BlancoRestAutotestTestCaseData testCaseData : BlancoRestAutotestUtil.testCaseDataList) {
                String key = testCaseData.getTargetApiSimpleId() + "-" + testCaseData.getMethod() + "-" + testCaseData.getCaseId();
                System.out.println(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
