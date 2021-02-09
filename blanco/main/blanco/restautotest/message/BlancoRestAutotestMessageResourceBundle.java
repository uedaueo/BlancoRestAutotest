package blanco.restautotest.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * メッセージ定義[BlancoRestAutotest]が内部的に利用するリソースバンドルクラス。
 *
 * リソースバンドル定義: [BlancoRestAutotestMessage]。<BR>
 * このクラスはリソースバンドル定義書から自動生成されたリソースバンドルクラスです。<BR>
 */
class BlancoRestAutotestMessageResourceBundle {
    /**
     * リソースバンドルオブジェクト。
     *
     * 内部的に実際に入力を行うリソースバンドルを記憶します。
     */
    private ResourceBundle fResourceBundle;

    /**
     * BlancoRestAutotestMessageResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoRestAutotestMessage]、デフォルトのロケール、呼び出し側のクラスローダを使用して、リソースバンドルを取得します。
     */
    public BlancoRestAutotestMessageResourceBundle() {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/restautotest/message/BlancoRestAutotestMessage");
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoRestAutotestMessageResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoRestAutotestMessage]、指定されたロケール、呼び出し側のクラスローダを使用して、リソースバンドルを取得します。
     *
     * @param locale ロケールの指定
     */
    public BlancoRestAutotestMessageResourceBundle(final Locale locale) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/restautotest/message/BlancoRestAutotestMessage", locale);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoRestAutotestMessageResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoRestAutotestMessage]、指定されたロケール、指定されたクラスローダを使用して、リソースバンドルを取得します。
     *
     * @param locale ロケールの指定
     * @param loader クラスローダの指定
     */
    public BlancoRestAutotestMessageResourceBundle(final Locale locale, final ClassLoader loader) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/restautotest/message/BlancoRestAutotestMessage", locale, loader);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * 内部的に保持しているリソースバンドルオブジェクトを取得します。
     *
     * @return 内部的に保持しているリソースバンドルオブジェクト。
     */
    public ResourceBundle getResourceBundle() {
        return fResourceBundle;
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI01]
     *
     * [クラス名[{0}]のパッケージ名が指定されていません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji01(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}]のパッケージ名が指定されていません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI01");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI02]
     *
     * [クラス名[{0}]のフィールド[{1}]の型名が指定されていません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI02]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji02(final String arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}]のフィールド[{1}]の型名が指定されていません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI02");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI03]
     *
     * [クラス名[{0}] でフィールド名が指定されていないものがあります。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI03]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji03(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}] でフィールド名が指定されていないものがあります。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI03");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI04]
     *
     * [クラス名[{0}] フィールド[{1}]の「型」が指定されていません。「型」を指定してください。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI04]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji04(final String arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}] フィールド[{1}]の「型」が指定されていません。「型」を指定してください。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI04");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI05]
     *
     * [クラス名[{0}] フィールド[{1}]の「デフォルト値({2})」がセットされています。しかし「{3}」はデフォルト値をサポートしません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.lang.String型を与えてください。
     * @param arg3 置換文字列{3}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI05]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji05(final String arg0, final String arg1, final String arg2, final String arg3) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}] フィールド[{1}]の「デフォルト値({2})」がセットされています。しかし「{3}」はデフォルト値をサポートしません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI05");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2, arg3}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI06]
     *
     * [クラス名[{0}] フィールド[{1}]の「総称型({2})」がセットされています。しかし「{3}」は総称型をサポートしません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.lang.String型を与えてください。
     * @param arg3 置換文字列{3}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI06]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji06(final String arg0, final String arg1, final String arg2, final String arg3) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}] フィールド[{1}]の「総称型({2})」がセットされています。しかし「{3}」は総称型をサポートしません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI06");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2, arg3}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI07]
     *
     * [クラス名[{0}] フィールド[{1}] では null は許容しません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI07]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji07(final String arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}] フィールド[{1}] では null は許容しません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI07");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI08]
     *
     * [入出力[{0}] で、親プロパティが継続なのに左隣にプロパティが定義されていません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI08]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji08(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "入出力[{0}] で、親プロパティが継続なのに左隣にプロパティが定義されていません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI08");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI09]
     *
     * [入出力[{0}] で、型[{1}]が定義されていますがそれは存在しません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI09]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji09(final String arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "入出力[{0}] で、型[{1}]が定義されていますがそれは存在しません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI09");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJI10]
     *
     * [電文クラス[{0}] の情報が存在しません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJI10]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoji10(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "電文クラス[{0}] の情報が存在しません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJI10");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoRestAutotestMessage], key[MBVOJA01]
     *
     * [メタディレクトリ[{0}]が存在しません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[MBVOJA01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMbvoja01(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "メタディレクトリ[{0}]が存在しません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("MBVOJA01");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }
}
