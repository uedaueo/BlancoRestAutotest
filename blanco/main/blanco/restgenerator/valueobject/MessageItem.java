package blanco.restgenerator.valueobject;

/**
 * APIが返す通知メッセージを定義します
 */
public class MessageItem {
    /**
     * メッセージコード（主にエラー時に使用）
     *
     * フィールド: [code]。
     */
    private String fCode;

    /**
     * メッセージ
     *
     * フィールド: [message]。
     */
    private String fMessage;

    /**
     * メッセージナンバー（主に画面API用に使用）
     *
     * フィールド: [messageNumber]。
     */
    private String fMessageNumber;

    /**
     * フィールド [code] の値を設定します。
     *
     * フィールドの説明: [メッセージコード（主にエラー時に使用）]。
     *
     * @param argCode フィールド[code]に設定する値。
     */
    public void setCode(final String argCode) {
        fCode = argCode;
    }

    /**
     * フィールド [code] の値を取得します。
     *
     * フィールドの説明: [メッセージコード（主にエラー時に使用）]。
     *
     * @return フィールド[code]から取得した値。
     */
    public String getCode() {
        return fCode;
    }

    /**
     * フィールド [message] の値を設定します。
     *
     * フィールドの説明: [メッセージ]。
     *
     * @param argMessage フィールド[message]に設定する値。
     */
    public void setMessage(final String argMessage) {
        fMessage = argMessage;
    }

    /**
     * フィールド [message] の値を取得します。
     *
     * フィールドの説明: [メッセージ]。
     *
     * @return フィールド[message]から取得した値。
     */
    public String getMessage() {
        return fMessage;
    }

    /**
     * フィールド [messageNumber] の値を設定します。
     *
     * フィールドの説明: [メッセージナンバー（主に画面API用に使用）]。
     *
     * @param argMessageNumber フィールド[messageNumber]に設定する値。
     */
    public void setMessageNumber(final String argMessageNumber) {
        fMessageNumber = argMessageNumber;
    }

    /**
     * フィールド [messageNumber] の値を取得します。
     *
     * フィールドの説明: [メッセージナンバー（主に画面API用に使用）]。
     *
     * @return フィールド[messageNumber]から取得した値。
     */
    public String getMessageNumber() {
        return fMessageNumber;
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
    public void copyTo(final MessageItem target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: MessageItem#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fCode
        // Type: java.lang.String
        target.fCode = this.fCode;
        // Name: fMessage
        // Type: java.lang.String
        target.fMessage = this.fMessage;
        // Name: fMessageNumber
        // Type: java.lang.String
        target.fMessageNumber = this.fMessageNumber;
    }
}
