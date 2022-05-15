package blanco.sample;

import java.util.ArrayList;

/**
 * テーブルサンプルのオプションデータクラスです。
 */
public class TableSampleOption3 {
    /**
     * フィールド: [foo]。
     */
    private ArrayList<String> fFoo;

    /**
     * フィールド: [bar]。
     */
    private ArrayList<String> fBar;

    /**
     * フィールド [foo] の値を設定します。
     *
     * @param argFoo フィールド[foo]に設定する値。
     */
    public void setFoo(final ArrayList<String> argFoo) {
        fFoo = argFoo;
    }

    /**
     * フィールド [foo] の値を取得します。
     *
     * @return フィールド[foo]から取得した値。
     */
    public ArrayList<String> getFoo() {
        return fFoo;
    }

    /**
     * フィールド [bar] の値を設定します。
     *
     * @param argBar フィールド[bar]に設定する値。
     */
    public void setBar(final ArrayList<String> argBar) {
        fBar = argBar;
    }

    /**
     * フィールド [bar] の値を取得します。
     *
     * @return フィールド[bar]から取得した値。
     */
    public ArrayList<String> getBar() {
        return fBar;
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
    public void copyTo(final TableSampleOption3 target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: TableSampleOption3#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fFoo
        // Type: java.util.ArrayList
        // Field[fFoo] is an unsupported type[java.util.ArrayListjava.lang.String].
        // Name: fBar
        // Type: java.util.ArrayList
        // Field[fBar] is an unsupported type[java.util.ArrayListjava.lang.String].
    }
}
