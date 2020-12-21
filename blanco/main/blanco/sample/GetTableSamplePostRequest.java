/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.sample;

import java.util.ArrayList;

import blanco.restgenerator.valueobject.ApiPostTelegram;

/**
 * blancoRestのサンプルAPIの要求電文です。
 */
public class GetTableSamplePostRequest extends ApiPostTelegram {
    /**
     * フィールド [kind]
     *
     * 項目の型 [java.lang.String]
     */
    private String fKind;

    /**
     * フィールド [options]
     *
     * 項目の型 [java.util.ArrayList]
     */
    private ArrayList<TableSampleOption> fOptions;

    /**
     * フィールド [memo]
     *
     * 項目の型 [java.util.ArrayList]
     */
    private ArrayList<String> fMemo;

    /**
     * フィールド [kind]のセッターメソッド
     *
     * 項目の型 [java.lang.String]
     *
     * @param argKind フィールド[kind]に格納したい値
     */
    public void setKind(final String argKind) {
        this.fKind = argKind;
    }

    /**
     * フィールド[kind]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     *
     * @return フィールド[kind]に格納されている値
     */
    public String getKind() {
        return this.fKind;
    }

    /**
     * フィールド[kind]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     *
     * @return フィールド[kind]の型名文字列
     */
    public static String typeKind() {
        return "java.lang.String";
    }

    /**
     * フィールド [options]のセッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     *
     * @param argOptions フィールド[options]に格納したい値
     */
    public void setOptions(final ArrayList<TableSampleOption> argOptions) {
        this.fOptions = argOptions;
    }

    /**
     * フィールド[options]のゲッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     *
     * @return フィールド[options]に格納されている値
     */
    public ArrayList<TableSampleOption> getOptions() {
        return this.fOptions;
    }

    /**
     * フィールド[options]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     *
     * @return フィールド[options]の型名文字列
     */
    public static String typeOptions() {
        return "java.util.ArrayList<blanco.sample.TableSampleOption>";
    }

    /**
     * フィールド [memo]のセッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     *
     * @param argMemo フィールド[memo]に格納したい値
     */
    public void setMemo(final ArrayList<String> argMemo) {
        this.fMemo = argMemo;
    }

    /**
     * フィールド[memo]のゲッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     *
     * @return フィールド[memo]に格納されている値
     */
    public ArrayList<String> getMemo() {
        return this.fMemo;
    }

    /**
     * フィールド[memo]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     *
     * @return フィールド[memo]の型名文字列
     */
    public static String typeMemo() {
        return "java.util.ArrayList<java.lang.String>";
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
        buf = buf + "blanco.sample.GetTableSamplePostRequest[";
        buf = buf + "kind=" + this.fKind;
        buf = buf + ",options=" +  this.fOptions;
        buf = buf + ",memo=" +  this.fMemo;
        buf = buf + "]";
        return buf;
    }
}