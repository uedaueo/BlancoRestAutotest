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
import blanco.restautotest.valueobject.BlancoRestAutotestTestCaseData;
import blanco.restautotest.valueobject.BlancoRestAutotestTestCaseFieldStructure;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            final File argDirectoryTarget, final List<BlancoRestAutotestTestCaseData> argAllTestCaseData) throws IOException {

        // 得られた情報からJavaソースコードを生成します。
        testCaseData2Json(argDirectoryTarget, argAllTestCaseData);
    }

    public void testCaseData2Json(
            final File argDirectoryTarget,
            final List<BlancoRestAutotestTestCaseData> argAllTestCaseData
    ) throws JsonProcessingException {
        // 従来と互換性を持たせるため、/mainサブフォルダに出力します。
        final File fileBlancoMain = new File(argDirectoryTarget.getAbsolutePath());

        /* tueda DEBUG */
        System.out.println("testCaseData2Json : " + fileBlancoMain.getAbsolutePath());

        for (BlancoRestAutotestTestCaseData testCaseData : argAllTestCaseData) {
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper.writeValueAsString(testCaseData.getInput());
            System.out.println("JSON: " + requestJson);
        }
    }
}
