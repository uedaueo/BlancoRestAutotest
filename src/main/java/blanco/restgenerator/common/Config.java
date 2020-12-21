package blanco.restgenerator.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration for test.
 */
public class Config {

    public static Properties properties = new Properties();

    public Config() throws IOException {
    }

    public Config(String filename) throws IOException {
        read(filename);
    }

    /*
     * ユーザ設定ファイルで定義
     */
    /** API サーバのURL */
    public static final String apiUrlKey = "ApiUrl";
    /** BASIC 認証id */
    public static final String authIdKey = "AuthId"; //"";
    /** BASIC 認証パスワード */
    public static final String authPasswdKey = "AuthPass"; //"";

    public static final String userAgentKey = "UserAgent"; //"O-PLUX-v3-api test client";

    /** HttpClientにおける socket の timeout 時間（秒） */
    public static final String socketTimeoutKey = "SocketTimeout";
    /** HttpClientにおける接続の timeout 時間（秒） */
    public static final String connectionTimeoutKey = "ConnectionTimeout";
    //public static int connectionTimeout = 3;

    /** 簡易なログレベル */
    public static final String logLevelKey = "LogLevel"; //"DEBUG";

    public static final String defaultPackageKey = "DefaultPackage";

    public static final String sessionManagerKey = "SessionManagerImplClass";

    public static final String errorCodeOnDismissKey = "ErrorCodeOnDismiss";
    public static final String errorMessageOnDismissKey = "ErrorMessageOnDismiss";

    //public static final String tokenKey = "Token"; //"dummy";

    //public static final String langKey = "Lang";//"ja";

    private void read(String filename) throws IOException{
        System.out.println("read filename = " + filename);
        InputStream stream = new FileInputStream(filename);
        properties.loadFromXML(stream);
        stream.close();
        properties.list(System.out);
    }
}
