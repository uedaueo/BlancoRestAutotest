package blanco.restgenerator.valueobject;

/**
 * APIの電文を表すクラスです．全てのRequest, Responseオブジェクトが継承する必要があります．
 */
public class ApiDeleteTelegram extends ApiTelegram {
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
    public void copyTo(final ApiDeleteTelegram target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: ApiDeleteTelegram#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

    }
}
