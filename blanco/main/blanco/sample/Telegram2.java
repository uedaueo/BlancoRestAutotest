package blanco.sample;

import java.util.ArrayList;

import blanco.restgenerator.valueobject.ApiTelegram;

/**
 * コードマスタを表すクラスです。
 */
public class Telegram2 extends ApiTelegram {
    /**
     * フィールド: [id]。
     */
    private Long fId;

    /**
     * フィールド: [name]。
     */
    private ArrayList<String> fName;

    /**
     * フィールド [id] の値を設定します。
     *
     * @param argId フィールド[id]に設定する値。
     */
    public void setId(final Long argId) {
        fId = argId;
    }

    /**
     * フィールド [id] の値を取得します。
     *
     * @return フィールド[id]から取得した値。
     */
    public Long getId() {
        return fId;
    }

    /**
     * フィールド [name] の値を設定します。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final ArrayList<String> argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * @return フィールド[name]から取得した値。
     */
    public ArrayList<String> getName() {
        return fName;
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
    public void copyTo(final Telegram2 target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: Telegram2#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fId
        // Type: java.lang.Long
        target.fId = this.fId;
        // Name: fName
        // Type: java.util.ArrayList
        // Field[fName] is an unsupported type[java.util.ArrayListjava.lang.String].
    }
}
