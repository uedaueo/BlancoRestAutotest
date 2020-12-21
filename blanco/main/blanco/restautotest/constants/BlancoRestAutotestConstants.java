package blanco.restautotest.constants;

/**
 * BlancoRestAutotest が利用する定数を蓄えます。
 */
public class BlancoRestAutotestConstants {
    /**
     * 項目番号:1<br>
     * プロダクト名。英字で指定します。
     */
    public static final String PRODUCT_NAME = "BlancoRestAutotest";

    /**
     * 項目番号:2<br>
     * プロダクト名の小文字版。英字で指定します。
     */
    public static final String PRODUCT_NAME_LOWER = "blancorestautotest";

    /**
     * 項目番号:3<br>
     * バージョン番号。
     */
    public static final String VERSION = "0.0.1";

    /**
     * 項目番号:4<br>
     * 処理の過程で利用されるサブディレクトリ。
     */
    public static final String TARGET_SUBDIRECTORY = "/blancorestautotest";

    /**
     * 項目番号:5<br>
     * inputのmax値(input1～input30)
     */
    public static final int INPUT_MAX = 30;

    /**
     * 項目番号:6<br>
     * outputのmax値(output1～output30)
     */
    public static final int OUTPUT_MAX = 30;

    /**
     * 項目番号:7<br>
     * targetdirに設定される文字列
     */
    public static final String TARGET_STYLE_BLANCO = "blanco";

    /**
     * 項目番号:8<br>
     * targetdirに設定される文字列
     */
    public static final String TARGET_STYLE_MAVEN = "maven";

    /**
     * 項目番号:9<br>
     * targetdirに設定される文字列
     */
    public static final String TARGET_STYLE_FREE = "free";

    /**
     * 項目番号:10<br>
     * 生成したソースコードを保管するディレクトリのsuffix
     */
    public static final String TARGET_DIR_SUFFIX_BLANCO = "main";

    /**
     * 項目番号:11<br>
     * 生成したソースコードを保管するディレクトリのsuffix
     */
    public static final String TARGET_DIR_SUFFIX_MAVEN = "main/java";

    /**
     * 項目番号:14<br>
     * 入出力定義書の種別：プロパティ
     */
    public static final String INPUT_RESULT_KIND_PROPERTY = "property";

    /**
     * 項目番号:15<br>
     * 入出力定義書の種別：比較方法
     */
    public static final String INPUT_RESULT_KIND_ASSERT = "assert";

    /**
     * 項目番号:16<br>
     * 入出力定義書の種別：値
     */
    public static final String INPUT_RESULT_KIND_VALUE = "value";
}
