package blanco.sample;

/**
 * テーブルサンプルの行データクラスです。
 */
public class TableSampleRow {
    /**
     * フィールド: [name]。
     */
    private String fName;

    /**
     * フィールド: [calories]。
     */
    private Long fCalories;

    /**
     * フィールド: [fat]。
     */
    private Long fFat;

    /**
     * フィールド: [carbs]。
     */
    private Long fCarbs;

    /**
     * フィールド: [protein]。
     */
    private Long fProtein;

    /**
     * フィールド: [iron]。
     */
    private String fIron;

    /**
     * フィールド [name] の値を設定します。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [calories] の値を設定します。
     *
     * @param argCalories フィールド[calories]に設定する値。
     */
    public void setCalories(final Long argCalories) {
        fCalories = argCalories;
    }

    /**
     * フィールド [calories] の値を取得します。
     *
     * @return フィールド[calories]から取得した値。
     */
    public Long getCalories() {
        return fCalories;
    }

    /**
     * フィールド [fat] の値を設定します。
     *
     * @param argFat フィールド[fat]に設定する値。
     */
    public void setFat(final Long argFat) {
        fFat = argFat;
    }

    /**
     * フィールド [fat] の値を取得します。
     *
     * @return フィールド[fat]から取得した値。
     */
    public Long getFat() {
        return fFat;
    }

    /**
     * フィールド [carbs] の値を設定します。
     *
     * @param argCarbs フィールド[carbs]に設定する値。
     */
    public void setCarbs(final Long argCarbs) {
        fCarbs = argCarbs;
    }

    /**
     * フィールド [carbs] の値を取得します。
     *
     * @return フィールド[carbs]から取得した値。
     */
    public Long getCarbs() {
        return fCarbs;
    }

    /**
     * フィールド [protein] の値を設定します。
     *
     * @param argProtein フィールド[protein]に設定する値。
     */
    public void setProtein(final Long argProtein) {
        fProtein = argProtein;
    }

    /**
     * フィールド [protein] の値を取得します。
     *
     * @return フィールド[protein]から取得した値。
     */
    public Long getProtein() {
        return fProtein;
    }

    /**
     * フィールド [iron] の値を設定します。
     *
     * @param argIron フィールド[iron]に設定する値。
     */
    public void setIron(final String argIron) {
        fIron = argIron;
    }

    /**
     * フィールド [iron] の値を取得します。
     *
     * @return フィールド[iron]から取得した値。
     */
    public String getIron() {
        return fIron;
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
    public void copyTo(final TableSampleRow target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: TableSampleRow#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fCalories
        // Type: java.lang.Long
        target.fCalories = this.fCalories;
        // Name: fFat
        // Type: java.lang.Long
        target.fFat = this.fFat;
        // Name: fCarbs
        // Type: java.lang.Long
        target.fCarbs = this.fCarbs;
        // Name: fProtein
        // Type: java.lang.Long
        target.fProtein = this.fProtein;
        // Name: fIron
        // Type: java.lang.String
        target.fIron = this.fIron;
    }
}
