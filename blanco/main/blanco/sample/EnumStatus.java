package blanco.sample;

/**
 * ステータスを表す列挙型クラスです。
 */
public enum EnumStatus {
    /** 状態１ */
    State01("State01"),
    /** 状態２ */
    State02("State02"),
    /** 状態３ */
    State03("State03"),
    /** 状態４ */
    State04("State04");

    /**
     * Constructor for Enum.
     *
     * @param fState 状態変数
     */
    EnumStatus(final String fState) {
        this.fState = fState;
    }

    /**
     * 状態変数
     *
     * フィールド: [state]。
     */
    private String fState;

    /**
     * フィールド [state] の値を取得します。
     *
     * フィールドの説明: [状態変数]。
     *
     * @return フィールド[state]から取得した値。
     */
    public String getState() {
        return fState;
    }
}
