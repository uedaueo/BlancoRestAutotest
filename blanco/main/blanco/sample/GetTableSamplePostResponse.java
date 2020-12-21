/*
 * このソースコードは blanco Frameworkによって自動生成されています。
 */
package blanco.sample;

import java.util.ArrayList;

import blanco.restgenerator.valueobject.ApiPostTelegram;

/**
 * blancoRestのサンプルAPIの応答電文です。
 */
public class GetTableSamplePostResponse extends ApiPostTelegram {
    /**
     * フィールド [tableData]
     *
     * 項目の型 [java.util.ArrayList]
     * テーブルデータ
     */
    private ArrayList<TableSampleRow> fTableData;

    /**
     * フィールド [tableData]のセッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     * テーブルデータ
     *
     * @param argTableData フィールド[tableData]に格納したい値
     */
    public void setTableData(final ArrayList<TableSampleRow> argTableData) {
        this.fTableData = argTableData;
    }

    /**
     * フィールド[tableData]のゲッターメソッド
     *
     * 項目の型 [java.util.ArrayList]
     * テーブルデータ
     *
     * @return フィールド[tableData]に格納されている値
     */
    public ArrayList<TableSampleRow> getTableData() {
        return this.fTableData;
    }

    /**
     * フィールド[tableData]のゲッターメソッド
     *
     * 項目の型 [java.lang.String]
     * テーブルデータ
     *
     * @return フィールド[tableData]の型名文字列
     */
    public static String typeTableData() {
        return "java.util.ArrayList<blanco.sample.TableSampleRow>";
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
        buf = buf + "blanco.sample.GetTableSamplePostResponse[";
        buf = buf + "tableData=" +  this.fTableData;
        buf = buf + "]";
        return buf;
    }
}
