package blanco.restgenerator.valueobject;

/**
 * APIの電文を表すクラスです．全てのRequest, Responseオブジェクトが継承する必要があります．
 */
public class ApiPutTelegram extends ApiTelegram {
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
    public void copyTo(final ApiPutTelegram target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: ApiPutTelegram#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

    }
}
