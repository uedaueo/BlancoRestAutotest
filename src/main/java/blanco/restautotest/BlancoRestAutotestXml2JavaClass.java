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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import blanco.restautotest.valueobject.BlancoRestAutotestTestCaseData;

/**
 * バリューオブジェクト用中間XMLファイルから Javaソースコードを自動生成するクラス。
 * <p>
 * blancoValueObjectの主たるクラスのひとつです。
 *
 * @author IGA Tosiki
 * @author Kinoko Matsumoto 2017.09.01
 */
public class BlancoRestAutotestXml2JavaClass {
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

    /**
     * バリューオブジェクトを表現する中間XMLファイルから、Javaソースコードを自動生成します。
     *
     * @param argDirectoryTarget ソースコード生成先ディレクトリ
     * @throws IOException 入出力例外が発生した場合
     */
    public void process(
            final File argDirectoryTarget, final List<BlancoRestAutotestTestCaseData> argAllTestCaseData) throws IOException {

        // 得られた情報からJavaソースコードを生成します。
        if (BlancoRestAutotestUtil.isOutputJson) {
            testCaseData2Json(argDirectoryTarget, argAllTestCaseData);
        }
    }

    public void testCaseData2Json(
            final File argDirectoryTarget,
            final List<BlancoRestAutotestTestCaseData> argAllTestCaseData
    ) throws JsonProcessingException {
        // 従来と互換性を持たせるため、/mainサブフォルダに出力します。
        final File fileBlancoMain = new File(argDirectoryTarget.getAbsolutePath());

        /* tueda DEBUG */
        System.out.println("testCaseData2Json : " + fileBlancoMain.getAbsolutePath());
        System.out.println("targetFilePath: " + argDirectoryTarget.getAbsolutePath());

        for (BlancoRestAutotestTestCaseData testCaseData : argAllTestCaseData) {            
            ObjectMapper mapper = JsonMapper.builder()
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .build();
            DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
            printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
            String requestJson = mapper.writer(printer).writeValueAsString(testCaseData.getInput());
            System.out.println("### " + testCaseData.getExpect());
            String responseJson = mapper.writer(printer).writeValueAsString(testCaseData.getExpect());
            String requestFileName = testCaseData.getSimpleInputId() + "-" + testCaseData.getCaseId() + ".json";
            String responseFileName = testCaseData.getSimpleExpectId() + "-" + testCaseData.getCaseId() + ".json";
            makeJsonFiles(requestFileName, argDirectoryTarget, requestJson);
            makeJsonFiles(responseFileName, argDirectoryTarget, responseJson);
            System.out.println("JSON: " + requestJson);
            System.out.println("JSON: " + responseJson);
        }
    }

    public void makeJsonFiles(String fileName, File argDirectoryTarget, String jsonData) {
        File file = new File(argDirectoryTarget.getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("新規ディレクトリを作成しました。 FilePath : " + file.getAbsolutePath());
        }
        try {
            File jsonFile = new File(argDirectoryTarget.getAbsolutePath() + "/" + fileName);
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(jsonFile), BlancoRestAutotestUtil.fileEncoding)));
            printWriter.print(jsonData);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
