package blanco.sample;

/**
 * Objectのサンプルです
 */
public class Object2Sample {
    /**
     * コード値
     *
     * フィールド: [primitive31]。
     */
    private String fPrimitive31;

    /**
     * 表示名
     *
     * フィールド: [primitive32]。
     */
    private String fPrimitive32;

    /**
     * フィールド [primitive31] の値を設定します。
     *
     * フィールドの説明: [コード値]。
     *
     * @param argPrimitive31 フィールド[primitive31]に設定する値。
     */
    public void setPrimitive31(final String argPrimitive31) {
        fPrimitive31 = argPrimitive31;
    }

    /**
     * フィールド [primitive31] の値を取得します。
     *
     * フィールドの説明: [コード値]。
     *
     * @return フィールド[primitive31]から取得した値。
     */
    public String getPrimitive31() {
        return fPrimitive31;
    }

    /**
     * フィールド [primitive32] の値を設定します。
     *
     * フィールドの説明: [表示名]。
     *
     * @param argPrimitive32 フィールド[primitive32]に設定する値。
     */
    public void setPrimitive32(final String argPrimitive32) {
        fPrimitive32 = argPrimitive32;
    }

    /**
     * フィールド [primitive32] の値を取得します。
     *
     * フィールドの説明: [表示名]。
     *
     * @return フィールド[primitive32]から取得した値。
     */
    public String getPrimitive32() {
        return fPrimitive32;
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
    public void copyTo(final Object2Sample target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: Object2Sample#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fPrimitive31
        // Type: java.lang.String
        target.fPrimitive31 = this.fPrimitive31;
        // Name: fPrimitive32
        // Type: java.lang.String
        target.fPrimitive32 = this.fPrimitive32;
    }
}
