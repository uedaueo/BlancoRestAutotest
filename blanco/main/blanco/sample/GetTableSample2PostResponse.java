/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.sample;

import java.util.ArrayList;

import blanco.restgenerator.valueobject.ApiPostTelegram;

/**
 * blancoRestのサンプルAPIの応答電文です。
 */
public class GetTableSample2PostResponse extends ApiPostTelegram {
    /**
     * フィールド [codeMasters]
     *
     * 項目の型 [java.util.ArrayList]
     * コードマスター
     */
    private ArrayList<CodeDefinition> fCodeMasters;

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
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * オブジェクトのシャロー範囲でしかtoStringされない点に注意して利用してください。
     *
     * @return バリューオブジェクトの文字列表現。
     */
    @Override
    public String toString() {
        java.lang.String buf = "";
        buf = buf + "blanco.sample.GetTableSample2PostResponse[";
        buf = buf + "codeMasters=" +  this.fCodeMasters;
        buf = buf + "]";
        return buf;
    }
}
