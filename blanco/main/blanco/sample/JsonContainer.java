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
     * Copies this value object to the specified target.
     *
     * <P>Cautions for use</P>
     * <UL>
     * <LI>Only the shallow range of the object will be subject to the copying process.
     * <LI>Do not use this method if the object has a circular reference.
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
        // Field[fJsonDataList] is an unsupported type[java.util.ArrayListblanco.sample.TableSampleOption].
    }
}
