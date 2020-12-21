package blanco.restautotest.valueobject;

import blanco.restgenerator.valueobject.ApiTelegram;

/**
 * テストケースに記載されたデータを格納するためのクラスです。
 */
public class BlancoRestAutotestTestCaseData {
    /**
     * テストケースの入出力を格納します。
     *
     * フィールド: [telegram]。
     */
    private ApiTelegram fTelegram;

    /**
     * 電文クラスの正式名を格納します。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * フィールド: [caseId]。
     */
    private String fCaseId;

    /**
     * テストケースの定義書上の行数を格納します。
     *
     * フィールド: [caseLineNum]。
     * デフォルト: [0]。
     */
    private Integer fCaseLineNum = 0;

    /**
     * フィールド [telegram] の値を設定します。
     *
     * フィールドの説明: [テストケースの入出力を格納します。]。
     *
     * @param argTelegram フィールド[telegram]に設定する値。
     */
    public void setTelegram(final ApiTelegram argTelegram) {
        fTelegram = argTelegram;
    }

    /**
     * フィールド [telegram] の値を取得します。
     *
     * フィールドの説明: [テストケースの入出力を格納します。]。
     *
     * @return フィールド[telegram]から取得した値。
     */
    public ApiTelegram getTelegram() {
        return fTelegram;
    }

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [電文クラスの正式名を格納します。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [電文クラスの正式名を格納します。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [caseId] の値を設定します。
     *
     * @param argCaseId フィールド[caseId]に設定する値。
     */
    public void setCaseId(final String argCaseId) {
        fCaseId = argCaseId;
    }

    /**
     * フィールド [caseId] の値を取得します。
     *
     * @return フィールド[caseId]から取得した値。
     */
    public String getCaseId() {
        return fCaseId;
    }

    /**
     * フィールド [caseLineNum] の値を設定します。
     *
     * フィールドの説明: [テストケースの定義書上の行数を格納します。]。
     *
     * @param argCaseLineNum フィールド[caseLineNum]に設定する値。
     */
    public void setCaseLineNum(final Integer argCaseLineNum) {
        fCaseLineNum = argCaseLineNum;
    }

    /**
     * フィールド [caseLineNum] の値を取得します。
     *
     * フィールドの説明: [テストケースの定義書上の行数を格納します。]。
     * デフォルト: [0]。
     *
     * @return フィールド[caseLineNum]から取得した値。
     */
    public Integer getCaseLineNum() {
        return fCaseLineNum;
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
        buf.append("telegram=" + fTelegram);
        buf.append(",name=" + fName);
        buf.append(",caseId=" + fCaseId);
        buf.append(",caseLineNum=" + fCaseLineNum);
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

        // Name: fTelegram
        // Type: blanco.restgenerator.valueobject.ApiTelegram
        // フィールド[fTelegram]はサポート外の型[blanco.restgenerator.valueobject.ApiTelegram]です。
        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fCaseId
        // Type: java.lang.String
        target.fCaseId = this.fCaseId;
        // Name: fCaseLineNum
        // Type: java.lang.Integer
        target.fCaseLineNum = this.fCaseLineNum;
    }
}
