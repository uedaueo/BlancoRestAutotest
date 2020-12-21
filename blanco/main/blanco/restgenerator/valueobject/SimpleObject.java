package blanco.restgenerator.valueobject;

/**
 * サンプルオブジェクト
 */
public class SimpleObject {
    /**
     * 通信に関するメタ情報
     *
     * フィールド: [info]。
     */
    private String fInfo;

    /**
     * API毎の要求電文, ApiTelegramを継承してAPI毎に独自の型を指定
     *
     * フィールド: [telegram]。
     */
    private String fTelegram;

    /**
     * フィールド [info] の値を設定します。
     *
     * フィールドの説明: [通信に関するメタ情報]。
     *
     * @param argInfo フィールド[info]に設定する値。
     */
    public void setInfo(final String argInfo) {
        fInfo = argInfo;
    }

    /**
     * フィールド [info] の値を取得します。
     *
     * フィールドの説明: [通信に関するメタ情報]。
     *
     * @return フィールド[info]から取得した値。
     */
    public String getInfo() {
        return fInfo;
    }

    /**
     * フィールド [telegram] の値を設定します。
     *
     * フィールドの説明: [API毎の要求電文, ApiTelegramを継承してAPI毎に独自の型を指定]。
     *
     * @param argTelegram フィールド[telegram]に設定する値。
     */
    public void setTelegram(final String argTelegram) {
        fTelegram = argTelegram;
    }

    /**
     * フィールド [telegram] の値を取得します。
     *
     * フィールドの説明: [API毎の要求電文, ApiTelegramを継承してAPI毎に独自の型を指定]。
     *
     * @return フィールド[telegram]から取得した値。
     */
    public String getTelegram() {
        return fTelegram;
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
    public void copyTo(final SimpleObject target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: SimpleObject#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fInfo
        // Type: java.lang.String
        target.fInfo = this.fInfo;
        // Name: fTelegram
        // Type: java.lang.String
        target.fTelegram = this.fTelegram;
    }
}
