package blanco.sample;

import java.util.ArrayList;

/**
 * テーブルサンプルのオプションデータクラスです。
 */
public class TableSampleOption {
    /**
     * フィールド: [item]。
     */
    private String fItem;

    /**
     * フィールド: [quantity]。
     */
    private Long fQuantity;

    /**
     * フィールド: [option3]。
     */
    private ArrayList<TableSampleOption3> fOption3;

    /**
     * フィールド: [option4]。
     */
    private TableSampleOption4 fOption4;

    /**
     * フィールド [item] の値を設定します。
     *
     * @param argItem フィールド[item]に設定する値。
     */
    public void setItem(final String argItem) {
        fItem = argItem;
    }

    /**
     * フィールド [item] の値を取得します。
     *
     * @return フィールド[item]から取得した値。
     */
    public String getItem() {
        return fItem;
    }

    /**
     * フィールド [quantity] の値を設定します。
     *
     * @param argQuantity フィールド[quantity]に設定する値。
     */
    public void setQuantity(final Long argQuantity) {
        fQuantity = argQuantity;
    }

    /**
     * フィールド [quantity] の値を取得します。
     *
     * @return フィールド[quantity]から取得した値。
     */
    public Long getQuantity() {
        return fQuantity;
    }

    /**
     * フィールド [option3] の値を設定します。
     *
     * @param argOption3 フィールド[option3]に設定する値。
     */
    public void setOption3(final ArrayList<TableSampleOption3> argOption3) {
        fOption3 = argOption3;
    }

    /**
     * フィールド [option3] の値を取得します。
     *
     * @return フィールド[option3]から取得した値。
     */
    public ArrayList<TableSampleOption3> getOption3() {
        return fOption3;
    }

    /**
     * フィールド [option4] の値を設定します。
     *
     * @param argOption4 フィールド[option4]に設定する値。
     */
    public void setOption4(final TableSampleOption4 argOption4) {
        fOption4 = argOption4;
    }

    /**
     * フィールド [option4] の値を取得します。
     *
     * @return フィールド[option4]から取得した値。
     */
    public TableSampleOption4 getOption4() {
        return fOption4;
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
    public void copyTo(final TableSampleOption target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: TableSampleOption#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fItem
        // Type: java.lang.String
        target.fItem = this.fItem;
        // Name: fQuantity
        // Type: java.lang.Long
        target.fQuantity = this.fQuantity;
        // Name: fOption3
        // Type: java.util.ArrayList
        // Field[fOption3] is an unsupported type[java.util.ArrayListblanco.sample.TableSampleOption3].
        // Name: fOption4
        // Type: blanco.sample.TableSampleOption4
        // Field[fOption4] is an unsupported type[blanco.sample.TableSampleOption4].
    }
}
