/*
 * blanco Framework
 * Copyright (C) 2004-2010 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.restautotest;

import blanco.cg.BlancoCgObjectFactory;
import blanco.cg.BlancoCgSupportedLang;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.restautotest.constants.BlancoRestAutotestConstants;
import blanco.restautotest.message.BlancoRestAutotestMessage;
import blanco.restautotest.resourcebundle.BlancoRestAutotestResourceBundle;
import blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure;
import blanco.restautotest.valueobject.BlancoRestAutotestTestCaseFieldStructure;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * バリューオブジェクト用中間XMLファイルから Javaソースコードを自動生成するクラス。
 *
 * blancoValueObjectの主たるクラスのひとつです。
 *
 * @author IGA Tosiki
 * @author Kinoko Matsumoto 2017.09.01
 */
public class BlancoRestAutotestXml2JavaClass {
    /**
     * メッセージ。
     */
    private final BlancoRestAutotestMessage fMsg = new BlancoRestAutotestMessage();

    /**
     * blancoValueObjectのリソースバンドルオブジェクト。
     */
    private final BlancoRestAutotestResourceBundle fBundle = new BlancoRestAutotestResourceBundle();

    /**
     * 入力シートに期待するプログラミング言語
     */
    private int fSheetLang = BlancoCgSupportedLang.JAVA;

    /**
     * 内部的に利用するblancoCg用ファクトリ。
     */
    private BlancoCgObjectFactory fCgFactory = null;

    /**
     * 内部的に利用するblancoCg用ソースファイル情報。
     */
    private BlancoCgSourceFile fCgSourceFile = null;

    /**
     * 内部的に利用するblancoCg用クラス情報。
     */
    private BlancoCgClass fCgClass = null;

    /**
     * 自動生成するソースファイルの文字エンコーディング。
     */
    private String fEncoding = null;

    public void setEncoding(final String argEncoding) {
        fEncoding = argEncoding;
    }

    private boolean fIsXmlRootElement = false;

    /**
     * ソースコード生成先ディレクトリのスタイル
     */
    private boolean fTargetStyleAdvanced = false;
    public void setTargetStyleAdvanced(boolean argTargetStyleAdvanced) {
        this.fTargetStyleAdvanced = argTargetStyleAdvanced;
    }
    public boolean isTargetStyleAdvanced() {
        return this.fTargetStyleAdvanced;
    }

    private boolean fVerbose = false;
    public void setVerbose(boolean argVerbose) {
        this.fVerbose = argVerbose;
    }
    public boolean isVerbose() {
        return this.fVerbose;
    }

    private int testcaseMax = 0;

    /**
     * バリューオブジェクトを表現する中間XMLファイルから、Javaソースコードを自動生成します。
     *
     * @param argDirectoryTarget
     *            ソースコード生成先ディレクトリ
     * @throws IOException
     *             入出力例外が発生した場合
     */
    public void process(
            final File argDirectoryTarget) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // 得られた情報からJavaソースコードを生成します。
//        structure2Source(argDirectoryTarget);
    }

 /**
     * 与えられたクラス情報バリューオブジェクトから、ソースコードを自動生成します。
     *
     * @param argClassStructure
     *            クラス情報
     * @param argDirectoryTarget
     *            Javaソースコードの出力先ディレクトリ
     * @throws IOException
     *             入出力例外が発生した場合。
     */
//    public void structure2Source(
//            final File argDirectoryTarget) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        // 従来と互換性を持たせるため、/mainサブフォルダに出力します。→今回は/outputに変更。
//        final File fileBlancoMain = new File(argDirectoryTarget.getAbsolutePath());
//
//        /* tueda DEBUG */
//        System.out.println("/* ama */ structure2Source : " + argClassStructure.get("blancometa2xml-process").getName());
//
//        // テストの依存関係（前提条件）から、TestSuiteを作る
//        structure2TestRunner(argClassStructure, fileBlancoMain);
//
//        // 抽象クラスを作らずに直接実装クラスのみを作成します。
//        structure2MainSource(argClassStructure, fileBlancoMain);
//
//        System.out.println("/* tueda */ structure2Source : structure2MainSource done.");
//
//        // APIパッケージ内のすべてのテストを順に流すための入り口となるクラスを作成
//        structure2AllTestSuite(argClassStructure.get("blancometa2xml-process"), fileBlancoMain);
//
//        System.out.println("/* tueda */ structure2Source : structure2AllTestSuite done.");
//
//        // APIパッケージ内のすべてのテストを順に流すためのTestRunnerクラスを作成
//        structure2AllTestRunner(argClassStructure.get("blancometa2xml-process"), fileBlancoMain);
//
//        System.out.println("/* tueda */ structure2Source : structure2AllTestRunner done.");
//
//    }
//
//    private void structure2TestRunner(final Map<String, BlancoRestAutotestClassStructure> argClassStructure, final File argDirectoryTarget) {
//
//        BlancoRestAutotestClassStructure processStructure = argClassStructure.get("blancometa2xml-process");
//        BlancoRestAutotestClassStructure testCaseStructure = argClassStructure.get("blancotestcase");
//
//        //クラス生成
//        fCgFactory = BlancoCgObjectFactory.getInstance();
//        fCgSourceFile = fCgFactory.createSourceFile(processStructure
//                .getPackage(), "このソースコードは blanco Frameworkによって自動生成されています。");
//        fCgSourceFile.setEncoding(fEncoding);
//
//        // Api名+TestRunner
//        fCgClass = fCgFactory.createClass(processStructure.getName() + "Runner",
//                "このクラスはJunitのTestCaseの依存関係を処理します");
//
//        fCgClass.setAbstract(false);
//        fCgClass.getExtendClassList().add(fCgFactory.createType("org.junit.runners.ParentRunner<Runner>"));
//        fCgSourceFile.getClassList().add(fCgClass);
//
//        fCgSourceFile.getImportList().add("org.junit.runners.BlockJUnit4ClassRunner");
//        fCgSourceFile.getImportList().add("org.junit.runners.ParentRunner");
//
//        fCgSourceFile.getImportList().add("java.util.ArrayList");
//
//        {
//            final BlancoCgField field = fCgFactory.createField(
//                    "runners", "java.util.List<Runner>", "");
//            fCgClass.getFieldList().add(field);
//            field.setAccess("private");
//            field.setFinal(true);
//        }
//
//        {
//            final BlancoCgField field = fCgFactory.createField(
//                    "testCaseDependencyList[][]", "int", "");
//            fCgClass.getFieldList().add(field);
//            field.setAccess("private");
//            field.setFinal(true);
//            String dependencyList = getPreconditionList(testCaseStructure.getTestCaseFieldList());
//
//            field.setDefault(dependencyList);
//        }
//
//        {
//            final BlancoCgMethod constractor = fCgFactory.createMethod(argClassStructure.get("blancometa2xml-process").getName() + "Runner", "");
//
//            constractor.setConstructor(true);
//            constractor.getThrowList().add(fCgFactory.createException("org.junit.runners.model.InitializationError", ""));
//            constractor.getParameterList().add(fCgFactory.createParameter("testClass", "java.lang.Class<?>", ""));
//            final List<String> listLine = constractor.getLineList();
//
//            listLine.add("super(testClass);");
//            listLine.add("");
//            listLine.add("// テストクラス名からtestCase番号を取り出す");
//            listLine.add("String testClassName = testClass.getSimpleName();");
//            listLine.add("int testCaseNum = Integer.parseInt(testClassName.substring(testClassName.length() - 4));");
//            listLine.add("List<Class<?>> classList = getClassList(testCaseNum - 1);");
//            listLine.add("runners = new ArrayList<>();");
//            listLine.add("for (Class<?> clazz : classList) {");
//            listLine.add("runners.add(new BlockJUnit4ClassRunner(clazz));");
//            listLine.add("}");
//
//            fCgClass.getMethodList().add(constractor);
//        }
//
//        {
//            final BlancoCgMethod method = fCgFactory.createMethod("getClassList", "依存関係のあるテストクラス名のリストを返します");
//            method.getParameterList().add(fCgFactory.createParameter("num", "int", ""));
//            method.setReturn(fCgFactory.createReturn("java.util.List<Class<?>>", ""));
//            final List<String> listLine = method.getLineList();
//
//            listLine.add("");
//            listLine.add("String prefix = \"" + processStructure.getPackage() + "." +  processStructure.getName() + "\";");
//            listLine.add("List<Class<?>> classList = new ArrayList<>();");
//            listLine.add("");
//            listLine.add("for (int testCaseNum : testCaseDependencyList[num]) {");
//            listLine.add("String classFullName = prefix + String.format(\"%04d\", testCaseNum);");
////            listLine.add("System.out.println(\"/* debug kinoko */ testcaseName = \" + classFullName);");
//            listLine.add("try {");
//            listLine.add("classList.add(Class.forName(classFullName));");
//            listLine.add("} catch (ClassNotFoundException e) {");
//            listLine.add("// 例外処理");
//            listLine.add("throw new RuntimeException(\"クラスが存在しない : \" + classFullName);");
//            listLine.add("}");
//            listLine.add("}");
//            listLine.add("");
//            listLine.add("return classList;");
//
//            fCgClass.getMethodList().add(method);
//        }
//
//        {
//            final BlancoCgMethod method = fCgFactory.createMethod("getChildren", "");
//            method.setReturn(fCgFactory.createReturn("java.util.List<Runner>", ""));
//            method.setAccess("protected");
//            method.getAnnotationList().add("Override");
//            final List<String> listLine = method.getLineList();
//
//            listLine.add("return runners;");
//
//            fCgClass.getMethodList().add(method);
//        }
//
//        {
//            final BlancoCgMethod method = fCgFactory.createMethod("describeChild", "");
//            method.getParameterList().add(fCgFactory.createParameter("child", "org.junit.runner.Runner", ""));
//            method.setReturn(fCgFactory.createReturn("org.junit.runner.Description", ""));
//            method.setAccess("protected");
//            method.getAnnotationList().add("Override");
//            final List<String> listLine = method.getLineList();
//
//            listLine.add("return child.getDescription();");
//
//            fCgClass.getMethodList().add(method);
//        }
//
//        {
//            final BlancoCgMethod method = fCgFactory.createMethod("runChild", "");
//            method.getParameterList().add(fCgFactory.createParameter("child", "org.junit.runner.Runner", ""));
//            method.getParameterList().add(fCgFactory.createParameter("notifier", "org.junit.runner.notification.RunNotifier", ""));
//            method.setAccess("protected");
//            method.getAnnotationList().add("Override");
//            final List<String> listLine = method.getLineList();
//
//            listLine.add("child.run(notifier);");
//
//            fCgClass.getMethodList().add(method);
//        }
//
//        BlancoCgTransformerFactory.getJavaSourceTransformer().transform(
//                fCgSourceFile, argDirectoryTarget);
//
//    }

    /**
     * TestCaseシートで前提条件カラムより前提条件を取得
     *
     * @param elementTestCaseFieldList
     *            TestCaseシート全情報
     * @return それぞれのNo.に対する前提条件のリストを戻します。
     *           ex) preconditionList  = {"シートNo","前提条件であるNo"},{"0",null},{"1",{"0"}},{"2",{"0"}},{"3",{"0","2"}}....
     *
     */
    public String getPreconditionList(List<BlancoRestAutotestTestCaseFieldStructure> elementTestCaseFieldList){

        ArrayList<ArrayList<String>> preconditionList = new ArrayList<ArrayList<String>>();

        //elementTestCaseFieldList　null　でexception

        for (int index = 0; index < elementTestCaseFieldList.size(); index++) {

            ArrayList<String> elementList = new ArrayList<>();

            elementList = recursivePreconditionList(index, elementTestCaseFieldList, elementList);
            Collections.reverse(elementList);
            preconditionList.add(index, elementList);
        }

        String preconditionListString = preconditionList.toString().replace("[", "{");
        preconditionListString = preconditionListString.replace("]", "}");
        return preconditionListString;
    }

    /**
     * 前提条件を再帰的に取得
     *
     * @param indexNo 現在のNo.
     * @param elementList TestCaseシート全情報
     * @param stackList 再帰的に取得された前提条件リスト
     *
     * @return そのNo.に対する前提条件のリストを戻します。
     *
     */
    public ArrayList<String> recursivePreconditionList(Integer indexNo,
                                                        List<BlancoRestAutotestTestCaseFieldStructure> elementList,
                                                        ArrayList<String> stackList)

    {

//        if(indexNo < 0){
//            System.out.println("インデックスが0よりちいさいとき");
//            return stackList;
//        }

        // 前提条件が入っていないときは、自分自身を追加
        if(elementList.get(indexNo).getPrecondition() == null) {
            stackList.add(Integer.toString(indexNo + 1));
            return stackList;

        } else {
            // 前提条件が入っている時は、自分自身を追加してから、再帰的に前提条件を取得する
            Integer PreconditionValue = (new Integer(elementList.get(indexNo).getPrecondition()).intValue());
            stackList.add(Integer.toString(indexNo + 1));
            return recursivePreconditionList(PreconditionValue - 1, elementList, stackList); // エクセルのメタシートはNo.が1から始まっているのでindexとして使う時は-1してあげる
        }
    }

    /**
     * input&Resultシートで電文のカラム名情報取得
     *
     * @param  elementList
     *            input&Resultシートの電文のカラム名情報
     * @param telegramPattern
     *            "Input" or "Expect"
     * @return カラム名情報行から"Input" or "Expect"に対応するカラム名を返す
     *
     */
    public ArrayList<String> getColumnNameList(BlancoRestAutotestInputResultFieldStructure elementList, String telegramPattern)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ArrayList<String> columnNameList = new ArrayList<String>();

        // クラスを指定
        BlancoRestAutotestInputResultFieldStructure cdst = new BlancoRestAutotestInputResultFieldStructure();
        Class cl = cdst.getClass();

        int indexLimit;

        switch(telegramPattern){
            case "Input":
                indexLimit = BlancoRestAutotestConstants.INPUT_MAX;
                break;
            case "Expect":
                indexLimit = BlancoRestAutotestConstants.OUTPUT_MAX;
                break;
            default:
                indexLimit = BlancoRestAutotestConstants.INPUT_MAX;
                break;
        }
        for (int index = 1; index <= indexLimit; index++) {

            Method method = cl.getMethod("get" + telegramPattern + index);//getExpect1などのメソッドの値を取得
            String cc = (String) method.invoke(elementList);
            columnNameList.add(cc);

        }

        return columnNameList;
    }

    /**
     * アサーション行からExpectに対応するアサーションリストを取得
     *
     * @param  elementList
     *            input&Resultシートのアサーション行情報
     * @return アサーション行からExpectに対応するアサーションリストを返す
     *           ex) assertionList  = {"Eequal","-","Eequal","NotNull"....}
     *
     */
    public ArrayList<String> getAssertionList(BlancoRestAutotestInputResultFieldStructure elementList)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ArrayList<String> assertionList = new ArrayList<String>();

        // クラスを指定
        BlancoRestAutotestInputResultFieldStructure cdst = new BlancoRestAutotestInputResultFieldStructure();
        Class cl = cdst.getClass();

        for (int index = 1; index <= BlancoRestAutotestConstants.OUTPUT_MAX; index++) {

            Method method = cl.getMethod("getExpect" + index);//getExpect1などのメソッドの値を取得
            String cc = (String) method.invoke(elementList);
            assertionList.add(cc);

        }

        return assertionList;
    }

    /**
     * TestCaseシートのアノテーション情報から、ソースに吐き出す用のコメント情報に書き換え
     *
     * @param  element
     *            TestCaseシートのアノテーション情報
     * @return アノテーション情報から、ソースに吐き出す用のコメント情報に書き換えをして返却
     *
     */
//    public String getAnnotationList(BlancoRestAutotestTestCaseFieldStructure  element) {
//
//        String cc = "\r";
//
//        if(element.getNo() != null && element.getNo().length() != 0){
//            cc += " * ";
//            cc += "No. " + element.getNo() + "\r";
//        }
//        if(element.getCase() != null && element.getCase().length() != 0){
//            cc += " * ";
//            cc += "Case " + element.getCase() + "\r";
//        }
//        if(element.getMode() != null && element.getMode().length() != 0){
//            cc += " * ";
//            cc += "Mode " + element.getMode() + "\r";
//        }
//        if(element.getState() != null && element.getState().length() != 0){
//            cc += " * ";
//            cc += "State " + element.getState() + "\r";
//        }
//        if(element.getOpion() != null && element.getOpion().length() != 0){
//            cc += " * ";
//            cc += "Option " + element.getOpion() + "\r";
//        }
//        if(element.getAction() != null && element.getAction().length() != 0){
//            cc += " * ";
//            cc += "Action " + element.getAction() + "\r";
//        }
//        if(element.getResult() != null && element.getResult().length() != 0){
//            cc += " * ";
//            cc += "Result " + element.getResult() + "\r";
//        }
//        if(element.getResultCheck() != null && element.getResultCheck().length() != 0){
//            cc += " * ";
//            cc += "ResultCheck " + element.getResultCheck() + "\r";
//        }
//        if(element.getBikou() != null && element.getBikou().length() != 0){
//            cc += " * ";
//            cc += "備考 " + element.getBikou() + "\r";
//        }
//
//        return cc;
//    }

    /**
     * input&Resultシートで電文のパラメータ数を取得
     * (カラムを後ろから検索して、nullでない箇所を検索)
     *
     * @param elementList
     *            input&Resultシート全情報
     * @param telegramPattern
     *            "Input" or "Expect"
     * @return 電文のパラメータ数を戻します。
     *
     */
    public int getTelegramPramCount(BlancoRestAutotestInputResultFieldStructure elementList, String telegramPattern) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // クラスを指定
        BlancoRestAutotestInputResultFieldStructure cdst = new BlancoRestAutotestInputResultFieldStructure();
        Class cl = cdst.getClass();

        int indexLimit;

        switch(telegramPattern){
            case "Input":
                indexLimit = BlancoRestAutotestConstants.INPUT_MAX;
                break;
            case "Expect":
                indexLimit = BlancoRestAutotestConstants.OUTPUT_MAX;
                break;
            default:
                indexLimit = BlancoRestAutotestConstants.INPUT_MAX;
                break;
        }
        for (int index = indexLimit; index > 0; index--) {

            Method method = cl.getMethod("get" + telegramPattern + index);//getInput1などのメソッドの値を取得
            String cc = (String) method.invoke(elementList);

            if(cc != null){
                return index;
            }
        }

        return 0;
    }

    /**
     * 与えられたクラス情報バリューオブジェクトから、ソースコードを自動生成します。(実装クラス)
     *
     * @param argClassStructure
     *            クラス情報
     * @param argDirectoryTarget
     *            Javaソースコードの出力先ディレクトリ
     * @throws IOException
     *             入出力例外が発生した場合。
     */
//    public void structure2MainSource(
//            final Map<String, BlancoRestAutotestClassStructure> argClassStructure,
//            final File argDirectoryTarget) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//
//        BlancoRestAutotestInputResultFieldStructure cdst = new BlancoRestAutotestInputResultFieldStructure();
//        Class cdstClass = cdst.getClass();//リフレクション利用
//
//
//        int countClassNum = 1;
//        ArrayList<ArrayList<String>> preconditionList = new ArrayList<ArrayList<String>>();
//        ArrayList<String> inputColumnNameList = new ArrayList<String>();
//        ArrayList<String> resultColumnNameList = new ArrayList<String>();
//        ArrayList<String> assertionList = new ArrayList<String>();
//
//        String matchFormat = "yyyy-MM-dd";
//
//        BlancoRestAutotestClassStructure processStructure = argClassStructure.get("blancometa2xml-process") ;
//        BlancoRestAutotestClassStructure telegramStructure = argClassStructure.get("blancotelegram") ;
//        BlancoRestAutotestClassStructure testCaseStructure = argClassStructure.get("blancotestcase") ;
//        BlancoRestAutotestClassStructure inputResultStructure = argClassStructure.get("blancoInputResult") ;
//
//        List<BlancoRestAutotestTestCaseFieldStructure> elementTestCaseFieldList = testCaseStructure.getTestCaseFieldList();
//        List<BlancoRestAutotestInputResultFieldStructure> elementInputResultFieldList = inputResultStructure.getInputResultFieldList();
//        BlancoRestAutotestInputResultFieldStructure elementColumnNameList  = inputResultStructure.getColumnNameList();
//        BlancoRestAutotestInputResultFieldStructure elementAssertionList  = inputResultStructure.getAssertionList();
//
//        //前提条件番号を取得
////        preconditionList = getPreconditionList(elementTestCaseFieldList);
//        testcaseMax = elementInputResultFieldList.size();
//
//        //カラム名情報取得
//        inputColumnNameList  = getColumnNameList(elementColumnNameList, "Input");
//        resultColumnNameList = getColumnNameList(elementColumnNameList, "Expect");
//
//        //アサーション情報取得
//        assertionList = getAssertionList(elementAssertionList);
//
//        for (int index = 0; index < elementInputResultFieldList.size(); index++) {
//
//            //Setupで使用する前提条件カウント数
//            int countPreconditionSetUPNum = 1;
//            //TestCaseで使用するInput項目の前提条件カウント数
//            int countPreconditionInputNum = 1;
//            //TestCaseで使用するResult(Expected)項目の前提条件カウント数
//            int countPreconditionResultNum = 1;
//            //tearDownで使用するOutput項目の前提条件カウント数
//            int countPreconditionOutputNum = 0;
//
//            /*
//             * この行の情報を取得しておく
//             */
//            BlancoRestAutotestInputResultFieldStructure inputResultFieldStructure =  elementInputResultFieldList.get(index);
//
//            /*
//             * inputResultStructureListとtestCaseStructureList は
//             * 表記の順番が一致していないといけない
//             */
//            BlancoRestAutotestTestCaseFieldStructure testCaseFieldStructure = elementTestCaseFieldList.get(index);
//
//            /*
//             * まず対象APIとパラメータ情報を取得する
//             */
//            String targetApi = testCaseFieldStructure.getApi();
//
//            //実装クラス生成
//            fCgFactory = BlancoCgObjectFactory.getInstance();
//            fCgSourceFile = fCgFactory.createSourceFile(processStructure
//                    .getPackage(), "このソースコードは blanco Frameworkによって自動生成されています。");
//            fCgSourceFile.setEncoding(fEncoding);
//            if (processStructure.getFileDescription() != null) {
//                fCgSourceFile.getLangDoc().getDescriptionList().add(
//                        processStructure.getFileDescription());
//            }
//
//            //クラス名+suffix(数字)追加(ファイル名にも追加される)
//            // 違うAPIでも、主になるテスト対象のAPI名をファイル名、クラス名とする
//            // surfix の "Test" は定義書に入れてしまう
//            String fileName = processStructure.getName() + String.format("%04d", countClassNum);
//            fCgClass = fCgFactory.createClass(fileName,
//                    BlancoStringUtil.null2Blank(processStructure.getDescription()) + getAnnotationList(elementTestCaseFieldList.get(index)));
//
//            fCgClass.setAbstract(false);
//
//            /* SuperClass を継承 */
//            BlancoCgType fCgType = new BlancoCgType();
//            String superClass = processStructure.getSuperClass();
//            String superPackage = processStructure.getSuperPackage();
//            System.out.println("/*** tueda ***/ supserClass: " + superClass);
//            if (superClass != null) {
//                fCgType.setName(superPackage + "." + superClass);
//                fCgClass.setExtendClassList(new ArrayList<BlancoCgType>());
//                fCgClass.getExtendClassList().add(fCgType);
//
//            }
//
//            fCgSourceFile.getClassList().add(fCgClass);
//
//            fCgSourceFile.getImportList().add("java.io.IOException");
//            fCgSourceFile.getImportList().add("csj_web.common.Config");
//            fCgSourceFile.getImportList().add("blanco.rest.Exception.BlancoRestException");
//            fCgSourceFile.getImportList().add("blanco.rest.valueobject.ErrorItem");
//            fCgSourceFile.getImportList().add("org.junit.After");
//            fCgSourceFile.getImportList().add("org.junit.Before");
//            fCgSourceFile.getImportList().add("org.junit.Test");
//            fCgSourceFile.getImportList().add("org.junit.runner.RunWith");
//
//            fCgSourceFile.getImportList().add("java.text.DateFormat");
//            fCgSourceFile.getImportList().add("java.text.ParseException");
//            fCgSourceFile.getImportList().add("java.text.SimpleDateFormat");
//            fCgSourceFile.getImportList().add("static junit.framework.TestCase.*");
//
//            fCgClass.getAnnotationList().add("RunWith(" + processStructure.getName() + "Runner.class)");
//
////            {
////                final BlancoCgField field = fCgFactory.createField(
////                        "precDetail", "java.util.LinkedHashMap<String, String>",
////                        "前提条件として車載機に設定されるパラメータ情報を設定します。");
////                fCgClass.getFieldList().add(field);
////                field.setAccess("protected");
////                field.setDefault("new LinkedHashMap<String, String>()");
////            }
//
////            {
////                final BlancoCgField field = fCgFactory.createField(
////                        "request", telegramStructure.getPackage() + "." + requestId,
////                        "入力情報を設定します。");
////                fCgClass.getFieldList().add(field);
////                field.setAccess("private");
////                field.setDefault("new " + requestId + "()");
////            }
////
////            {
////                final BlancoCgField field = fCgFactory.createField(
////                        "expectedResponse", telegramStructure.getPackage() + "." + responseId,
////                        "期待される出力情報を設定します。");
////                fCgClass.getFieldList().add(field);
////                field.setAccess("private");
////                field.setDefault("new " + responseId + "()");
////            }
//
////            {
////                final BlancoCgField field = fCgFactory.createField(
////                        "actualResponse", telegramStructure.getPackage() + "." + responseId,
////                        "実際の出力情報を設定します。");
////                fCgClass.getFieldList().add(field);
////                field.setAccess("private");
////                field.setDefault("new " + responseId + "()");
////            }
//
//            {
//                final BlancoCgField field = fCgFactory.createField(
//                        "api", testCaseStructure.getPackage() + "." + targetApi,
//                        "テスト対象のAPI");
//                fCgClass.getFieldList().add(field);
//                field.setAccess("private");
//                field.setDefault("new " + targetApi + "()");
//            }
//
//            {
//                final BlancoCgMethod methodProcess1 = fCgFactory.createMethod(
//                        "setUp", "サーバ接続情報・トークン設定など");
//                List<String> annotationList1 = new ArrayList<String>();
//                annotationList1.add("Before");
//                methodProcess1.setAnnotationList(annotationList1);
//                fCgClass.getMethodList().add(methodProcess1);
//
//                final List<String> listLine = methodProcess1
//                        .getLineList();
//
//                listLine.add("try {");
//                listLine.add("new Config(\"test/config.xml\");");
//                listLine.add("} catch (IOException e) {");
//                listLine.add("e.printStackTrace();");
//                listLine.add("}");
//                listLine.add("");
//                listLine.add("String token = \"toke\";");
//                listLine.add("String lang = \"ja\";");
//                listLine.add("");
//
//                //テスト対象 車載機SetUpデータ
//                listLine.add("//テスト対象 車載機SetUp");
//
//                if(elementTestCaseFieldList.get(index).getPhone() != null ){
//                    listLine.add("precDetail" + ".put(\"phone\", \"" + elementTestCaseFieldList.get(index).getPhone() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getReset() != null ){
//                    listLine.add("precDetail" + ".put(\"reset\", \"" + elementTestCaseFieldList.get(index).getReset() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getOpt1() != null ){
//                    listLine.add("precDetail" + ".put(\"opt1\", \"" + elementTestCaseFieldList.get(index).getOpt1() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getOpt2() != null ){
//                    listLine.add("precDetail" + ".put(\"opt2\", \"" + elementTestCaseFieldList.get(index).getOpt2() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getOpt3() != null ){
//                    listLine.add("precDetail" + ".put(\"opt3\", \"" + elementTestCaseFieldList.get(index).getOpt3() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getOpt4() != null ){
//                    listLine.add("precDetail" + ".put(\"opt4\", \"" + elementTestCaseFieldList.get(index).getOpt4() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getOpt5() != null ){
//                    listLine.add("precDetail" + ".put(\"opt5\", \"" + elementTestCaseFieldList.get(index).getOpt5() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getOpt6() != null ){
//                    listLine.add("precDetail" + ".put(\"opt6\", \"" + elementTestCaseFieldList.get(index).getOpt6() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getOpt7() != null ){
//                    listLine.add("precDetail" + ".put(\"opt7\", \"" + elementTestCaseFieldList.get(index).getOpt7() + "\");");
//                }
//                if(elementTestCaseFieldList.get(index).getAuto() != null ){
//                    listLine.add("precDetail" + ".put(\"auto\", \"" + elementTestCaseFieldList.get(index).getAuto() + "\");");
//                }
//
//                listLine.add("");
//                listLine.add("// ここでprecDetailを使って車載機の設定を行う");
//                /*
//                 * SystemId と deviceId が必要
//                 */
////                int systemIdIndex = this.searchParamNumber(requestInfo, "SystemId");
////                int deviceIdIndex = this.searchParamNumber(requestInfo, "DeviceId");
//
//                Class<?> inputClass = inputResultFieldStructure.getClass();
////                Method inputMethod = inputClass.getMethod("getInput" + systemIdIndex);
////                String systemId = (String) inputMethod.invoke(inputResultFieldStructure);
////                inputMethod = inputClass.getMethod("getInput" + deviceIdIndex);
////                String deviceId = (String) inputMethod.invoke(inputResultFieldStructure);
//
////                listLine.add("this.doPreTestSettings(\"" +
////                        systemId + "\", \"" + deviceId + "\");"
////                );
//                listLine.add("");
//            }
//
//            {
//                final BlancoCgMethod methodProcess2 = fCgFactory.createMethod(
//                        "testCase", "テスト実行");
//                List<String> annotationList2 = new ArrayList<String>();
//                annotationList2.add("Test");
//                methodProcess2.setAnnotationList(annotationList2);
//                fCgClass.getMethodList().add(methodProcess2);
//
//                final List<String> listLine = methodProcess2.getLineList();
//
//                //テスト対象 input処理
//                //電文項目の数だけadd
//                int pramInputCount = getTelegramPramCount(elementInputResultFieldList.get(index), "Input");
//
//                listLine.add("//テスト対象 input");
//
//                //listLine.add(annotationList.get(index));
//
//                // RequestBeanオブジェクトのリフレクションから、フィールド数を取得して、inputの項目数と一致しているか調べる
//                try {
//                    Class<?> clazz = Class.forName(telegramStructure.getPackage() + "." + requestId);
//                    Field[] requestFields = this.getAllFields(clazz);
////                    if(pramInputCount != requestFields.length) {
////                        System.out.println("====error!!===== Inputの項目数とRequestのフィールド数が一致しません");
////                        return;
////                    }
//
//                    for (int indexMain = 1;indexMain <= requestFields.length; indexMain++) {
//
//                        Method method = cdstClass.getMethod("getInput" + indexMain);
//                        String cc = (String) method.invoke(elementInputResultFieldList.get(index));
//                        if (cc == null) {
//                            cc = "";
//                        }
//
//                        Method requestMethod = requestInfo.getClass().getMethod("getParam" + indexMain);
//                        String reqParam = (String) requestMethod.invoke(requestInfo);
//                        if (reqParam != null) {
//                            String requestSetter = "request.set"
//                                    + reqParam.substring(0, 1).toUpperCase() + reqParam.substring(1);
//
//                            listLine.add(requestSetter + "(\"" + cc + "\");//" + inputColumnNameList.get(indexMain - 1));
//                        } else {
//                            System.out.println("/* WARN */ reqParam is null.");
//                        }
//                    }
//
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//                listLine.add("");
//
//                //テスト対象 期待するResult処理
//                //電文項目の数だけadd
//                int pramResultCount = getTelegramPramCount(elementInputResultFieldList.get(index), "Expect");
//
//                listLine.add("//テスト対象 期待するResult" + countPreconditionResultNum);
//
//                // RequestBeanオブジェクトのリフレクションから、フィールド数を取得して、inputの項目数と一致しているか調べる
//                try {
//                    Class<?> clazz = Class.forName(telegramStructure.getPackage() + "." + responseId);
//                    Field[] responseFields = this.getAllFields(clazz);
////                    if(pramResultCount != responseFields.length) {
////                        System.out.println("====error!!===== Resultの項目数とResponseのフィールド数が一致しません");
////                        return;
////                    }
//                    for (int indexMain = 1;indexMain <= responseFields.length; indexMain++) {
//
//                        Method method = cdstClass.getMethod("getExpect" + indexMain);
//                        String cc = (String) method.invoke(elementInputResultFieldList.get(index));
//                        if (cc == null) {
//                            cc = "";
//                        }
//
//                        Method responseMethod = responseInfo.getClass().getMethod("getParam" + indexMain);
//                        String resParam = (String) responseMethod.invoke(responseInfo);
//
////                        System.out.println("/* tueda */ resParam:" + resParam + ", index:" + indexMain);
//
//                        if (resParam == null) {
//                            resParam = "";
//                        }
//
//                        String expectSetter = "expectedResponse.set"
//                                + resParam.substring(0, 1).toUpperCase() + resParam.substring(1);
//
//                        listLine.add(expectSetter + "(\"" + cc + "\");//" + resultColumnNameList.get(indexMain - 1));
//                    }
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//                listLine.add("");
//
//                listLine.add("System.out.println(\"======テスト" + String.format("%04d", countClassNum) + "開始=======\");");
//                listLine.add("try {");
//                listLine.add("actualResponse = (" + responseId + ") api.send(request);");
//                listLine.add("} catch (BlancoRestException e) {");
//                listLine.add("e.printStackTrace();");
//                listLine.add("}");
//
//            }
//
//
//            {
//                final BlancoCgMethod methodProcess3 = fCgFactory.createMethod(
//                        "tearDown", "アサーション");
//                List<String> annotationList3 = new ArrayList<String>();
//                annotationList3.add("After");
//                methodProcess3.setAnnotationList(annotationList3);
//                fCgClass.getMethodList().add(methodProcess3);
//
//                final List<String> listLine = methodProcess3
//                        .getLineList();
//
//                listLine.add("//テスト対象");
//
//                //電文項目の数だけadd 冗長だが念のためもう一度。
//                int pramResultCount = getTelegramPramCount(elementInputResultFieldList.get(index), "Expect");
//
//                try {
//                    Class<?> clazz = Class.forName(telegramStructure.getPackage() + "." + responseId);
//                    Field [] responseFields = this.getAllFields(clazz);
////                    if(pramResultCount != responseFields.length) {
////                        System.out.println("====error!!===== Resultの項目数とResponseのフィールド数が一致しません");
////                        return;
////                    }
//
//                    // ここでエラーのチェックを行うコードを吐く
//                    /*
//                     * まずエラーコードが設定されているか確認する
//                     */
//                    String code = this.getExpectedValueByName(responseInfo, elementInputResultFieldList.get(index), "code");
//                    String message = this.getExpectedValueByName(responseInfo, elementInputResultFieldList.get(index), "messages");
//
//                    listLine.add("if (api.hasErrors()) {");
//                    listLine.add("ErrorItem errorItem = api.getErrors().get(0);");
//                    if (code != null) {
//                        listLine.add("assertEquals(\"API エラー発生(code)\", \"" + code + "\", errorItem.getcode());");
//                    }
//                    if (message != null) {
//                        listLine.add("assertEquals(\"API エラー発生(messages)\", \"" + message + "\", errorItem.getmessages().get(0));");
//                    }
//                    if (code == null && message == null) {
//                        listLine.add("assertTrue(\"API エラーが発生しましたが期待値が設定されていません code : \" + errorItem.getcode(), false);");
//                    }
//                    listLine.add("");
//                    listLine.add("return;");
//                    listLine.add("}");
//
//                    for (int indexMainResult = 0;indexMainResult < responseFields.length; indexMainResult++) {
//
//                        String expected = resultColumnNameList.get(indexMainResult);
//
//                        Method responseMethod = responseInfo.getClass().getMethod("getParam" + (indexMainResult + 1));
//                        String resParam = (String) responseMethod.invoke(responseInfo);
//                        if (resParam == null) {
//                            resParam = "";
//                        }
//
//                        String actualGetter = "actualResponse.get"
//                                + resParam.substring(0, 1).toUpperCase() + resParam.substring(1);
//                        String expectGetter = "expectedResponse.get"
//                                + resParam.substring(0, 1).toUpperCase() + resParam.substring(1);
//
////                        System.out.println("/* tueda */ actualGetter:" + actualGetter + ", expectGetter:" + expectGetter);
//
//                        if (assertionList.get(indexMainResult) == null) {
//                            continue;
//                        }
//                        if(assertionList.get(indexMainResult).startsWith("checkDate")){
//                             Matcher matcher = Pattern.compile("\\('(.+)'\\)").matcher(assertionList.get(indexMainResult));
//                            if (matcher.find()) {
//                                matchFormat = matcher.group(1);
//                            }
//
//
//                            listLine.add("assertTrue(\"テスト対象" + " error at " + expected + "\",checkDate(" + actualGetter + "(),\"" + matchFormat + "\"));//" + expected);
//
//                        }
//
//                        switch (assertionList.get(indexMainResult)){
//                            case "-":
//                                break;
//                            case "NotNull":
//                                listLine.add("assertNotNull(\"テスト対象" + " error at " + expected + "\", " + actualGetter + "());//" + expected);
//                                break;
//                            case "Null":
//                                listLine.add("assertNull(\"テスト対象"+ " error at " + expected + "\", " + actualGetter + "());//" + expected);
//                                break;
//                            case "Equal":
//                                listLine.add("assertEquals(\"テスト対象"+ " error at " + expected + "\", " + expectGetter + "(), " + actualGetter + "());//" + expected);
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            {
//                final BlancoCgMethod methodProcess5 = fCgFactory.createMethod(
//                        "checkDate", "日付フォーマットチェック");
//                fCgClass.getMethodList().add(methodProcess5);
//                methodProcess5.setStatic(true);
//
//                methodProcess5.getParameterList().add(
//                        fCgFactory.createParameter("date",
//                                "java.lang.String", "入力情報"));
//
//                methodProcess5.getParameterList().add(
//                        fCgFactory.createParameter("dataFormat",
//                                "java.lang.String", "データフォーマット情報"));
//
//                final List<String> listLine = methodProcess5
//                        .getLineList();
//                listLine.add("try");
//                listLine.add("{");
//                listLine.add("DateFormat DF = new SimpleDateFormat(dataFormat);");
//                listLine.add("DF.parse(date);");
//                listLine.add("}");
//                listLine.add("catch (ParseException ex)");
//                listLine.add("{");
//                listLine.add("return false;");
//                listLine.add("}");
//                listLine.add("");
//                listLine.add("return true;");
//
//                methodProcess5.setReturn(fCgFactory.createReturn("boolean",
//                        "指定フォーマットに則しているかどうか"));
//            }
//
////            System.out.println("/* tueda */ Delimiter. : " + fCgSourceFile + "\n\n" + argDirectoryTarget);
//
//            BlancoCgTransformerFactory.getJavaSourceTransformer().transform(
//                    fCgSourceFile, argDirectoryTarget);
//
//            countClassNum++;
//        }
//    }
//
//    private void structure2AllTestRunner(final BlancoRestAutotestClassStructure argClassStructure, final File argDirectoryTarget) {
//        //クラス生成
//        fCgFactory = BlancoCgObjectFactory.getInstance();
//        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure.getPackage(), "このソースコードは blanco Frameworkによって自動生成されています。");
//        fCgSourceFile.setEncoding(fEncoding);
//
//        fCgClass = fCgFactory.createClass(argClassStructure.getName() + "AllRunner",
//                "このクラスはすべてのテストを順に一回ずつ行うJunitのTestRunnerです");
//
//        fCgClass.setAbstract(false);
//        fCgClass.getExtendClassList().add(fCgFactory.createType("org.junit.runners.ParentRunner<Runner>"));
//        fCgSourceFile.getClassList().add(fCgClass);
//
//        fCgSourceFile.getImportList().add("org.junit.runners.BlockJUnit4ClassRunner");
//        fCgSourceFile.getImportList().add("org.junit.runners.ParentRunner");
//
//        fCgSourceFile.getImportList().add("java.util.ArrayList");
//
//        {
//            final BlancoCgField field = fCgFactory.createField(
//                    "runners", "java.util.List<Runner>", "");
//            fCgClass.getFieldList().add(field);
//            field.setAccess("private");
//            field.setFinal(true);
//        }
//
//        {
//            final BlancoCgField field = fCgFactory.createField(
//                    "testCaseMax", "int", "");
//            fCgClass.getFieldList().add(field);
//            field.setAccess("private");
//            field.setFinal(true);
//
//            field.setDefault(Integer.toString(testcaseMax));
//        }
//
//        {
//            final BlancoCgMethod constractor = fCgFactory.createMethod(argClassStructure.getName() + "AllRunner", "");
//
//            constractor.setConstructor(true);
//            constractor.getThrowList().add(fCgFactory.createException("org.junit.runners.model.InitializationError", ""));
//            constractor.getParameterList().add(fCgFactory.createParameter("testClass", "java.lang.Class<?>", ""));
//            final List<String> listLine = constractor.getLineList();
//
//            listLine.add("super(testClass);");
//            listLine.add("");
//            listLine.add("List<Class<?>> classList = getClassList();");
//            listLine.add("runners = new ArrayList<>();");
//            listLine.add("for (Class<?> clazz : classList) {");
//            listLine.add("runners.add(new BlockJUnit4ClassRunner(clazz));");
//            listLine.add("}");
//
//            fCgClass.getMethodList().add(constractor);
//        }
//
//        {
//            final BlancoCgMethod method = fCgFactory.createMethod("getClassList", "すべてのテストクラス名のリストを返します");
//            method.setReturn(fCgFactory.createReturn("java.util.List<Class<?>>", ""));
//            final List<String> listLine = method.getLineList();
//
//            listLine.add("");
//            listLine.add("String prefix = \"" + argClassStructure.getPackage() + "." +  argClassStructure.getName() + "\";");
//            listLine.add("List<Class<?>> classList = new ArrayList<>();");
//            listLine.add("");
//            listLine.add("for (int testCaseNum = 1; testCaseNum <= testCaseMax; testCaseNum++) {");
//            listLine.add("String classFullName = prefix + String.format(\"%04d\", testCaseNum);");
//            listLine.add("try {");
//            listLine.add("classList.add(Class.forName(classFullName));");
//            listLine.add("} catch (ClassNotFoundException e) {");
//            listLine.add("// 例外処理");
//            listLine.add("throw new RuntimeException(\"クラスが存在しない : \" + classFullName);");
//            listLine.add("}");
//            listLine.add("}");
//            listLine.add("");
//            listLine.add("return classList;");
//
//            fCgClass.getMethodList().add(method);
//        }
//
//        {
//            final BlancoCgMethod method = fCgFactory.createMethod("getChildren", "");
//            method.setReturn(fCgFactory.createReturn("java.util.List<Runner>", ""));
//            method.setAccess("protected");
//            method.getAnnotationList().add("Override");
//            final List<String> listLine = method.getLineList();
//
//            listLine.add("return runners;");
//
//            fCgClass.getMethodList().add(method);
//        }
//
//        {
//            final BlancoCgMethod method = fCgFactory.createMethod("describeChild", "");
//            method.getParameterList().add(fCgFactory.createParameter("child", "org.junit.runner.Runner", ""));
//            method.setReturn(fCgFactory.createReturn("org.junit.runner.Description", ""));
//            method.setAccess("protected");
//            method.getAnnotationList().add("Override");
//            final List<String> listLine = method.getLineList();
//
//            listLine.add("return child.getDescription();");
//
//            fCgClass.getMethodList().add(method);
//        }
//
//        {
//            final BlancoCgMethod method = fCgFactory.createMethod("runChild", "");
//            method.getParameterList().add(fCgFactory.createParameter("child", "org.junit.runner.Runner", ""));
//            method.getParameterList().add(fCgFactory.createParameter("notifier", "org.junit.runner.notification.RunNotifier", ""));
//            method.setAccess("protected");
//            method.getAnnotationList().add("Override");
//            final List<String> listLine = method.getLineList();
//
//            listLine.add("child.run(notifier);");
//
//            fCgClass.getMethodList().add(method);
//        }
//
//        BlancoCgTransformerFactory.getJavaSourceTransformer().transform(
//                fCgSourceFile, argDirectoryTarget);
//    }
//
//    private void structure2AllTestSuite(final BlancoRestAutotestClassStructure argClassStructure, final File argDirectoryTarget) {
//        fCgFactory = BlancoCgObjectFactory.getInstance();
//        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure.getPackage(), "このソースコードは blanco Frameworkによって自動生成されています。");
//        fCgSourceFile.setEncoding(fEncoding);
//        fCgClass = fCgFactory.createClass(argClassStructure.getName() + "All", "");
//        fCgClass.setAbstract(false);
//        fCgSourceFile.getClassList().add(fCgClass);
//
//        fCgSourceFile.getImportList().add("org.junit.runner.RunWith");
//
//        fCgClass.getAnnotationList().add("RunWith(" + argClassStructure.getName() + "AllRunner.class)");
//
////        System.out.println("/* tueda AllRunner */ Delimiter. : " + fCgSourceFile + "\n\n" + argDirectoryTarget);
//
//        BlancoCgTransformerFactory.getJavaSourceTransformer().transform(
//                fCgSourceFile, argDirectoryTarget);
//    }
//
//    private BlancoRestAutotestTelegramListStructure searchTelegramInfo(List<BlancoRestAutotestTelegramListStructure> structureList, String api, String kind) {
//
//        BlancoRestAutotestTelegramListStructure telegram = null;
//
//        for (BlancoRestAutotestTelegramListStructure structure : structureList) {
//            if (api.equals(structure.getApiName()) && kind.equals(structure.getTelegramKind())) {
//                telegram = structure;
//                break;
//            }
//        }
//        return telegram;
//    }
//
//    private int searchParamNumber(BlancoRestAutotestTelegramListStructure structure, String param) {
//        int number = 0;
//
//        Class<?> clazz = structure.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//
//        for (int index = 1; index < fields.length; index++) {
//            try {
//                Method method = clazz.getMethod("getParam" + index);
//                String tmp = (String) method.invoke(structure);
//                if (tmp == null) {
//                    tmp = "";
//                }
//                if (tmp.equals(param)) {
//                    number = index;
//                    break;
//                }
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//                break;
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//                break;
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//                break;
//            }
//        }
//        return number;
//    }
//
//    private String getExpectedValueByName(BlancoRestAutotestTelegramListStructure telegramList,
//                                          BlancoRestAutotestInputResultFieldStructure inputResult,
//                                          String name) {
//        String value = null;
//        int index = this.searchParamNumber(telegramList, name);
//        if (index != 0) {
//            Method method = null;
//            try {
//                method = inputResult.getClass().getMethod("getExpect" + index);
//                value = (String) method.invoke(inputResult);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return value;
//    }
//
//    private Field [] getAllFields(Class<?> clazz) {
//        Field [] returnField = null;
//        List<Field> allFields = new ArrayList<>();
//        if (clazz != null) {
//            Class<?> superClazz = clazz.getSuperclass();
//            if (superClazz != null) {
//                Field [] superFields = superClazz.getDeclaredFields();
//                List<Field> tmp = new ArrayList<>(Arrays.asList(superFields));
//                allFields.addAll(tmp);
//            }
//            Field [] childField = clazz.getDeclaredFields();
//            List<Field> childList = new ArrayList<>(Arrays.asList(childField));
//            allFields.addAll(childList);
//            returnField = new Field[allFields.size()];
//
//        }
//        return allFields.toArray(returnField);
//    }

}
