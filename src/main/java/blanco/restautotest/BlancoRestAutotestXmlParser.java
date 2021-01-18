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
            fieldStructure.setInput31(BlancoXmlBindingUtil.getTextContent(elementList, "Input31"));
            fieldStructure.setInput32(BlancoXmlBindingUtil.getTextContent(elementList, "Input32"));
            fieldStructure.setInput33(BlancoXmlBindingUtil.getTextContent(elementList, "Input33"));
            fieldStructure.setInput34(BlancoXmlBindingUtil.getTextContent(elementList, "Input34"));
            fieldStructure.setInput35(BlancoXmlBindingUtil.getTextContent(elementList, "Input35"));
            fieldStructure.setInput36(BlancoXmlBindingUtil.getTextContent(elementList, "Input36"));
            fieldStructure.setInput37(BlancoXmlBindingUtil.getTextContent(elementList, "Input37"));
            fieldStructure.setInput38(BlancoXmlBindingUtil.getTextContent(elementList, "Input38"));
            fieldStructure.setInput39(BlancoXmlBindingUtil.getTextContent(elementList, "Input39"));
            fieldStructure.setInput40(BlancoXmlBindingUtil.getTextContent(elementList, "Input40"));
            fieldStructure.setInput41(BlancoXmlBindingUtil.getTextContent(elementList, "Input41"));
            fieldStructure.setInput42(BlancoXmlBindingUtil.getTextContent(elementList, "Input42"));
            fieldStructure.setInput43(BlancoXmlBindingUtil.getTextContent(elementList, "Input43"));
            fieldStructure.setInput44(BlancoXmlBindingUtil.getTextContent(elementList, "Input44"));
            fieldStructure.setInput45(BlancoXmlBindingUtil.getTextContent(elementList, "Input45"));
            fieldStructure.setInput46(BlancoXmlBindingUtil.getTextContent(elementList, "Input46"));
            fieldStructure.setInput47(BlancoXmlBindingUtil.getTextContent(elementList, "Input47"));
            fieldStructure.setInput48(BlancoXmlBindingUtil.getTextContent(elementList, "Input48"));
            fieldStructure.setInput49(BlancoXmlBindingUtil.getTextContent(elementList, "Input49"));
            fieldStructure.setInput50(BlancoXmlBindingUtil.getTextContent(elementList, "Input50"));
            fieldStructure.setInput51(BlancoXmlBindingUtil.getTextContent(elementList, "Input51"));
            fieldStructure.setInput52(BlancoXmlBindingUtil.getTextContent(elementList, "Input52"));
            fieldStructure.setInput53(BlancoXmlBindingUtil.getTextContent(elementList, "Input53"));
            fieldStructure.setInput54(BlancoXmlBindingUtil.getTextContent(elementList, "Input54"));
            fieldStructure.setInput55(BlancoXmlBindingUtil.getTextContent(elementList, "Input55"));
            fieldStructure.setInput56(BlancoXmlBindingUtil.getTextContent(elementList, "Input56"));
            fieldStructure.setInput57(BlancoXmlBindingUtil.getTextContent(elementList, "Input57"));
            fieldStructure.setInput58(BlancoXmlBindingUtil.getTextContent(elementList, "Input58"));
            fieldStructure.setInput59(BlancoXmlBindingUtil.getTextContent(elementList, "Input59"));
            fieldStructure.setInput60(BlancoXmlBindingUtil.getTextContent(elementList, "Input60"));
            fieldStructure.setInput61(BlancoXmlBindingUtil.getTextContent(elementList, "Input61"));
            fieldStructure.setInput62(BlancoXmlBindingUtil.getTextContent(elementList, "Input62"));
            fieldStructure.setInput63(BlancoXmlBindingUtil.getTextContent(elementList, "Input63"));
            fieldStructure.setInput64(BlancoXmlBindingUtil.getTextContent(elementList, "Input64"));
            fieldStructure.setInput65(BlancoXmlBindingUtil.getTextContent(elementList, "Input65"));
            fieldStructure.setInput66(BlancoXmlBindingUtil.getTextContent(elementList, "Input66"));
            fieldStructure.setInput67(BlancoXmlBindingUtil.getTextContent(elementList, "Input67"));
            fieldStructure.setInput68(BlancoXmlBindingUtil.getTextContent(elementList, "Input68"));
            fieldStructure.setInput69(BlancoXmlBindingUtil.getTextContent(elementList, "Input69"));
            fieldStructure.setInput70(BlancoXmlBindingUtil.getTextContent(elementList, "Input70"));
            fieldStructure.setInput71(BlancoXmlBindingUtil.getTextContent(elementList, "Input71"));
            fieldStructure.setInput72(BlancoXmlBindingUtil.getTextContent(elementList, "Input72"));
            fieldStructure.setInput73(BlancoXmlBindingUtil.getTextContent(elementList, "Input73"));
            fieldStructure.setInput74(BlancoXmlBindingUtil.getTextContent(elementList, "Input74"));
            fieldStructure.setInput75(BlancoXmlBindingUtil.getTextContent(elementList, "Input75"));
            fieldStructure.setInput76(BlancoXmlBindingUtil.getTextContent(elementList, "Input76"));
            fieldStructure.setInput77(BlancoXmlBindingUtil.getTextContent(elementList, "Input77"));
            fieldStructure.setInput78(BlancoXmlBindingUtil.getTextContent(elementList, "Input78"));
            fieldStructure.setInput79(BlancoXmlBindingUtil.getTextContent(elementList, "Input79"));
            fieldStructure.setInput80(BlancoXmlBindingUtil.getTextContent(elementList, "Input80"));
            fieldStructure.setInput81(BlancoXmlBindingUtil.getTextContent(elementList, "Input81"));
            fieldStructure.setInput82(BlancoXmlBindingUtil.getTextContent(elementList, "Input82"));
            fieldStructure.setInput83(BlancoXmlBindingUtil.getTextContent(elementList, "Input83"));
            fieldStructure.setInput84(BlancoXmlBindingUtil.getTextContent(elementList, "Input84"));
            fieldStructure.setInput85(BlancoXmlBindingUtil.getTextContent(elementList, "Input85"));
            fieldStructure.setInput86(BlancoXmlBindingUtil.getTextContent(elementList, "Input86"));
            fieldStructure.setInput87(BlancoXmlBindingUtil.getTextContent(elementList, "Input87"));
            fieldStructure.setInput88(BlancoXmlBindingUtil.getTextContent(elementList, "Input88"));
            fieldStructure.setInput89(BlancoXmlBindingUtil.getTextContent(elementList, "Input89"));
            fieldStructure.setInput90(BlancoXmlBindingUtil.getTextContent(elementList, "Input90"));
            fieldStructure.setInput91(BlancoXmlBindingUtil.getTextContent(elementList, "Input91"));
            fieldStructure.setInput92(BlancoXmlBindingUtil.getTextContent(elementList, "Input92"));
            fieldStructure.setInput93(BlancoXmlBindingUtil.getTextContent(elementList, "Input93"));
            fieldStructure.setInput94(BlancoXmlBindingUtil.getTextContent(elementList, "Input94"));
            fieldStructure.setInput95(BlancoXmlBindingUtil.getTextContent(elementList, "Input95"));
            fieldStructure.setInput96(BlancoXmlBindingUtil.getTextContent(elementList, "Input96"));
            fieldStructure.setInput97(BlancoXmlBindingUtil.getTextContent(elementList, "Input97"));
            fieldStructure.setInput98(BlancoXmlBindingUtil.getTextContent(elementList, "Input98"));
            fieldStructure.setInput99(BlancoXmlBindingUtil.getTextContent(elementList, "Input99"));
            fieldStructure.setInput100(BlancoXmlBindingUtil.getTextContent(elementList, "Input100"));
            fieldStructure.setInput101(BlancoXmlBindingUtil.getTextContent(elementList, "Input101"));
            fieldStructure.setInput102(BlancoXmlBindingUtil.getTextContent(elementList, "Input102"));
            fieldStructure.setInput103(BlancoXmlBindingUtil.getTextContent(elementList, "Input103"));
            fieldStructure.setInput104(BlancoXmlBindingUtil.getTextContent(elementList, "Input104"));
            fieldStructure.setInput105(BlancoXmlBindingUtil.getTextContent(elementList, "Input105"));
            fieldStructure.setInput106(BlancoXmlBindingUtil.getTextContent(elementList, "Input106"));
            fieldStructure.setInput107(BlancoXmlBindingUtil.getTextContent(elementList, "Input107"));
            fieldStructure.setInput108(BlancoXmlBindingUtil.getTextContent(elementList, "Input108"));
            fieldStructure.setInput109(BlancoXmlBindingUtil.getTextContent(elementList, "Input109"));
            fieldStructure.setInput110(BlancoXmlBindingUtil.getTextContent(elementList, "Input110"));
            fieldStructure.setInput111(BlancoXmlBindingUtil.getTextContent(elementList, "Input111"));
            fieldStructure.setInput112(BlancoXmlBindingUtil.getTextContent(elementList, "Input112"));
            fieldStructure.setInput113(BlancoXmlBindingUtil.getTextContent(elementList, "Input113"));
            fieldStructure.setInput114(BlancoXmlBindingUtil.getTextContent(elementList, "Input114"));
            fieldStructure.setInput115(BlancoXmlBindingUtil.getTextContent(elementList, "Input115"));
            fieldStructure.setInput116(BlancoXmlBindingUtil.getTextContent(elementList, "Input116"));
            fieldStructure.setInput117(BlancoXmlBindingUtil.getTextContent(elementList, "Input117"));
            fieldStructure.setInput118(BlancoXmlBindingUtil.getTextContent(elementList, "Input118"));
            fieldStructure.setInput119(BlancoXmlBindingUtil.getTextContent(elementList, "Input119"));
            fieldStructure.setInput120(BlancoXmlBindingUtil.getTextContent(elementList, "Input120"));
            fieldStructure.setInput121(BlancoXmlBindingUtil.getTextContent(elementList, "Input121"));
            fieldStructure.setInput122(BlancoXmlBindingUtil.getTextContent(elementList, "Input122"));
            fieldStructure.setInput123(BlancoXmlBindingUtil.getTextContent(elementList, "Input123"));
            fieldStructure.setInput124(BlancoXmlBindingUtil.getTextContent(elementList, "Input124"));
            fieldStructure.setInput125(BlancoXmlBindingUtil.getTextContent(elementList, "Input125"));
            fieldStructure.setInput126(BlancoXmlBindingUtil.getTextContent(elementList, "Input126"));
            fieldStructure.setInput127(BlancoXmlBindingUtil.getTextContent(elementList, "Input127"));
            fieldStructure.setInput128(BlancoXmlBindingUtil.getTextContent(elementList, "Input128"));
            fieldStructure.setInput129(BlancoXmlBindingUtil.getTextContent(elementList, "Input129"));
            fieldStructure.setInput130(BlancoXmlBindingUtil.getTextContent(elementList, "Input130"));
            fieldStructure.setInput131(BlancoXmlBindingUtil.getTextContent(elementList, "Input131"));
            fieldStructure.setInput132(BlancoXmlBindingUtil.getTextContent(elementList, "Input132"));
            fieldStructure.setInput133(BlancoXmlBindingUtil.getTextContent(elementList, "Input133"));
            fieldStructure.setInput134(BlancoXmlBindingUtil.getTextContent(elementList, "Input134"));
            fieldStructure.setInput135(BlancoXmlBindingUtil.getTextContent(elementList, "Input135"));
            fieldStructure.setInput136(BlancoXmlBindingUtil.getTextContent(elementList, "Input136"));
            fieldStructure.setInput137(BlancoXmlBindingUtil.getTextContent(elementList, "Input137"));
            fieldStructure.setInput138(BlancoXmlBindingUtil.getTextContent(elementList, "Input138"));
            fieldStructure.setInput139(BlancoXmlBindingUtil.getTextContent(elementList, "Input139"));
            fieldStructure.setInput140(BlancoXmlBindingUtil.getTextContent(elementList, "Input140"));
            fieldStructure.setInput141(BlancoXmlBindingUtil.getTextContent(elementList, "Input141"));
            fieldStructure.setInput142(BlancoXmlBindingUtil.getTextContent(elementList, "Input142"));
            fieldStructure.setInput143(BlancoXmlBindingUtil.getTextContent(elementList, "Input143"));
            fieldStructure.setInput144(BlancoXmlBindingUtil.getTextContent(elementList, "Input144"));
            fieldStructure.setInput145(BlancoXmlBindingUtil.getTextContent(elementList, "Input145"));
            fieldStructure.setInput146(BlancoXmlBindingUtil.getTextContent(elementList, "Input146"));
            fieldStructure.setInput147(BlancoXmlBindingUtil.getTextContent(elementList, "Input147"));
            fieldStructure.setInput148(BlancoXmlBindingUtil.getTextContent(elementList, "Input148"));
            fieldStructure.setInput149(BlancoXmlBindingUtil.getTextContent(elementList, "Input149"));
            fieldStructure.setInput150(BlancoXmlBindingUtil.getTextContent(elementList, "Input150"));
            fieldStructure.setInput151(BlancoXmlBindingUtil.getTextContent(elementList, "Input151"));
            fieldStructure.setInput152(BlancoXmlBindingUtil.getTextContent(elementList, "Input152"));
            fieldStructure.setInput153(BlancoXmlBindingUtil.getTextContent(elementList, "Input153"));
            fieldStructure.setInput154(BlancoXmlBindingUtil.getTextContent(elementList, "Input154"));
            fieldStructure.setInput155(BlancoXmlBindingUtil.getTextContent(elementList, "Input155"));
            fieldStructure.setInput156(BlancoXmlBindingUtil.getTextContent(elementList, "Input156"));
            fieldStructure.setInput157(BlancoXmlBindingUtil.getTextContent(elementList, "Input157"));
            fieldStructure.setInput158(BlancoXmlBindingUtil.getTextContent(elementList, "Input158"));
            fieldStructure.setInput159(BlancoXmlBindingUtil.getTextContent(elementList, "Input159"));
            fieldStructure.setInput160(BlancoXmlBindingUtil.getTextContent(elementList, "Input160"));
            fieldStructure.setInput161(BlancoXmlBindingUtil.getTextContent(elementList, "Input161"));
            fieldStructure.setInput162(BlancoXmlBindingUtil.getTextContent(elementList, "Input162"));
            fieldStructure.setInput163(BlancoXmlBindingUtil.getTextContent(elementList, "Input163"));
            fieldStructure.setInput164(BlancoXmlBindingUtil.getTextContent(elementList, "Input164"));
            fieldStructure.setInput165(BlancoXmlBindingUtil.getTextContent(elementList, "Input165"));
            fieldStructure.setInput166(BlancoXmlBindingUtil.getTextContent(elementList, "Input166"));
            fieldStructure.setInput167(BlancoXmlBindingUtil.getTextContent(elementList, "Input167"));
            fieldStructure.setInput168(BlancoXmlBindingUtil.getTextContent(elementList, "Input168"));
            fieldStructure.setInput169(BlancoXmlBindingUtil.getTextContent(elementList, "Input169"));
            fieldStructure.setInput170(BlancoXmlBindingUtil.getTextContent(elementList, "Input170"));
            fieldStructure.setInput171(BlancoXmlBindingUtil.getTextContent(elementList, "Input171"));
            fieldStructure.setInput172(BlancoXmlBindingUtil.getTextContent(elementList, "Input172"));
            fieldStructure.setInput173(BlancoXmlBindingUtil.getTextContent(elementList, "Input173"));
            fieldStructure.setInput174(BlancoXmlBindingUtil.getTextContent(elementList, "Input174"));
            fieldStructure.setInput175(BlancoXmlBindingUtil.getTextContent(elementList, "Input175"));
            fieldStructure.setInput176(BlancoXmlBindingUtil.getTextContent(elementList, "Input176"));
            fieldStructure.setInput177(BlancoXmlBindingUtil.getTextContent(elementList, "Input177"));
            fieldStructure.setInput178(BlancoXmlBindingUtil.getTextContent(elementList, "Input178"));
            fieldStructure.setInput179(BlancoXmlBindingUtil.getTextContent(elementList, "Input179"));
            fieldStructure.setInput180(BlancoXmlBindingUtil.getTextContent(elementList, "Input180"));
            fieldStructure.setInput181(BlancoXmlBindingUtil.getTextContent(elementList, "Input181"));
            fieldStructure.setInput182(BlancoXmlBindingUtil.getTextContent(elementList, "Input182"));
            fieldStructure.setInput183(BlancoXmlBindingUtil.getTextContent(elementList, "Input183"));
            fieldStructure.setInput184(BlancoXmlBindingUtil.getTextContent(elementList, "Input184"));
            fieldStructure.setInput185(BlancoXmlBindingUtil.getTextContent(elementList, "Input185"));
            fieldStructure.setInput186(BlancoXmlBindingUtil.getTextContent(elementList, "Input186"));
            fieldStructure.setInput187(BlancoXmlBindingUtil.getTextContent(elementList, "Input187"));
            fieldStructure.setInput188(BlancoXmlBindingUtil.getTextContent(elementList, "Input188"));
            fieldStructure.setInput189(BlancoXmlBindingUtil.getTextContent(elementList, "Input189"));
            fieldStructure.setInput190(BlancoXmlBindingUtil.getTextContent(elementList, "Input190"));
            fieldStructure.setInput191(BlancoXmlBindingUtil.getTextContent(elementList, "Input191"));
            fieldStructure.setInput192(BlancoXmlBindingUtil.getTextContent(elementList, "Input192"));
            fieldStructure.setInput193(BlancoXmlBindingUtil.getTextContent(elementList, "Input193"));
            fieldStructure.setInput194(BlancoXmlBindingUtil.getTextContent(elementList, "Input194"));
            fieldStructure.setInput195(BlancoXmlBindingUtil.getTextContent(elementList, "Input195"));
            fieldStructure.setInput196(BlancoXmlBindingUtil.getTextContent(elementList, "Input196"));
            fieldStructure.setInput197(BlancoXmlBindingUtil.getTextContent(elementList, "Input197"));
            fieldStructure.setInput198(BlancoXmlBindingUtil.getTextContent(elementList, "Input198"));
            fieldStructure.setInput199(BlancoXmlBindingUtil.getTextContent(elementList, "Input199"));
            fieldStructure.setInput200(BlancoXmlBindingUtil.getTextContent(elementList, "Input200"));
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
            fieldStructure.setExpect31(BlancoXmlBindingUtil.getTextContent(elementList, "Expect31"));
            fieldStructure.setExpect32(BlancoXmlBindingUtil.getTextContent(elementList, "Expect32"));
            fieldStructure.setExpect33(BlancoXmlBindingUtil.getTextContent(elementList, "Expect33"));
            fieldStructure.setExpect34(BlancoXmlBindingUtil.getTextContent(elementList, "Expect34"));
            fieldStructure.setExpect35(BlancoXmlBindingUtil.getTextContent(elementList, "Expect35"));
            fieldStructure.setExpect36(BlancoXmlBindingUtil.getTextContent(elementList, "Expect36"));
            fieldStructure.setExpect37(BlancoXmlBindingUtil.getTextContent(elementList, "Expect37"));
            fieldStructure.setExpect38(BlancoXmlBindingUtil.getTextContent(elementList, "Expect38"));
            fieldStructure.setExpect39(BlancoXmlBindingUtil.getTextContent(elementList, "Expect39"));
            fieldStructure.setExpect40(BlancoXmlBindingUtil.getTextContent(elementList, "Expect40"));
            fieldStructure.setExpect41(BlancoXmlBindingUtil.getTextContent(elementList, "Expect41"));
            fieldStructure.setExpect42(BlancoXmlBindingUtil.getTextContent(elementList, "Expect42"));
            fieldStructure.setExpect43(BlancoXmlBindingUtil.getTextContent(elementList, "Expect43"));
            fieldStructure.setExpect44(BlancoXmlBindingUtil.getTextContent(elementList, "Expect44"));
            fieldStructure.setExpect45(BlancoXmlBindingUtil.getTextContent(elementList, "Expect45"));
            fieldStructure.setExpect46(BlancoXmlBindingUtil.getTextContent(elementList, "Expect46"));
            fieldStructure.setExpect47(BlancoXmlBindingUtil.getTextContent(elementList, "Expect47"));
            fieldStructure.setExpect48(BlancoXmlBindingUtil.getTextContent(elementList, "Expect48"));
            fieldStructure.setExpect49(BlancoXmlBindingUtil.getTextContent(elementList, "Expect49"));
            fieldStructure.setExpect50(BlancoXmlBindingUtil.getTextContent(elementList, "Expect50"));
            fieldStructure.setExpect51(BlancoXmlBindingUtil.getTextContent(elementList, "Expect51"));
            fieldStructure.setExpect52(BlancoXmlBindingUtil.getTextContent(elementList, "Expect52"));
            fieldStructure.setExpect53(BlancoXmlBindingUtil.getTextContent(elementList, "Expect53"));
            fieldStructure.setExpect54(BlancoXmlBindingUtil.getTextContent(elementList, "Expect54"));
            fieldStructure.setExpect55(BlancoXmlBindingUtil.getTextContent(elementList, "Expect55"));
            fieldStructure.setExpect56(BlancoXmlBindingUtil.getTextContent(elementList, "Expect56"));
            fieldStructure.setExpect57(BlancoXmlBindingUtil.getTextContent(elementList, "Expect57"));
            fieldStructure.setExpect58(BlancoXmlBindingUtil.getTextContent(elementList, "Expect58"));
            fieldStructure.setExpect59(BlancoXmlBindingUtil.getTextContent(elementList, "Expect59"));
            fieldStructure.setExpect60(BlancoXmlBindingUtil.getTextContent(elementList, "Expect60"));
            fieldStructure.setExpect61(BlancoXmlBindingUtil.getTextContent(elementList, "Expect61"));
            fieldStructure.setExpect62(BlancoXmlBindingUtil.getTextContent(elementList, "Expect62"));
            fieldStructure.setExpect63(BlancoXmlBindingUtil.getTextContent(elementList, "Expect63"));
            fieldStructure.setExpect64(BlancoXmlBindingUtil.getTextContent(elementList, "Expect64"));
            fieldStructure.setExpect65(BlancoXmlBindingUtil.getTextContent(elementList, "Expect65"));
            fieldStructure.setExpect66(BlancoXmlBindingUtil.getTextContent(elementList, "Expect66"));
            fieldStructure.setExpect67(BlancoXmlBindingUtil.getTextContent(elementList, "Expect67"));
            fieldStructure.setExpect68(BlancoXmlBindingUtil.getTextContent(elementList, "Expect68"));
            fieldStructure.setExpect69(BlancoXmlBindingUtil.getTextContent(elementList, "Expect69"));
            fieldStructure.setExpect70(BlancoXmlBindingUtil.getTextContent(elementList, "Expect70"));
            fieldStructure.setExpect71(BlancoXmlBindingUtil.getTextContent(elementList, "Expect71"));
            fieldStructure.setExpect72(BlancoXmlBindingUtil.getTextContent(elementList, "Expect72"));
            fieldStructure.setExpect73(BlancoXmlBindingUtil.getTextContent(elementList, "Expect73"));
            fieldStructure.setExpect74(BlancoXmlBindingUtil.getTextContent(elementList, "Expect74"));
            fieldStructure.setExpect75(BlancoXmlBindingUtil.getTextContent(elementList, "Expect75"));
            fieldStructure.setExpect76(BlancoXmlBindingUtil.getTextContent(elementList, "Expect76"));
            fieldStructure.setExpect77(BlancoXmlBindingUtil.getTextContent(elementList, "Expect77"));
            fieldStructure.setExpect78(BlancoXmlBindingUtil.getTextContent(elementList, "Expect78"));
            fieldStructure.setExpect79(BlancoXmlBindingUtil.getTextContent(elementList, "Expect79"));
            fieldStructure.setExpect80(BlancoXmlBindingUtil.getTextContent(elementList, "Expect80"));
            fieldStructure.setExpect81(BlancoXmlBindingUtil.getTextContent(elementList, "Expect81"));
            fieldStructure.setExpect82(BlancoXmlBindingUtil.getTextContent(elementList, "Expect82"));
            fieldStructure.setExpect83(BlancoXmlBindingUtil.getTextContent(elementList, "Expect83"));
            fieldStructure.setExpect84(BlancoXmlBindingUtil.getTextContent(elementList, "Expect84"));
            fieldStructure.setExpect85(BlancoXmlBindingUtil.getTextContent(elementList, "Expect85"));
            fieldStructure.setExpect86(BlancoXmlBindingUtil.getTextContent(elementList, "Expect86"));
            fieldStructure.setExpect87(BlancoXmlBindingUtil.getTextContent(elementList, "Expect87"));
            fieldStructure.setExpect88(BlancoXmlBindingUtil.getTextContent(elementList, "Expect88"));
            fieldStructure.setExpect89(BlancoXmlBindingUtil.getTextContent(elementList, "Expect89"));
            fieldStructure.setExpect90(BlancoXmlBindingUtil.getTextContent(elementList, "Expect90"));
            fieldStructure.setExpect91(BlancoXmlBindingUtil.getTextContent(elementList, "Expect91"));
            fieldStructure.setExpect92(BlancoXmlBindingUtil.getTextContent(elementList, "Expect92"));
            fieldStructure.setExpect93(BlancoXmlBindingUtil.getTextContent(elementList, "Expect93"));
            fieldStructure.setExpect94(BlancoXmlBindingUtil.getTextContent(elementList, "Expect94"));
            fieldStructure.setExpect95(BlancoXmlBindingUtil.getTextContent(elementList, "Expect95"));
            fieldStructure.setExpect96(BlancoXmlBindingUtil.getTextContent(elementList, "Expect96"));
            fieldStructure.setExpect97(BlancoXmlBindingUtil.getTextContent(elementList, "Expect97"));
            fieldStructure.setExpect98(BlancoXmlBindingUtil.getTextContent(elementList, "Expect98"));
            fieldStructure.setExpect99(BlancoXmlBindingUtil.getTextContent(elementList, "Expect99"));
            fieldStructure.setExpect100(BlancoXmlBindingUtil.getTextContent(elementList, "Expect100"));
            fieldStructure.setExpect101(BlancoXmlBindingUtil.getTextContent(elementList, "Expect101"));
            fieldStructure.setExpect102(BlancoXmlBindingUtil.getTextContent(elementList, "Expect102"));
            fieldStructure.setExpect103(BlancoXmlBindingUtil.getTextContent(elementList, "Expect103"));
            fieldStructure.setExpect104(BlancoXmlBindingUtil.getTextContent(elementList, "Expect104"));
            fieldStructure.setExpect105(BlancoXmlBindingUtil.getTextContent(elementList, "Expect105"));
            fieldStructure.setExpect106(BlancoXmlBindingUtil.getTextContent(elementList, "Expect106"));
            fieldStructure.setExpect107(BlancoXmlBindingUtil.getTextContent(elementList, "Expect107"));
            fieldStructure.setExpect108(BlancoXmlBindingUtil.getTextContent(elementList, "Expect108"));
            fieldStructure.setExpect109(BlancoXmlBindingUtil.getTextContent(elementList, "Expect109"));
            fieldStructure.setExpect110(BlancoXmlBindingUtil.getTextContent(elementList, "Expect110"));
            fieldStructure.setExpect111(BlancoXmlBindingUtil.getTextContent(elementList, "Expect111"));
            fieldStructure.setExpect112(BlancoXmlBindingUtil.getTextContent(elementList, "Expect112"));
            fieldStructure.setExpect113(BlancoXmlBindingUtil.getTextContent(elementList, "Expect113"));
            fieldStructure.setExpect114(BlancoXmlBindingUtil.getTextContent(elementList, "Expect114"));
            fieldStructure.setExpect115(BlancoXmlBindingUtil.getTextContent(elementList, "Expect115"));
            fieldStructure.setExpect116(BlancoXmlBindingUtil.getTextContent(elementList, "Expect116"));
            fieldStructure.setExpect117(BlancoXmlBindingUtil.getTextContent(elementList, "Expect117"));
            fieldStructure.setExpect118(BlancoXmlBindingUtil.getTextContent(elementList, "Expect118"));
            fieldStructure.setExpect119(BlancoXmlBindingUtil.getTextContent(elementList, "Expect119"));
            fieldStructure.setExpect120(BlancoXmlBindingUtil.getTextContent(elementList, "Expect120"));
            fieldStructure.setExpect121(BlancoXmlBindingUtil.getTextContent(elementList, "Expect121"));
            fieldStructure.setExpect122(BlancoXmlBindingUtil.getTextContent(elementList, "Expect122"));
            fieldStructure.setExpect123(BlancoXmlBindingUtil.getTextContent(elementList, "Expect123"));
            fieldStructure.setExpect124(BlancoXmlBindingUtil.getTextContent(elementList, "Expect124"));
            fieldStructure.setExpect125(BlancoXmlBindingUtil.getTextContent(elementList, "Expect125"));
            fieldStructure.setExpect126(BlancoXmlBindingUtil.getTextContent(elementList, "Expect126"));
            fieldStructure.setExpect127(BlancoXmlBindingUtil.getTextContent(elementList, "Expect127"));
            fieldStructure.setExpect128(BlancoXmlBindingUtil.getTextContent(elementList, "Expect128"));
            fieldStructure.setExpect129(BlancoXmlBindingUtil.getTextContent(elementList, "Expect129"));
            fieldStructure.setExpect130(BlancoXmlBindingUtil.getTextContent(elementList, "Expect130"));
            fieldStructure.setExpect131(BlancoXmlBindingUtil.getTextContent(elementList, "Expect131"));
            fieldStructure.setExpect132(BlancoXmlBindingUtil.getTextContent(elementList, "Expect132"));
            fieldStructure.setExpect133(BlancoXmlBindingUtil.getTextContent(elementList, "Expect133"));
            fieldStructure.setExpect134(BlancoXmlBindingUtil.getTextContent(elementList, "Expect134"));
            fieldStructure.setExpect135(BlancoXmlBindingUtil.getTextContent(elementList, "Expect135"));
            fieldStructure.setExpect136(BlancoXmlBindingUtil.getTextContent(elementList, "Expect136"));
            fieldStructure.setExpect137(BlancoXmlBindingUtil.getTextContent(elementList, "Expect137"));
            fieldStructure.setExpect138(BlancoXmlBindingUtil.getTextContent(elementList, "Expect138"));
            fieldStructure.setExpect139(BlancoXmlBindingUtil.getTextContent(elementList, "Expect139"));
            fieldStructure.setExpect140(BlancoXmlBindingUtil.getTextContent(elementList, "Expect140"));
            fieldStructure.setExpect141(BlancoXmlBindingUtil.getTextContent(elementList, "Expect141"));
            fieldStructure.setExpect142(BlancoXmlBindingUtil.getTextContent(elementList, "Expect142"));
            fieldStructure.setExpect143(BlancoXmlBindingUtil.getTextContent(elementList, "Expect143"));
            fieldStructure.setExpect144(BlancoXmlBindingUtil.getTextContent(elementList, "Expect144"));
            fieldStructure.setExpect145(BlancoXmlBindingUtil.getTextContent(elementList, "Expect145"));
            fieldStructure.setExpect146(BlancoXmlBindingUtil.getTextContent(elementList, "Expect146"));
            fieldStructure.setExpect147(BlancoXmlBindingUtil.getTextContent(elementList, "Expect147"));
            fieldStructure.setExpect148(BlancoXmlBindingUtil.getTextContent(elementList, "Expect148"));
            fieldStructure.setExpect149(BlancoXmlBindingUtil.getTextContent(elementList, "Expect149"));
            fieldStructure.setExpect150(BlancoXmlBindingUtil.getTextContent(elementList, "Expect150"));
            fieldStructure.setExpect151(BlancoXmlBindingUtil.getTextContent(elementList, "Expect151"));
            fieldStructure.setExpect152(BlancoXmlBindingUtil.getTextContent(elementList, "Expect152"));
            fieldStructure.setExpect153(BlancoXmlBindingUtil.getTextContent(elementList, "Expect153"));
            fieldStructure.setExpect154(BlancoXmlBindingUtil.getTextContent(elementList, "Expect154"));
            fieldStructure.setExpect155(BlancoXmlBindingUtil.getTextContent(elementList, "Expect155"));
            fieldStructure.setExpect156(BlancoXmlBindingUtil.getTextContent(elementList, "Expect156"));
            fieldStructure.setExpect157(BlancoXmlBindingUtil.getTextContent(elementList, "Expect157"));
            fieldStructure.setExpect158(BlancoXmlBindingUtil.getTextContent(elementList, "Expect158"));
            fieldStructure.setExpect159(BlancoXmlBindingUtil.getTextContent(elementList, "Expect159"));
            fieldStructure.setExpect160(BlancoXmlBindingUtil.getTextContent(elementList, "Expect160"));
            fieldStructure.setExpect161(BlancoXmlBindingUtil.getTextContent(elementList, "Expect161"));
            fieldStructure.setExpect162(BlancoXmlBindingUtil.getTextContent(elementList, "Expect162"));
            fieldStructure.setExpect163(BlancoXmlBindingUtil.getTextContent(elementList, "Expect163"));
            fieldStructure.setExpect164(BlancoXmlBindingUtil.getTextContent(elementList, "Expect164"));
            fieldStructure.setExpect165(BlancoXmlBindingUtil.getTextContent(elementList, "Expect165"));
            fieldStructure.setExpect166(BlancoXmlBindingUtil.getTextContent(elementList, "Expect166"));
            fieldStructure.setExpect167(BlancoXmlBindingUtil.getTextContent(elementList, "Expect167"));
            fieldStructure.setExpect168(BlancoXmlBindingUtil.getTextContent(elementList, "Expect168"));
            fieldStructure.setExpect169(BlancoXmlBindingUtil.getTextContent(elementList, "Expect169"));
            fieldStructure.setExpect170(BlancoXmlBindingUtil.getTextContent(elementList, "Expect170"));
            fieldStructure.setExpect171(BlancoXmlBindingUtil.getTextContent(elementList, "Expect171"));
            fieldStructure.setExpect172(BlancoXmlBindingUtil.getTextContent(elementList, "Expect172"));
            fieldStructure.setExpect173(BlancoXmlBindingUtil.getTextContent(elementList, "Expect173"));
            fieldStructure.setExpect174(BlancoXmlBindingUtil.getTextContent(elementList, "Expect174"));
            fieldStructure.setExpect175(BlancoXmlBindingUtil.getTextContent(elementList, "Expect175"));
            fieldStructure.setExpect176(BlancoXmlBindingUtil.getTextContent(elementList, "Expect176"));
            fieldStructure.setExpect177(BlancoXmlBindingUtil.getTextContent(elementList, "Expect177"));
            fieldStructure.setExpect178(BlancoXmlBindingUtil.getTextContent(elementList, "Expect178"));
            fieldStructure.setExpect179(BlancoXmlBindingUtil.getTextContent(elementList, "Expect179"));
            fieldStructure.setExpect180(BlancoXmlBindingUtil.getTextContent(elementList, "Expect180"));
            fieldStructure.setExpect181(BlancoXmlBindingUtil.getTextContent(elementList, "Expect181"));
            fieldStructure.setExpect182(BlancoXmlBindingUtil.getTextContent(elementList, "Expect182"));
            fieldStructure.setExpect183(BlancoXmlBindingUtil.getTextContent(elementList, "Expect183"));
            fieldStructure.setExpect184(BlancoXmlBindingUtil.getTextContent(elementList, "Expect184"));
            fieldStructure.setExpect185(BlancoXmlBindingUtil.getTextContent(elementList, "Expect185"));
            fieldStructure.setExpect186(BlancoXmlBindingUtil.getTextContent(elementList, "Expect186"));
            fieldStructure.setExpect187(BlancoXmlBindingUtil.getTextContent(elementList, "Expect187"));
            fieldStructure.setExpect188(BlancoXmlBindingUtil.getTextContent(elementList, "Expect188"));
            fieldStructure.setExpect189(BlancoXmlBindingUtil.getTextContent(elementList, "Expect189"));
            fieldStructure.setExpect190(BlancoXmlBindingUtil.getTextContent(elementList, "Expect190"));
            fieldStructure.setExpect191(BlancoXmlBindingUtil.getTextContent(elementList, "Expect191"));
            fieldStructure.setExpect192(BlancoXmlBindingUtil.getTextContent(elementList, "Expect192"));
            fieldStructure.setExpect193(BlancoXmlBindingUtil.getTextContent(elementList, "Expect193"));
            fieldStructure.setExpect194(BlancoXmlBindingUtil.getTextContent(elementList, "Expect194"));
            fieldStructure.setExpect195(BlancoXmlBindingUtil.getTextContent(elementList, "Expect195"));
            fieldStructure.setExpect196(BlancoXmlBindingUtil.getTextContent(elementList, "Expect196"));
            fieldStructure.setExpect197(BlancoXmlBindingUtil.getTextContent(elementList, "Expect197"));
            fieldStructure.setExpect198(BlancoXmlBindingUtil.getTextContent(elementList, "Expect198"));
            fieldStructure.setExpect199(BlancoXmlBindingUtil.getTextContent(elementList, "Expect199"));
            fieldStructure.setExpect200(BlancoXmlBindingUtil.getTextContent(elementList, "Expect200"));

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
                                BlancoRestAutotestUtil.addPrimitiveToList(propObj, value, genericObj.getClass().getName());
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
