package blanco.restautotest.task;

import blanco.restautotest.BlancoRestAutotestUtil;
import blanco.restautotest.BlancoRestAutotestXml2JavaClass;
import blanco.restautotest.BlancoRestAutotestXmlParser;
import blanco.restautotest.constants.BlancoRestAutotestConstants;
import blanco.restautotest.message.BlancoRestAutotestMessage;
import blanco.restautotest.meta2xml.BlancoRestAutotestMeta2Xml;
import blanco.restautotest.task.valueobject.BlancoRestAutotestProcessInput;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * テストケース生成処理
 */
public class BlancoRestAutotestProcessImpl implements BlancoRestAutotestProcess {

    /**
     * メッセージ。
     */
    private final BlancoRestAutotestMessage fMsg = new BlancoRestAutotestMessage();

    /**
     * {@inheritDoc}
     */
    public int execute(final BlancoRestAutotestProcessInput input)
            throws IOException, IllegalArgumentException {
        System.out.println("- " + BlancoRestAutotestConstants.PRODUCT_NAME
                + " (" + BlancoRestAutotestConstants.VERSION + ")");
        try {
            final File fileMetadir = new File(input.getMetadir());
            if (fileMetadir.exists() == false) {
                throw new IllegalArgumentException(fMsg.getMbvoja01(input
                        .getMetadir()));
            }


            /*
             * 改行コードを決定します。
             */
            String LF = "\n";
            String CR = "\r";
            String CRLF = CR + LF;
            String lineSeparatorMark = input.getLineSeparator();
            String lineSeparator = "";
            if ("LF".equals(lineSeparatorMark)) {
                lineSeparator = LF;
            } else if ("CR".equals(lineSeparatorMark)) {
                lineSeparator = CR;
            } else if ("CRLF".equals(lineSeparatorMark)) {
                lineSeparator = CRLF;
            }
            if (lineSeparator.length() != 0) {
                System.setProperty("line.separator", lineSeparator);
                if (input.getVerbose()) {
                    System.out.println("lineSeparator try to change to " + lineSeparatorMark);
                    String newProp = System.getProperty("line.separator");
                    String newMark = "other";
                    if (LF.equals(newProp)) {
                        newMark = "LF";
                    } else if (CR.equals(newProp)) {
                        newMark = "CR";
                    } else if (CRLF.equals(newProp)) {
                        newMark = "CRLF";
                    }
                    System.out.println("New System Props = " + newMark);
                }
            }

            /*
             * targetdir, targetStyleの処理。
             * 生成されたコードの保管場所を設定する。
             * targetstyle = blanco:
             *  targetdirの下に main ディレクトリを作成
             * targetstyle = maven:
             *  targetdirの下に main/java ディレクトリを作成
             * targetstyle = free:
             *  targetdirをそのまま使用してディレクトリを作成。
             *  ただしtargetdirがからの場合はデフォルト文字列(blanco)使用する。
             * by tueda, 2019/08/30
             */
            String strTarget = input.getTargetdir();
            String style = input.getTargetStyle();
            // ここを通ったら常にtrue
            boolean isTargetStyleAdvanced = true;
            if (style != null && BlancoRestAutotestConstants.TARGET_STYLE_MAVEN.equals(style)) {
                strTarget = strTarget + "/" + BlancoRestAutotestConstants.TARGET_DIR_SUFFIX_MAVEN;
            } else if (style == null ||
                    !BlancoRestAutotestConstants.TARGET_STYLE_FREE.equals(style)) {
                strTarget = strTarget + "/" + BlancoRestAutotestConstants.TARGET_DIR_SUFFIX_BLANCO;
            }
            /* style が free だったらtargetdirをそのまま使う */
            if (input.getVerbose()) {
                System.out.println("/* tueda */ TARGETDIR = " + strTarget);
            }

            // テンポラリディレクトリを作成。
            new File(input.getTmpdir()
                    + BlancoRestAutotestConstants.TARGET_SUBDIRECTORY).mkdirs();

            new BlancoRestAutotestMeta2Xml().processDirectory(fileMetadir, input
                    .getTmpdir()
                    + BlancoRestAutotestConstants.TARGET_SUBDIRECTORY);

            // XML化されたメタファイルからValueObjectを生成
            // 最初にテンポラリフォルダを走査
            final File[] fileMeta2 = new File(input.getTmpdir()
                    + BlancoRestAutotestConstants.TARGET_SUBDIRECTORY)
                    .listFiles();

            BlancoRestAutotestUtil.isVerbose = input.getVerbose();

            for (int index = 0; index < fileMeta2.length; index++) {
                if (fileMeta2[index].getName().endsWith(".xml") == false) {
                    continue;
                }

                // 一旦、すべての XML を parse する
                //中間XMLファイルのXMLドキュメントをパースしClassStructureを取得
                new BlancoRestAutotestXmlParser()
                        .parseTestCase(fileMeta2[index]);
            }

            // ソースコードを生成する
            final BlancoRestAutotestXml2JavaClass xml2JavaClass = new BlancoRestAutotestXml2JavaClass();
            xml2JavaClass.setEncoding(input.getEncoding());
            xml2JavaClass.setVerbose(input.getVerbose());
            xml2JavaClass.setTargetStyleAdvanced(isTargetStyleAdvanced);
            xml2JavaClass.process(new File(strTarget));

        } catch (TransformerException e) {
            throw new IOException("XML変換の過程で例外が発生しました: " + e.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return BlancoRestAutotestBatchProcess.END_SUCCESS;
    }

    /**
     * {@inheritDoc}
     */
    public boolean progress(final String argProgressMessage) {
        System.out.println(argProgressMessage);
        return false;
    }
}
