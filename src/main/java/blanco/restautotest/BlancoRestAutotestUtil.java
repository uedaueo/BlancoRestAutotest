package blanco.restautotest;

import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoStringUtil;
import blanco.restautotest.valueobject.BlancoRestAutotestTestCaseData;
import blanco.restgenerator.valueobject.ApiTelegram;
import blanco.restautotest.constants.BlancoRestAutotestConstants;
import blanco.restautotest.valueobject.BlancoRestAutotestInputResultClassStructure;
import blanco.restautotest.valueobject.BlancoRestAutotestTestCaseClassStructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ユーティリティクラスです
 */
public class BlancoRestAutotestUtil {

    /** デバグ情報を大量に出力します */
    public static boolean isVerbose = false;

    /** 現在処理中の InputResult シートの Input 欄の数 */
    public static int inputColumnMax = BlancoRestAutotestConstants.INPUT_MAX;
    /** 現在処理中の InputResult シートの input プロパティ行の数 */
    public static int inputNestDepth = Integer.MAX_VALUE;
    /** 現在処理中の InputResult シートの Expected 欄の数 */
    public static int expectedColumnMax = BlancoRestAutotestConstants.OUTPUT_MAX;
    /** 現在処理中の InputResult シートの Expected プロパティ行の数 */
    public static int expectedNestDepth = Integer.MAX_VALUE;
    /**
     * 今回のテストケース生成の対象となった、全てのテストケースクラスを保持します。
     */
    public static List<BlancoRestAutotestTestCaseData> testCaseDataList = new ArrayList<>();

    /**
     * 今回のテストケース生成の対象となった、全ての入力値期待値クラスを保持します。
     * key: API名
     * value: BlancoRestAutotestInputResultClassStructure
     *
     * ■ 注意事項
     * key が重複する場合、BlancoRestAutotestInputResultClassStructure のマージが必要。
     * BlancoRestAutotestInputResultClassStructure で caseId が重複する場合はエラーにする。
     */
    public static Map<String, BlancoRestAutotestInputResultClassStructure> inputResults = new HashMap<>();

    public static Object createObjectById(String objectId) {
        Class<?> clazz;
        try {
            clazz = Class.forName(objectId);
            Constructor<?> constructor =
                    clazz.getConstructor(new Class<?>[0]);

            // インスタンス生成
            return constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public static ApiTelegram createTelegramById(String telegramId) {
        return (ApiTelegram) createObjectById(telegramId);
    }

    public static Object getTelegramValue(ApiTelegram telegram, String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return  getValue(telegram, property);
    }

    public static void setTelegramValue(ApiTelegram telegram, String property, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        setValue(telegram, property, value);
    }

    public static Object getValue(Object obj, String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (obj == null || BlancoStringUtil.null2Blank(property).length() == 0) {
            return null;
        }
        Class<?> clazz = obj.getClass();
        String getterId = "get" + BlancoNameAdjuster.toClassName(property);
        Method method = clazz.getMethod(getterId);
        return  method.invoke(obj);
    }

    /**
     *
     * TODO 型チェックは完了して呼ばれる前提
     * @param obj
     * @param property
     * @param value
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void setValue(Object obj, String property, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (obj == null || value == null || BlancoStringUtil.null2Blank(property).length() == 0) {
            System.out.println("setValue: arguments null.");
            return;
        }
        Class<?> clazz = obj.getClass();
        String setterId = "set" + BlancoNameAdjuster.toClassName(property);
        Method method = BlancoRestAutotestUtil.getMethodByName(clazz, setterId);
        if (method == null) {
            throw new NoSuchMethodException(obj.getClass().getName() + "." + setterId);
        }
        method.invoke(obj, value);
    }

    public static String getStringValue(Object obj, String property) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        return (String)getValue(obj, property);
    }

    public static void setStringValue(Object parentObj, String property, String value) throws InvocationTargetException, IllegalAccessException {
        if (parentObj == null || value == null || BlancoStringUtil.null2Blank(property).length() == 0) {
            throw new IllegalArgumentException("setStringValue: arguments null.");
        }
        Class<?> clazz = parentObj.getClass();
        String setterId = "set" + BlancoNameAdjuster.toClassName(property);
        Method method = getMethodByName(clazz, setterId);
        String typeId = method.getParameterTypes()[0].getName();
        Object valueObj = value;
        if ("java.lang.Integer".equals(typeId)) {
            valueObj = Integer.parseInt(value);
        } else if ("java.lang.Long".equals(typeId)) {
            valueObj = Long.parseLong(value);
        } else if ("java.lang.Float".equals(typeId)) {
            valueObj = Float.parseFloat(value);
        } else if ("java.lang.Double".equals(typeId)) {
            valueObj = Double.parseDouble(value);
        } else if ("java.lang.String".equals(typeId)) {
            valueObj = value;
        } else {
            throw new IllegalArgumentException("Cannot convert String to " + typeId);
        }

        method.invoke(parentObj, valueObj);
    }

    public static void addToList(Object list, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (list == null || value == null || !isArrayObject(list)) {
            throw new IllegalArgumentException("setValue: arguments null.");
        }
        ((List)list).add(value);
    }

    public static Object initObjectProperty(final Object object, final String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        if (object == null || BlancoStringUtil.null2Blank(property).length() == 0) {
            System.out.println("initObjectProperty: arguments null.");
            return null;
        }
        String blancoProp = "f" + BlancoNameAdjuster.toClassName(property);
        Object propValue = BlancoRestAutotestUtil.getValue(object, property);
        if (propValue == null) {
            Field field = BlancoRestAutotestUtil.getFieldFromClass(object.getClass(), blancoProp);
            System.out.println("XXXX " + field.getType().getName());
            System.out.println("YYYY " + field.getGenericType().getTypeName());
            BlancoRestAutotestUtil.setValue(
                    object,
                    property,
                    createInstanceFromField(field)
            );
        }
        return propValue;
    }

    public static Object initProperty(final Object object, final String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Field field = BlancoRestAutotestUtil.getFieldFromBlancoValueObject(object, property);
        Object propValue = BlancoRestAutotestUtil.getValue(object, property);
        if (propValue == null) {
            System.out.println("XXXX " + field.getType().getName());
            System.out.println("YYYY " + field.getGenericType().getTypeName());
            BlancoRestAutotestUtil.setValue(
                    object,
                    property,
                    createInstanceFromField(field)
            );
        }
        Object genericObj = createSimpleGenericFromField(field);
        if (isArrayField(field) && genericObj != null) {

            return genericObj;
        }
        return propValue;
    }

    /**
     * Field 情報から Generic で指定されているクラスのインスタンスを返す。
     * が、元のクラスが List か ArrayList の場合しか考慮しない。
     * （クラスはblancoRestGenerator や blancoValueObject で定義されている
     * 前提なので、複雑な総称型はないという前提。もしあれば、blancoCg を参考に、
     * ちゃんと解析する）
     *
     * @param argField
     * @return
     */
    public static Object createSimpleGenericFromField(final Field argField) {
        if (argField == null) {
            return null;
        }
        String genericType = argField.getGenericType().getTypeName();
        if (genericType == null) {
            return null;
        }
        int genericStart = genericType.indexOf("<");
        if (genericStart == -1) {
            return null;
        }
        /*
         * Generic は List<Hoge> 的な単純なもの前提
         */
        String genericId = genericType.substring(genericStart + 1, genericType.length() - 1);
        Object instance = null;
        if ("java.lang.Integer".equals(genericId)) {
            instance = Integer.MAX_VALUE;
        } else if ("java.lang.Long".equals(genericId)) {
            instance = Long.MAX_VALUE;
        } else if ("java.lang.Float".equals(genericId)) {
            instance = Float.MAX_VALUE;
        } else if ("java.lang.Double".equals(genericId)) {
            instance = Double.MAX_VALUE;
        } else if ("java.lang.Boolean".equals(genericId)) {
            instance = Boolean.FALSE;
        } else {
            instance = createObjectById(genericId);
        }
        return instance;
    }

    /**
     * Field が List または ArrayList であるかどうかを確認する。
     * それ以外にも Array を表現するクラスはあるが、とりあえずここではふたつに絞る。
     *
     * @param field
     * @return
     */
    public static boolean isArrayField(final Field field) {
        boolean isArray = false;
        if (field != null) {
            isArray = isArrayType(field.getType().getName());
        }
        return isArray;
    }

    public static boolean isArrayObject(final Object object) {
        boolean isArray = false;
        if (object != null) {
            isArray = isArrayType(object.getClass().getName());
        }
        return isArray;
    }

    public static boolean isArrayType(final String type) {
        boolean isArray = false;
        if (type != null) {
            if ("java.util.List".equals(type) ||
                    "java.util.ArrayList".equals(type)
            ) {
                isArray = true;
            }
        }
        return isArray;
    }

    public static boolean isPrimitiveObject(final Object object) {
        boolean isPrimitive = false;
        if (object != null) {
            isPrimitive = isPrimitiveType(object.getClass().getName());
        }
        return isPrimitive;
    }

    public static boolean isPrimitiveType(final String type) {
        boolean isPrimitive = false;
        if (type != null) {
            if ("java.lang.Integer".equals(type) ||
                    "java.lang.Long".equals(type) ||
                    "java.lang.Float".equals(type) ||
                    "java.lang.Double".equals(type) ||
                    "java.lang.Boolean".equals(type) ||
                    "java.lang.String".equals(type)
            ) {
                isPrimitive = true;
            }
        }
        return isPrimitive;
    }

    public static Field getFieldFromBlancoValueObject(Object object, String property) throws NoSuchFieldException {
        if (object == null || BlancoStringUtil.null2Blank(property).length() == 0) {
            System.out.println("getFieldFromBlancoValueObject: arguments null.");
            throw new IllegalArgumentException("Null arguments are not allowed.");
        }
        String blancoProp = "f" + BlancoNameAdjuster.toClassName(property);
        return BlancoRestAutotestUtil.getFieldFromClass(object.getClass(), blancoProp);
    }

    public static Field getFieldFromClass(Class clazz, String fieldName)
            throws NoSuchFieldException {
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }

        if (field == null) {
            throw new NoSuchFieldException();
        }
        return field;
    }

    /**
     * Java Bean から Getter/Setter メソッドを取得します。
     * Getter/Setter 名の重複はありえないので、名前だけで検索します。
     *
     * @param clazz
     * @param methodName
     * @return
     */
    public static Method getMethodByName(Class clazz, String methodName) {
        Method method = null;
        while (clazz != null) {
            Method [] methods = clazz.getDeclaredMethods();
            for (Method tmpMethod : methods) {
                String methodParamName = tmpMethod.getName();
//                System.out.println("getMethodFromClass : " + methodParamName);
                if (methodParamName.equals(methodName)) {
                    method = tmpMethod;
                    break;
                }
            }
            if (method == null) {
                clazz = clazz.getSuperclass();
            } else {
                break;
            }
        }
        return method;
    }

    /**
     * java.reflection.Field から適合する型のインスタンスを作成します。
     * Integer, Long, Float, Double, Boolean には primitive な初期値が必要。
     *
     * @param field
     * @return
     * @throws NoSuchMethodException
     */
    public static Object createInstanceFromField(final Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object instance = null;
        String typeId = field.getType().getName();
        if ("java.lang.Integer".equals(typeId)) {
            instance = Integer.MAX_VALUE;
        } else if ("java.lang.Long".equals(typeId)) {
            instance = Long.MAX_VALUE;
        } else if ("java.lang.Float".equals(typeId)) {
            instance = Float.MAX_VALUE;
        } else if ("java.lang.Double".equals(typeId)) {
            instance = Double.MAX_VALUE;
        } else if ("java.lang.Boolean".equals(typeId)) {
            instance = Boolean.FALSE;
        } else {
            instance = field.getType().getConstructor(new Class<?>[0]).newInstance();

        }
        return instance;
    }

    public static Object createObjectFromProperty(Object parentObj, String property) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Field field = getFieldFromBlancoValueObject(parentObj, property);
        return createInstanceFromField(field);
    }

    public static Object createGenericFromProperty(Object parentObj, String property) throws NoSuchFieldException {
        Field field = getFieldFromBlancoValueObject(parentObj, property);
        return createSimpleGenericFromField(field);
    }


    /**
     * Make canonical classname into Simple.
     *
     * @param argClassNameCanon
     * @return simpleName
     */
    static public String getSimpleClassName(final String argClassNameCanon) {
        if (argClassNameCanon == null) {
            return "";
        }

        String simpleName = "";
        final int findLastDot = argClassNameCanon.lastIndexOf('.');
        if (findLastDot == -1) {
            simpleName = argClassNameCanon;
        } else if (findLastDot != argClassNameCanon.length() - 1) {
            simpleName = argClassNameCanon.substring(findLastDot + 1);
        }
        return simpleName;
    }

    /**
     * Make canonical classname into packageName
     *
     * @param argClassNameCanon
     * @return
     */
    static public String getPackageName(final String argClassNameCanon) {
        if (argClassNameCanon == null) {
            return "";
        }

        String simpleName = "";
        final int findLastDot = argClassNameCanon.lastIndexOf('.');
        if (findLastDot > 0) {
            simpleName = argClassNameCanon.substring(0, findLastDot);
        }
        return simpleName;
    }
}
