package blanco.sample;

/**
 * コードマスタを表すクラスです。
 */
public class Code {
    /**
     * コード値
     *
     * フィールド: [id]。
     */
    private String fId;

    /**
     * 表示名
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * フィールド [id] の値を設定します。
     *
     * フィールドの説明: [コード値]。
     *
     * @param argId フィールド[id]に設定する値。
     */
    public void setId(final String argId) {
        fId = argId;
    }

    /**
     * フィールド [id] の値を取得します。
     *
     * フィールドの説明: [コード値]。
     *
     * @return フィールド[id]から取得した値。
     */
    public String getId() {
        return fId;
    }

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [表示名]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [表示名]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
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
    public void copyTo(final Code target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: Code#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fId
        // Type: java.lang.String
        target.fId = this.fId;
        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
    }
}
