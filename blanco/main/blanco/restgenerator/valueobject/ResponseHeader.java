package blanco.restgenerator.valueobject;

/**
 * APIが返すレスポンスのヘッダを定義します
 */
public class ResponseHeader {
    /**
     * 認証キー
     *
     * フィールド: [token]。
     */
    private String fToken;

    /**
     * ロケール情報
     *
     * フィールド: [locale]。
     */
    private LocaleInfo fLocale;

    /**
     * 文字情報（常にUTF-8）
     *
     * フィールド: [charset]。
     */
    private String fCharset;

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
     * フィールド [locale] の値を設定します。
     *
     * フィールドの説明: [ロケール情報]。
     *
     * @param argLocale フィールド[locale]に設定する値。
     */
    public void setLocale(final LocaleInfo argLocale) {
        fLocale = argLocale;
    }

    /**
     * フィールド [locale] の値を取得します。
     *
     * フィールドの説明: [ロケール情報]。
     *
     * @return フィールド[locale]から取得した値。
     */
    public LocaleInfo getLocale() {
        return fLocale;
    }

    /**
     * フィールド [charset] の値を設定します。
     *
     * フィールドの説明: [文字情報（常にUTF-8）]。
     *
     * @param argCharset フィールド[charset]に設定する値。
     */
    public void setCharset(final String argCharset) {
        fCharset = argCharset;
    }

    /**
     * フィールド [charset] の値を取得します。
     *
     * フィールドの説明: [文字情報（常にUTF-8）]。
     *
     * @return フィールド[charset]から取得した値。
     */
    public String getCharset() {
        return fCharset;
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
    public void copyTo(final ResponseHeader target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: ResponseHeader#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fToken
        // Type: java.lang.String
        target.fToken = this.fToken;
        // Name: fLocale
        // Type: blanco.restgenerator.valueobject.LocaleInfo
        // フィールド[fLocale]はサポート外の型[blanco.restgenerator.valueobject.LocaleInfo]です。
        // Name: fCharset
        // Type: java.lang.String
        target.fCharset = this.fCharset;
    }
}
