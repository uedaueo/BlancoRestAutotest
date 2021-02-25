/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.sample;

import java.util.ArrayList;

import blanco.restgenerator.valueobject.ApiPostTelegram;
import blanco.restgenerator.valueobject.ApiTelegram;

/**
 * blancoRestのサンプルAPIの応答電文です。
 */
public class GetTableSample5PostResponse extends ApiPostTelegram {
    /**
     * フィールド [telegram1]
     *
     * 項目の型 [blanco.restgenerator.valueobject.ApiTelegram]
     * 実態はTelegram1
     */
    private ApiTelegram fTelegram1;

    /**
     * フィールド [telegram2]
     *
     * 項目の型 [java.util.ArrayList]
     * 実体はList<Telegram2>
     */
    private ArrayList<ApiTelegram> fTelegram2;

    /**
     * フィールド [memo]
     *
     * 項目の型 [java.lang.String]
     * メモ
     */
    private String fMemo;

    /**
     * フィールド [jsonContainer]
     *
     * 項目の型 [blanco.sample.JsonContainer]
     * JSONデータコンテナ
     */
    private JsonContainer fJsonContainer;

    /**
     * フィールド [telegram1]のセッターメソッド
     *
     * 項目の型 [blanco.restgenerator.valueobject.ApiTelegram]
     * 実態はTelegram1
     *
     * @param argTelegram1 フィールド[telegram1]に格納したい値
     */
    public void setTelegram1(final ApiTelegram argTelegram1) {
        this.fTelegram1 = argTelegram1;
    }

    /**
     * フィールド[telegram1]のゲッターメソッド
     *
     * 項目の型 [blanco.restgenerator.valueobject.ApiTelegram]
     * 実態はTelegram1
     *
     * @return フィールド[telegram1]に格納されている値
     */
    public ApiTelegram getTelegram1() {
        return this.fTelegram1;
    }

    /**
     * フィールド[telegram1]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * 実態はTelegram1
     *
     * @return フィールド[telegram1]の型名文字列
     */
    public static String typeTelegram1() {
        return "blanco.restgenerator.valueobject.ApiTelegram";
    }

    /**
     * フィールド [telegram2]のセッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     * 実体はList<Telegram2>
     *
     * @param argTelegram2 フィールド[telegram2]に格納したい値
     */
    public void setTelegram2(final ArrayList<ApiTelegram> argTelegram2) {
        this.fTelegram2 = argTelegram2;
    }

    /**
     * フィールド[telegram2]のゲッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     * 実体はList<Telegram2>
     *
     * @return フィールド[telegram2]に格納されている値
     */
    public ArrayList<ApiTelegram> getTelegram2() {
        return this.fTelegram2;
    }

    /**
     * フィールド[telegram2]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * 実体はList<Telegram2>
     *
     * @return フィールド[telegram2]の型名文字列
     */
    public static String typeTelegram2() {
        return "java.util.ArrayList<blanco.restgenerator.valueobject.ApiTelegram>";
    }

    /**
     * フィールド [memo]のセッターメソッド
     *
     * 項目の型 [java.lang.String]
     * メモ
     *
     * @param argMemo フィールド[memo]に格納したい値
     */
    public void setMemo(final String argMemo) {
        this.fMemo = argMemo;
    }

    /**
     * フィールド[memo]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * メモ
     *
     * @return フィールド[memo]に格納されている値
     */
    public String getMemo() {
        return this.fMemo;
    }

    /**
     * フィールド[memo]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * メモ
     *
     * @return フィールド[memo]の型名文字列
     */
    public static String typeMemo() {
        return "java.lang.String";
    }

    /**
     * フィールド [jsonContainer]のセッターメソッド
     *
     * 項目の型 [blanco.sample.JsonContainer]
     * JSONデータコンテナ
     *
     * @param argJsonContainer フィールド[jsonContainer]に格納したい値
     */
    public void setJsonContainer(final JsonContainer argJsonContainer) {
        this.fJsonContainer = argJsonContainer;
    }

    /**
     * フィールド[jsonContainer]のゲッターメソッド
     *
     * 項目の型 [blanco.sample.JsonContainer]
     * JSONデータコンテナ
     *
     * @return フィールド[jsonContainer]に格納されている値
     */
    public JsonContainer getJsonContainer() {
        return this.fJsonContainer;
    }

    /**
     * フィールド[jsonContainer]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * JSONデータコンテナ
     *
     * @return フィールド[jsonContainer]の型名文字列
     */
    public static String typeJsonContainer() {
        return "blanco.sample.JsonContainer";
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
        buf = buf + "blanco.sample.GetTableSample5PostResponse[";
        buf = buf + "telegram1=" +  this.fTelegram1;
        buf = buf + ",telegram2=" +  this.fTelegram2;
        buf = buf + ",memo=" + this.fMemo;
        buf = buf + ",jsonContainer=" +  this.fJsonContainer;
        buf = buf + "]";
        return buf;
    }
}