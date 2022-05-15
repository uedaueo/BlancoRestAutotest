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
    public void copyTo(final ObjectSample target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: ObjectSample#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fObject21
        // Type: blanco.sample.Object2Sample
        // Field[fObject21] is an unsupported type[blanco.sample.Object2Sample].
        // Name: fPrimitive21
        // Type: java.lang.String
        target.fPrimitive21 = this.fPrimitive21;
        // Name: fPrimitive22
        // Type: java.lang.String
        target.fPrimitive22 = this.fPrimitive22;
    }
}
