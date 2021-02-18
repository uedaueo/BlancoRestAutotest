package blanco.sample;

import java.util.ArrayList;

/**
 * コードマスタを表すクラスです。
 */
public class JsonContainer {
    /**
     * フィールド: [jsonDataList]。
     */
    private ArrayList<TableSampleOption> fJsonDataList;

    /**
     * フィールド [jsonDataList] の値を設定します。
     *
     * @param argJsonDataList フィールド[jsonDataList]に設定する値。
     */
    public void setJsonDataList(final ArrayList<TableSampleOption> argJsonDataList) {
        fJsonDataList = argJsonDataList;
    }

    /**
     * フィールド [jsonDataList] の値を取得します。
     *
     * @return フィールド[jsonDataList]から取得した値。
     */
    public ArrayList<TableSampleOption> getJsonDataList() {
        return fJsonDataList;
    }

    /**
     * このバリューオブジェクトを指定のターゲットに複写します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ複写処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @param target target value object.
     */
    public void copyTo(final JsonContainer target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: JsonContainer#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fJsonDataList
        // Type: java.util.ArrayList
        // フィールド[fJsonDataList]はサポート外の型[java.util.ArrayListblanco.sample.TableSampleOption]です。
    }
}
