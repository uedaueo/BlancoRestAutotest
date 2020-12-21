package blanco.restgenerator.valueobject;

/**
 * 通信に関するメタ情報
 */
public class ApiRequestHeader extends RequestHeader {
    /**
     * 認証キー
     *
     * フィールド: [token]。
     */
    private String fToken;

    /**
     * 画面ID
     *
     * フィールド: [screenId]。
     */
    private String fScreenId;

    /**
     * 店舗ID
     *
     * フィールド: [shopId]。
     */
    private String fShopId;

    /**
     * フィールド [token] の値を設定します。
     *
     * フィールドの説明: [認証キー]。
     *
     * @param argToken フィールド[token]に設定する値。
     */
    public void setToken(final String argToken) {
        fToken = argToken;
    }

    /**
     * フィールド [token] の値を取得します。
     *
     * フィールドの説明: [認証キー]。
     *
     * @return フィールド[token]から取得した値。
     */
    public String getToken() {
        return fToken;
    }

    /**
     * フィールド [screenId] の値を設定します。
     *
     * フィールドの説明: [画面ID]。
     *
     * @param argScreenId フィールド[screenId]に設定する値。
     */
    public void setScreenId(final String argScreenId) {
        fScreenId = argScreenId;
    }

    /**
     * フィールド [screenId] の値を取得します。
     *
     * フィールドの説明: [画面ID]。
     *
     * @return フィールド[screenId]から取得した値。
     */
    public String getScreenId() {
        return fScreenId;
    }

    /**
     * フィールド [shopId] の値を設定します。
     *
     * フィールドの説明: [店舗ID]。
     *
     * @param argShopId フィールド[shopId]に設定する値。
     */
    public void setShopId(final String argShopId) {
        fShopId = argShopId;
    }

    /**
     * フィールド [shopId] の値を取得します。
     *
     * フィールドの説明: [店舗ID]。
     *
     * @return フィールド[shopId]から取得した値。
     */
    public String getShopId() {
        return fShopId;
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
    public void copyTo(final ApiRequestHeader target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: ApiRequestHeader#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fToken
        // Type: java.lang.String
        target.fToken = this.fToken;
        // Name: fScreenId
        // Type: java.lang.String
        target.fScreenId = this.fScreenId;
        // Name: fShopId
        // Type: java.lang.String
        target.fShopId = this.fShopId;
    }
}
