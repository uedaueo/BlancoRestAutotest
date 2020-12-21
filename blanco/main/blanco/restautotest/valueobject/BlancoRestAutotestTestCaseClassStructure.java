package blanco.restautotest.valueobject;

import java.util.List;

/**
 * バリューオブジェクトのクラスをあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにクラスが自動生成されます。
 */
public class BlancoRestAutotestTestCaseClassStructure {
    /**
     * フィールド名を指定します。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * パッケージ名を指定します。必須項目です。
     *
     * フィールド: [package]。
     */
    private String fPackage;

    /**
     * クラスの説明です。
     *
     * フィールド: [description]。
     */
    private String fDescription;

    /**
     * クラスの補助説明です。文字参照エンコード済みの値を格納してください。
     *
     * フィールド: [descriptionList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fDescriptionList = new java.util.ArrayList<java.lang.String>();

    /**
     * クラスのアクセス。通常は public。
     *
     * フィールド: [access]。
     * デフォルト: [&quot;public&quot;]。
     */
    private String fAccess = "public";

    /**
     * 抽象クラスかどうか。通常は false。
     *
     * フィールド: [abstract]。
     * デフォルト: [false]。
     */
    private boolean fAbstract = false;

    /**
     * toStringメソッドを生成するかどうか。
     *
     * フィールド: [generateToString]。
     * デフォルト: [false]。
     */
    private boolean fGenerateToString = false;

    /**
     * フィールド名の名前変形をおこなうかどうか。
     *
     * フィールド: [adjustFieldName]。
     * デフォルト: [true]。
     */
    private boolean fAdjustFieldName = true;

    /**
     * デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。
     *
     * フィールド: [adjustDefaultValue]。
     * デフォルト: [true]。
     */
    private boolean fAdjustDefaultValue = true;

    /**
     * リクエストヘッダの実装クラス名
     *
     * フィールド: [requestHeaderImple]。
     */
    private String fRequestHeaderImple;

    /**
     * 継承するクラスを指定します。
     *
     * フィールド: [extends]。
     */
    private String fExtends;

    /**
     * 実装するインタフェース(java.lang.String)の一覧。
     *
     * フィールド: [implementsList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fImplementsList = new java.util.ArrayList<java.lang.String>();

    /**
     * フィールドを記憶するリストを指定します。
     *
     * フィールド: [testCaseFieldList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.restautotest.valueobject.BlancoRestAutotestTestCaseFieldStructure&gt;()]。
     */
    private List<BlancoRestAutotestTestCaseFieldStructure> fTestCaseFieldList = new java.util.ArrayList<blanco.restautotest.valueobject.BlancoRestAutotestTestCaseFieldStructure>();

    /**
     * フィールドを記憶するリストを指定します。
     *
     * フィールド: [inputResultFieldList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure&gt;()]。
     */
    private List<BlancoRestAutotestInputResultFieldStructure> fInputResultFieldList = new java.util.ArrayList<blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure>();

    /**
     * フィールドを記憶するリストを指定します。(カラム名のリスト)
     *
     * フィールド: [columnNameList]。
     * デフォルト: [new blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure()]。
     */
    private BlancoRestAutotestInputResultFieldStructure fColumnNameList = new blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure();

    /**
     * フィールドを記憶するリストを指定します。(アサーション処理リスト)
     *
     * フィールド: [assertionList]。
     * デフォルト: [new blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure()]。
     */
    private BlancoRestAutotestInputResultFieldStructure fAssertionList = new blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure();

    /**
     * ファイル説明
     *
     * フィールド: [fileDescription]。
     */
    private String fFileDescription;

    /**
     * 要求電文ID（POSTメソッド）
     *
     * フィールド: [telegramPostRequestId]。
     */
    private String fTelegramPostRequestId;

    /**
     * 応答電文ID（POSTメソッド）
     *
     * フィールド: [telegramPostResponseId]。
     */
    private String fTelegramPostResponseId;

    /**
     * 処理定義・継承
     *
     * フィールド: [superClass]。
     */
    private String fSuperClass;

    /**
     * 処理定義・継承
     *
     * フィールド: [superPackage]。
     */
    private String fSuperPackage;

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [フィールド名を指定します。必須項目です。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [フィールド名を指定します。必須項目です。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [package] の値を設定します。
     *
     * フィールドの説明: [パッケージ名を指定します。必須項目です。]。
     *
     * @param argPackage フィールド[package]に設定する値。
     */
    public void setPackage(final String argPackage) {
        fPackage = argPackage;
    }

    /**
     * フィールド [package] の値を取得します。
     *
     * フィールドの説明: [パッケージ名を指定します。必須項目です。]。
     *
     * @return フィールド[package]から取得した値。
     */
    public String getPackage() {
        return fPackage;
    }

    /**
     * フィールド [description] の値を設定します。
     *
     * フィールドの説明: [クラスの説明です。]。
     *
     * @param argDescription フィールド[description]に設定する値。
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド [description] の値を取得します。
     *
     * フィールドの説明: [クラスの説明です。]。
     *
     * @return フィールド[description]から取得した値。
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * フィールド [descriptionList] の値を設定します。
     *
     * フィールドの説明: [クラスの補助説明です。文字参照エンコード済みの値を格納してください。]。
     *
     * @param argDescriptionList フィールド[descriptionList]に設定する値。
     */
    public void setDescriptionList(final List<String> argDescriptionList) {
        fDescriptionList = argDescriptionList;
    }

    /**
     * フィールド [descriptionList] の値を取得します。
     *
     * フィールドの説明: [クラスの補助説明です。文字参照エンコード済みの値を格納してください。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[descriptionList]から取得した値。
     */
    public List<String> getDescriptionList() {
        return fDescriptionList;
    }

    /**
     * フィールド [access] の値を設定します。
     *
     * フィールドの説明: [クラスのアクセス。通常は public。]。
     *
     * @param argAccess フィールド[access]に設定する値。
     */
    public void setAccess(final String argAccess) {
        fAccess = argAccess;
    }

    /**
     * フィールド [access] の値を取得します。
     *
     * フィールドの説明: [クラスのアクセス。通常は public。]。
     * デフォルト: [&quot;public&quot;]。
     *
     * @return フィールド[access]から取得した値。
     */
    public String getAccess() {
        return fAccess;
    }

    /**
     * フィールド [abstract] の値を設定します。
     *
     * フィールドの説明: [抽象クラスかどうか。通常は false。]。
     *
     * @param argAbstract フィールド[abstract]に設定する値。
     */
    public void setAbstract(final boolean argAbstract) {
        fAbstract = argAbstract;
    }

    /**
     * フィールド [abstract] の値を取得します。
     *
     * フィールドの説明: [抽象クラスかどうか。通常は false。]。
     * デフォルト: [false]。
     *
     * @return フィールド[abstract]から取得した値。
     */
    public boolean getAbstract() {
        return fAbstract;
    }

    /**
     * フィールド [generateToString] の値を設定します。
     *
     * フィールドの説明: [toStringメソッドを生成するかどうか。]。
     *
     * @param argGenerateToString フィールド[generateToString]に設定する値。
     */
    public void setGenerateToString(final boolean argGenerateToString) {
        fGenerateToString = argGenerateToString;
    }

    /**
     * フィールド [generateToString] の値を取得します。
     *
     * フィールドの説明: [toStringメソッドを生成するかどうか。]。
     * デフォルト: [false]。
     *
     * @return フィールド[generateToString]から取得した値。
     */
    public boolean getGenerateToString() {
        return fGenerateToString;
    }

    /**
     * フィールド [adjustFieldName] の値を設定します。
     *
     * フィールドの説明: [フィールド名の名前変形をおこなうかどうか。]。
     *
     * @param argAdjustFieldName フィールド[adjustFieldName]に設定する値。
     */
    public void setAdjustFieldName(final boolean argAdjustFieldName) {
        fAdjustFieldName = argAdjustFieldName;
    }

    /**
     * フィールド [adjustFieldName] の値を取得します。
     *
     * フィールドの説明: [フィールド名の名前変形をおこなうかどうか。]。
     * デフォルト: [true]。
     *
     * @return フィールド[adjustFieldName]から取得した値。
     */
    public boolean getAdjustFieldName() {
        return fAdjustFieldName;
    }

    /**
     * フィールド [adjustDefaultValue] の値を設定します。
     *
     * フィールドの説明: [デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。]。
     *
     * @param argAdjustDefaultValue フィールド[adjustDefaultValue]に設定する値。
     */
    public void setAdjustDefaultValue(final boolean argAdjustDefaultValue) {
        fAdjustDefaultValue = argAdjustDefaultValue;
    }

    /**
     * フィールド [adjustDefaultValue] の値を取得します。
     *
     * フィールドの説明: [デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。]。
     * デフォルト: [true]。
     *
     * @return フィールド[adjustDefaultValue]から取得した値。
     */
    public boolean getAdjustDefaultValue() {
        return fAdjustDefaultValue;
    }

    /**
     * フィールド [requestHeaderImple] の値を設定します。
     *
     * フィールドの説明: [リクエストヘッダの実装クラス名]。
     *
     * @param argRequestHeaderImple フィールド[requestHeaderImple]に設定する値。
     */
    public void setRequestHeaderImple(final String argRequestHeaderImple) {
        fRequestHeaderImple = argRequestHeaderImple;
    }

    /**
     * フィールド [requestHeaderImple] の値を取得します。
     *
     * フィールドの説明: [リクエストヘッダの実装クラス名]。
     *
     * @return フィールド[requestHeaderImple]から取得した値。
     */
    public String getRequestHeaderImple() {
        return fRequestHeaderImple;
    }

    /**
     * フィールド [extends] の値を設定します。
     *
     * フィールドの説明: [継承するクラスを指定します。]。
     *
     * @param argExtends フィールド[extends]に設定する値。
     */
    public void setExtends(final String argExtends) {
        fExtends = argExtends;
    }

    /**
     * フィールド [extends] の値を取得します。
     *
     * フィールドの説明: [継承するクラスを指定します。]。
     *
     * @return フィールド[extends]から取得した値。
     */
    public String getExtends() {
        return fExtends;
    }

    /**
     * フィールド [implementsList] の値を設定します。
     *
     * フィールドの説明: [実装するインタフェース(java.lang.String)の一覧。]。
     *
     * @param argImplementsList フィールド[implementsList]に設定する値。
     */
    public void setImplementsList(final List<String> argImplementsList) {
        fImplementsList = argImplementsList;
    }

    /**
     * フィールド [implementsList] の値を取得します。
     *
     * フィールドの説明: [実装するインタフェース(java.lang.String)の一覧。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[implementsList]から取得した値。
     */
    public List<String> getImplementsList() {
        return fImplementsList;
    }

    /**
     * フィールド [testCaseFieldList] の値を設定します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。]。
     *
     * @param argTestCaseFieldList フィールド[testCaseFieldList]に設定する値。
     */
    public void setTestCaseFieldList(final List<BlancoRestAutotestTestCaseFieldStructure> argTestCaseFieldList) {
        fTestCaseFieldList = argTestCaseFieldList;
    }

    /**
     * フィールド [testCaseFieldList] の値を取得します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.restautotest.valueobject.BlancoRestAutotestTestCaseFieldStructure&gt;()]。
     *
     * @return フィールド[testCaseFieldList]から取得した値。
     */
    public List<BlancoRestAutotestTestCaseFieldStructure> getTestCaseFieldList() {
        return fTestCaseFieldList;
    }

    /**
     * フィールド [inputResultFieldList] の値を設定します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。]。
     *
     * @param argInputResultFieldList フィールド[inputResultFieldList]に設定する値。
     */
    public void setInputResultFieldList(final List<BlancoRestAutotestInputResultFieldStructure> argInputResultFieldList) {
        fInputResultFieldList = argInputResultFieldList;
    }

    /**
     * フィールド [inputResultFieldList] の値を取得します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure&gt;()]。
     *
     * @return フィールド[inputResultFieldList]から取得した値。
     */
    public List<BlancoRestAutotestInputResultFieldStructure> getInputResultFieldList() {
        return fInputResultFieldList;
    }

    /**
     * フィールド [columnNameList] の値を設定します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。(カラム名のリスト)]。
     *
     * @param argColumnNameList フィールド[columnNameList]に設定する値。
     */
    public void setColumnNameList(final BlancoRestAutotestInputResultFieldStructure argColumnNameList) {
        fColumnNameList = argColumnNameList;
    }

    /**
     * フィールド [columnNameList] の値を取得します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。(カラム名のリスト)]。
     * デフォルト: [new blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure()]。
     *
     * @return フィールド[columnNameList]から取得した値。
     */
    public BlancoRestAutotestInputResultFieldStructure getColumnNameList() {
        return fColumnNameList;
    }

    /**
     * フィールド [assertionList] の値を設定します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。(アサーション処理リスト)]。
     *
     * @param argAssertionList フィールド[assertionList]に設定する値。
     */
    public void setAssertionList(final BlancoRestAutotestInputResultFieldStructure argAssertionList) {
        fAssertionList = argAssertionList;
    }

    /**
     * フィールド [assertionList] の値を取得します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。(アサーション処理リスト)]。
     * デフォルト: [new blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure()]。
     *
     * @return フィールド[assertionList]から取得した値。
     */
    public BlancoRestAutotestInputResultFieldStructure getAssertionList() {
        return fAssertionList;
    }

    /**
     * フィールド [fileDescription] の値を設定します。
     *
     * フィールドの説明: [ファイル説明]。
     *
     * @param argFileDescription フィールド[fileDescription]に設定する値。
     */
    public void setFileDescription(final String argFileDescription) {
        fFileDescription = argFileDescription;
    }

    /**
     * フィールド [fileDescription] の値を取得します。
     *
     * フィールドの説明: [ファイル説明]。
     *
     * @return フィールド[fileDescription]から取得した値。
     */
    public String getFileDescription() {
        return fFileDescription;
    }

    /**
     * フィールド [telegramPostRequestId] の値を設定します。
     *
     * フィールドの説明: [要求電文ID（POSTメソッド）]。
     *
     * @param argTelegramPostRequestId フィールド[telegramPostRequestId]に設定する値。
     */
    public void setTelegramPostRequestId(final String argTelegramPostRequestId) {
        fTelegramPostRequestId = argTelegramPostRequestId;
    }

    /**
     * フィールド [telegramPostRequestId] の値を取得します。
     *
     * フィールドの説明: [要求電文ID（POSTメソッド）]。
     *
     * @return フィールド[telegramPostRequestId]から取得した値。
     */
    public String getTelegramPostRequestId() {
        return fTelegramPostRequestId;
    }

    /**
     * フィールド [telegramPostResponseId] の値を設定します。
     *
     * フィールドの説明: [応答電文ID（POSTメソッド）]。
     *
     * @param argTelegramPostResponseId フィールド[telegramPostResponseId]に設定する値。
     */
    public void setTelegramPostResponseId(final String argTelegramPostResponseId) {
        fTelegramPostResponseId = argTelegramPostResponseId;
    }

    /**
     * フィールド [telegramPostResponseId] の値を取得します。
     *
     * フィールドの説明: [応答電文ID（POSTメソッド）]。
     *
     * @return フィールド[telegramPostResponseId]から取得した値。
     */
    public String getTelegramPostResponseId() {
        return fTelegramPostResponseId;
    }

    /**
     * フィールド [superClass] の値を設定します。
     *
     * フィールドの説明: [処理定義・継承]。
     *
     * @param argSuperClass フィールド[superClass]に設定する値。
     */
    public void setSuperClass(final String argSuperClass) {
        fSuperClass = argSuperClass;
    }

    /**
     * フィールド [superClass] の値を取得します。
     *
     * フィールドの説明: [処理定義・継承]。
     *
     * @return フィールド[superClass]から取得した値。
     */
    public String getSuperClass() {
        return fSuperClass;
    }

    /**
     * フィールド [superPackage] の値を設定します。
     *
     * フィールドの説明: [処理定義・継承]。
     *
     * @param argSuperPackage フィールド[superPackage]に設定する値。
     */
    public void setSuperPackage(final String argSuperPackage) {
        fSuperPackage = argSuperPackage;
    }

    /**
     * フィールド [superPackage] の値を取得します。
     *
     * フィールドの説明: [処理定義・継承]。
     *
     * @return フィールド[superPackage]から取得した値。
     */
    public String getSuperPackage() {
        return fSuperPackage;
    }

    /**
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ文字列化の処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @return バリューオブジェクトの文字列表現。
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.restautotest.valueobject.BlancoRestAutotestTestCaseClassStructure[");
        buf.append("name=" + fName);
        buf.append(",package=" + fPackage);
        buf.append(",description=" + fDescription);
        buf.append(",descriptionList=" + fDescriptionList);
        buf.append(",access=" + fAccess);
        buf.append(",abstract=" + fAbstract);
        buf.append(",generateToString=" + fGenerateToString);
        buf.append(",adjustFieldName=" + fAdjustFieldName);
        buf.append(",adjustDefaultValue=" + fAdjustDefaultValue);
        buf.append(",requestHeaderImple=" + fRequestHeaderImple);
        buf.append(",extends=" + fExtends);
        buf.append(",implementsList=" + fImplementsList);
        buf.append(",testCaseFieldList=" + fTestCaseFieldList);
        buf.append(",inputResultFieldList=" + fInputResultFieldList);
        buf.append(",columnNameList=" + fColumnNameList);
        buf.append(",assertionList=" + fAssertionList);
        buf.append(",fileDescription=" + fFileDescription);
        buf.append(",telegramPostRequestId=" + fTelegramPostRequestId);
        buf.append(",telegramPostResponseId=" + fTelegramPostResponseId);
        buf.append(",superClass=" + fSuperClass);
        buf.append(",superPackage=" + fSuperPackage);
        buf.append("]");
        return buf.toString();
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
    public void copyTo(final BlancoRestAutotestTestCaseClassStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoRestAutotestTestCaseClassStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fPackage
        // Type: java.lang.String
        target.fPackage = this.fPackage;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fDescriptionList
        // Type: java.util.List
        // フィールド[fDescriptionList]はサポート外の型[java.util.Listjava.lang.String]です。
        // Name: fAccess
        // Type: java.lang.String
        target.fAccess = this.fAccess;
        // Name: fAbstract
        // Type: boolean
        target.fAbstract = this.fAbstract;
        // Name: fGenerateToString
        // Type: boolean
        target.fGenerateToString = this.fGenerateToString;
        // Name: fAdjustFieldName
        // Type: boolean
        target.fAdjustFieldName = this.fAdjustFieldName;
        // Name: fAdjustDefaultValue
        // Type: boolean
        target.fAdjustDefaultValue = this.fAdjustDefaultValue;
        // Name: fRequestHeaderImple
        // Type: java.lang.String
        target.fRequestHeaderImple = this.fRequestHeaderImple;
        // Name: fExtends
        // Type: java.lang.String
        target.fExtends = this.fExtends;
        // Name: fImplementsList
        // Type: java.util.List
        // フィールド[fImplementsList]はサポート外の型[java.util.Listjava.lang.String]です。
        // Name: fTestCaseFieldList
        // Type: java.util.List
        // フィールド[fTestCaseFieldList]はサポート外の型[java.util.Listblanco.restautotest.valueobject.BlancoRestAutotestTestCaseFieldStructure]です。
        // Name: fInputResultFieldList
        // Type: java.util.List
        // フィールド[fInputResultFieldList]はサポート外の型[java.util.Listblanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure]です。
        // Name: fColumnNameList
        // Type: blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure
        // フィールド[fColumnNameList]はサポート外の型[blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure]です。
        // Name: fAssertionList
        // Type: blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure
        // フィールド[fAssertionList]はサポート外の型[blanco.restautotest.valueobject.BlancoRestAutotestInputResultFieldStructure]です。
        // Name: fFileDescription
        // Type: java.lang.String
        target.fFileDescription = this.fFileDescription;
        // Name: fTelegramPostRequestId
        // Type: java.lang.String
        target.fTelegramPostRequestId = this.fTelegramPostRequestId;
        // Name: fTelegramPostResponseId
        // Type: java.lang.String
        target.fTelegramPostResponseId = this.fTelegramPostResponseId;
        // Name: fSuperClass
        // Type: java.lang.String
        target.fSuperClass = this.fSuperClass;
        // Name: fSuperPackage
        // Type: java.lang.String
        target.fSuperPackage = this.fSuperPackage;
    }
}
