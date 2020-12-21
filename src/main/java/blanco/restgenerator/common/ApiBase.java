package blanco.restgenerator.common;

import blanco.restgenerator.exception.BlancoRestException;
import blanco.restgenerator.valueobject.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import blanco.restautotest.resourcebundle.BlancoRestAutotestResourceBundle;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by tueda on 15/10/05.
 */
abstract public class ApiBase {

    private BlancoRestAutotestResourceBundle fBundle = new BlancoRestAutotestResourceBundle();

    private CommonRequest request = null;
    private CommonResponse response = null;
    private static SessionManager sessionManager = new SessionManagerImpl();

    public void setRequest(CommonRequest request) {
        this.request = request;
    }

    public void setResponse(CommonResponse response) {
        this.response = response;
    }

    public CommonRequest getRequest() {
        return request;
    }

    public CommonResponse getResponse() {
        return response;
    }


    /**
     * 自動生成されたabstractクラスでoverrideされる事を想定しています
     */
    public Boolean isAuthenticationRequired() {
        return true;
    }

    public static SessionManager getSessionManager() {
        return sessionManager;
    }

    public static void setSessionManager(SessionManager session) {
        sessionManager = session;
    }

    final private ApiTelegram sendTo(ApiTelegram request, String httpMethod) throws BlancoRestException {
        ApiTelegram response = null;

        CommonRequest commonRequest = this.getRequest();
        if (commonRequest == null) {
            commonRequest = new CommonRequest();
            ApiRequestHeader info = new ApiRequestHeader();
            commonRequest.setInfo(info);
            info.setToken("dummy");
            info.setLang("ja");
        }

        commonRequest.setTelegram(request);

        CommonResponse commonResponse = null;

        ApiTelegram output = newResponseInstance(httpMethod);

        ObjectMapper mapper = new ObjectMapper();
        try {

            String requestJson = mapper.writeValueAsString(commonRequest);
            System.out.println("JSON: " + requestJson);

            String responseJson = null;
            String url = Config.properties.getProperty(Config.apiUrlKey) + this.getClass().getSimpleName();
            if("GET".equalsIgnoreCase(httpMethod) || "DELETE".equalsIgnoreCase(httpMethod)) {
                // GETとDELETEはURLにデータを乗せる
                url += "&data="
                    + requestJson;
                Util.infoPrintln(LogLevel.LOG_DEBUG, "URL: " + url);
                BlancoHttpConnection conn = new BlancoHttpConnection(url);
                responseJson = conn.connect(httpMethod);

            } else if("PUT".equalsIgnoreCase(httpMethod) || "POST".equalsIgnoreCase(httpMethod)) {
                // POSTとPUTはJsonにデータを乗せる
                BlancoHttpConnection conn = new BlancoHttpConnection(url);
                System.out.println("URL: " + url);
                responseJson = conn.connect(requestJson, httpMethod);
            } else {
                Util.infoPrintln(LogLevel.LOG_CRIT,"No method specified");
                throw  new BlancoRestException("No method specified");
            }

            System.out.println("Response: " + responseJson);

            ResponseDeserializer deserializer = new ResponseDeserializer();
            deserializer.setResponseClass(output);

            SimpleModule module =
                    new SimpleModule("PolymorphicAnimalDeserializerModule");
            module.addDeserializer(CommonResponse.class, deserializer);

            /*
             * 念のため作り直し
             */
            mapper = new ObjectMapper();
            mapper.registerModule(module);

            System.out.println("ApiBase#responseJson " + responseJson);
            commonResponse = (CommonResponse)mapper.readValue(responseJson, CommonResponse.class);
            System.out.println("ApiBase#commonResponse" + commonResponse);

            if(commonResponse != null){

                this.setResponse(commonResponse);

//                if (BlancoRestConstants.API_STATUS_ACCEPTED.equalsIgnoreCase(commonResponse.getstatus())){
//                    response = commonResponse.gettelegram();
//                }else {
//                    Util.infoPrintln(LogLevel.LOG_DEBUG,"ApiBase#sendTo response status: " + commonResponse.getstatus());
//                }
            }else{
                Util.infoPrintln(LogLevel.LOG_DEBUG,"ApiBase#sendTo readValue null");
            }



        } catch (JsonProcessingException e) {
            throw new BlancoRestException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
        HttpClient httpclient = new HttpClient();
        GetMethod httpget = new GetMethod("https://www.verisign.com/");
        try {
            httpclient.executeMethod(httpget);
            System.out.println(httpget.getStatusLine());
        } finally {
            httpget.releaseConnection();
        }

        response = getDummyResponse();
        **/
        return response;
    }

    final public ApiPostTelegram send(ApiPostTelegram request) throws BlancoRestException {
        if (request == null) {
            throw new BlancoRestException(
                    fBundle.getRuntimeErrorNotnull("request: ApiPostTelegram")
            );
        }

//        if (!this.getPostRequestId().equalsIgnoreCase(request.getClass().getCanonicalName())) {
//            throw new BlancoRestException(
//                    fBundle.getBlancorestErrorMsg02(
//                            this.getPostRequestId(), request.getClass().getCanonicalName())
//            );
//        }

        return (ApiPostTelegram) sendTo(request, "POST");
    }

    final public ApiGetTelegram send(ApiGetTelegram request) throws BlancoRestException {
        if (request == null) {
            throw new BlancoRestException(
                    fBundle.getRuntimeErrorNotnull("request: ApiGetTelegram")
            );
        }

//        if (!this.getGetRequestId().equalsIgnoreCase(request.getClass().getCanonicalName())) {
//            throw new BlancoRestException(
//                    fBundle.getBlancorestErrorMsg02(
//                            this.getGetRequestId(), request.getClass().getCanonicalName())
//            );
//        }
        return (ApiGetTelegram) sendTo(request, "GET");
    }

    final public ApiPutTelegram send(ApiPutTelegram request) throws BlancoRestException {
        if (request == null) {
            throw new BlancoRestException(
                    fBundle.getRuntimeErrorNotnull("request: ApiPutTelegram")
            );
        }

//        if (!this.getPutRequestId().equalsIgnoreCase(request.getClass().getCanonicalName())) {
//            throw new BlancoRestException(
//                    fBundle.getBlancorestErrorMsg02(
//                            this.getPutRequestId(), request.getClass().getCanonicalName())
//            );
//        }
        return (ApiPutTelegram) sendTo(request, "PUT");
    }

    final public ApiDeleteTelegram send(ApiDeleteTelegram request) throws BlancoRestException {
        if (request == null) {
            throw new BlancoRestException(
                    fBundle.getRuntimeErrorNotnull("request: ApiDeleteTelegram")
            );
        }

//        if (!this.getDeleteRequestId().equalsIgnoreCase(request.getClass().getCanonicalName())) {
//            throw new BlancoRestException(
//                    fBundle.getBlancorestErrorMsg02(
//                            this.getDeleteRequestId(), request.getClass().getCanonicalName())
//            );
//        }
        return (ApiDeleteTelegram) sendTo(request, "DELETE");
    }

    /**
     * Response インスタンス名は API 名から名前規則に従って生成
     *
     * @param httpMethod
     * @return
     * @throws BlancoRestException
     */
    final public ApiTelegram newResponseInstance(String httpMethod) throws BlancoRestException {
        ApiTelegram response = null;

        String responseId = this.getClass().getSimpleName();;
        if ("GET".equalsIgnoreCase(httpMethod)){
            responseId += "GetResponse";
        }else if("POST".equalsIgnoreCase(httpMethod)){
            responseId += "PostResponse";
        } else if("PUT".equalsIgnoreCase(httpMethod)){
            responseId += "PutResponse";
        }else if("DELETE".equalsIgnoreCase(httpMethod)){
            responseId += "DeleteResponse";
        }else {
            Util.infoPrintln(LogLevel.LOG_CRIT,"No method specified");
            throw  new BlancoRestException("No method specified");
        }

        Class<?> clazz;
        try {
            clazz = Class.forName(responseId);

            Constructor<?> constructor =
                    clazz.getConstructor(new Class<?>[0]);

            // インスタンス生成
            response = (ApiTelegram) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * Request インスタンス名は API 名から名前規則に従って生成
     *
     * @param httpMethod
     * @return
     * @throws BlancoRestException
     */
    final public ApiTelegram newRequestInstance(String httpMethod) throws BlancoRestException {
        ApiTelegram request = null;


        String requestId = this.getClass().getSimpleName();
        if ("GET".equalsIgnoreCase(httpMethod)){
            requestId += "GetResponse";
        }else if("POST".equalsIgnoreCase(httpMethod)){
            requestId += "PostResponse";
        } else if("PUT".equalsIgnoreCase(httpMethod)){
            requestId += "PutResponse";
        }else if("DELETE".equalsIgnoreCase(httpMethod)){
            requestId += "DeleteResponse";
        }else {
            Util.infoPrintln(LogLevel.LOG_CRIT,"No method specified");
            throw  new BlancoRestException("No method specified");
        }

        Class<?> clazz;
        try {
            clazz = Class.forName(requestId);

            Constructor<?> constructor =
                    clazz.getConstructor(new Class<?>[0]);

            // インスタンス生成
            request = (ApiTelegram) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return request;
    }
}
