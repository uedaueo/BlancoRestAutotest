package blanco.restautotest.valueobject;

import java.util.List;
import java.util.Map;

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
     * 電文クラスの短縮名を格納します。
     *
     * フィールド: [simpleInputId]。
     */
    private String fSimpleInputId;

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
     * 電文クラスの短縮名を格納します。
     *
     * フィールド: [simpleExpectId]。
     */
    private String fSimpleExpectId;

    /**
     * テストケースIDを格納します。
     *
     * フィールド: [caseId]。
     */
    private String fCaseId;

    /**
     * このテストケースのInputカラムの最大値を保持します。
     *
     * フィールド: [inputColumnMax]。
     * デフォルト: [0]。
     */
    private Integer fInputColumnMax = 0;

    /**
     * このテストケースのExpectカラムの最大値を保持します。
     *
     * フィールド: [expectedColumnMax]。
     * デフォルト: [0]。
     */
    private Integer fExpectedColumnMax = 0;

    /**
     * Input?/Expected? ごとに入っているはずのプロパティリストを保持する
     *
     * フィールド: [propertyMap]。
     */
    private Map<String, String> fPropertyMap;

    /**
     * property.property... という形式で、そのプロパティの横幅（定義書のカラム数）を保持する。
     *
     * フィールド: [propertySizeMap]。
     */
    private Map<String, Integer> fPropertySizeMap;

    /**
     * Expect? のカラムごとの比較方法を保持します。
     *
     * フィールド: [assertKindList]。
     */
    private List<String> fAssertKindList;

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
     * フィールド [simpleInputId] の値を設定します。
     *
     * フィールドの説明: [電文クラスの短縮名を格納します。]。
     *
     * @param argSimpleInputId フィールド[simpleInputId]に設定する値。
     */
    public void setSimpleInputId(final String argSimpleInputId) {
        fSimpleInputId = argSimpleInputId;
    }

    /**
     * フィールド [simpleInputId] の値を取得します。
     *
     * フィールドの説明: [電文クラスの短縮名を格納します。]。
     *
     * @return フィールド[simpleInputId]から取得した値。
     */
    public String getSimpleInputId() {
        return fSimpleInputId;
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
     * フィールド [simpleExpectId] の値を設定します。
     *
     * フィールドの説明: [電文クラスの短縮名を格納します。]。
     *
     * @param argSimpleExpectId フィールド[simpleExpectId]に設定する値。
     */
    public void setSimpleExpectId(final String argSimpleExpectId) {
        fSimpleExpectId = argSimpleExpectId;
    }

    /**
     * フィールド [simpleExpectId] の値を取得します。
     *
     * フィールドの説明: [電文クラスの短縮名を格納します。]。
     *
     * @return フィールド[simpleExpectId]から取得した値。
     */
    public String getSimpleExpectId() {
        return fSimpleExpectId;
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
     * フィールド [inputColumnMax] の値を設定します。
     *
     * フィールドの説明: [このテストケースのInputカラムの最大値を保持します。]。
     *
     * @param argInputColumnMax フィールド[inputColumnMax]に設定する値。
     */
    public void setInputColumnMax(final Integer argInputColumnMax) {
        fInputColumnMax = argInputColumnMax;
    }

    /**
     * フィールド [inputColumnMax] の値を取得します。
     *
     * フィールドの説明: [このテストケースのInputカラムの最大値を保持します。]。
     * デフォルト: [0]。
     *
     * @return フィールド[inputColumnMax]から取得した値。
     */
    public Integer getInputColumnMax() {
        return fInputColumnMax;
    }

    /**
     * フィールド [expectedColumnMax] の値を設定します。
     *
     * フィールドの説明: [このテストケースのExpectカラムの最大値を保持します。]。
     *
     * @param argExpectedColumnMax フィールド[expectedColumnMax]に設定する値。
     */
    public void setExpectedColumnMax(final Integer argExpectedColumnMax) {
        fExpectedColumnMax = argExpectedColumnMax;
    }

    /**
     * フィールド [expectedColumnMax] の値を取得します。
     *
     * フィールドの説明: [このテストケースのExpectカラムの最大値を保持します。]。
     * デフォルト: [0]。
     *
     * @return フィールド[expectedColumnMax]から取得した値。
     */
    public Integer getExpectedColumnMax() {
        return fExpectedColumnMax;
    }

    /**
     * フィールド [propertyMap] の値を設定します。
     *
     * フィールドの説明: [Input?/Expected? ごとに入っているはずのプロパティリストを保持する]。
     *
     * @param argPropertyMap フィールド[propertyMap]に設定する値。
     */
    public void setPropertyMap(final Map<String, String> argPropertyMap) {
        fPropertyMap = argPropertyMap;
    }

    /**
     * フィールド [propertyMap] の値を取得します。
     *
     * フィールドの説明: [Input?/Expected? ごとに入っているはずのプロパティリストを保持する]。
     *
     * @return フィールド[propertyMap]から取得した値。
     */
    public Map<String, String> getPropertyMap() {
        return fPropertyMap;
    }

    /**
     * フィールド [propertySizeMap] の値を設定します。
     *
     * フィールドの説明: [property.property... という形式で、そのプロパティの横幅（定義書のカラム数）を保持する。]。
     *
     * @param argPropertySizeMap フィールド[propertySizeMap]に設定する値。
     */
    public void setPropertySizeMap(final Map<String, Integer> argPropertySizeMap) {
        fPropertySizeMap = argPropertySizeMap;
    }

    /**
     * フィールド [propertySizeMap] の値を取得します。
     *
     * フィールドの説明: [property.property... という形式で、そのプロパティの横幅（定義書のカラム数）を保持する。]。
     *
     * @return フィールド[propertySizeMap]から取得した値。
     */
    public Map<String, Integer> getPropertySizeMap() {
        return fPropertySizeMap;
    }

    /**
     * フィールド [assertKindList] の値を設定します。
     *
     * フィールドの説明: [Expect? のカラムごとの比較方法を保持します。]。
     *
     * @param argAssertKindList フィールド[assertKindList]に設定する値。
     */
    public void setAssertKindList(final List<String> argAssertKindList) {
        fAssertKindList = argAssertKindList;
    }

    /**
     * フィールド [assertKindList] の値を取得します。
     *
     * フィールドの説明: [Expect? のカラムごとの比較方法を保持します。]。
     *
     * @return フィールド[assertKindList]から取得した値。
     */
    public List<String> getAssertKindList() {
        return fAssertKindList;
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
        buf.append(",simpleInputId=" + fSimpleInputId);
        buf.append(",expect=" + fExpect);
        buf.append(",expectId=" + fExpectId);
        buf.append(",simpleExpectId=" + fSimpleExpectId);
        buf.append(",caseId=" + fCaseId);
        buf.append(",inputColumnMax=" + fInputColumnMax);
        buf.append(",expectedColumnMax=" + fExpectedColumnMax);
        buf.append(",propertyMap=" + fPropertyMap);
        buf.append(",propertySizeMap=" + fPropertySizeMap);
        buf.append(",assertKindList=" + fAssertKindList);
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
        // Name: fSimpleInputId
        // Type: java.lang.String
        target.fSimpleInputId = this.fSimpleInputId;
        // Name: fExpect
        // Type: blanco.restgenerator.valueobject.ApiTelegram
        // フィールド[fExpect]はサポート外の型[blanco.restgenerator.valueobject.ApiTelegram]です。
        // Name: fExpectId
        // Type: java.lang.String
        target.fExpectId = this.fExpectId;
        // Name: fSimpleExpectId
        // Type: java.lang.String
        target.fSimpleExpectId = this.fSimpleExpectId;
        // Name: fCaseId
        // Type: java.lang.String
        target.fCaseId = this.fCaseId;
        // Name: fInputColumnMax
        // Type: java.lang.Integer
        target.fInputColumnMax = this.fInputColumnMax;
        // Name: fExpectedColumnMax
        // Type: java.lang.Integer
        target.fExpectedColumnMax = this.fExpectedColumnMax;
        // Name: fPropertyMap
        // Type: java.util.Map
        // フィールド[fPropertyMap]はサポート外の型[java.util.MapString, String]です。
        // Name: fPropertySizeMap
        // Type: java.util.Map
        // フィールド[fPropertySizeMap]はサポート外の型[java.util.MapString, Integer]です。
        // Name: fAssertKindList
        // Type: java.util.List
        // フィールド[fAssertKindList]はサポート外の型[java.util.ListString]です。
    }
}
