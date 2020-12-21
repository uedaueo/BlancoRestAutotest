package blanco.restgenerator.valueobject;

import java.util.ArrayList;

/**
 * 共通レスポンスを表すオブジェクトです。
 */
public class CommonResponse {
    /**
     * 処理時間（ミリ秒）
     *
     * フィールド: [time]。
     */
    private Long fTime;

    /**
     * 処理結果（success/error）
     *
     * フィールド: [result]。
     */
    private String fResult;

    /**
     * 処理時間（ミリ秒）
     *
     * フィールド: [errors]。
     */
    private ArrayList<ErrorItem> fErrors;

    /**
     * エラー情報
     *
     * フィールド: [telegram]。
     */
    private ApiTelegram fTelegram;

    /**
     * 処理時間（ミリ秒）
     *
     * フィールド: [messages]。
     */
    private ArrayList<MessageItem> fMessages;

    /**
     * フィールド [time] の値を設定します。
     *
     * フィールドの説明: [処理時間（ミリ秒）]。
     *
     * @param argTime フィールド[time]に設定する値。
     */
    public void setTime(final Long argTime) {
        fTime = argTime;
    }

    /**
     * フィールド [time] の値を取得します。
     *
     * フィールドの説明: [処理時間（ミリ秒）]。
     *
     * @return フィールド[time]から取得した値。
     */
    public Long getTime() {
        return fTime;
    }

    /**
     * フィールド [result] の値を設定します。
     *
     * フィールドの説明: [処理結果（success/error）]。
     *
     * @param argResult フィールド[result]に設定する値。
     */
    public void setResult(final String argResult) {
        fResult = argResult;
    }

    /**
     * フィールド [result] の値を取得します。
     *
     * フィールドの説明: [処理結果（success/error）]。
     *
     * @return フィールド[result]から取得した値。
     */
    public String getResult() {
        return fResult;
    }

    /**
     * フィールド [errors] の値を設定します。
     *
     * フィールドの説明: [処理時間（ミリ秒）]。
     *
     * @param argErrors フィールド[errors]に設定する値。
     */
    public void setErrors(final ArrayList<ErrorItem> argErrors) {
        fErrors = argErrors;
    }

    /**
     * フィールド [errors] の値を取得します。
     *
     * フィールドの説明: [処理時間（ミリ秒）]。
     *
     * @return フィールド[errors]から取得した値。
     */
    public ArrayList<ErrorItem> getErrors() {
        return fErrors;
    }

    /**
     * フィールド [telegram] の値を設定します。
     *
     * フィールドの説明: [エラー情報]。
     *
     * @param argTelegram フィールド[telegram]に設定する値。
     */
    public void setTelegram(final ApiTelegram argTelegram) {
        fTelegram = argTelegram;
    }

    /**
     * フィールド [telegram] の値を取得します。
     *
     * フィールドの説明: [エラー情報]。
     *
     * @return フィールド[telegram]から取得した値。
     */
    public ApiTelegram getTelegram() {
        return fTelegram;
    }

    /**
     * フィールド [messages] の値を設定します。
     *
     * フィールドの説明: [処理時間（ミリ秒）]。
     *
     * @param argMessages フィールド[messages]に設定する値。
     */
    public void setMessages(final ArrayList<MessageItem> argMessages) {
        fMessages = argMessages;
    }

    /**
     * フィールド [messages] の値を取得します。
     *
     * フィールドの説明: [処理時間（ミリ秒）]。
     *
     * @return フィールド[messages]から取得した値。
     */
    public ArrayList<MessageItem> getMessages() {
        return fMessages;
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
    public void copyTo(final CommonResponse target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: CommonResponse#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fTime
        // Type: java.lang.Long
        target.fTime = this.fTime;
        // Name: fResult
        // Type: java.lang.String
        target.fResult = this.fResult;
        // Name: fErrors
        // Type: java.util.ArrayList
        // フィールド[fErrors]はサポート外の型[java.util.ArrayListblanco.restgenerator.valueobject.ErrorItem]です。
        // Name: fTelegram
        // Type: blanco.restgenerator.valueobject.ApiTelegram
        // フィールド[fTelegram]はサポート外の型[blanco.restgenerator.valueobject.ApiTelegram]です。
        // Name: fMessages
        // Type: java.util.ArrayList
        // フィールド[fMessages]はサポート外の型[java.util.ArrayListblanco.restgenerator.valueobject.MessageItem]です。
    }
}
