/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.sample;

import java.util.ArrayList;

import blanco.restgenerator.valueobject.ApiPostTelegram;

/**
 * blancoRestのサンプルAPIの要求電文です。
 */
public class GetTableSample2PostRequest extends ApiPostTelegram {
    /**
     * フィールド [codeNames]
     *
     * 項目の型 [java.util.ArrayList]
     */
    private ArrayList<String> fCodeNames;

    /**
     * フィールド [codeNames]のセッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     *
     * @param argCodeNames フィールド[codeNames]に格納したい値
     */
    public void setCodeNames(final ArrayList<String> argCodeNames) {
        this.fCodeNames = argCodeNames;
    }

    /**
     * フィールド[codeNames]のゲッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     *
     * @return フィールド[codeNames]に格納されている値
     */
    public ArrayList<String> getCodeNames() {
        return this.fCodeNames;
    }

    /**
     * フィールド[codeNames]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     *
     * @return フィールド[codeNames]の型名文字列
     */
    public static String typeCodeNames() {
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
        buf = buf + "blanco.sample.GetTableSample2PostRequest[";
        buf = buf + "codeNames=" +  this.fCodeNames;
        buf = buf + "]";
        return buf;
    }
}
