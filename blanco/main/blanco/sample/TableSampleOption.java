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
        // フィールド[fOption3]はサポート外の型[java.util.ArrayListblanco.sample.TableSampleOption3]です。
        // Name: fOption4
        // Type: blanco.sample.TableSampleOption4
        // フィールド[fOption4]はサポート外の型[blanco.sample.TableSampleOption4]です。
    }
}
