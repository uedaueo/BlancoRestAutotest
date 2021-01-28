package blanco.sample;

import java.util.ArrayList;

/**
 * コードマスターのコードとコードに紐づく情報を表すクラス
 */
public class CodeDefinition2 {
    /**
     * コード
     *
     * フィールド: [code]。
     */
    private Long fCode;

    /**
     * コード情報
     *
     * フィールド: [codeName]。
     */
    private String fCodeName;

    /**
     * コード情報
     *
     * フィールド: [reason]。
     */
    private ArrayList<String> fReason;

    /**
     * コード情報
     *
     * フィールド: [username]。
     */
    private String fUsername;

    /**
     * コード情報
     *
     * フィールド: [memo]。
     */
    private String fMemo;

    /**
     * フィールド: [numList]。
     */
    private ArrayList<Long> fNumList;

    /**
     * フィールド [code] の値を設定します。
     *
     * フィールドの説明: [コード]。
     *
     * @param argCode フィールド[code]に設定する値。
     */
    public void setCode(final Long argCode) {
        fCode = argCode;
    }

    /**
     * フィールド [code] の値を取得します。
     *
     * フィールドの説明: [コード]。
     *
     * @return フィールド[code]から取得した値。
     */
    public Long getCode() {
        return fCode;
    }

    /**
     * フィールド [codeName] の値を設定します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @param argCodeName フィールド[codeName]に設定する値。
     */
    public void setCodeName(final String argCodeName) {
        fCodeName = argCodeName;
    }

    /**
     * フィールド [codeName] の値を取得します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @return フィールド[codeName]から取得した値。
     */
    public String getCodeName() {
        return fCodeName;
    }

    /**
     * フィールド [reason] の値を設定します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @param argReason フィールド[reason]に設定する値。
     */
    public void setReason(final ArrayList<String> argReason) {
        fReason = argReason;
    }

    /**
     * フィールド [reason] の値を取得します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @return フィールド[reason]から取得した値。
     */
    public ArrayList<String> getReason() {
        return fReason;
    }

    /**
     * フィールド [username] の値を設定します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @param argUsername フィールド[username]に設定する値。
     */
    public void setUsername(final String argUsername) {
        fUsername = argUsername;
    }

    /**
     * フィールド [username] の値を取得します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @return フィールド[username]から取得した値。
     */
    public String getUsername() {
        return fUsername;
    }

    /**
     * フィールド [memo] の値を設定します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @param argMemo フィールド[memo]に設定する値。
     */
    public void setMemo(final String argMemo) {
        fMemo = argMemo;
    }

    /**
     * フィールド [memo] の値を取得します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @return フィールド[memo]から取得した値。
     */
    public String getMemo() {
        return fMemo;
    }

    /**
     * フィールド [numList] の値を設定します。
     *
     * @param argNumList フィールド[numList]に設定する値。
     */
    public void setNumList(final ArrayList<Long> argNumList) {
        fNumList = argNumList;
    }

    /**
     * フィールド [numList] の値を取得します。
     *
     * @return フィールド[numList]から取得した値。
     */
    public ArrayList<Long> getNumList() {
        return fNumList;
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
    public void copyTo(final CodeDefinition2 target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: CodeDefinition2#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fCode
        // Type: java.lang.Long
        target.fCode = this.fCode;
        // Name: fCodeName
        // Type: java.lang.String
        target.fCodeName = this.fCodeName;
        // Name: fReason
        // Type: java.util.ArrayList
        // フィールド[fReason]はサポート外の型[java.util.ArrayListjava.lang.String]です。
        // Name: fUsername
        // Type: java.lang.String
        target.fUsername = this.fUsername;
        // Name: fMemo
        // Type: java.lang.String
        target.fMemo = this.fMemo;
        // Name: fNumList
        // Type: java.util.ArrayList
        // フィールド[fNumList]はサポート外の型[java.util.ArrayListjava.lang.Long]です。
    }
}
