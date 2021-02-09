package blanco.restautotest;

import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoStringUtil;
import blanco.restautotest.constants.BlancoRestAutotestConstants;
import blanco.restautotest.task.valueobject.BlancoRestAutotestProcessInput;
import blanco.restautotest.valueobject.BlancoRestAutotestInputResultClassStructure;
import blanco.restautotest.valueobject.BlancoRestAutotestTestCaseData;
import blanco.restgenerator.BlancoRestGeneratorConstants;
import blanco.restgenerator.BlancoRestGeneratorXmlParser;
import blanco.restgenerator.valueobject.ApiTelegram;
import blanco.restgenerator.valueobject.BlancoRestGeneratorTelegram;
import blanco.valueobject.BlancoValueObjectXmlParser;
import blanco.valueobject.valueobject.BlancoValueObjectClassStructure;
import blanco.xml.bind.BlancoXmlBindingUtil;
import blanco.xml.bind.BlancoXmlUnmarshaller;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * ユーティリティクラスです
 */
public class BlancoRestAutotestUtil {

    /** デバグ情報を大量に出力します */
    public static boolean isVerbose = false;
    /** 電文情報をJSONファイルとして出力します */
    public static boolean isOutputJson = false;

    /** 現在処理中の InputResult シートの Input 欄の数 */
    public static int inputColumnMax = BlancoRestAutotestConstants.INPUT_MAX;
    /** 現在処理中の InputResult シートの input プロパティ行の数 */
    public static int inputNestDepth = Integer.MAX_VALUE;
    /** 現在処理中の InputResult シートの Expected 欄の数 */
    public static int expectedColumnMax = BlancoRestAutotestConstants.OUTPUT_MAX;
    /** 現在処理中の InputResult シートの Expected プロパティ行の数 */
    public static int expectedNestDepth = Integer.MAX_VALUE;

    /**
     * 型が指定される場合があるので、ValueObject定義書から情報を読み取っておきます。
     */
    public static HashMap<String, BlancoValueObjectClassStructure> objects = new HashMap<>();

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

    static public void processValueObjects(final BlancoRestAutotestProcessInput input) throws IOException {
        if (isVerbose) {
            System.out.println("BlancoRestAutotestUtil : processValueObjects start !");
        }

        /* tmpdir はみない */
        /* searchTmpdir はカンマ区切り */
        String tmpTmpdirs = input.getSearchTmpdir();
        List<String> searchTmpdirList = null;
        if (tmpTmpdirs != null) {
            String[] searchTmpdirs = tmpTmpdirs.split(",");
            searchTmpdirList = new ArrayList<>(Arrays.asList(searchTmpdirs));
        }
        if (searchTmpdirList == null) {
            searchTmpdirList = new ArrayList<>();
        }

        for (String tmpdir : searchTmpdirList) {
            searchValueObjectTmpdir(tmpdir.trim());
            searchTelegramTmpdir(tmpdir.trim());
        }
    }

    static private void searchValueObjectTmpdir(String tmpdir) {

        // XML化された中間ファイルから情報を読み込む
        final File[] fileMeta3 = new File(tmpdir
                + BlancoRestGeneratorConstants.OBJECT_SUBDIRECTORY)
                .listFiles();

        if (fileMeta3 == null) {
            System.out.println("!!! NO FILES in " + tmpdir
                    + BlancoRestGeneratorConstants.OBJECT_SUBDIRECTORY);
            throw new IllegalArgumentException("!!! NO FILES in " + tmpdir
                    + BlancoRestGeneratorConstants.OBJECT_SUBDIRECTORY);
        }

        for (int index = 0; index < fileMeta3.length; index++) {
            if (fileMeta3[index].getName().endsWith(".xml") == false) {
                continue;
            }

            BlancoValueObjectXmlParser parser = new BlancoValueObjectXmlParser();
//            parser.setVerbose(this.isVerbose());
            /*
             * まず始めにすべてのシートを検索して，クラス名とpackage名のリストを作ります．
             * php形式の定義書では，クラスを指定する際にpackage名が指定されていないからです．
             *
             */
            final BlancoValueObjectClassStructure[] structures = parser.parse(fileMeta3[index]);

            if (structures != null ) {
                for (int index2 = 0; index2 < structures.length; index2++) {
                    BlancoValueObjectClassStructure structure = structures[index2];
                    if (structure != null) {
                        if (isVerbose) {
                            System.out.println("processValueObjects: " + structure.getName());
                        }
                        objects.put(structure.getName(), structure);
                    } else {
                        System.out.println("processValueObjects: a structure is NULL!!!");
                    }
                }
            } else {
                System.out.println("processValueObjects: structures are NULL!!!");
            }
        }
    }

    static private void searchTelegramTmpdir(String tmpdir) {

        // XML化された中間ファイルから情報を読み込む
        final File[] fileMeta3 = new File(tmpdir
                + BlancoRestGeneratorConstants.TARGET_SUBDIRECTORY)
                .listFiles();

        if (fileMeta3 == null) {
            System.out.println("!!! NO FILES in " + tmpdir
                    + BlancoRestGeneratorConstants.TARGET_SUBDIRECTORY);
            throw new IllegalArgumentException("!!! NO FILES in " + tmpdir
                    + BlancoRestGeneratorConstants.TARGET_SUBDIRECTORY);
        }

        for (int index = 0; index < fileMeta3.length; index++) {
            if (fileMeta3[index].getName().endsWith(".xml") == false) {
                continue;
            }

            BlancoRestGeneratorXmlParser parser = new BlancoRestGeneratorXmlParser();
//            parser.setVerbose(this.isVerbose());
            /*
             * まず始めにすべてのシートを検索して，クラス名とpackage名のリストを作ります．
             * php形式の定義書では，クラスを指定する際にpackage名が指定されていないからです．
             *
             */
            final BlancoXmlDocument documentMeta = new BlancoXmlUnmarshaller()
                    .unmarshal(fileMeta3[index]);
            if (documentMeta == null) {
                System.out.println("Fail to unmarshal XML.");
                continue;
            }

            // ルートエレメントを取得します。
            final BlancoXmlElement elementRoot = BlancoXmlBindingUtil
                    .getDocumentElement(documentMeta);
            if (elementRoot == null) {
                // ルートエレメントが無い場合には処理中断します。
                if (isVerbose) {
                    System.out.println("praser !!! NO ROOT ELEMENT !!!");
                }
                continue;
            }

            if (isVerbose) {
                System.out.println("[" + fileMeta3[index].getName() + "の処理を開始します]");
            }
            Map<String, BlancoRestGeneratorTelegram> telegramStructureMap = parser.parseTelegrams(elementRoot);

            /* telegramStructure から必要な情報を取得して、ValueObjectStructure に詰め直します。 */
            if (telegramStructureMap != null ) {
                Set<String> keySet = telegramStructureMap.keySet();
                for (String key : keySet) {
                    BlancoRestGeneratorTelegram telegram = telegramStructureMap.get(key);
                    if (telegram == null) {
                        System.out.println("searchTelegramTmpdir: telegramStructure for " + key + " not defined.");
                        continue;
                    }
                    BlancoValueObjectClassStructure voStructure = new BlancoValueObjectClassStructure();
                    if (objects.containsKey(key)) {
                        System.out.println("!!! WARN !!! Duplicate Key : " + key);
                    }
                    objects.put(key, voStructure);
                    voStructure.setName(telegram.getName());
                    voStructure.setDescription(telegram.getDescription());
                    voStructure.setPackage(telegram.getPackage());
                    /* package だけあればいいかも */
                }
            } else {
                System.out.println("searchTelegramTmpdir: structures are NULL!!!");
            }
        }
    }

    public static String getClassIdFromSimpleID(String simpleId) {
        String voId = simpleId;
        BlancoValueObjectClassStructure structure = objects.get(simpleId);
        if (structure != null) {
            voId = structure.getPackage() + "." + simpleId;
        }
        return voId;
    }

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

//    public static Object getTelegramValue(ApiTelegram telegram, String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        return  getValue(telegram, property);
//    }

//    public static void setTelegramValue(ApiTelegram telegram, String property, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        setValue(telegram, property, value);
//    }

    public static Object getValue(Object obj, String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (obj == null || BlancoStringUtil.null2Blank(property).length() == 0) {
            return null;
        }
        String propertyId = property;
        String [] typedProperty = property.split(":");
        if (typedProperty.length > 1) {
            propertyId = typedProperty[0].trim();
        }

        Class<?> clazz = obj.getClass();
        String getterId = "get" + BlancoNameAdjuster.toClassName(propertyId);
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
        if (obj == null || BlancoStringUtil.null2Blank(property).length() == 0) {
            System.out.println("setValue: Target is null or not specified.");
            return;
        }
        String propertyId = property;
        String [] typedProperty = property.split(":");
        if (typedProperty.length > 1) {
            propertyId = typedProperty[0].trim();
        }

//        System.out.println("obj = " + obj.getClass().getName() + ", propertyId = " + propertyId + ", value = " + value.getClass().getName());

        Class<?> clazz = obj.getClass();
        String setterId = "set" + BlancoNameAdjuster.toClassName(propertyId);
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
        String propertyId = property;
        String [] typedProperty = property.split(":");
        if (typedProperty.length > 1) {
            propertyId = typedProperty[0].trim();
        }

        Class<?> clazz = parentObj.getClass();
        String setterId = "set" + BlancoNameAdjuster.toClassName(propertyId);
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
        } else if ("java.lang.Boolean".equals(typeId)) {
            valueObj = Boolean.parseBoolean(value);
        } else if ("java.lang.String".equals(typeId)) {
            if ("\"\"".equals(value)) {
                value = "";
            }
            valueObj = value;
        } else {
            throw new IllegalArgumentException("Cannot convert String to " + typeId);
        }

//        System.out.println("setValue : parentObj = " + parentObj.getClass().getName() + ", propertyId = " + propertyId + ", valueObj = " + valueObj.getClass().getName() + ", value = " + value);
        method.invoke(parentObj, valueObj);
    }

    /**
     * List Object に、primitive ではない Object value を追加します。
     * @param list
     * @param value
     */
    public static void addToList(Object list, Object value) {
        if (list == null || !isArrayObject(list)) {
            throw new IllegalArgumentException("addToList: arguments is null or non array.");
        }
        ((List)list).add(value);
    }

    /**
     * String 値 value を typeId で指定された primitive object に変換して、list Object に追加する。
     * @param list
     * @param value
     * @param typeId
     */
    public static void addPrimitiveToList(Object list, String value, String typeId) {
        if (typeId == null) {
            throw new IllegalArgumentException("addPrimitiveToList: typeId null.");
        }
        Object valueObj = null;
        if (value == null) {
            valueObj = value;
        } else if ("java.lang.Integer".equals(typeId)) {
            valueObj = Integer.parseInt(value);
        } else if ("java.lang.Long".equals(typeId)) {
            valueObj = Long.parseLong(value);
        } else if ("java.lang.Float".equals(typeId)) {
            valueObj = Float.parseFloat(value);
        } else if ("java.lang.Double".equals(typeId)) {
            valueObj = Double.parseDouble(value);
        } else if ("java.lang.Boolean".equals(typeId)) {
            valueObj = Boolean.parseBoolean(value);
        } else if ("java.lang.String".equals(typeId)) {
            if ("\"\"".equals(value)) {
                value = "";
            }
            valueObj = value;
        } else {
            throw new IllegalArgumentException("addPrimitiveToList: It is not primitive: " + typeId);
        }
        addToList(list, valueObj);
    }


//    public static Object initObjectProperty(final Object object, final String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
//        if (object == null || BlancoStringUtil.null2Blank(property).length() == 0) {
//            System.out.println("initObjectProperty: arguments null.");
//            return null;
//        }
//        String blancoProp = "f" + BlancoNameAdjuster.toClassName(property);
//        Object propValue = BlancoRestAutotestUtil.getValue(object, property);
//        if (propValue == null) {
//            Field field = BlancoRestAutotestUtil.getFieldFromClass(object.getClass(), blancoProp);
//            System.out.println("XXXX " + field.getType().getName());
//            System.out.println("YYYY " + field.getGenericType().getTypeName());
//            BlancoRestAutotestUtil.setValue(
//                    object,
//                    property,
//                    createInstanceFromField(field)
//            );
//        }
//        return propValue;
//    }

//    public static Object initProperty(final Object object, final String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
//        Field field = BlancoRestAutotestUtil.getFieldFromBlancoValueObject(object, property);
//        Object propValue = BlancoRestAutotestUtil.getValue(object, property);
//        if (propValue == null) {
//            System.out.println("XXXX " + field.getType().getName());
//            System.out.println("YYYY " + field.getGenericType().getTypeName());
//            BlancoRestAutotestUtil.setValue(
//                    object,
//                    property,
//                    createInstanceFromField(field)
//            );
//        }
//        Object genericObj = createSimpleGenericFromField(field);
//        if (isArrayField(field) && genericObj != null) {
//
//            return genericObj;
//        }
//        return propValue;
//    }

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
        String propertyId = property;
        String [] typedProperty = property.split(":");
        if (typedProperty.length > 1) {
            propertyId = typedProperty[0].trim();
        }
        Field field = getFieldFromBlancoValueObject(parentObj, propertyId);
        return createInstanceFromField(field);
    }

    public static Object createGenericFromProperty(Object parentObj, String property) throws NoSuchFieldException {
        String propertyId = property;
        String [] typedProperty = property.split(":");
        if (typedProperty.length > 1) {
            propertyId = typedProperty[0].trim();
        }
        Field field = getFieldFromBlancoValueObject(parentObj, propertyId);
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
