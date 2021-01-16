package blanco.restautotest;

import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoNameUtil;
import blanco.commons.util.BlancoStringUtil;
import blanco.restautotest.constants.BlancoRestAutotestConstants;
import blanco.restautotest.message.BlancoRestAutotestMessage;
import blanco.restautotest.valueobject.*;
import blanco.restgenerator.valueobject.ApiTelegram;
import blanco.xml.bind.BlancoXmlBindingUtil;
import blanco.xml.bind.BlancoXmlUnmarshaller;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * autotest定義書の中間XMLファイル形式をパース(読み書き)するクラス。
 *
 * @author tueda
 */
public class BlancoRestAutotestXmlParser {
    /**
     * メッセージ。
     */
    private final BlancoRestAutotestMessage fMsg = new BlancoRestAutotestMessage();

    private int terminateArrayDepth = 0;

    /**
     * 中間XMLファイルのXMLドキュメントをパースして、autotest情報を取得します。
     *
     * @param argMetaXmlSourceFile
     *            中間XMLファイル。
     */
    public List<BlancoRestAutotestTestCaseData> parseTestCase(
            final File argMetaXmlSourceFile) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        final BlancoXmlDocument documentMeta = new BlancoXmlUnmarshaller()
                .unmarshal(argMetaXmlSourceFile);
        if (documentMeta == null) {
            return null;
        }

        // ルートエレメントを取得します。
        final BlancoXmlElement elementRoot = BlancoXmlBindingUtil
                .getDocumentElement(documentMeta);
        if (elementRoot == null) {
            // ルートエレメントが無い場合には処理中断します。
            return null;
        }

        /*
         * BlancoRestAutotest では TestCase のパースはしません。
         * TestCaseのパースにはAPIヘッダの解析が必須であり、それは アプリケーション毎に
         * 違う可能性があるからです。
         */
        //  次に、全ての InputResult を parse します。
        return parseInputResult(elementRoot);
    }

    /**
     * 中間XMLファイル形式のXMLドキュメントをパースして、InputResult情報を取得します。
     *
     * @param argElementRoot
     */
    public List<BlancoRestAutotestTestCaseData> parseInputResult(final BlancoXmlElement argElementRoot) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        List<BlancoRestAutotestTestCaseData> testCaseDataList = new ArrayList<>();

        // sheet(Excelシート)のリストを取得します。
        final List<BlancoXmlElement> listSheet = BlancoXmlBindingUtil
                .getElementsByTagName(argElementRoot, "sheet");

        final int sizeListSheet = listSheet.size();
        for (int index = 0; index < sizeListSheet; index++) {
            final BlancoXmlElement elementSheet = listSheet.get(index);

            if (BlancoRestAutotestUtil.isVerbose) {
                if (elementSheet != null) {
                    System.out.println("Parse InputResult Sheet: " + elementSheet.getAtts().get(0).getValue());
                } else {
                    System.out.println("!!! Parse InputResult elementSheet is null!");
                }
            }

            // InputResult の一覧を取得します。
            BlancoRestAutotestInputResultClassStructure inputResultClassStructure = parseInputResulElementSheet(elementSheet);

            if (inputResultClassStructure != null) {
                System.out.println("InputResult class: " + inputResultClassStructure.getName());

                /*
                 * Input?/Expected? ごとに入っているはずのプロパティリストを
                 * property.property.property という書式で保持する
                 * この変数はシート毎にクリアされる必要がある。
                 */
                Map<String, String> propertyMap = new HashMap<>();
                /*
                 * property.property... という形式で、そのプロパティの横幅（定義書のカラム数）を保持する。
                 * この変数はシート毎にクリアされる必要がある。
                 */
                Map<String, Integer> propertySizeMap = new HashMap<>();
                this.analyzeInputResultClass(inputResultClassStructure.getInputResultFieldList(), propertyMap, propertySizeMap);

                /*
                 * テストケースの入出力値を読み取る
                 */
                String requestId = inputResultClassStructure.getPackage() + "." + inputResultClassStructure.getName() + BlancoNameAdjuster.toClassName(inputResultClassStructure.getMethod()) + "Request";
                String responseId = inputResultClassStructure.getPackage() + "." + inputResultClassStructure.getName() + BlancoNameAdjuster.toClassName(inputResultClassStructure.getMethod()) + "Response";

                this.readInputResultValue(inputResultClassStructure.getName(), requestId, responseId, inputResultClassStructure.getMethod(), inputResultClassStructure.getInputResultFieldList(), propertyMap, propertySizeMap, testCaseDataList);

            } else {
                if (BlancoRestAutotestUtil.isVerbose) {
                    System.out.println("!!! InputResult is null");
                }
            }
        }
        return testCaseDataList;
    }

    /**
     * 中間XMLファイル形式の「sheet」XMLエレメントをパースして、バリューオブジェクト情報を取得します。(common)
     *
     * @param argElementSheet
     *            中間XMLファイルの「sheet」XMLエレメント。
     * @return パースの結果得られたバリューオブジェクト情報。「name」が見つからなかった場合には nullを戻します。
     */
    public BlancoRestAutotestTestCaseClassStructure parseTestCaseElementSheet(
            final BlancoXmlElement argElementSheet) {

        final BlancoRestAutotestTestCaseClassStructure objClassStructure = new BlancoRestAutotestTestCaseClassStructure();

        final List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "autotest-testcase-common");
        if (listCommon == null || listCommon.size() == 0) {
            // commonが無い場合にはスキップします。
            return null;
        }
        final BlancoXmlElement elementCommon = listCommon.get(0);
        final String name = BlancoXmlBindingUtil.getTextContent(
                elementCommon, "name");
        if (BlancoStringUtil.null2Blank(name).trim().length() == 0) {
            // nameが無い場合にはスキップしnullを戻します。
            return null;
        }
        objClassStructure.setName(name);

        objClassStructure.setPackage(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "package"));

        if (objClassStructure.getPackage() == null) {
            throw new IllegalArgumentException(fMsg
                    .getMbvoji01(objClassStructure.getName()));
        }

        objClassStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "description"));
        final String[] lines = BlancoNameUtil.splitString(
                objClassStructure.getDescription(), '\n');
        for (int indexLine = 0; indexLine < lines.length; indexLine++) {
            if (indexLine == 0) {
                objClassStructure.setDescription(lines[indexLine]);
            } else {
                // 複数行の description については、これを分割して格納します。
                // ２行目からは、適切に文字参照エンコーディングが実施されているものと仮定します。
                objClassStructure.getDescriptionList().add(
                        lines[indexLine]);
            }
        }

        objClassStructure.setRequestHeaderImple(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "requestHeaderImple"));

        if (objClassStructure.getRequestHeaderImple() == null) {
            throw new IllegalArgumentException(fMsg
                    .getMbvoji01(objClassStructure.getName()));
        }

        /* 継承情報の取得 */
        final BlancoXmlElement elementExtends = BlancoXmlBindingUtil
                .getElement(argElementSheet, "autotest-testcase-extends");


        objClassStructure.setSuperClass(BlancoXmlBindingUtil.getTextContent(
                elementExtends, "superClass"));

        objClassStructure.setSuperPackage(BlancoXmlBindingUtil.getTextContent(
                elementExtends, "package"));


        final List<BlancoXmlElement> listTestCaseList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, "autotest-testcase-list");
        final BlancoXmlElement elementListRoot = listTestCaseList.get(0);
        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(elementListRoot, "case");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);

            final BlancoRestAutotestTestCaseFieldStructure fieldStructure = new BlancoRestAutotestTestCaseFieldStructure();

            fieldStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "No"));
            if (fieldStructure.getNo() == null
                    || fieldStructure.getNo().trim().length() == 0) {
                continue;
            }
            fieldStructure.setCaseTitle(BlancoXmlBindingUtil.getTextContent(elementList, "caseTitle"));
            fieldStructure.setPrecondition(BlancoXmlBindingUtil.getTextContent(elementList, "precondition"));
            fieldStructure.setApi(BlancoXmlBindingUtil.getTextContent(elementList, "api"));
            fieldStructure.setMethod(BlancoXmlBindingUtil.getTextContent(elementList, "method"));
            fieldStructure.setRefreshToken("true".equalsIgnoreCase(BlancoXmlBindingUtil.getTextContent(elementList, "refreshToken")));
            fieldStructure.setSqlScript(BlancoXmlBindingUtil.getTextContent(elementList, "sqlScript"));
            fieldStructure.setPreScript(BlancoXmlBindingUtil.getTextContent(elementList, "preScript"));
            fieldStructure.setPostScript(BlancoXmlBindingUtil.getTextContent(elementList, "postScript"));
            fieldStructure.setSearchString(BlancoXmlBindingUtil.getTextContent(elementList, "searchString"));
            fieldStructure.setOnDetect("true".equalsIgnoreCase(BlancoXmlBindingUtil.getTextContent(elementList, "onDetect")));
            fieldStructure.setTargetCase(BlancoXmlBindingUtil.getTextContent(elementList, "targetCase"));
            fieldStructure.setScreenId(BlancoXmlBindingUtil.getTextContent(elementList, "screenId"));
            fieldStructure.setShopId(BlancoXmlBindingUtil.getTextContent(elementList, "shopId"));
            fieldStructure.setLang(BlancoXmlBindingUtil.getTextContent(elementList, "lang"));
            fieldStructure.setTz(BlancoXmlBindingUtil.getTextContent(elementList, "timezone"));
            fieldStructure.setCurrency(BlancoXmlBindingUtil.getTextContent(elementList, "currency"));
            fieldStructure.setVersion(BlancoXmlBindingUtil.getTextContent(elementList, "version"));
//            fieldStructure.setTime(Long.parseLong(BlancoXmlBindingUtil.getTextContent(elementList, "time")));
            fieldStructure.setResult(BlancoXmlBindingUtil.getTextContent(elementList, "result"));
            fieldStructure.setErrorCode(BlancoXmlBindingUtil.getTextContent(elementList, "errorCode"));
            fieldStructure.setErrorMessage(BlancoXmlBindingUtil.getTextContent(elementList, "errorMesssage"));
            fieldStructure.setMessageMessage(BlancoXmlBindingUtil.getTextContent(elementList, "errorMesssageNumber"));
            fieldStructure.setMessageCode(BlancoXmlBindingUtil.getTextContent(elementList, "messageCode"));
            fieldStructure.setMessageMessage(BlancoXmlBindingUtil.getTextContent(elementList, "messsageMessage"));
            fieldStructure.setMessageMessageNumber(BlancoXmlBindingUtil.getTextContent(elementList, "messsageNumber"));
            fieldStructure.setBikou(BlancoXmlBindingUtil.getTextContent(elementList, "Bikou"));

            objClassStructure.getTestCaseFieldList().add(fieldStructure);
        }

        return objClassStructure;
    }

    /**
     * 中間XMLファイル形式の「sheet」XMLエレメントをパースして、バリューオブジェクト情報を取得します。(inputResul)
     * また、電文カラム名はcolumnNameListとして、アサーション処理リストをassertionListとして、取得します。
     * @param argElementSheet
     *            中間XMLファイルの「sheet」XMLエレメント。
     * @return パースの結果得られたバリューオブジェクト情報。data行では「InputNo」が見つからなかった場合には nullを戻します。
     */
    public BlancoRestAutotestInputResultClassStructure parseInputResulElementSheet(
            final BlancoXmlElement argElementSheet) {

        final BlancoRestAutotestInputResultClassStructure objClassStructure = new BlancoRestAutotestInputResultClassStructure();

        final List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "autotest-InputResult-common");
        if (listCommon == null || listCommon.size() == 0) {
            // commonが無い場合にはスキップします。
            return null;
        }

        final BlancoXmlElement elementCommon = listCommon.get(0);
        objClassStructure.setName(BlancoXmlBindingUtil.getTextContent(elementCommon, "name"));
        if (objClassStructure.getName() == null) {
            throw new IllegalArgumentException(fMsg
                    .getMbvoji07(objClassStructure.getName(), "name"));
        }

        objClassStructure.setPackage(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "package"));
        if (objClassStructure.getPackage() == null) {
            throw new IllegalArgumentException(fMsg
                    .getMbvoji01(objClassStructure.getName()));
        }

        objClassStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "description"));
        final String[] lines = BlancoNameUtil.splitString(
                objClassStructure.getDescription(), '\n');
        for (int indexLine = 0; indexLine < lines.length; indexLine++) {
            if (indexLine == 0) {
                objClassStructure.setDescription(lines[indexLine]);
            } else {
                // 複数行の description については、これを分割して格納します。
                // ２行目からは、適切に文字参照エンコーディングが実施されているものと仮定します。
                objClassStructure.getDescriptionList().add(
                        lines[indexLine]);
            }
        }

        objClassStructure.setMethod(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "method"));
        if (objClassStructure.getMethod() == null) {
            throw new IllegalArgumentException(fMsg
                    .getMbvoji07(objClassStructure.getName(), "method"));
        }

        final List<BlancoXmlElement> listInputResultList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, "autotest-InputResult-list");
        if (listInputResultList == null || listInputResultList.size() == 0) {
            return null;
        }

        final BlancoXmlElement elementListRoot = listInputResultList.get(0);
        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(elementListRoot, "InputResult");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);

            final BlancoRestAutotestInputResultFieldStructure fieldStructure = new BlancoRestAutotestInputResultFieldStructure();

            fieldStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "InputNo"));
            fieldStructure.setKind(BlancoXmlBindingUtil.getTextContent(
                    elementList, "kind"));
            if (BlancoStringUtil.null2Blank(fieldStructure.getKind()).length() == 0) {
                fMsg.getMbvoji07(objClassStructure.getName(), "kind");
            }

            fieldStructure.setCaseId(BlancoXmlBindingUtil.getTextContent(
                    elementList, "case"));

            fieldStructure.setInput1(BlancoXmlBindingUtil.getTextContent(elementList, "Input1"));
            fieldStructure.setInput2(BlancoXmlBindingUtil.getTextContent(elementList, "Input2"));
            fieldStructure.setInput3(BlancoXmlBindingUtil.getTextContent(elementList, "Input3"));
            fieldStructure.setInput4(BlancoXmlBindingUtil.getTextContent(elementList, "Input4"));
            fieldStructure.setInput5(BlancoXmlBindingUtil.getTextContent(elementList, "Input5"));
            fieldStructure.setInput6(BlancoXmlBindingUtil.getTextContent(elementList, "Input6"));
            fieldStructure.setInput7(BlancoXmlBindingUtil.getTextContent(elementList, "Input7"));
            fieldStructure.setInput8(BlancoXmlBindingUtil.getTextContent(elementList, "Input8"));
            fieldStructure.setInput9(BlancoXmlBindingUtil.getTextContent(elementList, "Input9"));
            fieldStructure.setInput10(BlancoXmlBindingUtil.getTextContent(elementList, "Input10"));
            fieldStructure.setInput11(BlancoXmlBindingUtil.getTextContent(elementList, "Input11"));
            fieldStructure.setInput12(BlancoXmlBindingUtil.getTextContent(elementList, "Input12"));
            fieldStructure.setInput13(BlancoXmlBindingUtil.getTextContent(elementList, "Input13"));
            fieldStructure.setInput14(BlancoXmlBindingUtil.getTextContent(elementList, "Input14"));
            fieldStructure.setInput15(BlancoXmlBindingUtil.getTextContent(elementList, "Input15"));
            fieldStructure.setInput16(BlancoXmlBindingUtil.getTextContent(elementList, "Input16"));
            fieldStructure.setInput17(BlancoXmlBindingUtil.getTextContent(elementList, "Input17"));
            fieldStructure.setInput18(BlancoXmlBindingUtil.getTextContent(elementList, "Input18"));
            fieldStructure.setInput19(BlancoXmlBindingUtil.getTextContent(elementList, "Input19"));
            fieldStructure.setInput20(BlancoXmlBindingUtil.getTextContent(elementList, "Input20"));
            fieldStructure.setInput21(BlancoXmlBindingUtil.getTextContent(elementList, "Input21"));
            fieldStructure.setInput22(BlancoXmlBindingUtil.getTextContent(elementList, "Input22"));
            fieldStructure.setInput23(BlancoXmlBindingUtil.getTextContent(elementList, "Input23"));
            fieldStructure.setInput24(BlancoXmlBindingUtil.getTextContent(elementList, "Input24"));
            fieldStructure.setInput25(BlancoXmlBindingUtil.getTextContent(elementList, "Input25"));
            fieldStructure.setInput26(BlancoXmlBindingUtil.getTextContent(elementList, "Input26"));
            fieldStructure.setInput27(BlancoXmlBindingUtil.getTextContent(elementList, "Input27"));
            fieldStructure.setInput28(BlancoXmlBindingUtil.getTextContent(elementList, "Input28"));
            fieldStructure.setInput29(BlancoXmlBindingUtil.getTextContent(elementList, "Input29"));
            fieldStructure.setInput30(BlancoXmlBindingUtil.getTextContent(elementList, "Input30"));
            fieldStructure.setExpect1(BlancoXmlBindingUtil.getTextContent(elementList, "Expect1"));
            fieldStructure.setExpect2(BlancoXmlBindingUtil.getTextContent(elementList, "Expect2"));
            fieldStructure.setExpect3(BlancoXmlBindingUtil.getTextContent(elementList, "Expect3"));
            fieldStructure.setExpect4(BlancoXmlBindingUtil.getTextContent(elementList, "Expect4"));
            fieldStructure.setExpect5(BlancoXmlBindingUtil.getTextContent(elementList, "Expect5"));
            fieldStructure.setExpect6(BlancoXmlBindingUtil.getTextContent(elementList, "Expect6"));
            fieldStructure.setExpect7(BlancoXmlBindingUtil.getTextContent(elementList, "Expect7"));
            fieldStructure.setExpect8(BlancoXmlBindingUtil.getTextContent(elementList, "Expect8"));
            fieldStructure.setExpect9(BlancoXmlBindingUtil.getTextContent(elementList, "Expect9"));
            fieldStructure.setExpect10(BlancoXmlBindingUtil.getTextContent(elementList, "Expect10"));
            fieldStructure.setExpect11(BlancoXmlBindingUtil.getTextContent(elementList, "Expect11"));
            fieldStructure.setExpect12(BlancoXmlBindingUtil.getTextContent(elementList, "Expect12"));
            fieldStructure.setExpect13(BlancoXmlBindingUtil.getTextContent(elementList, "Expect13"));
            fieldStructure.setExpect14(BlancoXmlBindingUtil.getTextContent(elementList, "Expect14"));
            fieldStructure.setExpect15(BlancoXmlBindingUtil.getTextContent(elementList, "Expect15"));
            fieldStructure.setExpect16(BlancoXmlBindingUtil.getTextContent(elementList, "Expect16"));
            fieldStructure.setExpect17(BlancoXmlBindingUtil.getTextContent(elementList, "Expect17"));
            fieldStructure.setExpect18(BlancoXmlBindingUtil.getTextContent(elementList, "Expect18"));
            fieldStructure.setExpect19(BlancoXmlBindingUtil.getTextContent(elementList, "Expect19"));
            fieldStructure.setExpect20(BlancoXmlBindingUtil.getTextContent(elementList, "Expect20"));
            fieldStructure.setExpect21(BlancoXmlBindingUtil.getTextContent(elementList, "Expect21"));
            fieldStructure.setExpect22(BlancoXmlBindingUtil.getTextContent(elementList, "Expect22"));
            fieldStructure.setExpect23(BlancoXmlBindingUtil.getTextContent(elementList, "Expect23"));
            fieldStructure.setExpect24(BlancoXmlBindingUtil.getTextContent(elementList, "Expect24"));
            fieldStructure.setExpect25(BlancoXmlBindingUtil.getTextContent(elementList, "Expect25"));
            fieldStructure.setExpect26(BlancoXmlBindingUtil.getTextContent(elementList, "Expect26"));
            fieldStructure.setExpect27(BlancoXmlBindingUtil.getTextContent(elementList, "Expect27"));
            fieldStructure.setExpect28(BlancoXmlBindingUtil.getTextContent(elementList, "Expect28"));
            fieldStructure.setExpect29(BlancoXmlBindingUtil.getTextContent(elementList, "Expect29"));
            fieldStructure.setExpect30(BlancoXmlBindingUtil.getTextContent(elementList, "Expect30"));

            objClassStructure.getInputResultFieldList().add(fieldStructure);
        }

        return objClassStructure;
    }

    private void analyzeInputResultClass(
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final Map<String, String> argPropertyMap,
            final Map<String, Integer> argPropertySizeMap
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        /* まず Input がいくつあるか確認する */
        BlancoRestAutotestUtil.inputColumnMax = this.getColumnMax(argFieldStructureList, "Input", BlancoRestAutotestConstants.INPUT_MAX);
        /* Input のプロパティ階層を確認する */
        BlancoRestAutotestUtil.inputNestDepth = this.getLineMax(argFieldStructureList, BlancoRestAutotestConstants.INPUT_RESULT_KIND_PROPERTY, "Input",  BlancoRestAutotestUtil.inputColumnMax);
//        System.out.println("Input Depth = " + BlancoRestAutotestUtil.inputNestDepth);
//        System.out.println("Input Column = " + BlancoRestAutotestUtil.inputColumnMax);
        /* 先に各プロパティの横幅を測ってしまう。 */

        /* Input プロパティツリー (Map) を構成する */
        makePropertyTree(argFieldStructureList, 0, 0, "Input", "", BlancoRestAutotestUtil.inputColumnMax, BlancoRestAutotestUtil.inputNestDepth, argPropertyMap, argPropertySizeMap);

        /* 次に Expected がいくつあるか確認する */
        BlancoRestAutotestUtil.expectedColumnMax = this.getColumnMax(argFieldStructureList, "Expect", BlancoRestAutotestConstants.OUTPUT_MAX);
        /* Expected のプロパティ階層を確認する */
        BlancoRestAutotestUtil.expectedNestDepth = this.getLineMax(argFieldStructureList, BlancoRestAutotestConstants.INPUT_RESULT_KIND_PROPERTY, "Expect",  BlancoRestAutotestUtil.expectedColumnMax);
//        System.out.println("Expect Depth = " + BlancoRestAutotestUtil.expectedNestDepth);
//        System.out.println("Expect Column = " + BlancoRestAutotestUtil.expectedColumnMax);
        /* Expected プロパティツリー (Map) を構成する */
        makePropertyTree(argFieldStructureList, 0, 0, "Expect", "", BlancoRestAutotestUtil.expectedColumnMax, BlancoRestAutotestUtil.expectedNestDepth, argPropertyMap, argPropertySizeMap);

    }

    private int getColumnMax(
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final String argPropertyTag,
            final int argDefaultMax
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int inputMax = 0;
        for (BlancoRestAutotestInputResultFieldStructure fieldStructure : argFieldStructureList) {
            if(fieldStructure.getKind().equals(BlancoRestAutotestConstants.INPUT_RESULT_KIND_PROPERTY)) {
                for (int i = 0; i < argDefaultMax; i++) {
                    String property = argPropertyTag + (i + 1);
                    String value = BlancoRestAutotestUtil.getStringValue(fieldStructure, property);
                    if (value != null) {
                        inputMax = i > inputMax ? i : inputMax;
                    }
                }
            }
        }
        return inputMax + 1;
    }

    private int getPropertySize(
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final int argTargetDepth,
            final int argStartColumn,
            final String argColumnTag,
            final int argColumnMax,
            final int argDepthMax
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        BlancoRestAutotestInputResultFieldStructure fieldStructure = argFieldStructureList.get(argTargetDepth);
        int size = argColumnMax;
        if(fieldStructure != null && fieldStructure.getKind().equals(BlancoRestAutotestConstants.INPUT_RESULT_KIND_PROPERTY)) {
            String startValue = "";
            for (int index = argStartColumn; index < argColumnMax; index++) {
                String tag = argColumnTag + (index + 1);
                String value = BlancoRestAutotestUtil.getStringValue(fieldStructure, tag);
                if (index == argStartColumn) {
                    startValue = value;
                }
                if (this.breakCheck(
                        argFieldStructureList,
                        argTargetDepth,
                        tag,
                        argDepthMax,
                        startValue,
                        value)
                ) {
                    size = index;
                    break;
                }
            }
        }
        return size - argStartColumn;
    }

    private boolean breakCheck(
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final int argTargetDepth,
            final String argTag,
            final int argDepthMax,
            final String argStartValue,
            final String argCurValue
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        boolean isBreak = false;
        if (argCurValue != null && !argCurValue.equals(argStartValue)) {
            isBreak = true;
        } else if (BlancoStringUtil.null2Blank(argCurValue).length() == 0) {
            if (argTargetDepth + 1 >= argDepthMax) {
                isBreak = true;
            } else {
                boolean found = false;
                for (int depthIndex = argTargetDepth + 1; depthIndex < argDepthMax; depthIndex++) {
                    BlancoRestAutotestInputResultFieldStructure nextFieldStructure = argFieldStructureList.get(depthIndex);
                    String nextValue = BlancoRestAutotestUtil.getStringValue(nextFieldStructure, argTag);
                    if (BlancoStringUtil.null2Blank(nextValue).length() > 0) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    isBreak = true;
                }
            }
        }
        return isBreak;
    }

    private int getLineMax(
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final String argKindTag,
            final String argPropertyTag,
            final int argColumnMax
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int lineMax = 0;
        for (BlancoRestAutotestInputResultFieldStructure fieldStructure : argFieldStructureList) {
            if (!fieldStructure.getKind().equals(argKindTag)) {
                break;
            }
            boolean found = false;
            for (int i = 0; i < argColumnMax; i++) {
                String property = argPropertyTag + (i + 1);
                String value = BlancoRestAutotestUtil.getStringValue(fieldStructure, property);
                if (value != null) {
                    lineMax++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                break;
            }
        }
        return lineMax;
    }

    /**
     * caseStart 番目から始まるテストケース定義が何行あるかを返します。
     *
     * @param argFieldStructureList
     * @param caseStart
     * @return
     */
    private int getCaseLineSize(
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final int caseStart
    ) {
        int count = 1;
        String caseId = "";
        for (int index = caseStart + 1; index < argFieldStructureList.size(); index++) {
            BlancoRestAutotestInputResultFieldStructure fieldStructure = argFieldStructureList.get(index);
            if (BlancoStringUtil.null2Blank(fieldStructure.getNo()).length() == 0) {
                break;
            }
            caseId = fieldStructure.getCaseId();
            String kind = fieldStructure.getKind();
            if (BlancoStringUtil.null2Blank(caseId).length() > 0 ||
                    (BlancoStringUtil.null2Blank(kind).length() > 0 &&
            !BlancoRestAutotestConstants.INPUT_RESULT_KIND_VALUE.equals(kind))) {
//                System.out.println("BREAK: No = " + fieldStructure.getNo() + ", caseId = " + caseId);
                break;
            }
            count++;
        }
//        System.out.println("getCaseLineMax: count = " + count + ", size = " + argFieldStructureList.size());
        return count;
    }

    /**
     * 入出力定義書で配列の最終行のindexを返します。
     * 最終行は #* のひとつ手前とします。
     *
     * @param argFieldStructureList
     * @param propertyTag Inputxx / Expectxx
     * @param propertyDepth 0 から。
     * @param searchStartIndex fieldStrucureList に対する index
     * @param caseEndIndex このテストケース定義の最終行のindex
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private int getArrayLastIndex(
        final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
        final String propertyTag,
        final int propertyDepth,
        final int searchStartIndex,
        final int caseEndIndex
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int count = 0;
        String terminator = "#";

        for (int i = 0; i < propertyDepth; i++) {
            terminator += "#";
        }

        boolean found = false;
        for (int index = searchStartIndex + 1; index < caseEndIndex ; index++) {
            BlancoRestAutotestInputResultFieldStructure fieldStructure = argFieldStructureList.get(index);
            if (BlancoStringUtil.null2Blank(fieldStructure.getNo()).length() == 0) {
                break;
            }
            String value = BlancoRestAutotestUtil.getStringValue(fieldStructure, propertyTag);
//            System.out.println("getArrayLastIndex index = " + index + ", value = " + value);
            count++;
            if (value != null && terminator.startsWith(value)) {
                found = true;
                break;
            }
        }
        if (!found) {
            count = 0;
        } else {
            count = searchStartIndex + count - 1;
        }
//        System.out.println("getArrayLineSize: count = " + count)
        return count;
    }

    private String makePropertyTree(
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final int argColumnNumber,
            final int argDepth,
            final String argColumnTag,
            final String argParentProperties,
            final int argColumnMax,
            final int argDepthMax,
            final Map<String, String> argPropertyMap,
            final Map<String, Integer> argPropertySizeMap
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        BlancoRestAutotestInputResultFieldStructure fieldStructure = argFieldStructureList.get(argDepth);
        if (fieldStructure != null) {
            if (argDepth >= argDepthMax || !fieldStructure.getKind().equals(BlancoRestAutotestConstants.INPUT_RESULT_KIND_PROPERTY)) {
                return argParentProperties;
            }

            /* プロパティのサイズ */
            int propSize = Integer.MAX_VALUE;
            for (int index = argColumnNumber; index < argColumnMax; index += propSize) {
                String tag = argColumnTag + (index + 1);
                String value = BlancoRestAutotestUtil.getStringValue(fieldStructure, tag);
//                System.out.println("(" + argDepth + "," + i + ") " + value);
                if (BlancoStringUtil.null2Blank(value).length() == 0) {
                    break;
                }
                /*
                 * ひとつ上も見て、break してないかチェックする
                 */
                if (argDepth > 1 && index != argColumnNumber) {
                    BlancoRestAutotestInputResultFieldStructure parentFieldStructure = argFieldStructureList.get(argDepth - 1);
                    String parentValue = BlancoRestAutotestUtil.getStringValue(parentFieldStructure, tag);
                    if (BlancoStringUtil.null2Blank(parentValue).length() > 0) {
                        break;
                    }
                }
                /*
                 * テストケース作成者の見やすさのために、配列の場合はプロパティ名の後ろに [] を付与することを許可する。（あれば無視するだけ。型チェックとかはしない。）
                 * また、半角スペースより後ろも無視する。
                 */
                int terminal = value.indexOf(" ");
                if (terminal != -1) {
                    value = value.substring(0, terminal);
                }
                terminal = value.indexOf("[]");
                if (terminal != -1) {
                    value = value.substring(0, terminal);
                };
//                System.out.println("makePropertyTree: (" + argDepth + "," + index + ") " + value);

                String propertyList = BlancoStringUtil.null2Blank(argParentProperties).length() > 0 ? argParentProperties + "." + value : value;
                argPropertyMap.put(tag, propertyList);
                makePropertyTree(argFieldStructureList,
                        index,
                        argDepth + 1,
                        argColumnTag,
                        propertyList,
                        argColumnMax,
                        argDepthMax,
                        argPropertyMap,
                        argPropertySizeMap);
                /* このプロパティのサイズを取得する */
                propSize = this.getPropertySize(
                        argFieldStructureList,
                        argDepth,
                        index,
                        argColumnTag,
                        argColumnMax,
                        argDepthMax);
                argPropertySizeMap.put(propertyList, propSize);
                if (BlancoRestAutotestUtil.isVerbose) {
                    System.out.println("makePropertyTree: propertyList = " + propertyList + ", propSize = " + propSize);
                }
            }
        }
        return argParentProperties;
    }

    private void readInputResultValue(
            final String apiSimpleId,
            final String requestId,
            final String responseId,
            final String methodId,
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final Map<String, String> argPropertyMap,
            final Map<String, Integer> argPropertySizeMap,
            final List<BlancoRestAutotestTestCaseData> argTestCaseDataList
    ) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        int startLine = Math.max(BlancoRestAutotestUtil.inputNestDepth, BlancoRestAutotestUtil.expectedNestDepth);

        int caseLineSize = Integer.MAX_VALUE;
        List<String> assertKindList = null;
        for (int index = startLine; index < argFieldStructureList.size() && caseLineSize > 0; index += caseLineSize) {
            if (BlancoRestAutotestUtil.isVerbose) {
                System.out.println("INDEX: " + index);
            }
            BlancoRestAutotestInputResultFieldStructure fieldStructure = argFieldStructureList.get(index);
            if (fieldStructure.getKind().equals(BlancoRestAutotestConstants.INPUT_RESULT_KIND_ASSERT)) {
                /* 比較方法行は読み込んで次へ */
                assertKindList = this.getAssertKindList(fieldStructure);
//                System.out.println("!!! GET Assert Kind List !!!");
                caseLineSize = 1;
                continue;
            }
            caseLineSize = this.getCaseLineSize(argFieldStructureList, index);
//            System.out.println("caseLineMax: " + caseLineMax);
            fieldStructure = argFieldStructureList.get(index);
            if (fieldStructure == null || BlancoStringUtil.null2Blank(fieldStructure.getNo()).length() == 0) {
                break;
            }
            if (BlancoRestAutotestUtil.isVerbose) {
                System.out.println("No: " + fieldStructure.getNo() + ", caseId = " + fieldStructure.getCaseId() + ", caseLineSize = " + caseLineSize);
            }

            BlancoRestAutotestTestCaseData testCaseData = new BlancoRestAutotestTestCaseData();
            argTestCaseDataList.add(testCaseData);
            testCaseData.setCaseId(fieldStructure.getCaseId());
            testCaseData.setInputId(requestId);
            testCaseData.setSimpleInputId(BlancoRestAutotestUtil.getSimpleClassName(requestId));
            testCaseData.setExpectId(responseId);
            testCaseData.setSimpleExpectId(BlancoRestAutotestUtil.getSimpleClassName(responseId));

            /* 入力定義読込 */
            ApiTelegram inputTelegram = this.createTelegram(
                    requestId,
                    argFieldStructureList,
                    index,
                    index + caseLineSize,
                    argPropertyMap,
                    "Input",
                    BlancoRestAutotestUtil.inputColumnMax,
                    argPropertySizeMap
            );
            if (inputTelegram == null) {
                throw new IllegalArgumentException("!!! Can not read Input data !!! for " + fieldStructure.getCaseId());
            }
            testCaseData.setInput(inputTelegram);

            /* 出力定義読込 */
            ApiTelegram expectTelegram = this.createTelegram(
                    responseId,
                    argFieldStructureList,
                    index,
                    index + caseLineSize,
                    argPropertyMap,
                    "Expect",
                    BlancoRestAutotestUtil.expectedColumnMax,
                    argPropertySizeMap
            );
            if (expectTelegram == null) {
                throw new IllegalArgumentException("!!! Can not read Expect data !!! for " + fieldStructure.getCaseId());
            }
            testCaseData.setExpect(expectTelegram);

            testCaseData.setTargetApiSimpleId(apiSimpleId);
            testCaseData.setInputColumnMax(BlancoRestAutotestUtil.inputColumnMax);
            testCaseData.setExpectedColumnMax(BlancoRestAutotestUtil.expectedColumnMax);
            testCaseData.setPropertyMap(argPropertyMap);
            testCaseData.setPropertySizeMap(argPropertySizeMap);
            testCaseData.setAssertKindList(assertKindList);
            testCaseData.setMethod(methodId);
//            System.out.println("testCaseData = " + testCaseData);
            assertKindList = null;
        }
    }

    private List<String> getAssertKindList(
            final BlancoRestAutotestInputResultFieldStructure argFieldStructure
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (!argFieldStructure.getKind().equals(BlancoRestAutotestConstants.INPUT_RESULT_KIND_ASSERT)) {
            return null;
        }
        List<String> assertKindList = new ArrayList<>();
        for (int index = 0; index < BlancoRestAutotestUtil.expectedColumnMax; index++) {
            String kind = BlancoRestAutotestUtil.getStringValue(argFieldStructure, "Expect" + (index + 1));
            if (kind != null) {
                assertKindList.add(kind);
            } else {
                throw new IllegalArgumentException(fMsg.getMbvoji07("比較方法", "kind"));
            }
        }
        return assertKindList;
    }

    public ApiTelegram createTelegram(
            final String telegramId,
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final int caseStartIndex,
            final int caselineMax,
            final Map<String, String> argPropertyMap,
            final String argPropertyTag,
            final int argPropertyMax,
            final Map<String ,Integer> argPropertySizeMap
    ) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ApiTelegram telegram = BlancoRestAutotestUtil.createTelegramById(telegramId);
        if (telegram == null) {
            throw new IllegalArgumentException("Cannot create telegram for " + telegramId);
        }

        int propSize = 1;
        int readLine = 0;
        for (int index = 0; index < argPropertyMax; index += propSize) {
            int readLine0 = 0;
            String columnName = argPropertyTag + (index + 1);
            String propertyList = argPropertyMap.get(columnName);
            if (propertyList != null) {
                String [] propTree = propertyList.split(Pattern.quote("."));
                /* TODO readProperty を呼び出し、戻り値をtestCaseDataにはめ込んで次の in/outへ */
                readLine0 = this.readProperty(
                        telegram,
                        propTree,
                        0,
                        argFieldStructureList,
                        caseStartIndex,
                        caselineMax,
                        argPropertyTag,
                        index + 1,
                        argPropertyMap,
                        argPropertySizeMap,
                        false,
                        "");
                readLine = Math.max(readLine, readLine0);
                /* 次のプロパティで処理されたであろう幅を取得する */
                String nextPropKey = propTree[0];
                propSize = argPropertySizeMap.get(nextPropKey);
            }
        }
        return telegram;
    }

    /**
     *
     * @param argParentObj
     * @param argPropertyList
     * @param argPropertyDepth
     * @param argFieldStructureList
     * @param argCaseLineStart このテストケース定義の開始index
     * @param argCaseLineMax テストケース定義の最大行数
     * @param argColumnTag Input または Expect
     * @param argColumnNumber 1から始まる数値
     * @param argPropertyMap
     * @param argPropertySizeMap property のカラム番号を格納するmap
     * @param argNotSearchProp
     * @return このプロパティを読み取るために消費した行数
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private int readProperty(
            final Object argParentObj,
            final String[] argPropertyList,
            final int argPropertyDepth,
            final List<BlancoRestAutotestInputResultFieldStructure> argFieldStructureList,
            final int argCaseLineStart,
            final int argCaseLineMax,
            final String argColumnTag,
            final int argColumnNumber,
            final Map<String, String> argPropertyMap,
            final Map<String, Integer> argPropertySizeMap,
            final boolean argNotSearchProp,
            final String indent
    ) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        if (argParentObj == null ||
                argPropertyList == null ||
                argFieldStructureList == null ||
                argFieldStructureList.size() == 0 ||
                argPropertyDepth < 0
        ) {
            throw new IllegalArgumentException("引数が不正です。");
        }
        if (argPropertyDepth > argPropertyList.length) {
            throw new IllegalArgumentException("与えられたindexが配列よりも長すぎます。 depth = " + argPropertyDepth + ", length = " + argPropertyList.length);
        }

        int readLine = 0;
        if (argCaseLineStart > argCaseLineMax) {
            /* テストケースデータの終了 */
            return readLine;
        }

        String columnId = argColumnTag + argColumnNumber;
        String propId = argPropertyList[argPropertyDepth];

        if (BlancoRestAutotestUtil.isVerbose) {
            System.out.println(indent + "START readProperty : parentObj = " + argParentObj.getClass().getName() + ", columnId = " + columnId + ", propId = " + propId + ", caseLineStart = " + argCaseLineStart + ", argNotSearchProp = " + argNotSearchProp);
        }

        Object propObj = argParentObj;
        if (!argNotSearchProp && !BlancoRestAutotestUtil.isPrimitiveObject(argParentObj)) {
            propObj = BlancoRestAutotestUtil.getValue(argParentObj, propId);
            if (propObj == null) {
                propObj = BlancoRestAutotestUtil.createObjectFromProperty(argParentObj, propId);
            }
        }

        if (BlancoRestAutotestUtil.isVerbose) {
            System.out.println(indent + "      readProperty : propObj = " + propObj.getClass().getName());
        }

        /*
         * まずオブジェクトの種別を判定する
         * * プリミティブの場合はシートから値を読み取る（1レコードのみ）
         * * Objectの場合はインスタンスを作成し、次のプロパティチェックに進む
         * * Arrayの場合はまずArrayListのインスタンスを作成し、次に総称型を取得し、次のプロパティチェックに進む
         */
        if (BlancoRestAutotestUtil.isPrimitiveObject(propObj)) {
            BlancoRestAutotestInputResultFieldStructure fieldStructure = argFieldStructureList.get(argCaseLineStart);
            if (fieldStructure != null) {
                String value = BlancoRestAutotestUtil.getStringValue(fieldStructure, columnId);
                if (BlancoRestAutotestUtil.isVerbose) {
                    System.out.println(indent + "readProperty: primitive: " + value);
                }
                if (value != null) {
                    /* ここで array の terminator チェック */
                    if (value.startsWith("#")) {
                        this.terminateArrayDepth = value.length();
                        readLine = 0;
                    } else {
                        BlancoRestAutotestUtil.setStringValue(argParentObj, propId, value);
                        readLine = 1;
                    }
                } else {
                    readLine = 0;
                }
            }
        } else if (BlancoRestAutotestUtil.isArrayObject(propObj)) {
            /*
             * Array なので Generic を生成して次のプロパティチェックに進む。
             * なお、field 定義に Generic は明記されている前提である。
             */
            /*
             * この array の最終行の index を取得する
             */
            int arrayEndIndex = this.getArrayLastIndex(
                    argFieldStructureList,
                    columnId,
                    argPropertyDepth,
                    argCaseLineStart,
                    argCaseLineMax
            );

            if (BlancoRestAutotestUtil.isVerbose) {
                System.out.println(indent + "arrayEndIndex = " + arrayEndIndex);
            }

            int readLine0 = 0;
            int arrayCount = 0;
            for (arrayCount = 0; arrayCount < arrayEndIndex + 1 - argCaseLineStart + 1; arrayCount = arrayCount + readLine0) {
                Object genericObj = BlancoRestAutotestUtil.createGenericFromProperty(argParentObj, propId);
                if (genericObj == null) {
                    throw new IllegalArgumentException("Array には総称型の指定が必須です。");
                }
                if (arrayCount == 0) {
                    if (BlancoRestAutotestUtil.isVerbose) {
                        System.out.println(indent + "Generci = " + genericObj.getClass().getName());
                    }
                }
                boolean isPrimitiveGeneric = BlancoRestAutotestUtil.isPrimitiveObject(genericObj);
                if (isPrimitiveGeneric) {
                    BlancoRestAutotestInputResultFieldStructure fieldStructure = argFieldStructureList.get(argCaseLineStart + arrayCount);
                    if (fieldStructure != null) {
                        String value = BlancoRestAutotestUtil.getStringValue(fieldStructure, columnId);
                        if (value != null) {
                            if (!value.startsWith("#")) {
                                BlancoRestAutotestUtil.addToList(propObj, value);
                                if (BlancoRestAutotestUtil.isVerbose) {
                                    System.out.println(indent + "readProperty array: primitive: " + value + " is pushed to " + propObj.getClass().getName());
                                }
                            } else {
                                if (BlancoRestAutotestUtil.isVerbose) {
                                    System.out.println(indent + "readProperty array: primitive: " + value + " is ignored");
                                }
                            }
                            readLine0 = 1;
                            readLine++;
                        } else {
                            break;
                        }
                    }
                } else {
                    readLine0 = readProperty(
                            genericObj,
                            argPropertyList,
                            argPropertyDepth,
                            argFieldStructureList,
                            argCaseLineStart + arrayCount,
                            argCaseLineMax,
                            argColumnTag,
                            argColumnNumber,
                            argPropertyMap,
                            argPropertySizeMap,
                            true,
                            indent + "  ");
                    if (readLine0 > 0) {
                        BlancoRestAutotestUtil.addToList(propObj, genericObj);
                        if (BlancoRestAutotestUtil.isVerbose) {
                            System.out.println(indent + "readProperty array: " + genericObj.getClass().getName() + " is pushed to " + propObj.getClass().getName());
                        }
                        readLine += readLine0;
                    } else {
                        readLine0 = 1;
                        readLine++;
                        break;
                    }
                }
            }
//            readLine += 1; /* 配列のターミネータ分 */
            BlancoRestAutotestUtil.setValue(argParentObj, propId, propObj);
            if (BlancoRestAutotestUtil.isVerbose) {
                System.out.println(indent + "readProperty array: " + propObj.getClass().getName() + " is set to " + argParentObj.getClass().getName() + " on " + propId);
            }
        } else {
            // Primitive でも Array でも無ければその他オブジェクトなので、次のプロパティチェックに進む
            /* このプロパティの幅を取得する */
            String propKey = "";
            for (int i = 0; i < argPropertyDepth + 1; i++) {
                propKey = propKey.length() > 0 ? propKey + "." + argPropertyList[i] : argPropertyList[i];
            }
            int readLine0 = 0;
            boolean hasValue = false;
            int propSize = 0;
            int propertyMax = argPropertySizeMap.get(propKey);
            if (BlancoRestAutotestUtil.isVerbose) {
                System.out.println(indent + "### readProperty object: propKey = " + propKey + ", propMax = " + propertyMax + ", propObj = " + propObj.getClass().getName());
            }
            for (int index = argColumnNumber; index < argColumnNumber + propertyMax; index += propSize) {
                String columnName = argColumnTag + index;
                String propertyList = argPropertyMap.get(columnName);
                if (BlancoRestAutotestUtil.isVerbose) {
                    System.out.println(indent + "readProperty object: columnName = " + columnName + ", propertyList = " + propertyList+ " (" + index + "), depth = " + argPropertyDepth + ", propObj = " + propObj.getClass().getName());
                }
                if (propertyList == null) {
                    throw new IllegalAccessException("No such column exists: " + columnName);
                }
                String[] propTree = propertyList.split(Pattern.quote("."));

                readLine0 = readProperty(
                        propObj,
                        propTree,
                        argPropertyDepth + 1,
                        argFieldStructureList,
                        argCaseLineStart,
                        argCaseLineMax,
                        argColumnTag,
                        index,
                        argPropertyMap,
                        argPropertySizeMap,
                        false,
                        indent + "  ");
                if (readLine0 > 0) {
                    hasValue = true;
                    readLine = Math.max(readLine, readLine0);
                }
                /* 次のプロパティで処理されたであろう幅を取得する */
                propSize = 1;
                if (argPropertyDepth + 1 < propTree.length) {
                    String nextPropKey = "";
                    for (int i = 0; i <= argPropertyDepth + 1; i++) {
                        nextPropKey = nextPropKey.length() > 0 ? nextPropKey + "." + propTree[i] : propTree[i];
                    }
                    propSize = argPropertySizeMap.get(nextPropKey);
                    if (BlancoRestAutotestUtil.isVerbose) {
                        System.out.println(indent + "readProperty object : propSize = " + propSize + " for " + nextPropKey);
                    }
                }
            }
            if (hasValue) {
                if (!argParentObj.getClass().getName().equals(propObj.getClass().getName())) {
                    /* Array から Generic で呼び出された場合は argParentObj と propObj が同じになっている */
                    BlancoRestAutotestUtil.setValue(argParentObj, propId, propObj);
                    if (BlancoRestAutotestUtil.isVerbose) {
                        System.out.println(indent + "readProperty object: " + propObj.getClass().getName() + " is set to " + argParentObj.getClass().getName());
                    }
                }
            } else {
                readLine = 0;
            }
        }

        if (BlancoRestAutotestUtil.isVerbose) {
            System.out.println(indent + "END readProperty : readLine = " + readLine);
        }
        return readLine;
    }
}
