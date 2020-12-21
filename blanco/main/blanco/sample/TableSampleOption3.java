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
    public void copyTo(final TableSampleOption3 target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: TableSampleOption3#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fFoo
        // Type: java.util.ArrayList
        // フィールド[fFoo]はサポート外の型[java.util.ArrayListjava.lang.String]です。
        // Name: fBar
        // Type: java.util.ArrayList
        // フィールド[fBar]はサポート外の型[java.util.ArrayListjava.lang.String]です。
    }
}
