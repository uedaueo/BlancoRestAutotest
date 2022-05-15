package blanco.restautotest.valueobject;

/**
 * バリューオブジェクトのフィールドをあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにフィールドとセッター・ゲッターが自動生成されます。
 */
public class BlancoRestAutotestTestCaseFieldStructure {
    /**
     * 番号
     *
     * フィールド: [no]。
     */
    private String fNo;

    /**
     * テスト内容。レポートのタイトルとして記載されます。
     *
     * フィールド: [caseTitle]。
     */
    private String fCaseTitle;

    /**
     * このケースを実行する前に実行するケース番号をしています。
     *
     * フィールド: [precondition]。
     */
    private String fPrecondition;

    /**
     * テスト対象のAPI名を記述します。
     *
     * フィールド: [api]。
     */
    private String fApi;

    /**
     * テスト対象のAPIのHTTPメソッドを記述します。
     *
     * フィールド: [method]。
     */
    private String fMethod;

    /**
     * tokenが更新されるAPIかどうか
     *
     * フィールド: [refreshToken]。
     */
    private Boolean fRefreshToken;

    /**
     * テスト前に実行されるSQLファイル
     *
     * フィールド: [sqlScript]。
     */
    private String fSqlScript;

    /**
     * テスト前に実行されるShellScript。戻り値が0以外の場合はテストに失敗する。
     *
     * フィールド: [preScript]。
     */
    private String fPreScript;

    /**
     * テスト後に実行されるShellScript。戻り値が0以外の場合はテストに失敗する。
     *
     * フィールド: [postScript]。
     */
    private String fPostScript;

    /**
     * ログファイルから検知する文字列。テスト実行後に追記された部分から検索します。
     *
     * フィールド: [searchString]。
     */
    private String fSearchString;

    /**
     * 検知文字列を検知したときのステータス。trueならば合格。falseなら不合格。
     *
     * フィールド: [onDetect]。
     */
    private Boolean fOnDetect;

    /**
     * 使用するテストケースID
     *
     * フィールド: [targetCase]。
     */
    private String fTargetCase;

    /**
     * 画面ID
     *
     * フィールド: [screenId]。
     */
    private String fScreenId;

    /**
     * 加盟店ID
     *
     * フィールド: [shopId]。
     */
    private String fShopId;

    /**
     * 使用言語
     *
     * フィールド: [lang]。
     */
    private String fLang;

    /**
     * 使用時間帯
     *
     * フィールド: [tz]。
     */
    private String fTz;

    /**
     * 使用通貨
     *
     * フィールド: [currency]。
     */
    private String fCurrency;

    /**
     * バージョン
     *
     * フィールド: [version]。
     */
    private String fVersion;

    /**
     * APIレスポンス期待値
     *
     * フィールド: [time]。
     */
    private Long fTime;

    /**
     * APIレスポンス期待値
     *
     * フィールド: [result]。
     */
    private String fResult;

    /**
     * APIレスポンス期待値
     *
     * フィールド: [errorCode]。
     */
    private String fErrorCode;

    /**
     * APIレスポンス期待値
     *
     * フィールド: [errorMessage]。
     */
    private String fErrorMessage;

    /**
     * APIレスポンス期待値
     *
     * フィールド: [errorMessageNumber]。
     */
    private String fErrorMessageNumber;

    /**
     * APIレスポンス期待値
     *
     * フィールド: [messageCode]。
     */
    private String fMessageCode;

    /**
     * APIレスポンス期待値
     *
     * フィールド: [messageMessage]。
     */
    private String fMessageMessage;

    /**
     * APIレスポンス期待値
     *
     * フィールド: [messageMessageNumber]。
     */
    private String fMessageMessageNumber;

    /**
     * 備考
     *
     * フィールド: [Bikou]。
     */
    private String fBikou;

    /**
     * フィールド [no] の値を設定します。
     *
     * フィールドの説明: [番号]。
     *
     * @param argNo フィールド[no]に設定する値。
     */
    public void setNo(final String argNo) {
        fNo = argNo;
    }

    /**
     * フィールド [no] の値を取得します。
     *
     * フィールドの説明: [番号]。
     *
     * @return フィールド[no]から取得した値。
     */
    public String getNo() {
        return fNo;
    }

    /**
     * フィールド [caseTitle] の値を設定します。
     *
     * フィールドの説明: [テスト内容。レポートのタイトルとして記載されます。]。
     *
     * @param argCaseTitle フィールド[caseTitle]に設定する値。
     */
    public void setCaseTitle(final String argCaseTitle) {
        fCaseTitle = argCaseTitle;
    }

    /**
     * フィールド [caseTitle] の値を取得します。
     *
     * フィールドの説明: [テスト内容。レポートのタイトルとして記載されます。]。
     *
     * @return フィールド[caseTitle]から取得した値。
     */
    public String getCaseTitle() {
        return fCaseTitle;
    }

    /**
     * フィールド [precondition] の値を設定します。
     *
     * フィールドの説明: [このケースを実行する前に実行するケース番号をしています。]。
     *
     * @param argPrecondition フィールド[precondition]に設定する値。
     */
    public void setPrecondition(final String argPrecondition) {
        fPrecondition = argPrecondition;
    }

    /**
     * フィールド [precondition] の値を取得します。
     *
     * フィールドの説明: [このケースを実行する前に実行するケース番号をしています。]。
     *
     * @return フィールド[precondition]から取得した値。
     */
    public String getPrecondition() {
        return fPrecondition;
    }

    /**
     * フィールド [api] の値を設定します。
     *
     * フィールドの説明: [テスト対象のAPI名を記述します。]。
     *
     * @param argApi フィールド[api]に設定する値。
     */
    public void setApi(final String argApi) {
        fApi = argApi;
    }

    /**
     * フィールド [api] の値を取得します。
     *
     * フィールドの説明: [テスト対象のAPI名を記述します。]。
     *
     * @return フィールド[api]から取得した値。
     */
    public String getApi() {
        return fApi;
    }

    /**
     * フィールド [method] の値を設定します。
     *
     * フィールドの説明: [テスト対象のAPIのHTTPメソッドを記述します。]。
     *
     * @param argMethod フィールド[method]に設定する値。
     */
    public void setMethod(final String argMethod) {
        fMethod = argMethod;
    }

    /**
     * フィールド [method] の値を取得します。
     *
     * フィールドの説明: [テスト対象のAPIのHTTPメソッドを記述します。]。
     *
     * @return フィールド[method]から取得した値。
     */
    public String getMethod() {
        return fMethod;
    }

    /**
     * フィールド [refreshToken] の値を設定します。
     *
     * フィールドの説明: [tokenが更新されるAPIかどうか]。
     *
     * @param argRefreshToken フィールド[refreshToken]に設定する値。
     */
    public void setRefreshToken(final Boolean argRefreshToken) {
        fRefreshToken = argRefreshToken;
    }

    /**
     * フィールド [refreshToken] の値を取得します。
     *
     * フィールドの説明: [tokenが更新されるAPIかどうか]。
     *
     * @return フィールド[refreshToken]から取得した値。
     */
    public Boolean getRefreshToken() {
        return fRefreshToken;
    }

    /**
     * フィールド [sqlScript] の値を設定します。
     *
     * フィールドの説明: [テスト前に実行されるSQLファイル]。
     *
     * @param argSqlScript フィールド[sqlScript]に設定する値。
     */
    public void setSqlScript(final String argSqlScript) {
        fSqlScript = argSqlScript;
    }

    /**
     * フィールド [sqlScript] の値を取得します。
     *
     * フィールドの説明: [テスト前に実行されるSQLファイル]。
     *
     * @return フィールド[sqlScript]から取得した値。
     */
    public String getSqlScript() {
        return fSqlScript;
    }

    /**
     * フィールド [preScript] の値を設定します。
     *
     * フィールドの説明: [テスト前に実行されるShellScript。戻り値が0以外の場合はテストに失敗する。]。
     *
     * @param argPreScript フィールド[preScript]に設定する値。
     */
    public void setPreScript(final String argPreScript) {
        fPreScript = argPreScript;
    }

    /**
     * フィールド [preScript] の値を取得します。
     *
     * フィールドの説明: [テスト前に実行されるShellScript。戻り値が0以外の場合はテストに失敗する。]。
     *
     * @return フィールド[preScript]から取得した値。
     */
    public String getPreScript() {
        return fPreScript;
    }

    /**
     * フィールド [postScript] の値を設定します。
     *
     * フィールドの説明: [テスト後に実行されるShellScript。戻り値が0以外の場合はテストに失敗する。]。
     *
     * @param argPostScript フィールド[postScript]に設定する値。
     */
    public void setPostScript(final String argPostScript) {
        fPostScript = argPostScript;
    }

    /**
     * フィールド [postScript] の値を取得します。
     *
     * フィールドの説明: [テスト後に実行されるShellScript。戻り値が0以外の場合はテストに失敗する。]。
     *
     * @return フィールド[postScript]から取得した値。
     */
    public String getPostScript() {
        return fPostScript;
    }

    /**
     * フィールド [searchString] の値を設定します。
     *
     * フィールドの説明: [ログファイルから検知する文字列。テスト実行後に追記された部分から検索します。]。
     *
     * @param argSearchString フィールド[searchString]に設定する値。
     */
    public void setSearchString(final String argSearchString) {
        fSearchString = argSearchString;
    }

    /**
     * フィールド [searchString] の値を取得します。
     *
     * フィールドの説明: [ログファイルから検知する文字列。テスト実行後に追記された部分から検索します。]。
     *
     * @return フィールド[searchString]から取得した値。
     */
    public String getSearchString() {
        return fSearchString;
    }

    /**
     * フィールド [onDetect] の値を設定します。
     *
     * フィールドの説明: [検知文字列を検知したときのステータス。trueならば合格。falseなら不合格。]。
     *
     * @param argOnDetect フィールド[onDetect]に設定する値。
     */
    public void setOnDetect(final Boolean argOnDetect) {
        fOnDetect = argOnDetect;
    }

    /**
     * フィールド [onDetect] の値を取得します。
     *
     * フィールドの説明: [検知文字列を検知したときのステータス。trueならば合格。falseなら不合格。]。
     *
     * @return フィールド[onDetect]から取得した値。
     */
    public Boolean getOnDetect() {
        return fOnDetect;
    }

    /**
     * フィールド [targetCase] の値を設定します。
     *
     * フィールドの説明: [使用するテストケースID]。
     *
     * @param argTargetCase フィールド[targetCase]に設定する値。
     */
    public void setTargetCase(final String argTargetCase) {
        fTargetCase = argTargetCase;
    }

    /**
     * フィールド [targetCase] の値を取得します。
     *
     * フィールドの説明: [使用するテストケースID]。
     *
     * @return フィールド[targetCase]から取得した値。
     */
    public String getTargetCase() {
        return fTargetCase;
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
     * フィールドの説明: [加盟店ID]。
     *
     * @param argShopId フィールド[shopId]に設定する値。
     */
    public void setShopId(final String argShopId) {
        fShopId = argShopId;
    }

    /**
     * フィールド [shopId] の値を取得します。
     *
     * フィールドの説明: [加盟店ID]。
     *
     * @return フィールド[shopId]から取得した値。
     */
    public String getShopId() {
        return fShopId;
    }

    /**
     * フィールド [lang] の値を設定します。
     *
     * フィールドの説明: [使用言語]。
     *
     * @param argLang フィールド[lang]に設定する値。
     */
    public void setLang(final String argLang) {
        fLang = argLang;
    }

    /**
     * フィールド [lang] の値を取得します。
     *
     * フィールドの説明: [使用言語]。
     *
     * @return フィールド[lang]から取得した値。
     */
    public String getLang() {
        return fLang;
    }

    /**
     * フィールド [tz] の値を設定します。
     *
     * フィールドの説明: [使用時間帯]。
     *
     * @param argTz フィールド[tz]に設定する値。
     */
    public void setTz(final String argTz) {
        fTz = argTz;
    }

    /**
     * フィールド [tz] の値を取得します。
     *
     * フィールドの説明: [使用時間帯]。
     *
     * @return フィールド[tz]から取得した値。
     */
    public String getTz() {
        return fTz;
    }

    /**
     * フィールド [currency] の値を設定します。
     *
     * フィールドの説明: [使用通貨]。
     *
     * @param argCurrency フィールド[currency]に設定する値。
     */
    public void setCurrency(final String argCurrency) {
        fCurrency = argCurrency;
    }

    /**
     * フィールド [currency] の値を取得します。
     *
     * フィールドの説明: [使用通貨]。
     *
     * @return フィールド[currency]から取得した値。
     */
    public String getCurrency() {
        return fCurrency;
    }

    /**
     * フィールド [version] の値を設定します。
     *
     * フィールドの説明: [バージョン]。
     *
     * @param argVersion フィールド[version]に設定する値。
     */
    public void setVersion(final String argVersion) {
        fVersion = argVersion;
    }

    /**
     * フィールド [version] の値を取得します。
     *
     * フィールドの説明: [バージョン]。
     *
     * @return フィールド[version]から取得した値。
     */
    public String getVersion() {
        return fVersion;
    }

    /**
     * フィールド [time] の値を設定します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @param argTime フィールド[time]に設定する値。
     */
    public void setTime(final Long argTime) {
        fTime = argTime;
    }

    /**
     * フィールド [time] の値を取得します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @return フィールド[time]から取得した値。
     */
    public Long getTime() {
        return fTime;
    }

    /**
     * フィールド [result] の値を設定します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @param argResult フィールド[result]に設定する値。
     */
    public void setResult(final String argResult) {
        fResult = argResult;
    }

    /**
     * フィールド [result] の値を取得します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @return フィールド[result]から取得した値。
     */
    public String getResult() {
        return fResult;
    }

    /**
     * フィールド [errorCode] の値を設定します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @param argErrorCode フィールド[errorCode]に設定する値。
     */
    public void setErrorCode(final String argErrorCode) {
        fErrorCode = argErrorCode;
    }

    /**
     * フィールド [errorCode] の値を取得します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @return フィールド[errorCode]から取得した値。
     */
    public String getErrorCode() {
        return fErrorCode;
    }

    /**
     * フィールド [errorMessage] の値を設定します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @param argErrorMessage フィールド[errorMessage]に設定する値。
     */
    public void setErrorMessage(final String argErrorMessage) {
        fErrorMessage = argErrorMessage;
    }

    /**
     * フィールド [errorMessage] の値を取得します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @return フィールド[errorMessage]から取得した値。
     */
    public String getErrorMessage() {
        return fErrorMessage;
    }

    /**
     * フィールド [errorMessageNumber] の値を設定します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @param argErrorMessageNumber フィールド[errorMessageNumber]に設定する値。
     */
    public void setErrorMessageNumber(final String argErrorMessageNumber) {
        fErrorMessageNumber = argErrorMessageNumber;
    }

    /**
     * フィールド [errorMessageNumber] の値を取得します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @return フィールド[errorMessageNumber]から取得した値。
     */
    public String getErrorMessageNumber() {
        return fErrorMessageNumber;
    }

    /**
     * フィールド [messageCode] の値を設定します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @param argMessageCode フィールド[messageCode]に設定する値。
     */
    public void setMessageCode(final String argMessageCode) {
        fMessageCode = argMessageCode;
    }

    /**
     * フィールド [messageCode] の値を取得します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @return フィールド[messageCode]から取得した値。
     */
    public String getMessageCode() {
        return fMessageCode;
    }

    /**
     * フィールド [messageMessage] の値を設定します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @param argMessageMessage フィールド[messageMessage]に設定する値。
     */
    public void setMessageMessage(final String argMessageMessage) {
        fMessageMessage = argMessageMessage;
    }

    /**
     * フィールド [messageMessage] の値を取得します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @return フィールド[messageMessage]から取得した値。
     */
    public String getMessageMessage() {
        return fMessageMessage;
    }

    /**
     * フィールド [messageMessageNumber] の値を設定します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @param argMessageMessageNumber フィールド[messageMessageNumber]に設定する値。
     */
    public void setMessageMessageNumber(final String argMessageMessageNumber) {
        fMessageMessageNumber = argMessageMessageNumber;
    }

    /**
     * フィールド [messageMessageNumber] の値を取得します。
     *
     * フィールドの説明: [APIレスポンス期待値]。
     *
     * @return フィールド[messageMessageNumber]から取得した値。
     */
    public String getMessageMessageNumber() {
        return fMessageMessageNumber;
    }

    /**
     * フィールド [Bikou] の値を設定します。
     *
     * フィールドの説明: [備考]。
     *
     * @param argBikou フィールド[Bikou]に設定する値。
     */
    public void setBikou(final String argBikou) {
        fBikou = argBikou;
    }

    /**
     * フィールド [Bikou] の値を取得します。
     *
     * フィールドの説明: [備考]。
     *
     * @return フィールド[Bikou]から取得した値。
     */
    public String getBikou() {
        return fBikou;
    }

    /**
     * Gets the string representation of this value object.
     *
     * <P>Precautions for use</P>
     * <UL>
     * <LI>Only the shallow range of the object will be subject to the stringification process.
     * <LI>Do not use this method if the object has a circular reference.
     * </UL>
     *
     * @return String representation of a value object.
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.restautotest.valueobject.BlancoRestAutotestTestCaseFieldStructure[");
        buf.append("no=" + fNo);
        buf.append(",caseTitle=" + fCaseTitle);
        buf.append(",precondition=" + fPrecondition);
        buf.append(",api=" + fApi);
        buf.append(",method=" + fMethod);
        buf.append(",refreshToken=" + fRefreshToken);
        buf.append(",sqlScript=" + fSqlScript);
        buf.append(",preScript=" + fPreScript);
        buf.append(",postScript=" + fPostScript);
        buf.append(",searchString=" + fSearchString);
        buf.append(",onDetect=" + fOnDetect);
        buf.append(",targetCase=" + fTargetCase);
        buf.append(",screenId=" + fScreenId);
        buf.append(",shopId=" + fShopId);
        buf.append(",lang=" + fLang);
        buf.append(",tz=" + fTz);
        buf.append(",currency=" + fCurrency);
        buf.append(",version=" + fVersion);
        buf.append(",time=" + fTime);
        buf.append(",result=" + fResult);
        buf.append(",errorCode=" + fErrorCode);
        buf.append(",errorMessage=" + fErrorMessage);
        buf.append(",errorMessageNumber=" + fErrorMessageNumber);
        buf.append(",messageCode=" + fMessageCode);
        buf.append(",messageMessage=" + fMessageMessage);
        buf.append(",messageMessageNumber=" + fMessageMessageNumber);
        buf.append(",Bikou=" + fBikou);
        buf.append("]");
        return buf.toString();
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
    public void copyTo(final BlancoRestAutotestTestCaseFieldStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoRestAutotestTestCaseFieldStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fNo
        // Type: java.lang.String
        target.fNo = this.fNo;
        // Name: fCaseTitle
        // Type: java.lang.String
        target.fCaseTitle = this.fCaseTitle;
        // Name: fPrecondition
        // Type: java.lang.String
        target.fPrecondition = this.fPrecondition;
        // Name: fApi
        // Type: java.lang.String
        target.fApi = this.fApi;
        // Name: fMethod
        // Type: java.lang.String
        target.fMethod = this.fMethod;
        // Name: fRefreshToken
        // Type: java.lang.Boolean
        target.fRefreshToken = this.fRefreshToken;
        // Name: fSqlScript
        // Type: java.lang.String
        target.fSqlScript = this.fSqlScript;
        // Name: fPreScript
        // Type: java.lang.String
        target.fPreScript = this.fPreScript;
        // Name: fPostScript
        // Type: java.lang.String
        target.fPostScript = this.fPostScript;
        // Name: fSearchString
        // Type: java.lang.String
        target.fSearchString = this.fSearchString;
        // Name: fOnDetect
        // Type: java.lang.Boolean
        target.fOnDetect = this.fOnDetect;
        // Name: fTargetCase
        // Type: java.lang.String
        target.fTargetCase = this.fTargetCase;
        // Name: fScreenId
        // Type: java.lang.String
        target.fScreenId = this.fScreenId;
        // Name: fShopId
        // Type: java.lang.String
        target.fShopId = this.fShopId;
        // Name: fLang
        // Type: java.lang.String
        target.fLang = this.fLang;
        // Name: fTz
        // Type: java.lang.String
        target.fTz = this.fTz;
        // Name: fCurrency
        // Type: java.lang.String
        target.fCurrency = this.fCurrency;
        // Name: fVersion
        // Type: java.lang.String
        target.fVersion = this.fVersion;
        // Name: fTime
        // Type: java.lang.Long
        target.fTime = this.fTime;
        // Name: fResult
        // Type: java.lang.String
        target.fResult = this.fResult;
        // Name: fErrorCode
        // Type: java.lang.String
        target.fErrorCode = this.fErrorCode;
        // Name: fErrorMessage
        // Type: java.lang.String
        target.fErrorMessage = this.fErrorMessage;
        // Name: fErrorMessageNumber
        // Type: java.lang.String
        target.fErrorMessageNumber = this.fErrorMessageNumber;
        // Name: fMessageCode
        // Type: java.lang.String
        target.fMessageCode = this.fMessageCode;
        // Name: fMessageMessage
        // Type: java.lang.String
        target.fMessageMessage = this.fMessageMessage;
        // Name: fMessageMessageNumber
        // Type: java.lang.String
        target.fMessageMessageNumber = this.fMessageMessageNumber;
        // Name: fBikou
        // Type: java.lang.String
        target.fBikou = this.fBikou;
    }
}
