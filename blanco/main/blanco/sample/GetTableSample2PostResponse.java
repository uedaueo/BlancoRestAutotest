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
     * フィールド [myLong]
     *
     * 項目の型 [Long]
     * Long値
     */
    private Long fMyLong;

    /**
     * フィールド [myArray]
     *
     * 項目の型 [java.util.ArrayList]
     * Longの配列
     */
    private ArrayList<Long> fMyArray;

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
     * フィールド [myLong]のセッターメソッド
     *
     * 項目の型 [Long]
     * Long値
     *
     * @param argMyLong フィールド[myLong]に格納したい値
     */
    public void setMyLong(final Long argMyLong) {
        this.fMyLong = argMyLong;
    }

    /**
     * フィールド[myLong]のゲッターメソッド
     *
     * 項目の型 [Long]
     * Long値
     *
     * @return フィールド[myLong]に格納されている値
     */
    public Long getMyLong() {
        return this.fMyLong;
    }

    /**
     * フィールド[myLong]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * Long値
     *
     * @return フィールド[myLong]の型名文字列
     */
    public static String typeMyLong() {
        return "Long";
    }

    /**
     * フィールド [myArray]のセッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     * Longの配列
     *
     * @param argMyArray フィールド[myArray]に格納したい値
     */
    public void setMyArray(final ArrayList<Long> argMyArray) {
        this.fMyArray = argMyArray;
    }

    /**
     * フィールド[myArray]のゲッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     * Longの配列
     *
     * @return フィールド[myArray]に格納されている値
     */
    public ArrayList<Long> getMyArray() {
        return this.fMyArray;
    }

    /**
     * フィールド[myArray]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * Longの配列
     *
     * @return フィールド[myArray]の型名文字列
     */
    public static String typeMyArray() {
        return "java.util.ArrayList<Long>";
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
        buf = buf + ",myLong=" +  this.fMyLong;
        buf = buf + ",myArray=" +  this.fMyArray;
        buf = buf + "]";
        return buf;
    }
}
