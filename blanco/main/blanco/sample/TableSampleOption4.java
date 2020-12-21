package blanco.sample;

import java.util.ArrayList;

/**
 * テーブルサンプルのオプションデータクラスです。
 */
public class TableSampleOption4 {
    /**
     * フィールド: [foo4]。
     */
    private String fFoo4;

    /**
     * フィールド: [bar4]。
     */
    private ArrayList<String> fBar4;

    /**
     * フィールド [foo4] の値を設定します。
     *
     * @param argFoo4 フィールド[foo4]に設定する値。
     */
    public void setFoo4(final String argFoo4) {
        fFoo4 = argFoo4;
    }

    /**
     * フィールド [foo4] の値を取得します。
     *
     * @return フィールド[foo4]から取得した値。
     */
    public String getFoo4() {
        return fFoo4;
    }

    /**
     * フィールド [bar4] の値を設定します。
     *
     * @param argBar4 フィールド[bar4]に設定する値。
     */
    public void setBar4(final ArrayList<String> argBar4) {
        fBar4 = argBar4;
    }

    /**
     * フィールド [bar4] の値を取得します。
     *
     * @return フィールド[bar4]から取得した値。
     */
    public ArrayList<String> getBar4() {
        return fBar4;
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
    public void copyTo(final TableSampleOption4 target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: TableSampleOption4#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fFoo4
        // Type: java.lang.String
        target.fFoo4 = this.fFoo4;
        // Name: fBar4
        // Type: java.util.ArrayList
        // フィールド[fBar4]はサポート外の型[java.util.ArrayListjava.lang.String]です。
    }
}
