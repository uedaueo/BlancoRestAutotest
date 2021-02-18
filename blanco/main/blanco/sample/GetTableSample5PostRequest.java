/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.sample;

import blanco.restgenerator.valueobject.ApiPostTelegram;
import blanco.restgenerator.valueobject.ApiTelegram;

/**
 * blancoRestのサンプルAPIの要求電文です。
 */
public class GetTableSample5PostRequest extends ApiPostTelegram {
    /**
     * フィールド [id]
     *
     * 項目の型 [blanco.restgenerator.valueobject.ApiTelegram]
     */
    private ApiTelegram fId;

    /**
     * フィールド [jsonData]
     *
     * 項目の型 [blanco.sample.TableSampleOption]
     */
    private TableSampleOption fJsonData;

    /**
     * フィールド [id]のセッターメソッド
     *
     * 項目の型 [blanco.restgenerator.valueobject.ApiTelegram]
     *
     * @param argId フィールド[id]に格納したい値
     */
    public void setId(final ApiTelegram argId) {
        this.fId = argId;
    }

    /**
     * フィールド[id]のゲッターメソッド
     *
     * 項目の型 [blanco.restgenerator.valueobject.ApiTelegram]
     *
     * @return フィールド[id]に格納されている値
     */
    public ApiTelegram getId() {
        return this.fId;
    }

    /**
     * フィールド[id]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     *
     * @return フィールド[id]の型名文字列
     */
    public static String typeId() {
        return "blanco.restgenerator.valueobject.ApiTelegram";
    }

    /**
     * フィールド [jsonData]のセッターメソッド
     *
     * 項目の型 [blanco.sample.TableSampleOption]
     *
     * @param argJsonData フィールド[jsonData]に格納したい値
     */
    public void setJsonData(final TableSampleOption argJsonData) {
        this.fJsonData = argJsonData;
    }

    /**
     * フィールド[jsonData]のゲッターメソッド
     *
     * 項目の型 [blanco.sample.TableSampleOption]
     *
     * @return フィールド[jsonData]に格納されている値
     */
    public TableSampleOption getJsonData() {
        return this.fJsonData;
    }

    /**
     * フィールド[jsonData]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     *
     * @return フィールド[jsonData]の型名文字列
     */
    public static String typeJsonData() {
        return "blanco.sample.TableSampleOption";
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
        buf = buf + "blanco.sample.GetTableSample5PostRequest[";
        buf = buf + "id=" +  this.fId;
        buf = buf + ",jsonData=" +  this.fJsonData;
        buf = buf + "]";
        return buf;
    }
}
