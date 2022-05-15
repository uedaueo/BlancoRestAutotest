package blanco.sample;

import java.util.ArrayList;

/**
 * コードマスターのコードとコードに紐づく情報を表すクラス
 */
public class CodeDefinition {
    /**
     * コード
     *
     * フィールド: [codeName]。
     */
    private String fCodeName;

    /**
     * コード情報
     *
     * フィールド: [codes]。
     */
    private ArrayList<Code> fCodes;

    /**
     * フィールド [codeName] の値を設定します。
     *
     * フィールドの説明: [コード]。
     *
     * @param argCodeName フィールド[codeName]に設定する値。
     */
    public void setCodeName(final String argCodeName) {
        fCodeName = argCodeName;
    }

    /**
     * フィールド [codeName] の値を取得します。
     *
     * フィールドの説明: [コード]。
     *
     * @return フィールド[codeName]から取得した値。
     */
    public String getCodeName() {
        return fCodeName;
    }

    /**
     * フィールド [codes] の値を設定します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @param argCodes フィールド[codes]に設定する値。
     */
    public void setCodes(final ArrayList<Code> argCodes) {
        fCodes = argCodes;
    }

    /**
     * フィールド [codes] の値を取得します。
     *
     * フィールドの説明: [コード情報]。
     *
     * @return フィールド[codes]から取得した値。
     */
    public ArrayList<Code> getCodes() {
        return fCodes;
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
    public void copyTo(final CodeDefinition target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: CodeDefinition#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fCodeName
        // Type: java.lang.String
        target.fCodeName = this.fCodeName;
        // Name: fCodes
        // Type: java.util.ArrayList
        // Field[fCodes] is an unsupported type[java.util.ArrayListblanco.sample.Code].
    }
}
