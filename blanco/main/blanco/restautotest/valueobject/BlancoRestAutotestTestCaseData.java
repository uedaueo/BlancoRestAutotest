package blanco.restautotest.valueobject;

import blanco.restgenerator.valueobject.ApiTelegram;

/**
 * テストケースに記載されたデータを格納するためのクラスです。
 */
public class BlancoRestAutotestTestCaseData {
    /**
     * テストケースの入力値を格納します。
     *
     * フィールド: [input]。
     */
    private ApiTelegram fInput;

    /**
     * 電文クラスの正式名を格納します。
     *
     * フィールド: [inputId]。
     */
    private String fInputId;

    /**
     * テストケースの出力値を格納します。
     *
     * フィールド: [expect]。
     */
    private ApiTelegram fExpect;

    /**
     * 電文クラスの正式名を格納します。
     *
     * フィールド: [expectId]。
     */
    private String fExpectId;

    /**
     * テストケースIDを格納します。
     *
     * フィールド: [caseId]。
     */
    private String fCaseId;

    /**
     * フィールド [input] の値を設定します。
     *
     * フィールドの説明: [テストケースの入力値を格納します。]。
     *
     * @param argInput フィールド[input]に設定する値。
     */
    public void setInput(final ApiTelegram argInput) {
        fInput = argInput;
    }

    /**
     * フィールド [input] の値を取得します。
     *
     * フィールドの説明: [テストケースの入力値を格納します。]。
     *
     * @return フィールド[input]から取得した値。
     */
    public ApiTelegram getInput() {
        return fInput;
    }

    /**
     * フィールド [inputId] の値を設定します。
     *
     * フィールドの説明: [電文クラスの正式名を格納します。]。
     *
     * @param argInputId フィールド[inputId]に設定する値。
     */
    public void setInputId(final String argInputId) {
        fInputId = argInputId;
    }

    /**
     * フィールド [inputId] の値を取得します。
     *
     * フィールドの説明: [電文クラスの正式名を格納します。]。
     *
     * @return フィールド[inputId]から取得した値。
     */
    public String getInputId() {
        return fInputId;
    }

    /**
     * フィールド [expect] の値を設定します。
     *
     * フィールドの説明: [テストケースの出力値を格納します。]。
     *
     * @param argExpect フィールド[expect]に設定する値。
     */
    public void setExpect(final ApiTelegram argExpect) {
        fExpect = argExpect;
    }

    /**
     * フィールド [expect] の値を取得します。
     *
     * フィールドの説明: [テストケースの出力値を格納します。]。
     *
     * @return フィールド[expect]から取得した値。
     */
    public ApiTelegram getExpect() {
        return fExpect;
    }

    /**
     * フィールド [expectId] の値を設定します。
     *
     * フィールドの説明: [電文クラスの正式名を格納します。]。
     *
     * @param argExpectId フィールド[expectId]に設定する値。
     */
    public void setExpectId(final String argExpectId) {
        fExpectId = argExpectId;
    }

    /**
     * フィールド [expectId] の値を取得します。
     *
     * フィールドの説明: [電文クラスの正式名を格納します。]。
     *
     * @return フィールド[expectId]から取得した値。
     */
    public String getExpectId() {
        return fExpectId;
    }

    /**
     * フィールド [caseId] の値を設定します。
     *
     * フィールドの説明: [テストケースIDを格納します。]。
     *
     * @param argCaseId フィールド[caseId]に設定する値。
     */
    public void setCaseId(final String argCaseId) {
        fCaseId = argCaseId;
    }

    /**
     * フィールド [caseId] の値を取得します。
     *
     * フィールドの説明: [テストケースIDを格納します。]。
     *
     * @return フィールド[caseId]から取得した値。
     */
    public String getCaseId() {
        return fCaseId;
    }

    /**
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ文字列化の処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @return バリューオブジェクトの文字列表現。
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.restautotest.valueobject.BlancoRestAutotestTestCaseData[");
        buf.append("input=" + fInput);
        buf.append(",inputId=" + fInputId);
        buf.append(",expect=" + fExpect);
        buf.append(",expectId=" + fExpectId);
        buf.append(",caseId=" + fCaseId);
        buf.append("]");
        return buf.toString();
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
    public void copyTo(final BlancoRestAutotestTestCaseData target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoRestAutotestTestCaseData#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fInput
        // Type: blanco.restgenerator.valueobject.ApiTelegram
        // フィールド[fInput]はサポート外の型[blanco.restgenerator.valueobject.ApiTelegram]です。
        // Name: fInputId
        // Type: java.lang.String
        target.fInputId = this.fInputId;
        // Name: fExpect
        // Type: blanco.restgenerator.valueobject.ApiTelegram
        // フィールド[fExpect]はサポート外の型[blanco.restgenerator.valueobject.ApiTelegram]です。
        // Name: fExpectId
        // Type: java.lang.String
        target.fExpectId = this.fExpectId;
        // Name: fCaseId
        // Type: java.lang.String
        target.fCaseId = this.fCaseId;
    }
}
