package blanco.sample;

/**
 * Objectのサンプルです
 */
public class ObjectSample {
    /**
     * コード値
     *
     * フィールド: [object21]。
     */
    private Object2Sample fObject21;

    /**
     * 表示名
     *
     * フィールド: [primitive21]。
     */
    private String fPrimitive21;

    /**
     * フィールド: [primitive22]。
     */
    private String fPrimitive22;

    /**
     * フィールド [object21] の値を設定します。
     *
     * フィールドの説明: [コード値]。
     *
     * @param argObject21 フィールド[object21]に設定する値。
     */
    public void setObject21(final Object2Sample argObject21) {
        fObject21 = argObject21;
    }

    /**
     * フィールド [object21] の値を取得します。
     *
     * フィールドの説明: [コード値]。
     *
     * @return フィールド[object21]から取得した値。
     */
    public Object2Sample getObject21() {
        return fObject21;
    }

    /**
     * フィールド [primitive21] の値を設定します。
     *
     * フィールドの説明: [表示名]。
     *
     * @param argPrimitive21 フィールド[primitive21]に設定する値。
     */
    public void setPrimitive21(final String argPrimitive21) {
        fPrimitive21 = argPrimitive21;
    }

    /**
     * フィールド [primitive21] の値を取得します。
     *
     * フィールドの説明: [表示名]。
     *
     * @return フィールド[primitive21]から取得した値。
     */
    public String getPrimitive21() {
        return fPrimitive21;
    }

    /**
     * フィールド [primitive22] の値を設定します。
     *
     * @param argPrimitive22 フィールド[primitive22]に設定する値。
     */
    public void setPrimitive22(final String argPrimitive22) {
        fPrimitive22 = argPrimitive22;
    }

    /**
     * フィールド [primitive22] の値を取得します。
     *
     * @return フィールド[primitive22]から取得した値。
     */
    public String getPrimitive22() {
        return fPrimitive22;
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
    public void copyTo(final ObjectSample target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: ObjectSample#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fObject21
        // Type: blanco.sample.Object2Sample
        // フィールド[fObject21]はサポート外の型[blanco.sample.Object2Sample]です。
        // Name: fPrimitive21
        // Type: java.lang.String
        target.fPrimitive21 = this.fPrimitive21;
        // Name: fPrimitive22
        // Type: java.lang.String
        target.fPrimitive22 = this.fPrimitive22;
    }
}
