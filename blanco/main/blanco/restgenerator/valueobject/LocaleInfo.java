package blanco.restgenerator.valueobject;

/**
 * ロケール情報
 */
public class LocaleInfo {
    /**
     * 言語コード．defaultはja
     *
     * フィールド: [lang]。
     */
    private String fLang;

    /**
     * 時間帯
     *
     * フィールド: [tz]。
     */
    private String fTz;

    /**
     * 通貨
     *
     * フィールド: [currency]。
     */
    private String fCurrency;

    /**
     * フィールド [lang] の値を設定します。
     *
     * フィールドの説明: [言語コード．defaultはja]。
     *
     * @param argLang フィールド[lang]に設定する値。
     */
    public void setLang(final String argLang) {
        fLang = argLang;
    }

    /**
     * フィールド [lang] の値を取得します。
     *
     * フィールドの説明: [言語コード．defaultはja]。
     *
     * @return フィールド[lang]から取得した値。
     */
    public String getLang() {
        return fLang;
    }

    /**
     * フィールド [tz] の値を設定します。
     *
     * フィールドの説明: [時間帯]。
     *
     * @param argTz フィールド[tz]に設定する値。
     */
    public void setTz(final String argTz) {
        fTz = argTz;
    }

    /**
     * フィールド [tz] の値を取得します。
     *
     * フィールドの説明: [時間帯]。
     *
     * @return フィールド[tz]から取得した値。
     */
    public String getTz() {
        return fTz;
    }

    /**
     * フィールド [currency] の値を設定します。
     *
     * フィールドの説明: [通貨]。
     *
     * @param argCurrency フィールド[currency]に設定する値。
     */
    public void setCurrency(final String argCurrency) {
        fCurrency = argCurrency;
    }

    /**
     * フィールド [currency] の値を取得します。
     *
     * フィールドの説明: [通貨]。
     *
     * @return フィールド[currency]から取得した値。
     */
    public String getCurrency() {
        return fCurrency;
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
    public void copyTo(final LocaleInfo target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: LocaleInfo#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fLang
        // Type: java.lang.String
        target.fLang = this.fLang;
        // Name: fTz
        // Type: java.lang.String
        target.fTz = this.fTz;
        // Name: fCurrency
        // Type: java.lang.String
        target.fCurrency = this.fCurrency;
    }
}
