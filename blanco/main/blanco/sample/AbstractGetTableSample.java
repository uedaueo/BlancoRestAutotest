/*
 * blancoRestのサンプルAPIです。
 */
package blanco.sample;

import blanco.restgenerator.common.ApiBase;
import blanco.restgenerator.valueobject.RequestHeader;

/**
 * blancoRestのサンプルAPIです。
 */
public abstract class AbstractGetTableSample extends ApiBase {
    /**
     * APIが認証を必要とするかどうかのフラグです．必要な場合はtrueです．
     *
     * @return APIが認証を必要とするかどうかのフラグです．必要な場合はtrueです．
     */
    public Boolean isAuthenticationRequired() {
        return true;
    }
}
