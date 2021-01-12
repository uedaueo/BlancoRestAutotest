package blanco.restautotest;

import blanco.restautotest.task.BlancoRestAutotestProcessImpl;
import blanco.restautotest.task.valueobject.BlancoRestAutotestProcessInput;
import org.junit.Test;

import java.io.IOException;

public class GenerateAutotestTest {
    @Test
    public void testGenerateAutotest() {
        BlancoRestAutotestProcessInput input = new BlancoRestAutotestProcessInput();
        input.setMetadir("meta/test");
        input.setEncoding("UTF-8");
        input.setSheetType("php");
        input.setTmpdir("tmpTest");
        input.setTargetdir("sample/blanco");
        input.setTargetStyle("maven");
        input.setVerbose(true);
        input.setOutputJson(true);
        input.setLineSeparator("LF");

        BlancoRestAutotestProcessImpl imple = new BlancoRestAutotestProcessImpl();
        try {
            imple.execute(input);
//            System.out.println("testCaseDataList = " + BlancoRestAutotestUtil.testCaseDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
