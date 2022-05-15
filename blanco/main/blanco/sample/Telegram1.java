package blanco.sample;

import java.util.ArrayList;

import blanco.restgenerator.valueobject.ApiTelegram;

/**
 * コードマスタを表すクラスです。
 */
public class Telegram1 extends ApiTelegram {
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
    private ArrayList<String> fName;

    /**
     * フィールド: [count]。
     */
    private Long fCount;

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
    public void setName(final ArrayList<String> argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [表示名]。
     *
     * @return フィールド[name]から取得した値。
     */
    public ArrayList<String> getName() {
        return fName;
    }

    /**
     * フィールド [count] の値を設定します。
     *
     * @param argCount フィールド[count]に設定する値。
     */
    public void setCount(final Long argCount) {
        fCount = argCount;
    }

    /**
     * フィールド [count] の値を取得します。
     *
     * @return フィールド[count]から取得した値。
     */
    public Long getCount() {
        return fCount;
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
    public void copyTo(final Telegram1 target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: Telegram1#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fId
        // Type: java.lang.String
        target.fId = this.fId;
        // Name: fName
        // Type: java.util.ArrayList
        // Field[fName] is an unsupported type[java.util.ArrayListjava.lang.String].
        // Name: fCount
        // Type: java.lang.Long
        target.fCount = this.fCount;
    }
}
