package blanco.sample;

import java.util.ArrayList;

/**
 * Objectのサンプルです
 */
public class StatusObject {
    /**
     * コード値
     *
     * フィールド: [id]。
     */
    private Long fId;

    /**
     * 表示名
     *
     * フィールド: [status]。
     */
    private ArrayList<EnumStatus> fStatus;

    /**
     * フィールド: [param]。
     */
    private String fParam;

    /**
     * フィールド [id] の値を設定します。
     *
     * フィールドの説明: [コード値]。
     *
     * @param argId フィールド[id]に設定する値。
     */
    public void setId(final Long argId) {
        fId = argId;
    }

    /**
     * フィールド [id] の値を取得します。
     *
     * フィールドの説明: [コード値]。
     *
     * @return フィールド[id]から取得した値。
     */
    public Long getId() {
        return fId;
    }

    /**
     * フィールド [status] の値を設定します。
     *
     * フィールドの説明: [表示名]。
     *
     * @param argStatus フィールド[status]に設定する値。
     */
    public void setStatus(final ArrayList<EnumStatus> argStatus) {
        fStatus = argStatus;
    }

    /**
     * フィールド [status] の値を取得します。
     *
     * フィールドの説明: [表示名]。
     *
     * @return フィールド[status]から取得した値。
     */
    public ArrayList<EnumStatus> getStatus() {
        return fStatus;
    }

    /**
     * フィールド [param] の値を設定します。
     *
     * @param argParam フィールド[param]に設定する値。
     */
    public void setParam(final String argParam) {
        fParam = argParam;
    }

    /**
     * フィールド [param] の値を取得します。
     *
     * @return フィールド[param]から取得した値。
     */
    public String getParam() {
        return fParam;
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
    public void copyTo(final StatusObject target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: StatusObject#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fId
        // Type: java.lang.Long
        target.fId = this.fId;
        // Name: fStatus
        // Type: java.util.ArrayList
        // Field[fStatus] is an unsupported type[java.util.ArrayListblanco.sample.EnumStatus].
        // Name: fParam
        // Type: java.lang.String
        target.fParam = this.fParam;
    }
}
