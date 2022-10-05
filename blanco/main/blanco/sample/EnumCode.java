package blanco.sample;

/**
 * コードを表す列挙型クラスです。
 */
public enum EnumCode {
    /** コード１ */
    CODE01(111L),
    /** コード２ */
    CODE02(112L),
    /** コード３ */
    CODE03(113L),
    /** コード４ */
    CODE04(114L);

    /**
     * Constructor for Enum.
     *
     * @param fCode コード変数
     */
    EnumCode(final Long fCode) {
        this.fCode = fCode;
    }

    /**
     * コード変数
     *
     * フィールド: [code]。
     */
    private Long fCode;

    /**
     * フィールド [code] の値を取得します。
     *
     * フィールドの説明: [コード変数]。
     *
     * @return フィールド[code]から取得した値。
     */
    public Long getCode() {
        return fCode;
    }
}
