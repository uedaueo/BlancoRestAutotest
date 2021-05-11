/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.sample;

import java.util.ArrayList;

import blanco.restgenerator.valueobject.ApiPostTelegram;

/**
 * blancoRestのサンプルAPIの応答電文です。
 */
public class GetTableSample6PostResponse extends ApiPostTelegram {
    /**
     * フィールド [codeMasters]
     *
     * 項目の型 [java.util.ArrayList]
     * コードマスター
     */
    private ArrayList<CodeDefinition> fCodeMasters;

    /**
     * フィールド [object1]
     *
     * 項目の型 [blanco.sample.ObjectSample]
     * オブジェクト１
     */
    private ObjectSample fObject1;

    /**
     * フィールド [codeMasters]のセッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     * コードマスター
     *
     * @param argCodeMasters フィールド[codeMasters]に格納したい値
     */
    public void setCodeMasters(final ArrayList<CodeDefinition> argCodeMasters) {
        this.fCodeMasters = argCodeMasters;
    }

    /**
     * フィールド[codeMasters]のゲッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     * コードマスター
     *
     * @return フィールド[codeMasters]に格納されている値
     */
    public ArrayList<CodeDefinition> getCodeMasters() {
        return this.fCodeMasters;
    }

    /**
     * フィールド[codeMasters]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * コードマスター
     *
     * @return フィールド[codeMasters]の型名文字列
     */
    public static String typeCodeMasters() {
        return "java.util.ArrayList<blanco.sample.CodeDefinition>";
    }

    /**
     * フィールド [object1]のセッターメソッド
     *
     * 項目の型 [blanco.sample.ObjectSample]
     * オブジェクト１
     *
     * @param argObject1 フィールド[object1]に格納したい値
     */
    public void setObject1(final ObjectSample argObject1) {
        this.fObject1 = argObject1;
    }

    /**
     * フィールド[object1]のゲッターメソッド
     *
     * 項目の型 [blanco.sample.ObjectSample]
     * オブジェクト１
     *
     * @return フィールド[object1]に格納されている値
     */
    public ObjectSample getObject1() {
        return this.fObject1;
    }

    /**
     * フィールド[object1]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * オブジェクト１
     *
     * @return フィールド[object1]の型名文字列
     */
    public static String typeObject1() {
        return "blanco.sample.ObjectSample";
    }

    /**
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * オブジェクトのシャロー範囲でしかtoStringされない点に注意して利用してください。
     *
     * @return バリューオブジェクトの文字列表現。
     */
    @Override
    public String toString() {
        java.lang.String buf = "";
        buf = buf + "blanco.sample.GetTableSample6PostResponse[";
        buf = buf + "codeMasters=" +  this.fCodeMasters;
        buf = buf + ",object1=" +  this.fObject1;
        buf = buf + "]";
        return buf;
    }
}
