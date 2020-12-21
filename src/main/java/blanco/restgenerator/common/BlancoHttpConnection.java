package blanco.restgenerator.common;

import blanco.restgenerator.exception.BlancoRestException;
import blanco.restautotest.resourcebundle.BlancoRestAutotestResourceBundle;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by tueda on 15/10/10.
 */
public class BlancoHttpConnection {

    static private BlancoRestAutotestResourceBundle fBundle = new BlancoRestAutotestResourceBundle();

    /** BASIC 認証 id */
    String userId = null;
    /** BASIC 認証パスワード */
    String passwd = null;
    /** API URL */
    String strUrl = null;

    /** API への接続, 普通にcloseすれば大丈夫らしい */
    CloseableHttpClient client = null;
    /** API からの response, 普通にcloseすれば大丈夫らしい */
    CloseableHttpResponse response = null;

    private String resJson = null;

    /**
     * 引数無しのコンストラクタは認めない
     */
    private BlancoHttpConnection() {

    }

    /**
     * Constructor
     * @param argUrl
     */
    public BlancoHttpConnection(String argUrl) {
        this.strUrl = argUrl;
    }

    /**
     * Constructor
     * @param argUrl
     * @param argUserId
     * @param argPasswd
     */
    public BlancoHttpConnection(String argUrl, String argUserId, String argPasswd) {
        this.strUrl = argUrl;
        this.userId = argUserId;
        this.passwd = argPasswd;
    }

    /**
     * 終了時にConnectionを確実にクローズ
     */
    @Override
    public void finalize() {
        close();
    }

    /**
     * APIへ接続（GET,DELETE）
     *
     * @param httpMethod GET or DELETE
     * @return Response body. JSON 文字列を期待しています．
     * @throws BlancoRestException
     */
    public String connect(String httpMethod) throws BlancoRestException {

        String ret = null;

        if (this.strUrl == null) {
            return ret;
        }

        HttpRequestBase request = null;
        if("GET".equalsIgnoreCase(httpMethod)) {
            request = new HttpGet(strUrl);
        } else if("DELETE".equalsIgnoreCase(httpMethod)) {
            request = new HttpDelete(strUrl);
        }

        try {
            request.setHeader("User-Agent", Config.properties.getProperty(Config.userAgentKey));
            request.setHeader("Content-Type", "application/json;charset=UTF-8");

            client = HttpClients.createDefault();
            response = client.execute(request);

            if (response == null) {
                throw new BlancoRestException(fBundle.getRuntimeErrorNotnull("response: CloseableHttpResponse"));
            }

            if (response.getStatusLine().getStatusCode() > 300) {
                throw new BlancoRestException(fBundle.getRuntimeErrorHttpcode(
                        "" + response.getStatusLine().getStatusCode()));
            }

            // 多分関係ないけど一応
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            HttpEntity responseEntity = response.getEntity();
            ret = EntityUtils.toString(responseEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return ret;
    }

    /**
     * APIへ接続（PUT,POST）
     *
     * @param strJson APIへの送信電文
     * @param httpMethod POST or PUT
     * @return Response body. JSON 文字列を期待しています．
     * @throws BlancoRestException
     */
    public String connect(String strJson, String httpMethod) throws BlancoRestException {

        String ret = null;

        if (this.strUrl == null) {
            return ret;
        }

        HttpEntityEnclosingRequestBase request = null;
        if("POST".equalsIgnoreCase(httpMethod)) {
            request = new HttpPost(strUrl);
        } else if("PUT".equalsIgnoreCase(httpMethod)) {
            request = new HttpPut(strUrl);
        }

        try {
            request.setHeader("User-Agent", Config.properties.getProperty(Config.userAgentKey));
            request.setHeader("Content-Type", "application/json;charset=UTF-8");

        /* data の設定 */
            HttpEntity requestEntity = new ByteArrayEntity(strJson.getBytes("UTF-8"));
            request.setEntity(requestEntity);

            client = HttpClients.createDefault();
            response = client.execute(request);

            if (response == null) {
                throw new BlancoRestException(fBundle.getRuntimeErrorNotnull("response: CloseableHttpResponse"));
            }

            if (response.getStatusLine().getStatusCode() > 300) {
                throw new BlancoRestException(fBundle.getRuntimeErrorHttpcode(
                        "" + response.getStatusLine().getStatusCode()));
            }

            // 多分関係ないけど一応
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            HttpEntity responseEntity = response.getEntity();
            ret = EntityUtils.toString(responseEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return ret;
    }


    /**
     * 接続を閉じます
     * @return
     */
    public boolean close() {
        boolean ret = false;
        try {
            if (response != null) {
                response.close();
                response = null;
            }
            if (client != null) {
                client.close();
                client = null;
            }
            ret = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
